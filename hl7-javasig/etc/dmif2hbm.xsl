<?xml version="1.0" encoding="UTF-8"?>
<!-- dmif2java.xsl - generates Hibernate mapping from MIF domain content.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is Brian DeCamp.
Portions created by Initial Developer are Copyright (C) 2004-2007
Health Level Seven, Inc. All Rights Reserved.

Contributor(s): 

-->
<!-- 
This translation works from the staticModel mif element and maps classes therein to a 
'subclass' database schema for the core RIM.
-->
<xsl:transform version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mif="urn:hl7-org:v3/mif" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" exclude-result-prefixes="xsl mif f saxon xs">
  <xsl:import href="mif2hbm-base.xsl"/>
  <xsl:import href="rim-func.xsl"/>
  <xsl:param name="namespace" select="''"/>
  <xsl:param name="rim-mif" select="'rim.coremif'"/>
  <xsl:variable name="rim" select="document($rim-mif)" as="node()"/>

  <xsl:function name="f:has-common-base-class" as="xs:boolean">
    <xsl:param name="distal-end" as="element()"/>
    <!-- 
      - If the distal end is only a single cmet, we can use that cmet's type. 
      - Otherwise, if the distal end is a choice between multiple cmets, or a cmet and a class in this MIF, 
      - then we have to default to a root class, because this is the only common hibernate mapping
      - class that satisfies two different cmets, or both a cmet and an internal class.
      - If the distal end is a choice of multiple classes within this MIF, that's OK, because
      - a XXXChoice class class will be created that is a base class for the choice.
    -->
    <xsl:variable name="first-cmet" select="$distal-end/mif:participantClassSpecialization[f:is-cmet-name(@className)][1]"  as="element()*"/>
    <xsl:variable name="first-non-cmet" select="$distal-end/mif:participantClassSpecialization[@className ne $first-cmet/@className][1]" as="element()*"/>
    <xsl:value-of select="not($first-cmet and $first-non-cmet)"/>
  </xsl:function>
  
  <xsl:function name="f:distal-impl-qname" as="xs:string">
    <xsl:param name="connection"/>
    <xsl:variable name="this-default-association-type" select="concat('org.hl7.rim.impl.Basic', $connection/mif:nonTraversableConnection/mif:derivationSupplier/@className, 'Impl')"/>
  	<xsl:variable name="distal-end" select="$connection/mif:traversableConnection"/>
  	<xsl:sequence select="if(f:has-common-base-class($distal-end)) then f:participant-to-qclass-name($distal-end/@participantClassName) else $this-default-association-type"/>
  </xsl:function>

  <!-- recursive function to find the root of the rim hierarchy, and the name of that root -->
  <xsl:function name="f:table-name" as="xs:string">
    <xsl:param name="rim-class" as="element()"/>
    <xsl:variable name="rim-root" select="f:root-rim-class($rim-class)" as="element()*"/>
    <xsl:value-of select="if(lower-case($rim-root/@name) eq 'role') then 'role_' else lower-case($rim-root/@name)"/>
  </xsl:function>

  <xsl:function name="f:specializationParent">
    <xsl:param name="class-node"/>
    <xsl:variable name="class-name" select="$class-node/@name"/>
    <xsl:sequence select="root($class-node)//mif:specializationChild[@childClassName eq $class-name]"/>
  </xsl:function>

  <xsl:function name="f:is-subclass">
    <xsl:param name="class-node"/>
    <xsl:sequence select="exists(f:specializationParent($class-node))"/>
  </xsl:function>

  <xsl:function name="f:foreign-key-name" as="xs:string">
    <xsl:param name="connection" as="element()"/>
    <xsl:value-of select="concat($connection/mif:nonTraversableConnection/mif:derivationSupplier/@associationEndName, 'InternalId')"/>
  </xsl:function>
  
  <!-- deep null transform -->
  <xsl:template match="/">
    <xsl:text disable-output-escaping="yes">&#10;&lt;!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd"&gt;&#10;</xsl:text>
    <hibernate-mapping auto-import="false">
      <xsl:comment><xsl:value-of select="$copyright-statement"/></xsl:comment>
      <!-- xsl:apply-templates mode="typedef" select="$hbm-template//typedef"/-->
      <xsl:apply-templates mode="hbm" select="/mif:staticModel/mif:ownedClass/mif:class[not(f:is-subclass(.))]"/>
      <xsl:apply-templates mode="association-loader" select="//mif:connections"/>
    </hibernate-mapping>
  </xsl:template>

  <!-- Apply templates for the corresponding rim attribute -->
  <xsl:template mode="dmif" match="mif:attribute">
    <xsl:variable name="rim-class-name" select="mif:derivationSupplier[1]/@className" as="attribute()"/>
    <xsl:variable name="rim-class" select="$rim//mif:ownedClass/mif:class[@name eq $rim-class-name]" as="element()"/>
    <xsl:variable name="attr-name" select="@name" as="attribute()"/>
    <!-- xsl:apply-templates mode="hbm" select="$rim-class/mif:attribute[@name eq $attr-name]"/-->
    <xsl:variable name="rim-type-name" select="f:data-type-name(f:rim-attribute($rim-class, $attr-name)/mif:type)"/>
    <xsl:variable name="type-name" select="if($rim-type-name eq 'SET&lt;TS>') then $rim-type-name else f:data-type-name(mif:type)"/>
    <xsl:apply-templates mode="hbm" select="f:rim-attribute($rim-class, $attr-name)[@isMandatory='false']">
      <xsl:with-param name="type-name" select="$type-name"/> <!-- Preserve the constrained type-name -->
    </xsl:apply-templates>
  </xsl:template>

  <xsl:template mode="association-loader" match="mif:connections[f:rim-association-cardinality(current()) ne '1']">
    <xsl:variable name="specialization-class-qnames" select="for $spec in mif:traversableConnection/mif:participantClassSpecialization return if(f:is-cmet-name($spec/@className)) then f:classes-for-cmet($spec/@className) else concat($namespace, '.', $spec/@className, $bean-suffix)" as="xs:string*"/>
    <xsl:variable name="sql-separator">', '</xsl:variable>
    <xsl:variable name="distal-class-qnames" select="if($specialization-class-qnames) then string-join($specialization-class-qnames, $sql-separator) else concat($namespace, '.', mif:traversableConnection/@participantClassName, $bean-suffix)" as="xs:string"/>
    <xsl:variable name="class-qname" select="f:participant-to-qclass-name(mif:nonTraversableConnection/@participantClassName)"/>
    <xsl:variable name="rim-class-name" select="mif:nonTraversableConnection/mif:derivationSupplier/@className"/>
    <xsl:variable name="rim-class" select="$rim//mif:ownedClass/mif:class[@name eq $rim-class-name]" as="element()"/>
    
    <sql-query name="{$class-qname}.{mif:traversableConnection/@name}">
      <load-collection alias="i" role="{$class-qname}.{mif:traversableConnection/@name}"/>
      <xsl:value-of select="concat('&#10;select * from ', f:table-name($rim-class), ' where ', f:foreign-key-name(.), ' = :id and internalDiscriminator in (')"/>
      <xsl:text>'</xsl:text>
      <xsl:value-of select="$distal-class-qnames"/>
      <xsl:text>')&#10;</xsl:text>
    </sql-query>
  </xsl:template>
  
  <xsl:template mode="associations" match="mif:connections">
    <xsl:variable name="distal-class-qname" select="f:distal-impl-qname(.)"/>
    <xsl:variable name="cardinality" select="f:rim-association-cardinality(.)" as="xs:string"/>
    <xsl:variable name="class-qname" select="f:participant-to-qclass-name(mif:nonTraversableConnection/@participantClassName)"/>
    <xsl:choose>
      <xsl:when test="$cardinality eq '1'">
        <xsl:variable name="endName" select="mif:traversableConnection/mif:derivationSupplier[1]/@associationEndName"/>
        <many-to-one name="{mif:traversableConnection/@name}" column="{$endName}InternalId" class="{$distal-class-qname}" cascade="save-update" access="property">
          <xsl:if test="not(f:has-common-base-class(mif:traversableConnection))">
            <xsl:attribute name="lazy">false</xsl:attribute>
          </xsl:if>
        </many-to-one>
      </xsl:when>
      <xsl:otherwise>
          <bag name="{mif:traversableConnection/@name}" cascade="all" access="org.hl7.hibernate.StrategicPropertyAccessor">
             <key column="{f:foreign-key-name(.)}"/>
             <one-to-many class="{$distal-class-qname}">
                <xsl:if test="not(f:has-common-base-class(mif:traversableConnection))">
                  <xsl:attribute name="lazy">false</xsl:attribute>
                </xsl:if>
             </one-to-many>
             <loader query-ref="{$class-qname}.{mif:traversableConnection/@name}"/>
          </bag>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- May be a choice class or a stand-alone class -->  
  <xsl:template mode="hbm" match="mif:staticModel/mif:ownedClass/mif:class[mif:derivationSupplier[1]]">
    <xsl:variable name="className" select="concat($namespace, '.', @name, $bean-suffix)"/>
    <xsl:variable name="derived-from" select="mif:derivationSupplier[1]/@className"/>
    <xsl:variable name="rim-class" select="$rim//mif:ownedClass/mif:class[@name eq $derived-from]" as="element()"/>
    <!-- TODO: clean this hardcoded stuff up a bit -->
    <xsl:variable name="rim-class-qname" select="concat('org.hl7.rim.', f:root-rim-class($rim-class)/@name)"/>
    <xsl:variable name="unq-name" select="@name"/>
    <subclass name="{$className}" lazy="true" discriminator-value="{$className}" extends="{concat('org.hl7.rim.impl.Basic',f:root-rim-class($rim-class)/@name,'Impl')}">
    <!-- 
      <id name="internalId" column="internalId" type="string" unsaved-value="null" access="property">
         <generator class="assigned"/>
      </id>
      <discriminator column="internalDiscriminator" type="string"/>
      <version name="internalVersionNumber" column="internalVersionNumber" type="long" unsaved-value="negative" access="property"/>
      -->
      <property name="cloneCode" type="CS_CloneCode">
        <column name="cloneCode_code" sql-type="VARCHAR(555)" length="555" />
      </property>
      <xsl:apply-templates mode="associations" select="/mif:staticModel/mif:ownedAssociation/mif:connections[mif:nonTraversableConnection/@participantClassName eq $unq-name]"/>
      <xsl:apply-templates mode="dmif" select="mif:attribute"/>
      <!-- If this is a choice class, make the choices subclasses -->
      <xsl:apply-templates mode="hbm" select="mif:specializationChild"/>
    </subclass>
  </xsl:template>

  <!-- for the specialization, find the subclass -->
  <xsl:template mode="hbm" match="mif:specializationChild">
    <xsl:variable name="childClassName" select="@childClassName"/>
    <xsl:apply-templates mode="hbm-subclass" select="/mif:staticModel/mif:ownedClass/mif:class[@name eq $childClassName][f:is-subclass(.)]"/>
  </xsl:template>

  <!-- choice class children -->
  <xsl:template mode="hbm-subclass" match="mif:staticModel/mif:ownedClass/mif:class[mif:derivationSupplier[1]]">
    <xsl:variable name="className" select="concat($namespace, '.', @name, $bean-suffix)"/>
    <xsl:variable name="unq-name" select="@name"/>
    <subclass name="{$className}" lazy="true" discriminator-value="{$className}">
      <xsl:apply-templates mode="associations" select="/mif:staticModel/mif:ownedAssociation/mif:connections[mif:nonTraversableConnection/@participantClassName eq $unq-name]"/>
      <xsl:apply-templates mode="dmif" select="mif:attribute"/>
    </subclass>
  </xsl:template>

</xsl:transform>

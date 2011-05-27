<?xml version="1.0" encoding="UTF-8"?>
<!-- mif2java.xsl - generates Hibernate mapping from MIF content.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is Gunther Schadow.
Portions created by Initial Developer are Copyright (C) 2002-2004
Health Level Seven, Inc. All Rights Reserved.

Contributor(s): 

-->
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mif="urn:hl7-org:v3/mif" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xsl mif f saxon xs">
  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:output name="hbm" method="xml" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/> 

  <!-- parameters to construct paths and names -->

  <xsl:param name="base-package" select="'org.hl7.rim'"/>
  <xsl:param name="interface-package" select="$base-package"/>
  <xsl:param name="bean-package" select="concat($base-package,'.beans')"/>
  <xsl:param name="bean-suffix" select="'Bean'"/>
  <xsl:param name="data-types-interface-package" select="'org.hl7.types'"/>
  <xsl:param name="hbm-template-url" select="'template.hbm.xml'"/>
  <xsl:param name="hbm-typedef-url" select="'typedef.hbm.xml'"/>
  <xsl:param name="cvf-config-url" select="'code-value-factory.xml'"/>
  <xsl:param name="name-prefix" select="'Basic'"/>

  <xsl:variable name="template-prefix" select="concat('#', $name-prefix)"/>
  <xsl:variable name="hbm-template" select="document($hbm-template-url)"/>
  <xsl:variable name="hbm-typedefs" select="document($hbm-typedef-url)"/>
  <xsl:variable name="cvf-config" select="document($cvf-config-url)"/>
  <xsl:variable name="mif-data" select="/"/>

  <!-- Some functions to deal with naming conventions -->

  <xsl:function name="f:path-from-package" as="xs:string">
    <xsl:param name="package-name"/>
    <xsl:sequence select="replace($package-name,'\.','/')"/>
  </xsl:function>

  <xsl:function name="f:interface-name" as="xs:string">
    <xsl:param name="rim-class-name"/>
    <xsl:sequence select="$rim-class-name"/>
  </xsl:function>

  <xsl:function name="f:bean-name" as="xs:string">
    <xsl:param name="rim-class-name"/>
    <xsl:sequence select="concat($rim-class-name,$bean-suffix)"/>
  </xsl:function> 

  <xsl:function name="f:capitalize" as="xs:string">
    <xsl:param name="string"/>
    <xsl:sequence select="concat(upper-case(substring($string,1,1)),substring($string,2))"/>
  </xsl:function> 

  <xsl:function name="f:data-type-name" as="xs:string">
    <xsl:param name="mif-type"/>
    <xsl:variable name="type-name" select="f:interface-name($mif-type/@name)"/>
    <xsl:variable name="parameters" select="$mif-type/mif:supplierBindingArgumentDatatype"/>
    <xsl:variable name="parameter-list" select="string-join(for $p in $parameters return f:data-type-name($p),',')"/>
    <xsl:choose>
      <xsl:when test="$type-name eq 'GTS'">
        <xsl:sequence select="'SET&lt;TS>'"/>
      </xsl:when>
      <xsl:when test="$type-name eq 'RTO'">
        <xsl:sequence select="'RTO'"/>
      </xsl:when>
      <xsl:when test="$parameter-list">
        <xsl:sequence select="concat($type-name,'&lt;',$parameter-list,'>')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$type-name"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <xsl:function name="f:import-data-type-name" as="xs:string">
    <xsl:param name="mif-type"/>
    <xsl:variable name="type-name" select="f:interface-name($mif-type/@name)"/>
    <xsl:choose>
      <xsl:when test="$type-name eq 'GTS'">
        <xsl:sequence select="'SET'"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$type-name"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!-- MAIN MODE -->

  <!-- deep null transform -->
  <xsl:template match="/">
    <xsl:text disable-output-escaping="yes">&#10;&lt;!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd"&gt;&#10;</xsl:text>
    <hibernate-mapping>
      <xsl:comment><xsl:value-of select="$copyright-statement"/></xsl:comment>
      <xsl:apply-templates mode="hbm" select="//mif:class[@name and $hbm-template//class/@name=concat($template-prefix,@name)]"/>
    </hibernate-mapping>
  </xsl:template>
  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <xsl:variable name="copyright-statement">THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

The contents of this file are subject to the Health Level-7 Public
License Version 1.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License
at http://www.hl7.org/HPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
the License for the specific language governing rights and limitations
under the License.

The Original Code is all this file.

The Initial Developer of the Original Code is automatically generated
from HL7 copyrighted standards specification which may be subject to
different license. Portions created by Initial Developer are Copyright
(C) 2002-2004 Health Level Seven, Inc. All Rights Reserved.

THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.</xsl:variable>

  <!-- MODE: hbm - Hibernate mapping file -->

  <!-- deep null transform -->
  <xsl:template mode="hbm" match="/|@*|node()">
    <xsl:apply-templates mode="hbm" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="hbm" match="mif:specializationChild[@childClassName]">
    <xsl:apply-templates mode="hbm" select="//mif:class[@name=current()/@childClassName]"/>
  </xsl:template>

  <!-- 
    Attributes that are XML Persisted. 
    These typedefs are included in the hibernate configuration, so it
    is only necessary to specify the property with the name of the type.

    TODO: Should this be used as a default for any attribute that does
    not match one of the other rules?
   -->
  <xsl:template mode="hbm" match="mif:attribute[$hbm-template//typedef[@name=f:data-type-name(current()/mif:type)]/@class='org.hl7.hibernate.XMLPersistedDataTypeUserType']" priority="2">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <property name="{$name}" type="{f:data-type-name(mif:type)}"/>
  </xsl:template>

  <!-- 
     Certain structural attributes that use the CS type with an enum specified in the code-value-factory config.
     These attributes are stored as 'MultiColumnPersistedDataTypeUserType', but only the code is stored in
     the column. The code system is assumed to be the constant for the particular code system used for this domain
     (e.g. RoleClass, ParticipationType).

     [some $d in $cvf-config//codeSystem satisfies $d/@domain=@domainName]
     This query in the match clause is causing a bug in both saxon and xalan. 
     It is causing a variable defined in another template to change values.
     Currently, it is unnecessary, because all domains are listed in the cvt-config file for the attribute names listed.
     I put in a constraint on the $cvt-entry variable of as="element()" to guarantee this is the case. 
     -->
  <xsl:template mode="hbm" match="mif:attribute[mif:type/@name='CS' and ('typeCode','moodCode','classCode','determinerCode')=@name]" priority="1">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:variable name="domain-name" select="mif:supplierDomainSpecification[1]/@domainName"/>
    <xsl:variable name="cvt-entry" select="$cvf-config//codeSystem[@domain=$domain-name][1]" as="element()"/>
    <xsl:message><INFO att="{@name}" dom="{$domain-name}"><xsl:sequence select="$cvt-entry"/></INFO></xsl:message>
    <property name="{$name}">
      <column name="{@name}" sql-type="VARCHAR(32)" length="32"/>
      <type name="org.hl7.hibernate.MultiColumnPersistedDataTypeUserType">
        <param name="type">CS</param>
        <param name="interface">org.hl7.types.CS</param>
        <param name="class">org.hl7.types.impl.CSimpl</param>
        <param name="staticFactoryMethod">valueOf</param>
        <param name="properties">2</param>
        <param name="property.0.name">code</param>
        <param name="property.0.class">org.hl7.types.ST</param>
        <param name="property.0.type">org.hl7.hibernate.StringLiteralPersistedDataTypeUserType</param>
        <param name="property.0.type.params">1</param>
        <param name="property.0.type.param.0.type">ST</param>
        <param name="property.1.const"><xsl:value-of select="$cvt-entry/@codeSystem"/></param>
        <param name="property.1.typeSpec">UID</param>
        <param name="property.1.class">org.hl7.types.UID</param>
      </type>
    </property>
  </xsl:template>

  <!-- 
    Attributes defined as StringLiteralType in the template. If the template defines a length for the particular data type,
    that length is used for the database column, although none specify @length at this time.
    -->
  <xsl:template mode="hbm" match="mif:attribute[$hbm-template//typedef[@name=f:data-type-name(current()/mif:type)]/@class='org.hl7.hibernate.StringLiteralPersistedDataTypeUserType']" priority="0">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>
    <xsl:variable name="length" select="$hbm-template//typedef[@name=$type-name]/@length"/>
    <property name="{$name}" type="{f:data-type-name(mif:type)}">
      <xsl:if test="$length">
        <xsl:attribute name="length"><xsl:value-of select="$length"/></xsl:attribute>
      </xsl:if>
    </property>
  </xsl:template>

  <!-- 
     Attributes that can be stored in their entirety as multiple columns (e.g. CS, TEL, II). The mode="composite-type"
     xsl Templates are applied to the $hbm-template/typedef. Some classes may have multiple attributes with these types, 
     so the attribute-name is supplied as a prefix to the db column name to disambiguate.
   -->
  <xsl:template mode="hbm" match="mif:attribute[$hbm-template//typedef[@name=f:data-type-name(current()/mif:type)]/@class='org.hl7.hibernate.MultiColumnPersistedDataTypeUserType']">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>
    <xsl:variable name="typedef" select="$hbm-template//typedef[@name=$type-name]"/>
    <property name="{$name}" type="{f:data-type-name(mif:type)}">
      <xsl:apply-templates mode="composite-type" select="$typedef">
        <xsl:with-param name="attribute-name" select="$name"/>
      </xsl:apply-templates>
    </property>
  </xsl:template>

  <!-- 
     Some attributes are not be stored in their entirety as db column data (e.g. ANY, RTO, EN, CE, CD, SET<TS>),
     however they do have predictable information that can be used for db indexing (e.g. number, unit, code,
     codeSystem, familyName, etc).  The 'xml' column will contain all the information necessary for persistence, 
     and the other columns will contain a copy of some information to be used for indexing. 
     
     The mode="composite-type" XSL Templates are applied to the $hbm-template/typedef. The attribute name is used as 
     a prefix to the name defined in the template to disambiguate column names where the same type is used multiple 
     times in one class / class hierarchy.
     -->  
  <xsl:template mode="hbm" match="mif:attribute[$hbm-template//typedef[@name=f:data-type-name(current()/mif:type)]/@class='org.hl7.hibernate.XMLWithIndexableColumnsPersistedDataTypeUserType']">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>
    <xsl:variable name="typedef" select="$hbm-template//typedef[@name=$type-name]"/>
    <property name="{$name}" type="{$type-name}" access="org.hl7.hibernate.StrategicPropertyAccessor">
      <xsl:apply-templates mode="composite-type" select="$typedef">
        <xsl:with-param name="attribute-name" select="$name"/>
      </xsl:apply-templates>
    </property>
  </xsl:template>

  <!-- 
     Attributes without a class defined for access. XSL Templates with mode="template-type" are applied
     to the $hbm-template/typedef in the template file.
     
     Currently, this is used for collection types that are stored in a separate table (i.e. SET<II>, BAG<EN>), 
     to allow for queries on the specific values of the collections.
   -->
  <xsl:template mode="hbm" match="mif:attribute[$hbm-template//typedef[@name=f:data-type-name(current()/mif:type) and not(@class)]]">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="attribute-name" select="@name"/>
    <xsl:param name="data-type-name" select="f:data-type-name(mif:type)"/>
    <xsl:variable name="typedef" select="$hbm-template//typedef[@name=$data-type-name]"/>
    <xsl:apply-templates mode="template-type" select="$typedef/*">
      <xsl:with-param name="class-name" select="$class-name"/>
      <xsl:with-param name="attribute-name" select="$attribute-name"/>
    </xsl:apply-templates>
  </xsl:template>

  <!-- MODE: template-type - create mappings for composite type properties -->
  <!-- deep identity transform -->
  <xsl:template mode="template-type" match="@*|node()">
    <xsl:param name="class-name" select="/.."/>
    <xsl:param name="attribute-name" select="/.."/>
    <xsl:copy>
      <xsl:apply-templates mode="template-type" select="@*|node()">
        <xsl:with-param name="class-name" select="$class-name"/>
        <xsl:with-param name="attribute-name" select="$attribute-name"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>

  <!-- Text substitution for the variables class-name and attribute-name -->
  <xsl:template mode="template-type" match="@*[contains(.,'{$')]" priority="1">
    <xsl:param name="class-name" select="/.."/>
    <xsl:param name="attribute-name" select="/.."/>
    <xsl:attribute name="{local-name(.)}">
      <xsl:value-of select="replace(replace(.,'\{\$class-name\}',$class-name),'\{\$attribute-name\}',$attribute-name)"/>
    </xsl:attribute>
  </xsl:template>

  <!-- 
     Match a set or bag/element in either a typedef declaration or a class template that has a 
     multi column type.
   -->
  <xsl:template mode="template-type" match="element[some $t in $hbm-template//typedef[@name=current()/@type] satisfies $t/@class='org.hl7.hibernate.MultiColumnPersistedDataTypeUserType' or $t/@class='org.hl7.hibernate.XMLWithIndexableColumnsPersistedDataTypeUserType']">
    <xsl:param name="class-name" select="/.."/>
    <xsl:param name="attribute-name" select="/.."/>
    <xsl:variable name="typedef" select="$hbm-template//typedef[@name=current()/@type]"/>
    <xsl:copy>
      <xsl:apply-templates mode="template-type" select="@*">
        <xsl:with-param name="class-name" select="$class-name"/>
        <xsl:with-param name="attribute-name" select="$attribute-name"/>
      </xsl:apply-templates>
      <xsl:apply-templates mode="composite-type" select="$typedef">
        <xsl:with-param name="template-body" select="." tunnel="yes"/>
        <xsl:with-param name="template-class-name" select="$class-name" tunnel="yes"/> 
        <xsl:with-param name="template-attribute-name" select="$attribute-name" tunnel="yes"/>
        <xsl:with-param name="attribute-name" select="/.."/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>

  <!-- MODE: composite-type - create mappings for composite type properties -->
  <!-- deep null transform -->
  <xsl:template mode="composite-type" match="/|@*|node()">
    <xsl:param name="attribute-name" select="/.."/>
    <xsl:apply-templates mode="composite-type" select="@*|node()">
      <xsl:with-param name="attribute-name" select="$attribute-name"/>
    </xsl:apply-templates>
  </xsl:template>

  <!-- 
     Match typedef params and create database column elements for a MultiColumnPersistedDataTypeUserType
     or a XMLWithIndexableColumnsPersistedDataTypeUserType.
    -->
  <xsl:template mode="composite-type" match="param[matches(@name,'^property\.[0-9]+\.name$')]">
    <xsl:param name="template-body" select="/.." tunnel="yes"/>
    <xsl:param name="template-class-name" select="/.." tunnel="yes"/> 
    <xsl:param name="template-attribute-name" select="/.." tunnel="yes"/>
    <xsl:param name="attribute-name" select="/.."/>
    <xsl:variable name="index" select="replace(@name,'^property\.([0-9]+)\.name$','$1')"/>
    <xsl:variable name="property-name" select="text()"/>
    <xsl:variable name="template-column" select="$template-body/column[number($index)+1]"/>
    <column name="{if($attribute-name) then concat($attribute-name,'_') else ''}{$property-name}">
      <xsl:choose>
        <xsl:when test="$property-name ne 'xml'">
          <xsl:attribute name="sql-type">VARCHAR(555)</xsl:attribute>
        </xsl:when>
        <xsl:otherwise>
          <!-- xsl:attribute name="sql-type">VARCHAR(555)</xsl:attribute -->
        </xsl:otherwise>
      </xsl:choose>
      <xsl:if test="$property-name ne 'xml'">
        <xsl:attribute name="length">555</xsl:attribute>
      </xsl:if>
      <xsl:apply-templates mode="template-type" select="$template-column/@*">
        <xsl:with-param name="class-name" select="$template-class-name"/>
        <xsl:with-param name="attribute-name" select="$template-attribute-name"/>
      </xsl:apply-templates>
    </column>
  </xsl:template>

</xsl:transform>

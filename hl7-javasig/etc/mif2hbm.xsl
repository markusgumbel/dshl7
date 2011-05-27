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
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mif="urn:hl7-org:v3/mif" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" exclude-result-prefixes="xsl mif f saxon">
  <xsl:import href="mif2hbm-base.xsl"/>

  <xsl:param name="specializationStyle" select="'union-subclass'"/>
  <xsl:param name="name-prefix" select="'Basic'"/>
  <xsl:variable name="template-prefix" select="concat('#', $name-prefix)"/>

  <!-- MODE: hbm-template - Hibernate mapping file template -->

  <!-- deep identity transform -->
  <xsl:template mode="hbm-template" match="/|@*|node()">
    <xsl:copy>
      <xsl:apply-templates mode="hbm-template" select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template mode="hbm-template" match="class/@name[starts-with(.,'#')]|@class[starts-with(.,'#')]">
    <xsl:attribute name="{local-name()}">
      <xsl:value-of select="concat($base-package,'.',f:interface-name(substring-after(.,'#')))"/>
    </xsl:attribute>
  </xsl:template>

  <xsl:template mode="hbm-template" match="@abstract[$specializationStyle eq 'subclass']"/>
  <xsl:template mode="hbm-template" match="discriminator[$specializationStyle eq 'union-subclass']"/>

  <xsl:template mode="hbm" match="mif:class[($specializationStyle eq 'subclass') and @name and $hbm-template//class/@name=concat($template-prefix,@name)]">
    <xsl:variable name="template-class" select="$hbm-template//class[@name=concat($template-prefix,current()/@name)]"/>
    <!-- Make copy of the variable to fix either a scoping issue or an xslt processor bug -->
    <xsl:variable name="template-class-copy" select="$hbm-template//class[@name=concat($template-prefix,current()/@name)]"/>
    
    <class discriminator-value="{@name}">
      <xsl:apply-templates mode="hbm-template" select="$template-class/@*"/>
      <id name="internalId" column="internalId" type="string" unsaved-value="null" access="property">
	<generator class="assigned"/>
      </id>
      <discriminator column="internalDiscriminator" type="string"/>
      <version name="internalVersionNumber" column="internalVersionNumber" type="long" unsaved-value="negative" access="property"/>
      <subclass name="{$bean-package}.{$name-prefix}{f:bean-name(@name)}" abstract="true">
	 <xsl:apply-templates mode="hbm" select="mif:attribute[@isMandatory='true']"/>
	 <subclass name="{$base-package}.{f:interface-name(@name)}" abstract="true">
	    <subclass name="{$bean-package}.{f:bean-name(@name)}" discriminator-value="{f:bean-name(@name)}">
	       <xsl:apply-templates mode="hbm-template" select="$template-class-copy/node()"/>
	       <xsl:apply-templates mode="hbm" select="node()[@isMandatory='false' and not(self::mif:specializationChild) and not(self::mif:attribute[@name=$template-class-copy/*/@name])]"/>
	       <xsl:apply-templates mode="hbm" select="//mif:connections/mif:traversableConnection[@participantClassName eq current()/@name]"/>
	       <xsl:apply-templates mode="hbm" select="mif:specializationChild"/>
	    </subclass>
	 </subclass>
      </subclass>
    </class>
  </xsl:template>
  
  <xsl:template mode="hbm" match="mif:class[($specializationStyle eq 'union-subclass') and @name and $hbm-template//class/@name=concat($template-prefix,@name)]">
    <xsl:variable name="template-class" select="$hbm-template//class[@name=concat($template-prefix,current()/@name)]"/>
    <class abstract="true">
      <xsl:apply-templates mode="hbm-template" select="$template-class/@*|$template-class/node()"/>
      <union-subclass name="{$bean-package}.{f:bean-name(@name)}">
        <xsl:apply-templates mode="hbm" select="node()[not(self::mif:specializationChild) and not(self::mif:attribute[@name=$template-class/*/@name])]"/>
        <xsl:apply-templates mode="hbm" select="//mif:connections/mif:traversableConnection[@participantClassName eq current()/@name]"/>
        <xsl:apply-templates mode="hbm" select="mif:specializationChild"/>
      </union-subclass>
    </class>
  </xsl:template>

  <xsl:template mode="hbm" match="mif:class[($specializationStyle eq 'subclass') and @name and not($hbm-template//class/@name=concat($template-prefix,@name)) and not(ends-with(@name,'Heir'))]">
    <subclass name="{$bean-package}.{f:bean-name(@name)}" discriminator-value="{@name}">
      <xsl:apply-templates mode="hbm" select="node()[not(self::mif:specializationChild)]"/>
      <xsl:apply-templates mode="hbm" select="//mif:connections/mif:traversableConnection[@participantClassName eq current()/@name]"/>
      <xsl:apply-templates mode="hbm" select="mif:specializationChild"/>
    </subclass>
  </xsl:template>

  <xsl:template mode="hbm" match="mif:class[($specializationStyle eq 'union-subclass') and @name and not($hbm-template//class/@name=concat($template-prefix,@name)) and not(ends-with(@name,'Heir'))]">
    <union-subclass name="{$bean-package}.{f:bean-name(@name)}">
      <xsl:apply-templates mode="hbm" select="node()[not(self::mif:specializationChild)]"/>
      <xsl:apply-templates mode="hbm" select="//mif:connections/mif:traversableConnection[@participantClassName eq current()/@name]"/>
      <xsl:apply-templates mode="hbm" select="mif:specializationChild"/>
    </union-subclass>
  </xsl:template>
</xsl:transform>

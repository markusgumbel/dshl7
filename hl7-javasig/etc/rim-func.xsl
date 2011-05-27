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
<xsl:transform version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mif="urn:hl7-org:v3/mif" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" exclude-result-prefixes="xsl mif f saxon xs">
  <xsl:param name="rim-mif" select="'rim.coremif'"/>
  <xsl:param name="cmet-mif" select="'cmetinfo.coremif'"/>
  <xsl:param name="cmet-package-root" select="'org.hl7'"/>
  <xsl:param name="rim-base-package" select="'org.hl7.rim'"/>
  <xsl:param name="rim-impl-package" select="concat($rim-base-package, '.impl')"/>
  <xsl:param name="base-class-prefix" select="'Basic'" as="xs:string"/>
  <xsl:param name="bean-suffix" select="'Bean'"/>
  <xsl:param name="namespace" select="''"/>
  
  <xsl:variable name="rim" select="document($rim-mif)" as="node()"/>
  <xsl:variable name="rim-parents" select="$rim/mif:staticModel/mif:ownedClass/mif:class[mif:specializationChild][@name ne 'InfrastructureRoot']" as="element()*"/>
  <xsl:variable name="cmets" select="document($cmet-mif)" as="node()"/>

  <xsl:function name="f:participant-to-qclass-name">
    <xsl:param name="class-or-cmet" as="xs:string"/>
    <xsl:variable name="cmet" select="$cmets//mif:ownedCommonModelElement[@name eq $class-or-cmet]"/>
    <xsl:variable name="rim-iface-name" select="substring-before($cmet/@entry-kind, '(')"/>
    <xsl:variable name="is-choice-entry" select="boolean($cmet/mif:specializationChildEntryClass/mif:specializationClass)" as="xs:boolean"/>
    
    <xsl:sequence select="if(f:is-cmet-name($class-or-cmet)) then if($is-choice-entry) then f:rim-base-class($rim-iface-name) else concat($cmet-package-root, '.', f:cmet-name($cmet), '.impl.', $cmet/mif:specializationChildEntryClass/@name, $bean-suffix) else concat($namespace, '.', $class-or-cmet, $bean-suffix)"/>
  </xsl:function>

  <xsl:function name="f:cmet-name" as="xs:string">
    <xsl:param name="cmet" as="element()"/>
    <xsl:variable name="csm" select="$cmet/mif:specializationChildStaticModel"/>
    <xsl:value-of select="concat($csm/@subSection, $csm/@domain, '_MT', $csm/@id, $csm/@realm)"/>
  </xsl:function>
  
  <xsl:function name="f:class-for-cmet" as="xs:string?">
    <xsl:param name="cmet" as="element()"/>
    <xsl:value-of select="if($cmet/mif:specializationChildEntryClass) then concat($cmet-package-root, '.', f:cmet-name($cmet), '.impl.', $cmet/mif:specializationChildEntryClass/@name, $bean-suffix) else ()"/>
  </xsl:function>
  
  <xsl:function name="f:classes-for-cmet" as="xs:string*">
    <xsl:param name="cmet-name" as="xs:string"/>
    <xsl:variable name="cmet" select="$cmets//mif:ownedCommonModelElement[@name eq $cmet-name]" as="element()"/>
    <xsl:value-of select="f:class-for-cmet($cmet), for $choice in $cmet//mif:specializationClass return if(f:is-cmet-name($choice/@name)) then f:classes-for-cmet($choice/@name) else concat($cmet-package-root, '.', f:cmet-name($cmet), '.impl.', $choice/@name, $bean-suffix)"/>
  </xsl:function>
    
  <xsl:function name="f:rim-base-class" as="xs:string">
    <xsl:param name="rim-class-name" as="xs:string"/>
    <xsl:value-of select="concat($rim-impl-package, '.', $base-class-prefix, $rim-class-name, $bean-suffix)"/>
  </xsl:function>
  
  <xsl:function name="f:is-cmet-name" as="xs:boolean">
    <!-- Maybe this should test for existence in $cmets var instead of the naming convention? -->
    <xsl:param name="class-name" as="xs:string"/>
    <xsl:sequence select="substring($class-name, 2,1) eq '_'"/>
  </xsl:function>

  <xsl:function name="f:parent-rim-class" as="element()*">
    <xsl:param name="rim-class" as="element()"/>
    <xsl:sequence select="$rim-parents[some $specChild in mif:specializationChild satisfies $specChild/@childClassName eq $rim-class/@name]"/>
  </xsl:function>

  <xsl:function name="f:rim-class" as="element()">
    <xsl:param name="rim-class-name" as="xs:string"/>
    <xsl:sequence select="$rim//mif:class[@name=$rim-class-name]"/>
  </xsl:function>
  
  <xsl:function name="f:root-rim-class" as="element()">
    <xsl:param name="rim-class" as="element()"/>
    <xsl:variable name="parent" select="f:parent-rim-class($rim-class)" as="element()*"/>
    <xsl:sequence select="if(empty($parent)) then $rim-class else f:root-rim-class($parent)"/>
  </xsl:function>

  <!-- recursive function to find the RIM class with a given attribute -->
  <xsl:function name="f:rim-attribute" as="element()">
    <xsl:param name="rim-class" as="element()"/>
    <xsl:param name="attr-name" as="xs:string"/>
    <xsl:variable name="attr" select="$rim-class/mif:attribute[@name=$attr-name]" as="element()*"/>
    <xsl:sequence select="if($attr) then $attr else f:rim-attribute(f:parent-rim-class($rim-class), $attr-name)"/>
  </xsl:function>
  
  <xsl:function name="f:rim-association-cardinality" as="xs:string">
    <xsl:param name="connection" as="element()"/>
    <xsl:variable name="rim-class-name" select="$connection/mif:traversableConnection/mif:derivationSupplier[1]/@className" as="xs:string"/>
    <xsl:variable name="rim-assoc-name" select="$connection/mif:traversableConnection/mif:derivationSupplier[1]/@associationEndName" as="xs:string"/>
    <xsl:variable name="rim-connection" select="$rim//mif:connections[mif:traversableConnection/@participantClassName=$rim-class-name][mif:traversableConnection/@name=$rim-assoc-name]" as="element()"/>
    <xsl:variable name="rim-end-point" select="$rim-connection/mif:traversableConnection[@participantClassName=$rim-class-name]" as="element()"/>
    <xsl:value-of select="$rim-end-point/@maximumMultiplicity"/>
  </xsl:function>
  
</xsl:transform>

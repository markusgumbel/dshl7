<?xml version="1.0" encoding="UTF-8"?>
<!-- voc2java.xsl - generates Java code from Vocabulary.xml dump.

The dump is from RoseTree, so far the only reliable source of
HL7 vocabulary. We will create Java classes that implement CS
for the so called "structural codes", such as Act.moodCode,
etc. These codes will be Java enumerations.

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
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	       xmlns:map="java:java.util.Map" 
	       xmlns:prop="java:java.util.Properties" 
	       xmlns:class="java:java.lang.Class" 
	       xmlns:obj="java:java.lang.Object" 
	       xmlns:saxon="http://saxon.sf.net/" 
	       exclude-result-prefixes="xsl saxon map prop class obj">
  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:output name="java" method="text" encoding="ASCII"/>
  <xsl:output name="html" method="html" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/>

  <!-- the code-system-map.xml file tells us what the OID should be and that way we'll filter what should be instantiated this way. -->
  <xsl:param name="code-value-factory-cfg" select="'code-value-factory.xml'"/>
  <xsl:variable name="codeSystemMap" select="document($code-value-factory-cfg)"/>
  <xsl:param name="domain-oid-map-cfg" select="'domain-oid-map.xml'"/>
  <xsl:variable name="domainOidMap" select="document($domain-oid-map-cfg)"/>

  <!-- deep null transform -->
  <xsl:template match="/">
    <domainMap>
      <xsl:apply-templates select="@*|node()"/>
    </domainMap>
  </xsl:template>

  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <!--xsl:template match="vocSet">
    <xsl:variable name="all">
      <domain domain="{@name}">
        <xsl:sequence select="$domainOidMap//domain[@name=current()/@name]/@codeSystem"/>
        <xsl:apply-templates select="@*|node()"/>
      </domain>
    </xsl:variable>
    <xsl:if test="$all//@codeSystem">
      <xsl:sequence select="$all"/>
    </xsl:if>
  </xsl:template-->

  <xsl:template match="vocSet|specDomain|abstDomain">
    <domain name="{@name}">
      <xsl:sequence select="$domainOidMap//domain[@name=current()/@name]/@codeSystem"/>
      <xsl:apply-templates select="@*|node()"/>
    </domain>
  </xsl:template>

</xsl:transform>

<?xml version="1.0" encoding="UTF-8"?>
<!-- mif2hbm-typedef.xsl - generates Hibernate mapping from MIF content.

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
  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/> 

  <!-- MAIN MODE -->
  <xsl:template match="/">
    <xsl:text disable-output-escaping="yes">&#10;&lt;!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd"&gt;&#10;</xsl:text>
    <hibernate-mapping>
      <xsl:comment><xsl:value-of select="$copyright-statement"/></xsl:comment>
      <xsl:apply-templates select="//typedef[@class]"/>
    </hibernate-mapping>
  </xsl:template>

  <!-- deep identity transform -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
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

</xsl:transform>

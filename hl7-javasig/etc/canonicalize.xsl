<?xml version="1.0" encoding="UTF-8"?>
<!-- Canonicalization transform, sort attributes alphabetically
     and remove some known noise. This is used to diff input and
     output XML sample messages. -->
<xsl:transform version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/> 

  <xsl:template match="/|@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*">
        <xsl:sort select="name()"/>
      </xsl:apply-templates>
      <xsl:apply-templates select="node()"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="@*[.='']"/>
  <xsl:template match="@mediaType"/>
  <xsl:template match="@operator"/>
  <xsl:template match="@representation"/>
  <xsl:template match="@integrityCheckAlgorithm"/>

  <xsl:template match="text()" priority="0">
    <xsl:sequence select="normalize-space(.)"/>
  </xsl:template>

</xsl:transform>

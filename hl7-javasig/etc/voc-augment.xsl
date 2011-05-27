<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
   <xsl:strip-space elements="*"/> 

   <!-- deep identity transform -->
   <xsl:template match="/|@*|node()">
      <xsl:copy>
	 <xsl:apply-templates select="@*|node()"/>
      </xsl:copy>
   </xsl:template>

   <xsl:template match="specDomain[@printName='pseudonym']/@name[.='PersonNameUse']">
      <xsl:attribute name="name">Pseudonym</xsl:attribute>
   </xsl:template>
   <xsl:template match="specDomain[@printName='pseudonym' and @name='PersonNameUse']/p/text()">
      <xsl:text>Any name which is known not to be the real name for the person.</xsl:text>
   </xsl:template>
</xsl:transform>

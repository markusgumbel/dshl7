<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions" >
	<xsl:template match="/">
		<html>
		<body>
		<h2>Classes and Properties</h2> 
		<xsl:apply-templates/> 
		</body>
		</html>
	</xsl:template>
	
	<xsl:template match="class">
	 <h3>
	 <xsl:value-of select="@discriminator-value"/>
	 </h3>
	 	<xsl:for-each select="*"> 
            <xsl:apply-templates/>
        </xsl:for-each>
	</xsl:template>
	
	<xsl:template match="subclass">
	<h4>
	Subclass:
	 <xsl:value-of select="@name"/>
	 </h4>
	 <xsl:apply-templates/> 
	</xsl:template>	
	
	
	<xsl:template name="property" match="property">
	<b>--Property: </b>
	<xsl:value-of select="@name"/>
		<br/>
	</xsl:template>	
	
	<xsl:template name="bag" match="bag">
	<b>--------Bag: </b>:
	<xsl:value-of select="@name"/>
	<br/>	
	</xsl:template>

	<xsl:template name="set" match="set">
	<b>----Set: </b>
	<xsl:value-of select="@name"/>
	<br/>	
	</xsl:template>		
	
</xsl:stylesheet>
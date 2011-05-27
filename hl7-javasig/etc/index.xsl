<?xml version="1.0" encoding="UTF-8"?>
<!-- XSLT Web Services Framework (XWSF)

  Copyright (C) 2002, Regenstrief Institute. All rights reserved.

  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation; either version 2 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

  Written and maintained by Gunther Schadow <gschadow@regenstrief.org>
  Regenstrief Institute for Health Care
  1050 Wishard Blvd., Indianapolis, IN 46202, USA.

  $Id: index.xsl 4711 2006-11-03 05:04:57Z gschadow $
-->
<!-- Generate an index from the web.xml file -->
<xsl:transform version="2.0"
    xmlns:web="http://java.sun.com/xml/ns/javaee"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:response="java:javax.servlet.http.HttpServletResponse"
    xmlns:gst="http://xwsf.sf.net/gstserver/">

<xsl:output method="html" 
	    indent="yes" 
	    encoding="UTF-8"
	    media-type="text/html"/>
<xsl:strip-space elements="*"/>

<xsl:param name="gst:request" select="/.."/>
<xsl:param name="gst:response" select="/.."/>

<!-- deep null transform -->
<xsl:template match="/|@*|node()">
  <xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template match="web:web-app">
  <html>
    <head>
      <title><xsl:value-of select="child::web:display-name"/></title>
    </head>
    <body>
      <h1><xsl:value-of select="child::web:display-name"/></h1>
      <p><xsl:value-of select="child::web:description"/></p>
      <dl>
        <xsl:apply-templates mode="list-item"
          select="child::web:servlet-mapping"/>
      </dl>
    </body>
  </html>
</xsl:template>

<!-- MODE: list-item -->

<!-- deep null transform -->
<xsl:template mode="list-item" match="/|@*|node()">
  <xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template mode="list-item" match="web:servlet-mapping">
  <xsl:apply-templates mode="list-item"
      select="//web:web-app/web:servlet[
          web:servlet-name/text()=current()/web:servlet-name/text()]">
     <xsl:with-param name="href" 
        select="substring-after(
                  substring-before(child::web:url-pattern/text(),'/*'),'/')"/>
  </xsl:apply-templates>
</xsl:template>

<xsl:template mode="list-item" match="web:servlet">
  <xsl:param name="href" select="/.."/>
  <dt>
    <a href="{$href}"><xsl:value-of select="child::web:display-name"/></a>
  </dt>
  <dd>
    <p><xsl:value-of select="web:description"/>
       [<a href="./{web:init-param[web:param-name/text()='transform']
       /web:param-value/text()}">source</a>]</p>
  </dd>
</xsl:template>

</xsl:transform>

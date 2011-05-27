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

  $Id: post.xsl 1615 2004-02-06 20:10:36Z gschadow $
-->
<xsl:transform version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:saxon="http://saxon.sf.net"
    xmlns:post="http://xwsf.sf.net/net.sf.xwsf.saxon.PostElementFactory"
    extension-element-prefixes="saxon post"
    exclude-result-prefixes="xsl saxon post">

	<xsl:output method="xml" indent="yes" encoding="ASCII"/>
	<xsl:strip-space elements="*"/> 

	<xsl:param name="url" select="/.."/>
	
	<!-- Deep identity transform. -->
	<xsl:template match="/|@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()"/>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="/" priority="1">
		<result>
			<post:message url="{string($url)}" debug-response="no">
				<xsl:apply-templates select="node()"/>
			</post:message>
		</result>
	</xsl:template>
</xsl:transform>

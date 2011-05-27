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

  $Id: soap-adapter.xsl 4714 2006-11-03 05:48:44Z gschadow $
-->
<!-- SOAP-adapter handles all the SOAP specific stuff needed to make
     a transform available as a SOAP Web Service.

     One can extend this by adding templates that

     - 'declare' SOAP Header elements with mustUnderstand='1' that are 
       actually handled

     - handle SOAP Body content

     The idea is that an existing transform need not be significantly
     changed.

     The only difference between a transform that is called 
     directly vs. via this SOAP adapter is that the SOAP adapter
     handles the document root.

     All SOAP specific processing is done in a special mode 'gst:soap' 
     and only the body content is processed in the default mode. Thus,
     the SOAP adapter interferes in no other way than claiming the 
     document root.

     Since many transforms also want to handle the root, the SOAP 
     Body element can be passed to a template named 'soap-adapter:root'.
     This template can be the same as that what would handle the 
     document root if the transform is executed in stand-alone mode
     without the SOAP adapter. E.g.,

     <xsl:template name="soap-adapter:root" match="/"
        xmlns:soap-adapter="http://xwsf.sf.net/gstserver/soap-adapter">
        <xsl:apply-templates select="node()"/>
     </xsl:template>

     SOAP Header elements that may come with mustUnderstand='1' must
     be declared by adding template handlers into the mode 'gst:soap'.
     These may be simple no-op handlers of the form:

     <xsl:template mode="gst:soap" match="se:Header/my:header"/> 
-->    
<xsl:transform version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:request="java:javax.servlet.http.HttpServletRequest"
    xmlns:response="java:javax.servlet.http.HttpServletResponse"
    xmlns:se="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:gst="http://xwsf.sf.net/gstserver/"
    xmlns:soap-adapter="http://xwsf.sf.net/gstserver/soap-adapter"
    xmlns:saxon="http://saxon.sf.net/"
    xmlns:ex="http://xwsf.sf.net/net.sf.xwsf.saxon.ExceptionElementFactory"
    extension-element-prefixes="saxon ex"
    exclude-result-prefixes="xsl saxon ex request response gst">

<xsl:output method="xml" 
	    indent="yes" 
	    encoding="UTF-8"
	    media-type="text/xml"/>
<xsl:strip-space elements="*"/>

<xsl:param name="gst:request" select="/.."/>
<xsl:param name="gst:response" select="/.."/>

<!-- We require the instance to be a SOAP Envelope -->

<!-- The SOAP adapter handles the document root and diverts processing
     into the mode 'gst'. We only return to the default mode when 
     processing the contents of the SOAP Body. -->
<xsl:template match="/" priority="100">
  <xsl:apply-templates mode="gst:soap" select="node()"/>
</xsl:template>

<!-- MODE 'gst:soap' -->

<!-- The toplevel must be a SOAP envelope -->
<xsl:template mode="gst:soap"
    match="se:Envelope[count(child::se:Body)=1
                   and count(child::se:Header)&lt;=1]">
  <xsl:apply-templates mode="gst:soap" select="@*|node()"/> 
</xsl:template>

<xsl:template mode="gst:soap" match="@se:encodingStyle"/>

<xsl:template mode="gst:soap" match="se:Header">
  <xsl:apply-templates mode="gst:soap" select="@*|node()"/>
</xsl:template>

<!-- Add templates for headers that the service DOES understand, in
     the simplest case this would be a NOP template. -->

<!-- Any mustUnderstand headers that is not handled MUST generate a 
     SOAP Fault, that's what the spec says. -->
<xsl:template mode="gst:soap" match="se:Header/*[@se:mustUnderstand='1']">
  <xsl:if test="$gst:response">
    <!-- FIXME: I abuse if/@test for its mere side effect! -->
    <xsl:if test="response:setStatus($gst:response, 500)"/>
  </xsl:if>

  <se:Envelope>
    <se:Header>
      <gst:ErrorDetail path="{saxon:path()}"/>
    </se:Header>
    <se:Body>
      <se:Fault>
        <faultcode>se:MustUnderstand</faultcode>
        <faultstring>Required header element not understood</faultstring>
      </se:Fault>
    </se:Body>
  </se:Envelope>
  
  <ex:throw exception="java.lang.Throwable" message="test"/>

  <xsl:message terminate="yes">Fault</xsl:message>
</xsl:template>

<!-- By default ignore header elements -->
<xsl:template mode="gst:soap" match="se:Header/*" priority="0"/>


<!-- Only the first Body element is processed and the allowed elements
     should be handled by appropriate templates. -->
<xsl:template mode="gst:soap" match="se:Envelope/se:Body[1]">
  <ex:try>
    <xsl:variable name="response-body">
      <!-- Here we return back to default mode -->
      <xsl:call-template name="soap-adapter:root"/>
    </xsl:variable>

    <xsl:if test="$response-body/se:Fault and $gst:response">
      <!-- FIXME: I abuse if/@test for its mere side effect! -->
      <xsl:if test="response:setStatus($gst:response, 200)"/>
    </xsl:if>

    <se:Envelope>
      <se:Body>
        <xsl:copy-of select="$response-body"/>
      </se:Body>
    </se:Envelope>     

    <!-- FIXME: add handlers for other exceptions, esp. add a
      mechanism by which error messages can be thrown -->

    <ex:catch exception="java.lang.Throwable">
      <xsl:if test="$gst:response">
        <!-- FIXME: I abuse if/@test for its mere side effect! -->
        <xsl:if test="response:setStatus($gst:response, 500)"/>
      </xsl:if>

      <se:Envelope>
        <se:Body>
          <se:Fault>
            <faultcode>se:Server</faultcode>
            <faultstring>Unhandled Exception While Processing SOAP Body</faultstring>
          </se:Fault>      
        </se:Body>
      </se:Envelope>
      <xsl:message>Fault</xsl:message>
    </ex:catch>
  </ex:try>
</xsl:template>

<!-- Comments are ignored -->
<xsl:template mode="gst:soap" match="comment()"/>

<!-- The first XML processing instruction is allowed and discarded -->
<xsl:template mode="gst:soap" match="processing-instruction('xml')"/>

<!-- Everything else is rejected (including processing instructions) -->
<xsl:template mode="gst:soap" match="/|@*|node()">
  <xsl:if test="$gst:response">
    <!-- FIXME: I abuse if/@test for its mere side effect! -->
    <xsl:if test="response:setStatus($gst:response, 500)"/>
  </xsl:if>

  <se:Envelope>
    <se:Header>
      <gst:ErrorDetail path="{saxon:path()}"/>
    </se:Header>
    <se:Body>
      <se:Fault>
        <faultcode>se:Client</faultcode>
        <faultstring>Malformed SOAP Request Error</faultstring>
      </se:Fault>
    </se:Body>
  </se:Envelope>
  <xsl:message>Fault</xsl:message>
</xsl:template>

<!-- default root, should be overridden. -->
<xsl:template name="soap-adapter:root">
  <hello/>
</xsl:template>

</xsl:transform>

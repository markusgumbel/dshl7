<?xml version="1.0" encoding="UTF-8"?>
<!-- mif2java.xsl - generates Java code from MIF content.
                                     
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
Portions created by Initial Developer are Copyright (C) 2002-2006
Health Level Seven, Inc. All Rights Reserved.

Contributor(s): 

-->
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:mif="urn:hl7-org:v3/mif" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xsl mif f saxon xs">
  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:output name="java" method="text" encoding="ASCII"/>
  <xsl:output name="html" method="html" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/> 

  <!-- parameters to construct paths and names -->

  <xsl:param name="base-path" select="'gencode'"/>
  <xsl:param name="base-package" select="'org.hl7.rim'"/>
  <xsl:param name="rim-base-package" select="'org.hl7.rim'"/>
  <xsl:param name="rim-impl-package" select="concat($rim-base-package, '.impl')"/>
  <xsl:param name="rim-decorator-package" select="concat($rim-base-package,'.decorators')"/>
  <xsl:param name="interface-package" select="$base-package"/>
  <xsl:param name="interface-path" select="concat($base-path,'/',f:path-from-package($interface-package))"/>
  <xsl:param name="bean-package" select="concat($base-package,'.beans')"/>
  <xsl:param name="bean-path" select="concat($base-path,'/',f:path-from-package($bean-package))"/>
  <xsl:param name="bean-suffix" select="'Bean'"/>
  <xsl:param name="decorator-package" select="concat($base-package,'.decorators')"/>
  <xsl:param name="decorator-path" select="concat($base-path,'/',f:path-from-package($decorator-package))"/>
  <xsl:param name="decorator-suffix" select="'Decorator'"/>
  <xsl:param name="data-types-interface-package" select="'org.hl7.types'"/>
  <xsl:param name="data-types-impl-package" select="'org.hl7.types.impl'"/>
  <xsl:param name="base-class-prefix" select="'Basic'" as="xs:string"/>

  <!-- Some functions to deal with naming conventions -->

  <xsl:function name="f:path-from-package" as="xs:string">
    <xsl:param name="package-name"/>
    <xsl:sequence select="replace($package-name,'\.','/')"/>
  </xsl:function>

  <!-- Name of type to qualified interface name -->  
  <xsl:function name="f:qualify-participant" as="xs:string">
    <xsl:param name="class-name" as="xs:string"/>
    <xsl:value-of select="concat($base-package, '.', f:interface-name($class-name))"/>
  </xsl:function>
  
  <xsl:function name="f:is-cmet-name" as="xs:boolean">
    <!-- Maybe this should test for existence in $cmets var instead of the naming convention? -->
  	<xsl:param name="class-name"/>
  	<xsl:sequence select="substring($class-name, 2,1) eq '_'"/>
  </xsl:function>

  <!-- distal end of a connection to qualified interface name -->  
  <xsl:function name="f:distal-interface-name" as="xs:string">
    <xsl:param name="distal-end" as="element()"/>
    <xsl:variable name="distal-participant" select="$distal-end/@participantClassName"/>
    <xsl:sequence select="f:qualify-participant($distal-participant)"/>
  </xsl:function>

  <!-- Name of type to unqualified interface name -->
  <xsl:function name="f:interface-name" as="xs:string">
    <xsl:param name="participant-name"/>
    <xsl:sequence select="$participant-name"/>
  </xsl:function>

  <!-- Name of class to implementation name -->
  <xsl:function name="f:bean-name" as="xs:string">
    <xsl:param name="rim-class-name"/>
    <xsl:sequence select="concat($rim-class-name,$bean-suffix)"/>
  </xsl:function> 

  <!-- Name of rim class to decorator interface name -->
  <xsl:function name="f:decorator-name" as="xs:string">
    <xsl:param name="rim-class-name"/>
    <xsl:sequence select="concat($rim-class-name,$decorator-suffix)"/>
  </xsl:function> 

  <xsl:function name="f:capitalize" as="xs:string">
    <xsl:param name="string"/>
    <xsl:sequence select="concat(upper-case(substring($string,1,1)),substring($string,2))"/>
  </xsl:function> 

  <xsl:function name="f:data-type-name" as="xs:string">
    <xsl:param name="mif-type" as="element()"/>
    <xsl:variable name="type-name" select="f:interface-name($mif-type/@name)"/>
    <xsl:variable name="parameters" select="$mif-type/mif:supplierBindingArgumentDatatype"/>
    <xsl:variable name="parameter-list" select="string-join(for $p in $parameters return f:data-type-name($p),',')"/>
    <xsl:choose>
      <xsl:when test="$type-name eq 'GTS'">
        <xsl:sequence select="'SET&lt;TS>'"/>
      </xsl:when>
      <xsl:when test="$type-name eq 'RTO'">
        <xsl:sequence select="'RTO'"/>
      </xsl:when>
      <xsl:when test="$parameter-list">
        <xsl:sequence select="concat($type-name,'&lt;',$parameter-list,'>')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$type-name"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <xsl:function name="f:import-data-type-name" as="xs:string">
    <xsl:param name="mif-type"/>
    <xsl:variable name="type-name" select="f:interface-name($mif-type/@name)"/>
    <xsl:choose>
      <xsl:when test="$type-name eq 'GTS'">
        <xsl:sequence select="'SET'"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$type-name"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <xsl:function name="f:null-data-type-name" as="xs:string">
    <xsl:param name="mif-type"/>
    <xsl:value-of select="concat(f:import-data-type-name($mif-type), if($mif-type/@name eq 'BL') then 'impl' else 'null')"/>
  </xsl:function>

  <xsl:function name="f:maximumMultiplicity" as="xs:string">
    <xsl:param name="proximal-end" as="element()"/>
    <xsl:sequence select="$proximal-end/@maximumMultiplicity"/>
  </xsl:function>
  
  <xsl:function name="f:getter-method-sig" as="xs:string">
    <xsl:param name="name" as="xs:string"/>
    <xsl:param name="type-name" as="xs:string"/>
    <xsl:value-of select="concat($type-name, ' get', f:capitalize($name), '()')"/>
  </xsl:function>
  
  <xsl:function name="f:setter-method-sig" as="xs:string">
    <xsl:param name="name" as="xs:string"/>
    <xsl:param name="type-name" as="xs:string"/>
    <xsl:value-of select="concat('void set', f:capitalize($name), '(', $type-name, ' ', $name, ')')"/>
  </xsl:function>
  
  <xsl:function name="f:field-decl" as="xs:string">
    <xsl:param name="name" as="xs:string"/>
    <xsl:param name="type-name" as="xs:string"/>
    <xsl:value-of select="concat('&#10;  private ', $type-name, ' _', $name, ';&#10;')"/>
  </xsl:function>

  <!-- Should be overridden in dmif2java.xsl -->
  <xsl:function name="f:isMandatory" as="xs:boolean">
    <xsl:param name="attribute" as="element()"/>
    <xsl:value-of select="$attribute/@isMandatory"/>
  </xsl:function>
  
  <!-- MAIN MODE -->

  <!-- deep null transform -->
  <xsl:template match="/">
    <xsl:apply-templates select="@*|node()"/>
    <gencode><!-- this output provided as a time stamp --></gencode>
  </xsl:template>
  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <!-- class to generate -->
  <xsl:template match="mif:class[@name and not(ends-with(@name,'Heir'))]">
  <xsl:choose>
    <xsl:when test=".//mif:attribute[@isMandatory=true()]">
        <!-- 
          If there are mandatory fields, make a class/interface with just these fields,
          and make another class/interface with all the non-mandatory fields.
          This should only happen for the 6 core classes.
        -->
        <xsl:result-document href="{$interface-path}/{f:interface-name(concat($base-class-prefix, @name))}.java" format="java">
          <xsl:apply-templates mode="interface" select=".">
            <xsl:with-param name="onlyMandatory" select="true()" as="xs:boolean"/>
            <xsl:with-param name="name-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>
        <xsl:result-document href="{$bean-path}/{f:bean-name(concat($base-class-prefix, @name))}.java" format="java">
          <xsl:apply-templates mode="bean" select=".">
            <xsl:with-param name="onlyMandatory" select="true()" as="xs:boolean"/>
            <xsl:with-param name="name-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>
        <xsl:result-document href="{$decorator-path}/{f:decorator-name(concat($base-class-prefix, @name))}.java" format="java">
          <xsl:apply-templates mode="decorator" select=".">
            <xsl:with-param name="onlyMandatory" select="true()" as="xs:boolean"/>
            <xsl:with-param name="name-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>

        <xsl:result-document href="{$interface-path}/{f:interface-name(@name)}.java" format="java">
          <xsl:apply-templates mode="interface" select=".">
            <xsl:with-param name="super-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>
        <xsl:result-document href="{$bean-path}/{f:bean-name(@name)}.java" format="java">
          <xsl:apply-templates mode="bean" select=".">
            <xsl:with-param name="super-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>
        <xsl:result-document href="{$decorator-path}/{f:decorator-name(@name)}.java" format="java">
          <xsl:apply-templates mode="decorator" select=".">
            <xsl:with-param name="super-prefix" select="$base-class-prefix"/>
          </xsl:apply-templates>
        </xsl:result-document>
    </xsl:when>
    <xsl:otherwise>
        <xsl:result-document href="{$interface-path}/{f:interface-name(@name)}.java" format="java">
          <xsl:apply-templates mode="interface" select="."/>
        </xsl:result-document>
        <xsl:result-document href="{$bean-path}/{f:bean-name(@name)}.java" format="java">
          <xsl:apply-templates mode="bean" select="."/>
        </xsl:result-document>
        <xsl:result-document href="{$decorator-path}/{f:decorator-name(@name)}.java" format="java">
          <xsl:apply-templates mode="decorator" select="."/>
        </xsl:result-document>
    </xsl:otherwise>
  </xsl:choose>
  
  </xsl:template>

  <!-- MODE: interface - generate Java interface -->

  <!-- deep null transform -->
  <xsl:template mode="interface" match="/|@*|node()">
    <xsl:apply-templates mode="interface" select="@*|node()"/>
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

  <xsl:template mode="interface" match="mif:class[@name]">
    <xsl:param name="name-prefix" select="''" as="xs:string"/>
    <xsl:param name="onlyMandatory" select="false()" as="xs:boolean"/>
    <xsl:param name="super-prefix" select="''" as="xs:string"/>

    <xsl:param name="parent" select="//mif:class[mif:specializationChild/@childClassName=current()/@name]"/>
    <xsl:param name="parent-name" select="if(@name='InfrastructureRoot') then 'RimObject' else if($parent) then $parent/@name else 'InfrastructureRoot'"/>
    <xsl:param name="super-qname" select="if($super-prefix ne '') then concat($super-prefix, @name) else if(//mif:class[@name eq $parent-name]) then concat($interface-package,'.',f:interface-name($parent-name)) else concat($rim-base-package,'.',f:interface-name($parent-name))"/>

    <xsl:text>/*&#10;</xsl:text>
    <xsl:value-of select="$copyright-statement"/>
    <xsl:text>&#10;*/&#10;</xsl:text>

    <xsl:text>package </xsl:text>
    <xsl:value-of select="$interface-package"/>
    <xsl:text>;&#10;&#10;</xsl:text>

    <xsl:if test="contains($super-qname, '.')">
        <xsl:text>import </xsl:text>
        <xsl:value-of select="$super-qname"/>
        <xsl:text>;&#10;</xsl:text>
    </xsl:if>
    
    <xsl:apply-templates mode="import" select="."/>
    <xsl:text>import /*org.hl7.rim.AssociationSet*/java.util.List;</xsl:text>
    <xsl:text>&#10;&#10;</xsl:text>

    <xsl:text>/**</xsl:text>
    <xsl:sequence select="f:javadoc-html(mif:annotations/mif:definition/mif:text)"/>
    <xsl:text>&#10;*/&#10;</xsl:text>
    <xsl:text>public interface </xsl:text>
    <xsl:value-of select="f:interface-name(concat($name-prefix, @name))"/>
    <xsl:text> extends </xsl:text>
    <xsl:value-of select="$super-qname"/>
    <xsl:text> {&#10;</xsl:text>
      <xsl:apply-templates mode="interface" select="mif:attribute[f:isMandatory(.)=$onlyMandatory]"/>
      <xsl:if test="not($onlyMandatory)">
        <xsl:apply-templates mode="interface" select="//mif:connections/node()[@participantClassName eq current()/@name]"/>
      </xsl:if>
    <xsl:text>}&#10;</xsl:text>
  </xsl:template>

  <xsl:template mode="interface" match="mif:attribute[mif:type]" name="make-interface-property">
    <xsl:param name="name" select="@name" as="xs:string"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)" as="xs:string"/>

    <xsl:text>&#10;  /**</xsl:text>
    <xsl:sequence select="f:javadoc-html(mif:annotations/mif:definition/mif:text)"/>
    <xsl:text>&#10;  */&#10;  </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $type-name)"/>
    <xsl:text>;&#10;</xsl:text>

    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see #get</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>&#10;  */&#10;  </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $type-name)"/>
    <xsl:text>;&#10;</xsl:text>
  </xsl:template>
 
  <xsl:template mode="interface" match="mif:connections/node()[@participantClassName]">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="proximal-end" select="."/>
    <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
    <xsl:variable name="maximumMultiplicity" select="f:maximumMultiplicity(.)" as="xs:string"/>
    <xsl:variable name="distal-interface-name" select="f:distal-interface-name($distal-end)"/>
    <xsl:variable name="name" select="$distal-end/@name"/>
    <xsl:variable name="type-name" select="if($maximumMultiplicity eq '1') then $distal-interface-name else concat('/*AssociationSet*/List&lt;',$distal-interface-name,'&gt;')"/>

    <xsl:call-template name="make-interface-property">
      <xsl:with-param name="name" select="$name"/>
      <xsl:with-param name="type-name" select="$type-name"/>
    </xsl:call-template>

    <xsl:if test="$maximumMultiplicity ne '1'">
      <xsl:text>  /** Adds an association </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>.&#10;      @see #add</xsl:text>
      <xsl:value-of select="f:capitalize($name)"/>
      <xsl:text>&#10;  */&#10;</xsl:text>
      <xsl:text>  void add</xsl:text>
      <xsl:value-of select="f:capitalize($name)"/>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$distal-interface-name"/>
      <xsl:text> </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>);&#10;</xsl:text>
    </xsl:if>
  </xsl:template>

  <!-- MODE: import - generate import directives -->
  
  <!-- deep null transform -->
  <xsl:template mode="import" match="/|@*|node()">
    <xsl:apply-templates mode="import" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="import" match="mif:class[@name]">
    <xsl:apply-templates mode="import" select="node()"/>
    <!-- This is a hack to deal with GTS -> QSET<TS> -->
      <xsl:variable name="type-references" select="mif:attribute/mif:type|mif:attribute/mif:type//mif:supplierBindingArgumentDatatype"/>
      <xsl:if test="$type-references[@name='GTS'] and not($type-references[@name='TS'])">
        <xsl:text>import </xsl:text>
        <xsl:value-of select="concat($data-types-interface-package,'.','TS')"/>
        <xsl:text>;&#10;</xsl:text>
      </xsl:if>
    <!-- END of hack to deal with GTS -->
    <xsl:text>&#10;</xsl:text>
    <xsl:apply-templates mode="import" select="//mif:connections/node()[@participantClassName eq current()/@name]"/>
  </xsl:template>

  <xsl:template mode="import" match="mif:attribute/mif:type|mif:attribute/mif:type//mif:supplierBindingArgumentDatatype">
    <xsl:variable name="type-name" select="f:import-data-type-name(.)"/>
    <xsl:if test="not(some $t in (../preceding-sibling::mif:attribute/mif:type|../preceding-sibling::mif:attribute/mif:type//mif:supplierBindingArgumentDatatype) satisfies f:import-data-type-name($t)=$type-name)">
      <xsl:text>import </xsl:text>
      <xsl:value-of select="concat($data-types-interface-package,'.',$type-name)"/>
      <xsl:text>;&#10;</xsl:text>
    </xsl:if>
    <xsl:apply-templates mode="import" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="import" match="mif:connections/node()[@participantClassName]">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="distal-end" select="../node()[@participantClassName ne $this-class-name]"/>
    <xsl:if test="not(preceding::mif:connections[mif:traversableConnection[@participantClassName=$this-class-name] and mif:traversableConnection[@participantClassName=$distal-end/@participantClassName]])">
      <xsl:variable name="cmet" select="f:is-cmet-name($distal-end/@participantClassName)"/>
      <xsl:if test="not($cmet)">
	      <xsl:text>import </xsl:text>
	      <xsl:value-of select="concat($interface-package,'.',f:interface-name($distal-end/@participantClassName))"/>
	      <xsl:text>;&#10;</xsl:text>
      </xsl:if>
    </xsl:if>
  </xsl:template>

  <!-- MODE: import-null - generate import directives for data type nulls -->
  
  <!-- deep null transform -->
  <xsl:template mode="import-null" match="/|@*|node()">
    <xsl:apply-templates mode="import-null" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="import-null" match="mif:attribute/mif:type|mif:attribute/mif:type//mif:supplierBindingArgumentDatatype">
    <xsl:variable name="type-name" select="f:null-data-type-name(.)"/>
    <xsl:if test="not(some $t in (../preceding-sibling::mif:attribute/mif:type|../preceding-sibling::mif:attribute/mif:type//mif:supplierBindingArgumentDatatype) satisfies f:null-data-type-name($t)=$type-name)">
      <xsl:text>import </xsl:text>
      <xsl:value-of select="concat($data-types-impl-package,'.',$type-name)"/>
      <xsl:text>;&#10;</xsl:text>
    </xsl:if>
    <xsl:apply-templates mode="import-null" select="@*|node()"/>
  </xsl:template>

  <!-- MODE: bean - generate Java bean -->

  <!-- deep null transform -->
  <xsl:template mode="bean" match="/|@*|node()">
    <xsl:apply-templates mode="bean" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="bean" match="mif:class[@name]">
    <xsl:param name="name-prefix" select="''" as="xs:string"/>
    <xsl:param name="onlyMandatory" select="false()" as="xs:boolean"/>
    <xsl:param name="super-prefix" select="''" as="xs:string"/>
    
    <xsl:param name="parent" select="//mif:class[mif:specializationChild/@childClassName=current()/@name]"/>
    <xsl:param name="parent-name" select="if(@name='InfrastructureRoot') then 'RimObject' else if($parent) then $parent/@name else 'InfrastructureRoot'"/>
    <xsl:param name="super-qname" select="if($super-prefix ne '') then concat($super-prefix, f:bean-name(@name)) else if(//mif:class[@name eq $parent-name]) then concat($bean-package,'.', $super-prefix, f:bean-name($parent-name)) else concat($rim-impl-package,'.',$super-prefix, f:bean-name($parent-name))"/>

    <xsl:text>/*&#10;</xsl:text>
    <xsl:value-of select="$copyright-statement"/>
    <xsl:text>&#10;*/&#10;</xsl:text>
    <xsl:text>package </xsl:text>
    <xsl:value-of select="$bean-package"/>
    <xsl:text>;&#10;&#10;</xsl:text>
    <xsl:text>import org.hibernate.Criteria;&#10;</xsl:text>
    <xsl:text>import org.hl7.hibernate.Persistence;&#10;&#10;</xsl:text>

    <!-- import the interface for this class -->
    <xsl:value-of select="concat('import ', $interface-package,'.',$name-prefix, f:interface-name(@name), ';&#10;')"/>

    <xsl:if test="$bean-package ne $rim-impl-package">
      <xsl:text>import org.hl7.rim.impl.AssociationSetImpl;&#10;</xsl:text>
    </xsl:if>
    
    <xsl:if test="contains($super-qname, '.')">
      <xsl:value-of select="concat('import ', $super-qname, ';&#10;')"/>
    </xsl:if>

    <xsl:apply-templates mode="import" select="."/>
    <xsl:text>import /*org.hl7.rim.AssociationSet*/java.util.List;</xsl:text>
    <xsl:text>&#10;&#10;</xsl:text>
    <xsl:text>/** Implementation of </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name(@name))"/> 
    <xsl:text> as a simple data holder bean.&#10;    @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name(@name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>public</xsl:text>
    <xsl:if test="@isAbstract eq 'true'">
      <xsl:text> abstract</xsl:text>
    </xsl:if>
    <xsl:text> class </xsl:text>
    <xsl:value-of select="concat($name-prefix, f:bean-name(@name))"/>
    <xsl:text> extends </xsl:text>
    <xsl:value-of select="$super-qname"/>
    <xsl:text> implements </xsl:text>
    <xsl:value-of select="concat($name-prefix, f:interface-name(@name))"/>
    <xsl:text> {&#10;</xsl:text>
      <xsl:apply-templates mode="bean" select="mif:attribute[f:isMandatory(.)=$onlyMandatory]"/>
      <xsl:if test="not($onlyMandatory)">
        <xsl:apply-templates mode="bean" select="//mif:connections/node()[@participantClassName eq current()/@name]"/>
      </xsl:if>
      <xsl:text>  public Object clone() throws CloneNotSupportedException {&#10;    </xsl:text>
      <xsl:value-of select="concat($name-prefix, f:bean-name(@name), ' that = (', $name-prefix, f:bean-name(@name), ') super.clone();&#10;')"/>
      <xsl:text>&#10;    // deep clone of persistent component collections&#10;</xsl:text>
        <xsl:apply-templates mode="clonefix" select="mif:attribute[f:isMandatory(.)=$onlyMandatory]"/>
        <xsl:if test="not($onlyMandatory)">
          <xsl:apply-templates mode="clonefix" select="//mif:connections/node()[@participantClassName eq current()/@name]"/>
        </xsl:if>
      <xsl:text>    return that;&#10;  }&#10;</xsl:text>
    <xsl:text>}&#10;</xsl:text>
  </xsl:template>

  <xsl:template mode="bean" match="mif:attribute[mif:type]" name="make-bean-property">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>

    <xsl:value-of select="f:field-decl($name, $type-name)"/>

    <xsl:text>  /** Gets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $type-name)"/>
    <xsl:text> { return _</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>; }&#10;</xsl:text>

    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $type-name)"/>
    <xsl:text> {&#10;</xsl:text>
    <xsl:text>    if(</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text> instanceof org.hl7.hibernate.ClonableCollection)&#10;      </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text> = ((org.hl7.hibernate.ClonableCollection&lt;</xsl:text>
    <xsl:value-of select="$type-name"/>
    <xsl:text>>) </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>).cloneHibernateCollectionIfNecessary();&#10;    _</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text> = </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>;&#10;  }&#10;</xsl:text>

    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>. To be used only by hibernate.&#10;      @deprecated to be used only by hibernate&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public void set</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>ForHibernate</xsl:text>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$type-name"/>
    <xsl:text> </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>) {&#10;    _</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text> = </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>;&#10;  }&#10;</xsl:text>
  </xsl:template>

  <xsl:template mode="bean" match="mif:connections/node()[@participantClassName]">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="proximal-end" select="."/>
    <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
    <xsl:variable name="maximumMultiplicity" select="f:maximumMultiplicity(.)" as="xs:string"/>
    <xsl:variable name="distal-interface-name" select="f:distal-interface-name($distal-end)"/>
    <xsl:variable name="name" select="$distal-end/@name"/>
    <xsl:variable name="inverse-name" select="$proximal-end/@name"/>
    <xsl:variable name="type-name" select="if($maximumMultiplicity eq '1') then $distal-interface-name else concat('/*AssociationSet*/List&lt;',$distal-interface-name,'&gt;')"/>

    <xsl:value-of select="f:field-decl($name, $type-name)"/>

    <xsl:text>  /** Gets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $type-name)"/>
    <xsl:text> {&#10;</xsl:text>
    <xsl:text>    return _</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>;&#10;  }&#10;</xsl:text>
    
    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $type-name)"/>
    <xsl:text> {&#10;</xsl:text> 
    <xsl:text>    _</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text> = </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>;&#10;</xsl:text>

    <xsl:text>  }&#10;</xsl:text>

    <xsl:apply-templates mode="bean-adder" select="."/>
     
  </xsl:template>

  <xsl:template mode="bean-adder" match="mif:connections/node()[@participantClassName][f:maximumMultiplicity(.) ne '1']">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="proximal-end" select="."/>
    <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
    <xsl:variable name="maximumMultiplicity" select="f:maximumMultiplicity(.)" as="xs:string"/>
    <xsl:variable name="distal-interface-name" select="f:distal-interface-name($distal-end)"/>
    <xsl:variable name="name" select="$distal-end/@name"/>
    <xsl:variable name="inverse-name" select="$proximal-end/@name"/>
    <xsl:variable name="type-name" select="if($maximumMultiplicity eq '1') then $distal-interface-name else concat('/*AssociationSet*/List&lt;',$distal-interface-name,'&gt;')"/>

      <xsl:text>  /** Adds an association </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>.&#10;      @see </xsl:text>
      <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#set',f:capitalize($name))"/>
      <xsl:text>&#10;  */&#10;</xsl:text>
      <xsl:text>  public void add</xsl:text>
      <xsl:value-of select="f:capitalize($name)"/>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$distal-interface-name"/>
      <xsl:text> </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>) {&#10;    </xsl:text> 

      <xsl:text>    // create the association set if it doesn't exist already&#10;</xsl:text>
      <xsl:text>    if(_</xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text> == null) _</xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text> = new AssociationSetImpl&lt;</xsl:text>    
      <xsl:value-of select="$distal-interface-name"/>
      <xsl:text>&gt;();&#10;</xsl:text>

      <xsl:text>    // add the association to the association set&#10;</xsl:text>
      <xsl:text>    get</xsl:text>
      <xsl:value-of select="f:capitalize($name)"/>
      <xsl:text>().add(</xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>);&#10;</xsl:text>

      <xsl:if test="$distal-end/@maximumMultiplicity eq '1'">
        <xsl:text>    // make the inverse link&#10;    </xsl:text>
        <xsl:value-of select="$name"/>
        <xsl:text>.set</xsl:text>
        <xsl:value-of select="f:capitalize($inverse-name)"/>
        <xsl:text>(this);&#10;</xsl:text>
      </xsl:if>

      <xsl:text>  }&#10;</xsl:text>
  </xsl:template>
  
  <!-- MODE: decorator - generate Java decorator -->

  <!-- deep null transform -->
  <xsl:template mode="decorator" match="/|@*|node()">
    <xsl:apply-templates mode="decorator" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="decorator" match="mif:class[@name]">
    <xsl:param name="name-prefix" select="''" as="xs:string"/>
    <xsl:param name="onlyMandatory" select="false()" as="xs:boolean"/>
    <xsl:param name="super-prefix" select="''" as="xs:string"/>

    <xsl:variable name="parent" select="//mif:class[mif:specializationChild/@childClassName=current()/@name]"/>
    <xsl:variable name="parent-name" select="if(@name='InfrastructureRoot') then 'RimObject' else if($parent) then $parent/@name else 'InfrastructureRoot'"/>
    <xsl:variable name="super-qname" select="if($super-prefix ne '') then concat($super-prefix, f:decorator-name(@name)) else if(//mif:class[@name eq $parent-name]) then concat($decorator-package,'.', $super-prefix, f:decorator-name($parent-name)) else concat($rim-impl-package,'.',$super-prefix, f:bean-name($parent-name))"/>

    <xsl:text>/*&#10;</xsl:text>
    <xsl:value-of select="$copyright-statement"/>
    <xsl:text>&#10;*/&#10;</xsl:text>
    <xsl:text>package </xsl:text>
    <xsl:value-of select="$decorator-package"/>
    <xsl:text>;&#10;&#10;</xsl:text>
    <xsl:text>import </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',$name-prefix,f:interface-name(@name))"/>
    <xsl:text>;&#10;</xsl:text>
    
    <xsl:if test="contains($super-qname, '.')">
      <xsl:value-of select="concat('import ', $super-qname, ';&#10;')"/>
    </xsl:if>

    <xsl:apply-templates mode="import" select="."/>
    <xsl:apply-templates mode="import-null" select="."/>

    <xsl:text>import /*org.hl7.rim.AssociationSet*/java.util.List;</xsl:text>
    <xsl:text>&#10;import java.util.Collections;</xsl:text>
    <xsl:text>&#10;&#10;</xsl:text>
    <xsl:text>/** Implementation of </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name(@name))"/> 
    <xsl:text> as an abstract decorator, i.e., a class that returns NULL/NA or nothing for all property accessprs amd that raises UnsupportedOperationExceptions for all mutators. This is used to adapt custom application classes to the RIM class interfaces and only bother about mapping those properties that actually apply to the application class. This can be done in one of two ways: (1) the client class can extend the decorator directly, and implement the applicable properties, or (2) the abstract decorator can be extend to a concrete decorator, which would hold a reference to the client object and method bodies to delegate and adapt the applicable properties.&#10; @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name(@name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>public abstract class </xsl:text>
    <xsl:value-of select="concat($name-prefix, f:decorator-name(@name))"/>
    <xsl:text> extends </xsl:text>
    <xsl:value-of select="$super-qname"/>
    <xsl:text> implements </xsl:text>
    <xsl:value-of select="concat($name-prefix, f:interface-name(@name))"/>
    <xsl:text> {&#10;</xsl:text>
      <xsl:apply-templates mode="decorator" select="mif:attribute[f:isMandatory(.)=$onlyMandatory]"/>
      <xsl:if test="not($onlyMandatory)">
        <xsl:apply-templates mode="decorator" select="//mif:connections/node()[@participantClassName eq current()/@name]"/>
      </xsl:if>
    <xsl:text>}&#10;</xsl:text>
  </xsl:template>

  <xsl:template mode="decorator" match="mif:attribute[mif:type]" name="make-decorator-property">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>
    <xsl:param name="null-name" select="f:null-data-type-name(mif:type)"/>

    <xsl:text>  /** Property accessor, returns NULL/NA if not overloaded.</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $type-name)"/>
    <xsl:text> { return </xsl:text>
    <xsl:value-of select="$null-name"/>
    <xsl:text>.NI; /* should be NA, but that causes trouble for string-literal hibernated properties. */ }&#10;</xsl:text>

    <!-- xsl:text>  /** Property mutator, thows UnsupportedOperationException if not overloaded.</xsl:text -->
    <xsl:text>  /** Property mutator, does nothing if not overloaded.</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $type-name)"/>
    <xsl:text> { /*throw new UnsupportedOperationException();*/ }&#10;</xsl:text>
  </xsl:template>

  <xsl:template mode="decorator" match="mif:connections/node()[@participantClassName]">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="proximal-end" select="."/>
    <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
    <xsl:variable name="maximumMultiplicity" select="f:maximumMultiplicity(.)" as="xs:string"/>
    <xsl:variable name="distal-interface-name" select="f:distal-interface-name($distal-end)"/>
    <xsl:variable name="name" select="$distal-end/@name"/>
    <xsl:variable name="inverse-name" select="$proximal-end/@name"/>
    <xsl:variable name="type-name" select="if($maximumMultiplicity eq '1') then $distal-interface-name else concat('/*AssociationSet*/List&lt;',$distal-interface-name,'&gt;')"/>

    <xsl:text>  /** Property accessor, returns </xsl:text>
    <xsl:choose>
      <xsl:when test="$maximumMultiplicity ne '1'">
	<xsl:text>an empty collection</xsl:text>
      </xsl:when>
      <xsl:otherwise>
	<xsl:text>null</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text> if not overloaded.</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="$type-name"/>
    <xsl:text> get</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>() { return </xsl:text>
    <xsl:choose>
      <xsl:when test="$maximumMultiplicity ne '1'">
	<xsl:text>Collections.EMPTY_LIST</xsl:text>
      </xsl:when>
      <xsl:otherwise>
	<xsl:text>null</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text>; }&#10;</xsl:text>
    
    <!--xsl:text>  /** Property mutator, thows UnsupportedOperationException if not overloaded.</xsl:text-->
    <xsl:text>  /** Property mutator, does nothing if not overloaded.</xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public void set</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$type-name"/>
    <xsl:text> </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>) { /* throw new UnsupportedOperationException(); */ }&#10;</xsl:text>

    <xsl:if test="$maximumMultiplicity ne '1'">
      <xsl:text>  /** Association adder, throws UnsupportedOperationException if not overloaded </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>.&#10;      @see </xsl:text>
      <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#set',f:capitalize($name))"/>
      <xsl:text>&#10;  */&#10;</xsl:text>
      <xsl:text>  public void add</xsl:text>
      <xsl:value-of select="f:capitalize($name)"/>
      <xsl:text>(</xsl:text>
      <xsl:value-of select="$distal-interface-name"/>
      <xsl:text> </xsl:text>
      <xsl:value-of select="$name"/>
      <xsl:text>) { throw new UnsupportedOperationException(); }&#10;</xsl:text>
    </xsl:if>
  </xsl:template>

  <!-- MODE: clonefix - generate code which fixes what comes from super.clone -->
  
  <!-- deep null transform -->
  <xsl:template mode="clonefix" match="/|@*|node()">
    <xsl:apply-templates mode="clonefix" select="@*|node()"/>
  </xsl:template>

  <xsl:template mode="clonefix" match="mif:class"/>

  <xsl:template mode="clonefix" match="mif:connections/mif:traversableConnection[@participantClassName]">
     <xsl:variable name="this-class-name" select="@participantClassName"/>
     <xsl:variable name="proximal-end" select="."/>
     <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
     <xsl:variable name="name" select="$distal-end/@name"/>
     
     <xsl:if test="$distal-end and $proximal-end/@maximumMultiplicity ne '1'">
	<xsl:text>    that._</xsl:text>
	<xsl:value-of select="$name"/>
	<xsl:text>= null;&#10;</xsl:text>
     </xsl:if>
  </xsl:template>

  <xsl:template mode="clonefix" match="mif:attribute[mif:type/@name=('SET','LIST','BAG')]">
    <xsl:param name="name" select="@name"/>
    <xsl:param name="type-name" select="f:data-type-name(mif:type)"/>

    <xsl:text>    that.set</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>(that.get</xsl:text>
    <xsl:value-of select="f:capitalize($name)"/>
    <xsl:text>());&#10;</xsl:text>
  </xsl:template>

  <!-- CREATE JAVADOC HTML -->

  <xsl:function name="f:javadoc-html">
    <xsl:param name="text"/>
    <xsl:variable name="html">
      <xsl:apply-templates mode="javadoc-html" select="$text"/>
    </xsl:variable>
    <xsl:sequence select="saxon:serialize($html,'html')"/>
  </xsl:function>

  <!-- MODE: javadoc-html -->
  <xsl:template mode="javadoc-html" match="/|@*|node()">
    <xsl:copy>
      <xsl:apply-templates mode="javadoc-html" select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <!-- remove the mif: namespace -->
  <xsl:template mode="javadoc-html" match="*" priority="0">
    <xsl:element name="{local-name(.)}">
      <xsl:apply-templates mode="javadoc-html" select="@*|node()"/>
    </xsl:element>
  </xsl:template>
  <xsl:template mode="javadoc-html" match="mif:text" priority="1">
    <xsl:apply-templates mode="javadoc-html" select="node()"/>
  </xsl:template>
  <xsl:template mode="javadoc-html" match="text()" priority="0">
    <xsl:sequence select="replace(.,'\s+',' ')"/>
  </xsl:template>

</xsl:transform>

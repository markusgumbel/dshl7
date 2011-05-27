<?xml version="1.0" encoding="UTF-8"?>
<!-- dmif2java.xsl - Java domain objects from MIF domain content.

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
  <xsl:import href="mif2java.xsl"/>
  <xsl:import href="rim-func.xsl"/>
  <xsl:param name="name-prefix" select="'Basic'"/>
  <xsl:param name="bean-suffix" select="'Impl'"/>
  <xsl:variable name="cloneCodeSystem" select="'cloneCodeSystemId'" as="xs:string"/>

  <!-- Override imported implementation -->
  <xsl:function name="f:maximumMultiplicity" as="attribute()">
    <xsl:param name="proximal-end" as="element()"/>
    <xsl:sequence select="$proximal-end/../mif:traversableConnection/@maximumMultiplicity"/>
  </xsl:function>

  <xsl:function name="f:dmif2rim-attribute">
    <xsl:param name="dmif-attribute" as="element()"/>
    <xsl:variable name="rim-class-name" select="$dmif-attribute/mif:derivationSupplier[1]/@className"/>
    <xsl:sequence select="f:rim-attribute(f:rim-class($rim-class-name), $dmif-attribute/@name)"/>
  </xsl:function>
  
  <!-- overridden from mif2java.xsl -->
  <xsl:function name="f:isMandatory" as="xs:boolean">
    <xsl:param name="attribute" as="element()"/>
    <xsl:variable name="rim-attribute" select="f:dmif2rim-attribute($attribute)" as="element()"/>
    <xsl:value-of select="$rim-attribute/@isMandatory"/>
  </xsl:function>
  
  <xsl:function name="f:distal-interface-name" as="xs:string">
    <xsl:param name="distal-end" as="element()"/>
    <!-- 
      - If the distal end is only a single cmet, we can use that cmet's type. 
      - Otherwise, if the distal end is a choice between multiple cmets, or a cmet and a class in this MIF, 
      - then we have to default to an InfrastructureRoot, because this is the only common base 
      - class that satisfies two different cmets, or both a cmet and an internal class.
      - If the distal end is a choice of multiple classes within this MIF, that's OK, because
      - a XXXChoice class class will be created that is a base class for the choice.
      -->
    <xsl:variable name="first-cmet-name" select="$distal-end/mif:participantClassSpecialization[f:is-cmet-name(@className)][1]/@className"/>
    <xsl:variable name="no-common-base-class" select="$first-cmet-name and $distal-end/mif:participantClassSpecialization[@className ne $first-cmet-name]" as="xs:boolean"/>
    <xsl:choose>
      <xsl:when test="$no-common-base-class">
        <!-- The association is to either two different CMETS or a CMET and a local class -->
        <xsl:variable name="distal-rim-class-name" select="$distal-end/ancestor::mif:staticModel//mif:commonModelElementRef[@name=$first-cmet-name]/mif:derivationSupplier[1]/@className"/>
        <xsl:value-of select="concat($rim-base-package, '.', $name-prefix, f:root-rim-class(f:rim-class($distal-rim-class-name))/@name)"/>
      </xsl:when>
      <xsl:otherwise>
        <!-- The association is to either a single CMET or a local class -->
        <xsl:variable name="distal-participant" select="$distal-end/@participantClassName"/>
        <xsl:choose> 
          <xsl:when test="f:is-cmet-name($distal-participant)">
            <!-- The association is to a CMET -->
            <xsl:variable name="cmet" select="$cmets//mif:ownedCommonModelElement[@name eq $distal-participant]" as="element()*"/>
            <xsl:if test="not($cmet)">
              <xsl:message>WARNING: cmetinfo.coremif does not define cmet: <xsl:value-of select="$distal-participant"/> defaulting to <xsl:value-of select="$distal-end/mif:derivationSupplier[1]/@className"/></xsl:message>
            </xsl:if>
            <xsl:variable name="undefined-entry-class" select="not($cmet) or boolean($cmet/mif:specializationChildEntryClass/mif:specializationClass[f:is-cmet-name(@name)])" as="xs:boolean"/>
            <xsl:choose>
              <xsl:when test="$undefined-entry-class">
                <!-- CMETs that have choice entry points to another CMET may not be handled well here -->
                <xsl:variable name="distal-rim-class-name" select="$distal-end/mif:derivationSupplier[1]/@className"/>
                <xsl:value-of select="concat($rim-base-package, '.', $name-prefix, f:root-rim-class(f:rim-class($distal-rim-class-name))/@name)"/>
              </xsl:when>
              <xsl:otherwise>
                <!-- CMET has a non-choice entry point -->
                <xsl:variable name="csm" select="$cmet/mif:specializationChildStaticModel"/>
                <xsl:variable name="cmet-name" select="concat($csm/@subSection, $csm/@domain, '_MT', $csm/@id, $csm/@realm)"/>
                <xsl:sequence select="concat('org.hl7.', $cmet-name, '.', $cmet/mif:specializationChildEntryClass/@name)"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:when>
          <xsl:otherwise>
            <!-- The association is to a class defined in this model -->
            <xsl:value-of select="f:qualify-participant($distal-participant)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!-- in a dmif we only want to process nonTraversableConnections (because they have a traversableConnection on the other end) -->  
  <xsl:template mode="bean" match="mif:connections/mif:traversableConnection[@participantClassName]" priority="1"/>
  <xsl:template mode="interface" match="mif:connections/mif:traversableConnection[@participantClassName]" priority="1"/>
  <xsl:template mode="decorator" match="mif:connections/mif:traversableConnection[@participantClassName]" priority="1"/>

  <!-- class to generate -->
  <xsl:template match="mif:class[@name and not(ends-with(@name,'Heir'))]">
    <xsl:variable name="this-class-name" select="@name"/>
    <xsl:variable name="owning-choice" select="//mif:class[mif:specializationChild/@childClassName=$this-class-name]" as="element()*"/>
    <xsl:variable name="owning-choice-has-cmet" select="boolean($owning-choice/mif:specializationChild[f:is-cmet-name(@childClassName)])" as="xs:boolean"/>
    <xsl:variable name="root-rim-class" select="f:root-rim-class(f:rim-class(mif:derivationSupplier[1]/@className))/@name" as="xs:string"/>

    <xsl:result-document href="{$interface-path}/{f:interface-name(@name)}.java" format="java">
      <xsl:apply-templates mode="interface" select=".">
        <xsl:with-param name="super-qname" select="if(not($owning-choice) or $owning-choice-has-cmet) then concat($rim-base-package, '.', $name-prefix, $root-rim-class) else concat($interface-package, '.', $owning-choice/@name)"/>
      </xsl:apply-templates>
    </xsl:result-document>
    <xsl:result-document href="{$bean-path}/{f:bean-name(@name)}.java" format="java">
      <xsl:apply-templates mode="bean" select=".">
        <xsl:with-param name="super-qname" select="if(not($owning-choice) or $owning-choice-has-cmet) then concat($rim-impl-package, '.', $name-prefix, $root-rim-class, $bean-suffix) else concat($bean-package, '.', $owning-choice/@name, $bean-suffix)"/>
      </xsl:apply-templates>
    </xsl:result-document>
  </xsl:template>

  <xsl:template mode="bean" match="mif:attribute[mif:type]" name="make-bean-property">
    <xsl:param name="class-name" select="ancestor::mif:class/@name"/>
    <xsl:param name="name" select="@name"/>
    
    <xsl:variable name="constrained-type-name" select="f:data-type-name(mif:type)" as="xs:string"/>
    <xsl:variable name="constrained-type-qname" select="concat($data-types-interface-package, '.', $constrained-type-name)" as="xs:string"/>
    <xsl:variable name="constrained-cardinality" select="@maximumMultiplicity" as="xs:string"/>
    <xsl:variable name="rim-class-name" select="mif:derivationSupplier[1]/@className"/>
    <xsl:variable name="rim-attribute" select="f:rim-attribute(f:rim-class($rim-class-name), $name)" as="element()"/>
    <xsl:variable name="isGTS" select="$rim-attribute/mif:type/@name eq 'GTS'" as="xs:boolean"/>
    <xsl:variable name="raw-rim-type-name" select="if($isGTS) then 'SET' else $rim-attribute/mif:type/@name" as="xs:string"/>
    <xsl:variable name="rim-type-name" select="f:data-type-name($rim-attribute/mif:type)" as="xs:string"/>
    <xsl:variable name="rim-cardinality" select="$rim-attribute/@maximumMultiplicity" as="xs:string"/>
    <xsl:variable name="cardinality-constrained" select="($constrained-cardinality eq '1' and $rim-cardinality ne '1' and $rim-type-name ne 'ANY') or ($rim-type-name eq 'SET&lt;TS>' and $constrained-type-name eq 'TS')" as="xs:boolean"/>
    <xsl:variable name="field-type-name" select="if($cardinality-constrained) then concat($raw-rim-type-name, '&lt;', $constrained-type-qname, '>') else $constrained-type-name"/>
    <xsl:variable name="field-type-qname" select="concat($data-types-interface-package, '.', $field-type-name)"/>
    <xsl:variable name="field-name" select="concat('_', $name)"/>
    
    <!-- Some convenience variables for cardinality-constrained methods -->
    <xsl:variable name="first-element" select="if($raw-rim-type-name eq 'SET') then 'any()' else 'iterator().next()'"/>
    <xsl:variable name="java-util-iface" select="if($raw-rim-type-name eq 'SET') then 'Set' else 'List'"/>
    <xsl:variable name="java-util-impl" select="if($raw-rim-type-name eq 'SET') then 'LinkedHashSet' else 'ArrayList'"/>

    <xsl:value-of select="f:field-decl($name, $field-type-qname)"/>

    <xsl:text>  /** Gets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $constrained-type-qname)"/>
    <xsl:text>  { &#10;</xsl:text>
    <xsl:choose>
        <xsl:when test="$cardinality-constrained">
            <xsl:value-of select="concat('    if(', $field-name, ' == null || ', $field-name, '.isNull().isTrue() || ', $field-name, '.isEmpty().isTrue()) {&#10;')"/>
            <xsl:value-of select="concat('      return ', $data-types-impl-package, '.', $constrained-type-name, 'null.NI;&#10;')"/>
            <xsl:text>    } else {&#10;</xsl:text>
            <xsl:value-of select="concat('      return ', $field-name, '.', $first-element, ';&#10;')"/>
            <xsl:text>    }&#10;</xsl:text>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat('    return ', $field-name, ';&#10;')"/>
        </xsl:otherwise>
    </xsl:choose>
    <xsl:text>  }&#10;</xsl:text>

    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $constrained-type-qname)"/>
    <xsl:text>  {&#10;</xsl:text>
    <xsl:choose>
      <xsl:when test="$cardinality-constrained">
        <xsl:value-of select="concat('    if(', $name, ' == null || ', $name, '.isNull().isTrue()) {&#10;')"/>
        <xsl:choose>
          <xsl:when test="$isGTS">
            <xsl:value-of select="concat('      ', $field-name, ' = ', $data-types-impl-package, '.QSETnull.NI;&#10;')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="concat('      ', $field-name, ' = ', $data-types-impl-package, '.', $raw-rim-type-name, 'null.NI;&#10;')"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>    } else {&#10;</xsl:text>
        <xsl:choose>
          <xsl:when test="$isGTS">
            <xsl:value-of select="concat('      ', $field-name, ' = ', $data-types-impl-package, '.QSETSingularityImpl.valueOf(', $name, ');&#10;')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="concat('      java.util.', $java-util-iface, '&lt;', $constrained-type-qname, '> value = new java.util.', $java-util-impl, '&lt;', $constrained-type-qname, '>();&#10;')"/>
            <xsl:value-of select="concat('      value.add(', $name, ');&#10;')"/>
            <xsl:value-of select="concat('      ', $field-name, ' = ', $data-types-impl-package, '.', $raw-rim-type-name, 'ju', $java-util-iface, 'Adapter.valueOf(value);&#10;')"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>    }&#10;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="concat('    if(', $name, ' instanceof org.hl7.hibernate.ClonableCollection)&#10;')"/>
        <xsl:value-of select="concat('      ', $name, ' = ((org.hl7.hibernate.ClonableCollection&lt;', $constrained-type-qname, '>) ', $name, ').cloneHibernateCollectionIfNecessary();&#10;')"/>
        <xsl:value-of select="concat('    ', $field-name, ' = ', $name, ';&#10;')"/>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text>  }&#10;</xsl:text>
  </xsl:template>

    <!-- Auto generate java code to set the cloneCode of an association's target(s)
      -->
  <xsl:template mode="autoset-clone-code" match="mif:nonTraversableConnection[@participantClassName]">
    <xsl:param name="name" as="xs:string"/>
    <xsl:param name="distal-interface-name" as="xs:string"/>
    <xsl:param name="collection" as="xs:boolean"/>
    <xsl:param name="traversableConnection" as="node()"/>
    <xsl:variable name="item" select="if($collection) then 'item' else $name" as="xs:string"/>

    <!-- Get out if the association is being set to null. -->
    <xsl:value-of select="concat('    if (', $name, ' == null) return;&#10;')"/> 

    <!-- loop through all the targets on a collection -->
    <xsl:if test="$collection">
        <xsl:value-of select="concat('    for (', $distal-interface-name, ' item : ', $name, ')&#10;  ' )"/>
    </xsl:if>

    <xsl:choose>
        <!-- If it is a choice target, try all the specializations we know about -->
        <xsl:when test="$traversableConnection/mif:participantClassSpecialization">
            <xsl:text>// Find the correct specialization of a choice target and use its traversal name&#10;</xsl:text>
            <xsl:value-of select="concat('    if (', $item, '.getCloneCode() != null) {}&#10;')"/>
            <xsl:for-each select="$traversableConnection/mif:participantClassSpecialization">
                <xsl:value-of select="concat('    else if (', $item, '.getClass().getSimpleName().equals(&quot;', @className, 'Impl&quot;))')"/>
                <xsl:value-of select="concat($item, '.setCloneCode(org.hl7.types.impl.CSimpl.valueOf(&quot;', @traversalName, '&quot;, &quot;2.16.840.1.113883.5.6&quot;));&#10;')"/>
            </xsl:for-each>
            <xsl:value-of select="concat('    else java.util.logging.Logger.getLogger(', $item)"/>
            <xsl:value-of select="concat('.getClass().getName()).warning(&quot;Unable to automatically set cloneClass for &quot; + ', $item)"/>
            <xsl:text>.getClass().getName() + " as the target of the association </xsl:text>
            <xsl:value-of select="concat($base-package, '.', $traversableConnection/../mif:nonTraversableConnection/@participantClassName, '.', $name)"/>
            <xsl:text>");&#10;</xsl:text>
        </xsl:when>
                
        <xsl:otherwise>
            <xsl:value-of select="concat('    ', $item, '.setCloneCode(org.hl7.types.impl.CSimpl.valueOf(&quot;', $name, '&quot;, &quot;2.16.840.1.113883.5.6&quot;));&#10;')"/>
        </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- Deep search for all possible class names for this association 
  <xsl:function name="f:connection-class-qnames" as="xs:string*">
    <xsl:param name="traversableConnection" as="element()"/>
    <xsl:variable name="pcs" select="for $pcs in $traversableConnection/mif:participantClassSpecialization return $pcs"/>
    <xsl:value-of select="for $pcs in $traversableConnection/mif:participantClassSpecialization return f:class-for-specialization($pcs)"/>
  </xsl:function>
  
  <xsl:function name="f:class-for-specialization" as="xs:string*">
    <xsl:param name="pcs" as="element()"/>
    <xsl:value-of select="return if(f:is-cmet-name($pcs/@className)) then return f:classes-for-cmet($pcs/@className) else if(f:is-choice($pcs)) return f:class-for-choice($pcs) else return $pcs/@className"/>
  </xsl:function>-->
  
  <xsl:template mode="bean" match="mif:connections/mif:nonTraversableConnection[@participantClassName]">
    <xsl:variable name="this-class-name" select="@participantClassName"/>
    <xsl:variable name="proximal-end" select="."/>
    <xsl:variable name="distal-end" select="../mif:traversableConnection[@participantClassName ne $this-class-name]"/>
    <xsl:variable name="constrained-cardinality" select="f:maximumMultiplicity(.)" as="xs:string"/>
    <xsl:variable name="rim-cardinality" select="f:rim-association-cardinality(ancestor::mif:connections)" as="xs:string"/>
    <xsl:variable name="is-cardinality-constrained" select="$rim-cardinality eq '*' and $constrained-cardinality eq '1'" as="xs:boolean"/>
    <xsl:variable name="distal-interface-name" select="f:distal-interface-name($distal-end)"/>
    <xsl:variable name="name" select="$distal-end/@name" as="xs:string"/>
    <xsl:variable name="field-name" select="concat('_', $name)"/>
    <xsl:variable name="inverse-name" select="$proximal-end/@name"/>
    <xsl:variable name="field-type-name" select="if($rim-cardinality eq '1') then $distal-interface-name else concat('/*AssociationSet*/java.util.List&lt;',$distal-interface-name,'>')"/>
    <xsl:variable name="constrained-type-name" select="if($is-cardinality-constrained) then $distal-interface-name else $field-type-name"/>

    <xsl:value-of select="f:field-decl($name, $field-type-name)"/>

    <xsl:text>  /** Gets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#get',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:getter-method-sig($name, $constrained-type-name)"/>
    <xsl:text>  {&#10;</xsl:text>
    <xsl:choose>
      <xsl:when test="$is-cardinality-constrained">
        <xsl:value-of select="concat('    if(', $field-name, ' == null || ', $field-name, '.isEmpty()) return null;&#10;')"/>
        <xsl:value-of select="concat('    else return ', $field-name, '.get(0);&#10;')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="concat('    return ', $field-name, ';&#10;')"/>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text>  }&#10;</xsl:text>
    
    <xsl:text>  /** Sets the property </xsl:text>
    <xsl:value-of select="$name"/>
    <xsl:text>.&#10;      @see </xsl:text>
    <xsl:value-of select="concat($interface-package,'.',f:interface-name($this-class-name),'#set',f:capitalize($name))"/>
    <xsl:text>&#10;  */&#10;</xsl:text>
    <xsl:text>  public </xsl:text>
    <xsl:value-of select="f:setter-method-sig($name, $constrained-type-name)"/>
    <xsl:text>  {&#10;</xsl:text> 
    <xsl:choose>
      <xsl:when test="$is-cardinality-constrained">
        <xsl:value-of select="concat('    if(', $name, ' == null) {&#10;')"/>
        <xsl:value-of select="concat('      ', $field-name, ' = null;&#10;')"/>
        <xsl:text>    } else {&#10;</xsl:text>
        <xsl:value-of select="concat('      ', $field-name, ' = new AssociationSetImpl&lt;', $distal-interface-name, '>();&#10;')"/>
        <xsl:value-of select="concat('      ', $field-name, '.add(', $name, ');&#10;')"/>
        <xsl:text>    }&#10;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="concat('    ', $field-name, ' = ', $name, ';&#10;')"/>
      </xsl:otherwise>
    </xsl:choose>

    <xsl:apply-templates mode="autoset-clone-code" select=".">
      <xsl:with-param name="name" select="$name"/>
      <xsl:with-param name="distal-interface-name" select="$distal-interface-name"/>
      <xsl:with-param name="collection" select="$constrained-cardinality ne '1'"/>
      <xsl:with-param name="traversableConnection" select="../mif:traversableConnection"/>    
    </xsl:apply-templates>

    <xsl:text>  }&#10;</xsl:text>

    <xsl:if test="not($is-cardinality-constrained)">
      <xsl:apply-templates mode="bean-adder" select="."/>
    </xsl:if>
     
  </xsl:template>

  <xsl:template mode="bean-adder" match="mif:connections/mif:nonTraversableConnection[@participantClassName][f:maximumMultiplicity(.) ne '1']">
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

      <xsl:apply-templates mode="autoset-clone-code" select=".">
        <xsl:with-param name="name" select="$name"/>
        <xsl:with-param name="distal-interface-name" select="$distal-interface-name"/>
        <xsl:with-param name="collection" select="false()"/>
        <xsl:with-param name="traversableConnection" select="../mif:traversableConnection"/>    
      </xsl:apply-templates>

      <xsl:if test="$distal-end/@maximumMultiplicity eq '1'">
        <xsl:text>    // make the inverse link&#10;    </xsl:text>
        <xsl:value-of select="$name"/>
        <xsl:text>.set</xsl:text>
        <xsl:value-of select="f:capitalize($inverse-name)"/>
        <xsl:text>(this);&#10;</xsl:text>
      </xsl:if>

      <xsl:text>  }&#10;</xsl:text>
  </xsl:template>
  

</xsl:transform>

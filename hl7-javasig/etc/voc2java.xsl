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
<xsl:transform version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:f="f" xmlns:saxon="http://saxon.sf.net/" exclude-result-prefixes="xsl f saxon">
  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:output name="java" method="text" encoding="ASCII"/>
  <xsl:output name="html" method="html" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/>

  <!-- the code-system-map.xml file tells us what the OID should be and that way we'll filter
       what should be instantiated this way. -->
  <xsl:param name="code-value-factory-cfg" select="'code-value-factory.xml'"/>
  <xsl:variable name="codeSystemMap" select="document($code-value-factory-cfg)"/>

  <!-- parameters to construct paths and names -->

  <xsl:param name="base-path" select="'gencode'"/>
  <xsl:param name="base-package" select="'org.hl7.types.enums'"/>
  <xsl:param name="enum-package" select="$base-package"/>
  <xsl:param name="enum-path" select="concat($base-path,'/',f:path-from-package($enum-package))"/>

  <!-- Some functions to deal with naming conventions -->

  <xsl:function name="f:path-from-package">
    <xsl:param name="package-name"/>
    <xsl:sequence select="replace($package-name,'\.','/')"/>
  </xsl:function>

  <xsl:function name="f:enum-name">
    <xsl:param name="vocSet"/>
    <xsl:sequence select="f:capitalize($vocSet/@name)"/>
  </xsl:function>

  <xsl:function name="f:enum-fqn">
    <xsl:param name="vocSet"/>
    <xsl:sequence select="concat($enum-package,'.',f:enum-name($vocSet))"/>
  </xsl:function>

  <xsl:function name="f:value-name">
    <xsl:param name="context"/>
    <xsl:variable name="rawName" select="$context/@name"/>
    <xsl:variable name="rawCode" select="$context/@Code"/>
    <xsl:variable name="rawPrintName" select="$context/@printName"/>
    <xsl:variable name="className" select="$context/ancestor-or-self::vocSet/@name"/>
    <xsl:sequence select="f:capitalize(replace(if(string-length($rawName) gt 0) then (if(starts-with($rawName,$className)) then substring-after($rawName,$className) else f:cookedPrintName($rawName)) else (if(string-length($rawPrintName) gt 0) then f:cookedPrintName($rawPrintName) else (if(string-length($rawCode) gt 0) then $rawCode else 'NIX')),'[^A-Za-z0-9_]+','_'))"/>
  </xsl:function>

  <xsl:function name="f:cookedPrintName">
    <xsl:param name="string"/>
    <xsl:sequence select="f:camelCaseConnect(replace($string,'\([^\)]*\)',''))"/>
  </xsl:function>

  <xsl:function name="f:camelCaseConnect">
    <xsl:param name="string"/>
    <xsl:variable name="parenthesesRemoved" select="replace($string,' *\([^\)]*\)','')"/>
    <xsl:sequence select="string-join(for $t in tokenize($parenthesesRemoved,'[\s\-_/]+') return f:capitalize($t),'')"/>
  </xsl:function>

  <xsl:function name="f:capitalize">
    <xsl:param name="string"/>
    <xsl:sequence select="concat(upper-case(substring($string,1,1)),substring($string,2))"/>
  </xsl:function>

  <xsl:function name="f:codeSystemMapEntry">
    <xsl:param name="vocSet"/>
    <xsl:variable name="fqn" select="f:enum-fqn($vocSet)"/>
    <xsl:sequence select="$codeSystemMap//codeSystem[@class=$fqn]"/>
  </xsl:function>

  <!-- MAIN MODE -->

  <!-- deep null transform -->
  <xsl:template match="/">
    <xsl:apply-templates select="node()"/>
    <gencode><!-- this output provided as a time stamp --></gencode>
  </xsl:template>
  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <!-- class to generate -->
  <xsl:template match="vocSet[not(@name) or not(f:codeSystemMapEntry(current()))]" priority="2"/>

  <xsl:template match="vocSet">
    <xsl:message><codeSystem name="{@name}" codeSystem="{f:codeSystemMapEntry(current())/@codeSystem}"/></xsl:message>
    <xsl:result-document href="{$enum-path}/{f:enum-name(current())}.java" format="java">
      <xsl:apply-templates mode="instantiate" select="$header-template">
        <xsl:with-param name="context" select="."/>
      </xsl:apply-templates>
      <xsl:apply-templates select="node()"/>
      <xsl:apply-templates mode="instantiate" select="$trailer-template">
        <xsl:with-param name="context" select="."/>
      </xsl:apply-templates>
    </xsl:result-document>
  </xsl:template>

  <xsl:template match="*[starts-with(@name,'x_')]" priority="2"/>

  <xsl:template match="specDomain|leafTerm[not(preceding-sibling::leafTerm[@name=current()/@name])]|abstDomain">
    <xsl:variable name="allOccurrences" select="ancestor::vocSet//*[@conceptID=current()/@conceptID and not(ancestor-or-self::abstDomain[starts-with(@name,'x_')])]"/>
    <xsl:if test="current() is $allOccurrences[last()]">
      <xsl:apply-templates mode="instantiate" select="$value-template">
        <xsl:with-param name="context" select="$allOccurrences"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:apply-templates select="node()"/>
  </xsl:template>


  <!-- INSTANTIATE A TEMPLATE -->

  <xsl:template mode="instantiate" match="/|@*|node()">
    <xsl:param name="context" select="/.."/>
    <xsl:apply-templates mode="instantiate" select="node()">
      <xsl:with-param name="context" select="$context"/>
    </xsl:apply-templates>
  </xsl:template>
  <xsl:template mode="instantiate" match="text()" priority="0">
    <xsl:copy/>
  </xsl:template>
  <xsl:template mode="instantiate" match="documentation">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="f:javadoc-html($context[last()]/p)"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="interfacePackage">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="$enum-package"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="className|domainName">
    <xsl:param name="context" select="/.."/>
    <!-- xsl:message><TEST name="{$context[last()]}"/></xsl:message -->
    <xsl:value-of select="f:quoted-data-or-null(f:enum-name($context[last()]),@quote)"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="valueName">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="f:value-name($context[last()])"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="displayName">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="f:quoted-data-or-null($context[last()]/@printName,@quote)"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="code">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="f:quoted-data-or-null($context[last()]/@Code,@quote)"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="oid">
    <xsl:param name="context" select="/.."/>
    <xsl:value-of select="f:quoted-data-or-null(f:codeSystemMapEntry($context[last()])/@codeSystem,@quote)"/>
  </xsl:template>
  <xsl:template mode="instantiate" match="parentNames">
    <xsl:param name="context" select="/.."/>
    <xsl:variable name="quote" select="@quote"/>
    <xsl:variable name="parents" select="for $c in $context return $c/ancestor::*[@id=$c/@parentConceptRef]"/>
    <xsl:choose>
       <xsl:when test="count($parents) gt 3">
        <xsl:message terminate="yes"><ERROR text="more than 3 parent concepts are not supported"><xsl:sequence select="$context"/></ERROR></xsl:message>
        <xsl:sequence select="string-join(for $parent in $parents return f:quoted-data-or-null(f:value-name($parent),$quote), ', ')"/>
      </xsl:when>
      <xsl:when test="count($parents) gt 0">
        <xsl:sequence select="string-join(for $parent in $parents return f:quoted-data-or-null(f:value-name($parent),$quote), ', ')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>root</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:function name="f:quoted-data-or-null">
    <xsl:param name="data"/>
    <xsl:param name="quote"/>
    <xsl:variable name="result">
      <xsl:choose>
        <xsl:when test="$data and string-length($data) gt 0">
          <xsl:if test="$quote"><xsl:text>"</xsl:text></xsl:if>
	  <xsl:value-of select="$data"/>
          <xsl:if test="$quote"><xsl:text>"</xsl:text></xsl:if>
        </xsl:when>
        <xsl:otherwise>
          <xsl:text>null</xsl:text>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$result"/>
  </xsl:function>

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
  <!-- remove the  namespace -->
  <xsl:template mode="javadoc-html" match="p[not(preceding-sibling::p)]" priority="1">
    <xsl:apply-templates mode="javadoc-html" select="@*|node()"/>
  </xsl:template>
  <xsl:template mode="javadoc-html" match="*" priority="0">
    <xsl:element name="{local-name(.)}">
      <xsl:apply-templates mode="javadoc-html" select="@*|node()"/>
    </xsl:element>
  </xsl:template>
  <xsl:template mode="javadoc-html" match="text" priority="1">
    <xsl:apply-templates mode="javadoc-html" select="node()"/>
  </xsl:template>
  <xsl:template mode="javadoc-html" match="text()" priority="0">
    <xsl:sequence select="replace(.,'\s+',' ')"/>
  </xsl:template>


  <!-- THE REST OF THIS FILE CONTAINS THE JAVA CODE TEMPLATES -->

  <!-- The code template: HEADER -->


  <xsl:variable name="header-template">/* THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.

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

THIS FILE IS GENERATED AUTOMATICALLY - DO NOT MODIFY.
*/
package <interfacePackage/>;

import org.hl7.types.ValueFactory;
import org.hl7.types.ANY;
import org.hl7.types.CS;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.SET;
import org.hl7.types.LIST;
import org.hl7.types.CR;
import org.hl7.types.NullFlavor;
import org.hl7.types.impl.NullFlavorImpl;
import org.hl7.types.impl.STnull;
import org.hl7.types.impl.BLimpl;
import org.hl7.types.impl.SETnull;
import org.hl7.types.impl.LISTnull;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;

/** <documentation/> */
public enum <className/> implements CS {

  // ACTUAL DATA

  root(null, <className quote="yes"/>, null, null)</xsl:variable>

  <xsl:variable name="value-template">,
  /** <documentation/> */
  <valueName/>(<parentNames/>, <domainName quote="yes"/>, <code quote="yes"/>, <displayName quote="yes"/>)</xsl:variable>

  <xsl:variable name="trailer-template">;

  // BOILER PLATE CODE IN WHICH ONLY THE ENUM NAME MUST BE INSERTED

  public static final UID CODE_SYSTEM = ValueFactory.getInstance().UIDvalueOfLiteral(<oid quote="yes"/>);
  public static final ST  CODE_SYSTEM_NAME = ValueFactory.getInstance().STvalueOfLiteral(<className quote="yes"/>);

  private final <className/> _parent;
  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private final <className/> _parent2;
  private final <className/> _parent3; // well, found a 3rd :(
  private EnumSet&lt;<className/>> _impliedConcepts = null;

  private <className/>(<className/> parent, String domainName, String code, String displayName) {
    this(parent, null, domainName, code, displayName);
  }

  private <className/>(<className/> parent, <className/> parent2, String domainName, String code, String displayName) {
    this(parent, null, null, domainName, code, displayName);
  }

  // duh! what a cheap way to make a set, but really we don't expect there to be too many more than 2 parents!
  private <className/>(<className/> parent, <className/> parent2, <className/> parent3, String domainName, String code, String displayName) {
    _parent = parent;
    _parent2 = parent2;
    _parent3 = parent3;
    _domainName = domainName;
    _code = ValueFactory.getInstance().STvalueOfLiteral(code);
    _displayName = ValueFactory.getInstance().STvalueOfLiteral(displayName);
  }

  private EnumSet&lt;<className/>> getImpliedConcepts() {
    if(_impliedConcepts == null) {
      if(_parent == null) { // then _parent2, 3 is also null
	_impliedConcepts = EnumSet.of(root);
	_impliedConcepts.add(this);
      } else {
	_impliedConcepts  = EnumSet.copyOf(_parent.getImpliedConcepts());
	_impliedConcepts.add(this);
	if(_parent2 != null)
	  _impliedConcepts.addAll(_parent2.getImpliedConcepts());
	if(_parent3 != null)
	  _impliedConcepts.addAll(_parent3.getImpliedConcepts());
      }
    }
    return _impliedConcepts;
  }

  public final BL equal(ANY that) {
    if(this == that)
      return BLimpl.TRUE;
    if (!(that instanceof CD))
      return BLimpl.FALSE;
    else if (that instanceof <className/>)
      return BLimpl.FALSE;
    else
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
  }

  public BL implies(CD that) {
    if(this == that || this.equals(that))
      return BLimpl.TRUE;
    if(! (that instanceof <className/>))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final <className/> that<className/> = (<className/>)that;
    return BLimpl.valueOf(getImpliedConcepts().contains(that<className/>));
  }

  public <className/> mostSpecificGeneralization(CD that) {
    if(this == that || this.equals(that))
      return this;
    if(! (that instanceof <className/>))
      throw new UnsupportedOperationException("cannot handle argument of class " + that.getClass());
    final <className/> that<className/> = (<className/>)that;

    // First build the intersection of the implied concepts
    // the remainder is a single path of which we have to
    // find the most specific concept, i.e., the one who
    // has all others as parents, i.e., the one with the
    // largest set of implied concepts.
    EnumSet&lt;<className/>> intersection = EnumSet.copyOf(getImpliedConcepts());
    intersection.removeAll(EnumSet.complementOf(that<className/>.getImpliedConcepts()));
    intersection.remove(root);

    // XXX: this iterative search is likely to be least optimal because
    // we probably have to search the path from the root here.
    // I don't know of any better way to do it right now though.

    <className/> mostSpecificKnownGeneralization = root;
    int maxKnownSpecificity = 1;

    for(<className/> candidate : intersection) {
      int specificity = candidate.getImpliedConcepts().size();
      if(specificity > maxKnownSpecificity) {
	maxKnownSpecificity = specificity;
	mostSpecificKnownGeneralization = candidate;
      }
    }

    return mostSpecificKnownGeneralization;
  }

  private static Map&lt;ST,<className/>> _codeMap = null;

  public static <className/> valueOf(ST code) {
    // set up the _codeMap if needed for the first time
    if(_codeMap == null) {
      synchronized(<className/>.class) { // that one time might just as well keep it thread-safe
	if(_codeMap == null) {
	  _codeMap = new HashMap();
	  for(<className/> concept : EnumSet.allOf(<className/>.class)) {
	    ST conceptCode = concept.code();
	    if(conceptCode != null &amp;&amp; conceptCode.nonNull().isTrue()) {
	      _codeMap.put(conceptCode,concept);
	    }
	  }
	}
      }
    }

    if(code.nonNull().isTrue()) {
      <className/> concept = _codeMap.get(code);
      if(concept == null)
        throw new IllegalArgumentException(("unknown code " + code + " at codeSystem = " +
          <oid quote="yes"/> + ", domain = " + <className quote="yes"/>));
      else
        return concept;
    } else 
      return null;
  }

  // INVARIANT BOILER PLATE CODE

  public String toString() {
    return code().toString();
  }

  private final String _domainName;
  private final ST _code;
  private final ST _displayName;

  public String domainName() { return _domainName; }
  public ST code() { return _code; }
  public ST displayName() { return _displayName; }
  public UID codeSystem() { return CODE_SYSTEM; }
  public ST codeSystemName() { return CODE_SYSTEM_NAME; }
  public ST codeSystemVersion() { return STnull.NI; }
  public ST originalText() { return STnull.NI; }
  public SET&lt;CD> translation() { return SETnull.NA; }
  public LIST&lt;CR> qualifier() { return LISTnull.NA; }

  public NullFlavor nullFlavor() { return NullFlavorImpl.NOT_A_NULL_FLAVOR; }
  public boolean isNullJ() { return false; }
  public boolean nonNullJ() { return true; }
  public boolean notApplicableJ() { return false; }
  public boolean unknownJ() { return false; }
  public boolean otherJ() { return false; }
  public BL isNull() { return BLimpl.FALSE; }
  public BL nonNull() { return BLimpl.TRUE; }
  public BL notApplicable() { return BLimpl.FALSE; }
  public BL unknown() { return BLimpl.FALSE; }
  public BL other() { return BLimpl.FALSE; }
}
</xsl:variable>

</xsl:transform>

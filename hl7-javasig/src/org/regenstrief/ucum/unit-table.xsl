<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:sys="java:org.regenstrief.ucum.UnitSystem"
    xmlns:dim="java:org.regenstrief.ucum.Dimension"
    xmlns:u="http://aurora.regenstrief.org/UCUM"
    xmlns:pfx="java:org.regenstrief.ucum.Prefix"
    xmlns:unit="java:org.regenstrief.ucum.Unit"
    xmlns:fun="java:org.regenstrief.ucum.Function"
    xmlns:saxon="http://saxon.sf.net/"
    xmlns:p="java:java.util.regex.Pattern"
    xmlns:m="java:java.util.regex.Matcher"
    xmlns:it="java:java.util.Iterator"
    xmlns:object="java:java.lang.Object"
    xmlns:class="java:java.lang.Class"
    extension-element-prefixes="saxon"
    exclude-result-prefixes="saxon xsl sys dim fun u p m pfx unit it object class">

  <xsl:output method="xml" indent="yes" encoding="ASCII"/>
  <xsl:strip-space elements="*"/>
 
  <xsl:variable name="functions">
    <function-pair Code="Cel" class="org.regenstrief.ucum.functions.Cel"/>
    <function-pair Code="degF" class="org.regenstrief.ucum.functions.degF"/>
    <function-pair Code="ln" class="org.regenstrief.ucum.functions.ln"/>
    <function-pair Code="ld" class="org.regenstrief.ucum.functions.ld"/>
    <function-pair Code="lg" class="org.regenstrief.ucum.functions.lg"/>
    <function-pair Code="2ln" class="org.regenstrief.ucum.functions.lnTimes2"/>
    <function-pair Code="2lg" class="org.regenstrief.ucum.functions.lgTimes2"/>
    <function-pair Code="10lg" class="org.regenstrief.ucum.functions.lgTimes10"/>
    <function-pair Code="20lg" class="org.regenstrief.ucum.functions.lgTimes20"/>
    <function-pair Code="inv" class="org.regenstrief.ucum.functions.inv"/>
    <function-pair Code="pH" class="org.regenstrief.ucum.functions.pH"/>
    <function-pair Code="hpX" class="org.regenstrief.ucum.functions.hpX"/>
    <function-pair Code="hpC" class="org.regenstrief.ucum.functions.hpC"/>
    <function-pair Code="100tan" class="org.regenstrief.ucum.functions.tanTimes100"/>
  </xsl:variable>

  <xsl:variable name="tests">
     <test value="1" unit="m"/>
     <test value="1" unit="dyn.s/cm5"/>
     <test value="1" unit="N"/>
     <test value="100" unit="Cel"/>
     <test value="100" unit="[degF]"/>
  </xsl:variable>

  <xsl:template match="/">
    <units codeSystem="2.16.840.1.113883.6.8" codeSystemName="UCUM">
      <xsl:variable name="base" select="//u:base-unit"/>
      <base max="{sys:initialize(count($base))}"/>
      <xsl:apply-templates select="$base"/>
      <xsl:apply-templates select="//u:prefix"/>
      <xsl:apply-templates select="$functions/function-pair"/>
      <xsl:apply-templates select="//u:unit">
	 <xsl:sort select="@id"/>
      </xsl:apply-templates>
      <xsl:apply-templates select="$tests/test"/>
    </units>
  </xsl:template>

  <!-- default is deep null transform -->
  <xsl:template match="@*|node()">
    <xsl:apply-templates select="@*|node()"/>
  </xsl:template>

  <xsl:template match="u:base-unit">
    <xsl:variable name="base-unit"
         select="sys:initializeBase(@Code,@dim)"/>
    <base-unit code="{unit:code($base-unit)}" dimension="{unit:dimension($base-unit)}"/>
  </xsl:template>

  <xsl:template match="u:prefix">
    <xsl:variable name="prefix"
         select="sys:definePrefix(@Code,value/@value)"/>
    <prefix code="{pfx:code($prefix)}" value="{pfx:value($prefix)}"/>
  </xsl:template>

  <xsl:template match="function-pair">
    <xsl:variable name="function-pair" select="sys:defineFunctionPair(@Code,@class)"/>
    <xsl:if test="$function-pair">
      <function-pair code="{@Code}" class="{@class}"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="test[@value and @unit]">
     <xsl:variable name="unit" select="unit:_valueOf(@unit)"/>
     <xsl:message>
	<test unit="{@unit}" code="{unit:code($unit)}"
	      magnitude="{unit:magnitude($unit)}"
	      canonical="{dim:toUnitString(unit:dimension($unit))}"
	      function-pair="{$functions/function-pair[@class=class:getName(object:getClass(unit:getFunctionPair($unit)))]/@Code}"
	      metric="{(if(unit:isMetric($unit)) then 'yes' else 'no')}"/>
     </xsl:message>
  </xsl:template>
     
  <xsl:template match="u:unit[not(.//function)]">
    <xsl:variable name="unit" select="sys:defineUnit(@Code,value/@value, value/@Unit,@isMetric='yes')"/>
    <unit code="{unit:code($unit)}"
          magnitude="{unit:magnitude($unit)}"
	  canonical="{dim:toUnitString(unit:dimension($unit))}"
	  metric="{(if(unit:isMetric($unit)) then 'yes' else 'no')}"/>
  </xsl:template>

  <xsl:template match="u:NONONONONONONOunit[value/function]">
    <xsl:variable name="unit" select="sys:defineSpecialUnit(@Code,value/function/@value,value/function/@Unit,value/function/name,@isMetric='yes')"/>
    <special-unit code="{unit:code($unit)}"
		  magnitude="{unit:magnitude($unit)}"
		  canonical="{dim:toUnitString(unit:dimension($unit))}"
		  function-pair="{$functions/function-pair[@class=class:getName(object:getClass(unit:getFunctionPair($unit)))]/@Code}"
		  metric="{(if(unit:isMetric($unit)) then 'yes' else 'no')}"/>
  </xsl:template>

  <xsl:param name="functionPattern" select="'([^(]+)\(([^ ]+) +(.+)\)'"/>

  <xsl:variable name="functionMatcher"
    select="p:matcher(p:compile($functionPattern),'')"/>

  <xsl:template match="u:unit[.//function and m:matches(m:reset($functionMatcher, value/@Unit))]" priority="1">
    <xsl:variable name="functionPair" select="m:group($functionMatcher,1)"/>
    <xsl:variable name="magnitude" select="m:group($functionMatcher,2)"/>
    <xsl:variable name="properUnit" select="m:group($functionMatcher,3)"/>
    <xsl:variable name="unit" select="sys:defineSpecialUnit(@Code, $magnitude, $properUnit, $functionPair, @isMetric='yes')"/>
    <xsl:message>
    <special-unit code="{unit:code($unit)}"
		  magnitude="{unit:magnitude($unit)}"
		  canonical="{dim:toUnitString(unit:dimension($unit))}"
		  function-pair="{$functionPair}"
		  function-pair2="{unit:getFunctionPair($unit)}"
		  function-pair3="{$functions/function-pair[@class=class:getName(object:getClass(unit:getFunctionPair($unit)))]/@Code}"
		  metric="{(if(unit:isMetric($unit)) then 'yes' else 'no')}"/>
    </xsl:message>
    <special-unit code="{unit:code($unit)}"
		  magnitude="{unit:magnitude($unit)}"
		  canonical="{dim:toUnitString(unit:dimension($unit))}"
		  function-pair="{$functionPair}"
		  metric="{(if(unit:isMetric($unit)) then 'yes' else 'no')}"/>
  </xsl:template>

  <xsl:template match="u:unit[.//function]">
    <xsl:message>
      <MATCH regex="{p:pattern(m:pattern($functionMatcher))}"
           text="{value/@Unit}"
           select="{m:matches(m:reset($functionMatcher, value/@Unit))}"/>
    </xsl:message>
  </xsl:template>

</xsl:transform>
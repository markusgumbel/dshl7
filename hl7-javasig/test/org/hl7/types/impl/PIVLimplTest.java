/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.IVL;
import org.hl7.types.PIVL;
import org.hl7.types.PQ;
import org.hl7.types.QTY;
import org.hl7.types.TS;
import org.hl7.types.ValueFactory;
import org.hl7.types.enums.CalendarCycle;


/**
 * * A test suite that exercises
 * {@link PQimpl}
 * class.
 *
 * @author Matthew R. Carlson
 */
public class PIVLimplTest extends TestCase
{
	/**
	 * Constructor for <code>test_PIVLimpl</code>.
	 *
	 * @param name  the test name
	 */
	
	PIVLimpl thisPivl;
	PIVLimpl thatPivl;
	PIVLimpl otherPivl;
	PIVLimpl anotherPivl;
	PIVLimpl perPivl;
	PIVLimpl per2Pivl;
	PIVLimpl blehPivl;
	PIVLimpl pid,pid2,mattpivl,ricepivl,pivl5,pivl6,pivl7,pivl8,
	pivl9,pivl10,pivl11,literal5,literal6,literal7,literal8;
	PIVL uchipivl, guchipivl;

	TS thisLow; 
	TS thisHigh; 
	QTY.diff thisPeriod; 
	CS thisAlignment;
	BL thisInstitutionSpecified;
	
	TS thatLow; 
	TS thatHigh; 
	QTY.diff thatPeriod; 
	CS thatAlignment;
	BL thatInstitutionSpecified;
	
	TS otherLow; 
	TS otherHigh; 
	QTY.diff otherPeriod; 
	CS otherAlignment;
	BL otherInstitutionSpecified;
	
	TS anotherLow; 
	TS anotherHigh; 
	QTY.diff anotherPeriod; 
	CS anotherAlignment;
	BL anotherInstitutionSpecified;
	
	TS perLow; 
	TS perHigh; 
	QTY.diff perPeriod; 
	CS perAlignment;
	BL perInstitutionSpecified;
	
	TS per2Low; 
	TS per2High; 
	QTY.diff per2Period; 
	CS per2Alignment;
	BL per2InstitutionSpecified;
	
	TS blehLow; 
	TS blehHigh; 
	QTY.diff blehPeriod; 
	CS blehAlignment;
	BL blehInstitutionSpecified;
	
	IVLimpl thisIVL;
	TS thisHighIVL;
	TS thisLowIVL;
	
	IVLimpl thatIVL;
	TS thatHighIVL;
	TS thatLowIVL;
	
	IVLimpl currIVL;
	TS currHighIVL;
	TS currLowIVL;
	
	IVLimpl nextIVL;
	TS nextHighIVL;
	TS nextLowIVL;
	
	TS one;
	TS two;
	TS three;
	
	IVL i33,i34;
	PQ pq1,pq2,pq3,pq4;
	
	IVLimpl i2,x5d,x15d,i1,i3,i4,i5,i6,i7,i8,i9,
	mattivl,riceivl,ivl5,ivl6,ivl7,ivl8,ivl9,ivl10,
	ivl11,i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,
	i20,i21,i22,i23,i24,i25,i26,i27,i28,i29,i30,i31,i32;
	
	public PIVLimplTest(String name)
	{
		super(name);
	}
	
	public void setUp()
	{		
		anotherLow=TSjuDateAdapter.valueOf("200004191101");	
		anotherHigh=TSjuDateAdapter.valueOf("200004191111");	
		anotherPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		anotherAlignment=CSnull.NI;	
		anotherInstitutionSpecified=BLimpl.FALSE;
		anotherPivl = (PIVLimpl)PIVLimpl.valueOf(anotherLow,anotherHigh,anotherPeriod, 
				anotherAlignment,
				anotherInstitutionSpecified);
		
		blehLow=TSjuDateAdapter.valueOf("200004111950");	
		blehHigh=TSjuDateAdapter.valueOf("200004112012");	
		blehPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		blehAlignment=CSnull.NI;	
		blehInstitutionSpecified=BLimpl.FALSE;
		blehPivl = (PIVLimpl)PIVLimpl.valueOf(blehLow,blehHigh,blehPeriod, 
				blehAlignment,
				blehInstitutionSpecified);
		
		perLow=TSjuDateAdapter.valueOf("200004181100");	
		perHigh=TSjuDateAdapter.valueOf("200004191111");	
		perPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		perAlignment=CSnull.NI;	
		perInstitutionSpecified=BLimpl.FALSE;
		perPivl = (PIVLimpl)PIVLimpl.valueOf(perLow,perHigh,perPeriod, 
				perAlignment,
				perInstitutionSpecified);
		
		thisLow=TSjuDateAdapter.valueOf("200004181100");
		thisHigh=TSjuDateAdapter.valueOf("200004181110");
		thisPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		thisAlignment=CSnull.NI;
		thisInstitutionSpecified=BLimpl.FALSE;
		thisPivl = (PIVLimpl)PIVLimpl.valueOf(thisLow,thisHigh,thisPeriod, 
				thisAlignment,
				thisInstitutionSpecified);
		
		per2Low=TSjuDateAdapter.valueOf("200004181104");	
		per2High=TSjuDateAdapter.valueOf("200004212012");	
		per2Period=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		per2Alignment=CSnull.NI;	
		per2InstitutionSpecified=BLimpl.FALSE;
		per2Pivl = (PIVLimpl)PIVLimpl.valueOf(per2Low,per2High,per2Period, 
				per2Alignment,
				per2InstitutionSpecified);
		
		thatLow=TSjuDateAdapter.valueOf("200004181104");
		thatHigh=TSjuDateAdapter.valueOf("200004181108");
		thatPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		thatAlignment=CSnull.NI;
		thatInstitutionSpecified=BLimpl.FALSE;
		thatPivl = (PIVLimpl)PIVLimpl.valueOf(thatLow,thatHigh,thatPeriod,
				thatAlignment,
				thatInstitutionSpecified);
		
		otherLow=TSjuDateAdapter.valueOf("200004281105");
		otherHigh=TSjuDateAdapter.valueOf("200004281107");
		otherPeriod=PQimpl.valueOf(REALdoubleAdapter.valueOf(10), "d");
		otherAlignment=CSnull.NI;
		otherInstitutionSpecified=BLimpl.FALSE;
		otherPivl = (PIVLimpl)PIVLimpl.valueOf(otherLow,otherHigh,otherPeriod, 
				otherAlignment,
				otherInstitutionSpecified);
		
		one=TSjuDateAdapter.valueOf("200004251348");
		two=TSjuDateAdapter.valueOf("200004200011");
		three=TSjuDateAdapter.valueOf("200004181105");
		
		thisLowIVL = TSjuDateAdapter.valueOf("200004281100");
		assertNotNull("thisLow is null!!",thisLowIVL);
		thisHighIVL = TSjuDateAdapter.valueOf("200004281110");
		assertNotNull("thisLow is null!!",thisHighIVL);
		thisIVL = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,thisLowIVL,thisHighIVL,BLimpl.TRUE);
		assertNotNull("thisIVL is null!!",thisIVL);
		
		thatLowIVL = TSjuDateAdapter.valueOf("200004181100");
		assertNotNull("thisLow is null!!",thatLowIVL);
		thatHighIVL = TSjuDateAdapter.valueOf("200004181110");
		assertNotNull("thisLow is null!!",thatHighIVL);
		thatIVL = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,thatLowIVL,thatHighIVL,BLimpl.TRUE);
		assertNotNull("thisIVL is null!!",thatIVL);
		
		x5d = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200402051200"), (TS)TSjuDateAdapter.valueOf("200402051210"), BLimpl.TRUE);
		pid = (PIVLimpl)PIVLimpl.valueOf(x5d, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
		
		i1 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200402241400"), TSjuDateAdapter.valueOf("200402241430"),BLimpl.TRUE);
		i2 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200402101200"), TSjuDateAdapter.valueOf("200402101210"),BLimpl.TRUE);
		i3 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200004281100"), TSjuDateAdapter.valueOf("200004281110"),BLimpl.TRUE);
		i4 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200004091100"), TSjuDateAdapter.valueOf("200004191110"),BLimpl.TRUE);
		i5 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200004071100"), TSjuDateAdapter.valueOf("200004191110"),BLimpl.TRUE);
		i6 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200003281100"), TSjuDateAdapter.valueOf("200004191110"),BLimpl.TRUE);
		i7 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200003071100"), TSjuDateAdapter.valueOf("200004291110"),BLimpl.TRUE);
		i8 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200003102200"), TSjuDateAdapter.valueOf("200003151110"),BLimpl.TRUE);
		i9 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf( "200003091103"), TSjuDateAdapter.valueOf("200003091103"),BLimpl.TRUE);
		i10 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20220213"), TSjuDateAdapter.valueOf("20220214"),BLimpl.TRUE);
		i11 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20080213"), TSjuDateAdapter.valueOf("20080214"),BLimpl.TRUE);
		i12 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("19240213"), TSjuDateAdapter.valueOf("19240214"),BLimpl.TRUE);
		i13 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("19260213"), TSjuDateAdapter.valueOf("19260214"),BLimpl.TRUE);
		i14 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("22260213"), TSjuDateAdapter.valueOf("22260214"),BLimpl.TRUE);
		i15 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20210813"), TSjuDateAdapter.valueOf("20210814"),BLimpl.TRUE);
		i16 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20210413"), TSjuDateAdapter.valueOf("20210414"),BLimpl.TRUE);
		i17 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20210213"), TSjuDateAdapter.valueOf("20210214"),BLimpl.TRUE);
		i18 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040213"), TSjuDateAdapter.valueOf("20040214"),BLimpl.TRUE);
		i19 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040413"), TSjuDateAdapter.valueOf("20040414"),BLimpl.TRUE);
		i20 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("19850413"), TSjuDateAdapter.valueOf("19850414"),BLimpl.TRUE);
		i21 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("19861013"), TSjuDateAdapter.valueOf("19861014"),BLimpl.TRUE);
		i22 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040427"), TSjuDateAdapter.valueOf("20040428"),BLimpl.TRUE);
		i23 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040511"), TSjuDateAdapter.valueOf("20040512"),BLimpl.TRUE);
		i24 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040525"), TSjuDateAdapter.valueOf("20040526"),BLimpl.TRUE);
		i25 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20060523"), TSjuDateAdapter.valueOf("20060524"),BLimpl.TRUE);
		i26 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040415"), TSjuDateAdapter.valueOf("20040416"),BLimpl.TRUE);
		i27 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20050414"), TSjuDateAdapter.valueOf("20050415"),BLimpl.TRUE);
		i28 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20060413"), TSjuDateAdapter.valueOf("20060414"),BLimpl.TRUE);
		i29 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20070412"), TSjuDateAdapter.valueOf("20070413"),BLimpl.TRUE);
		i30 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20080412"), TSjuDateAdapter.valueOf("20080413"),BLimpl.TRUE);
		i31 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20080414"), TSjuDateAdapter.valueOf("20080415"),BLimpl.TRUE);
		i32 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,TSjuDateAdapter.valueOf("20040403"), TSjuDateAdapter.valueOf("20040404"),BLimpl.TRUE);
		
		
		x15d = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200402091400"), (TS)TSjuDateAdapter.valueOf("200402091430"), BLimpl.TRUE);
		pid2 = (PIVLimpl)PIVLimpl.valueOf(x15d, PQimpl.valueOf("15", "d"), CSnull.NI, BLimpl.FALSE);
		
		mattivl = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200004181100"), (TS)TSjuDateAdapter.valueOf("200004181110"), BLimpl.TRUE);
		mattpivl = (PIVLimpl)PIVLimpl.valueOf(mattivl, PQimpl.valueOf("5", "d"), CSnull.NI, BLimpl.FALSE);
		
		riceivl = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200004181100"), (TS)TSjuDateAdapter.valueOf("200004181110"), BLimpl.TRUE);
		ricepivl = (PIVLimpl)PIVLimpl.valueOf(riceivl, PQimpl.valueOf("20", "d"), CSnull.NI, BLimpl.FALSE);
		
		ivl5 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200402151200"), (TS)TSjuDateAdapter.valueOf("200402151210"), BLimpl.TRUE);
		pivl5 = (PIVLimpl)PIVLimpl.valueOf(ivl5, PQimpl.valueOf("2", "d"), CSnull.NI, BLimpl.FALSE);

		literal5=(PIVLimpl)PIVLimpl.valueOf("[200402151200;200402151210]/(2 d)",DatatypeMetadataFactoryDatatypes.instance().TSTYPE);
		literal8=(PIVLimpl)PIVLimpl.valueOf("[20040213;20040214]/(2 mo)@MY",DatatypeMetadataFactoryDatatypes.instance().TSTYPE);
		
		ivl6 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("200402131100"), (TS)TSjuDateAdapter.valueOf("200402131400"), BLimpl.TRUE);
		pivl6 = (PIVLimpl)PIVLimpl.valueOf(ivl6, PQimpl.valueOf("2", "d"), CSnull.NI, BLimpl.FALSE);
		ivl7 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("20040213"), (TS)TSjuDateAdapter.valueOf("20040214"), BLimpl.TRUE);
		pivl7 = (PIVLimpl)PIVLimpl.valueOf(ivl7, PQimpl.valueOf("2", "a"), CalendarCycle.MonthOfTheYear, BLimpl.FALSE);
		ivl8 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("20040213"), (TS)TSjuDateAdapter.valueOf("20040214"), BLimpl.TRUE);
		pivl8 = (PIVLimpl)PIVLimpl.valueOf(ivl8, PQimpl.valueOf("2", "mo"), CalendarCycle.MonthOfTheYear, BLimpl.FALSE);

		ivl9 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("20040413"), (TS)TSjuDateAdapter.valueOf("20040414"), BLimpl.TRUE);
		pivl9 = (PIVLimpl)PIVLimpl.valueOf(ivl9, PQimpl.valueOf("2", "wk"), CalendarCycle.MonthOfTheYear, BLimpl.FALSE);
		
		ivl10 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("20040413"), (TS)TSjuDateAdapter.valueOf("20040414"), BLimpl.TRUE);
		pivl10 = (PIVLimpl)PIVLimpl.valueOf(ivl10, PQimpl.valueOf("2", "d"), CalendarCycle.MonthOfTheYear, BLimpl.FALSE);
		
		ivl11 = (IVLimpl)IVLimpl.valueOf(BLimpl.TRUE,(TS)TSjuDateAdapter.valueOf("20040413"), (TS)TSjuDateAdapter.valueOf("20040414"), BLimpl.TRUE);
		pivl11 = (PIVLimpl)PIVLimpl.valueOf(ivl11, PQimpl.valueOf("48", "h"), CalendarCycle.MonthOfTheYear, BLimpl.FALSE);

		PQ pq1 = ValueFactory.getInstance().PQvalueOf("34", "s");
		PQ pq2 = ValueFactory.getInstance().PQvalueOf("8", "d");
		IVL ivl33 = ValueFactory.getInstance().IVLvalueOf(TSnull.NI, pq1, BLimpl.TRUE, BLimpl.TRUE);
		uchipivl = ValueFactory.getInstance().PIVLvalueOf(ivl33, pq2, CSnull.NI, BLimpl.TRUE);
		
		PQ pq3 = ValueFactory.getInstance().PQvalueOf("34", "s");
		PQ pq4 = ValueFactory.getInstance().PQvalueOf("8", "d");
		i34 = ValueFactory.getInstance().IVLvalueOf(TSnull.NI, pq3, BLimpl.TRUE, BLimpl.TRUE);
		guchipivl = ValueFactory.getInstance().PIVLvalueOf(i34, pq4, CSnull.NI, BLimpl.TRUE);		
		
		//System.out.println(pivl7);
		//System.out.println(pid2);
		//System.out.println(i1);
		
		//System.out.println(thatIVL);
		//System.out.println(thisIVL);
		
		//System.out.println(perPivl);
		
		//System.out.println(thisPivl);
		//System.out.println(thisPivl.phase().low());
		//System.out.println(thisPivl.phase().high());
		//System.out.println(thisPivl.period());
		
		//System.out.println(thatPivl);
		//System.out.println(thatPivl.phase().low());
		//System.out.println(thatPivl.period());
		//System.out.println(thatPivl);
		
		//System.out.println(otherPivl);
		//System.out.println(otherPivl.phase().low());
		//System.out.println(otherPivl.period());
		//System.out.println(otherPivl);
	}
	
	public void tearDown()
	{
	}
	
	public void test_equal()
	{
		assertTrue(thisPivl.equal(thisPivl).isTrue());
		assertTrue(thatPivl.equal(thatPivl).isTrue());
		assertTrue(thisPivl.phase().equal(thatPivl.phase()).isFalse());
		assertTrue(thisPivl.period().equal(thatPivl.period()).isTrue());
		assertNotNull(thisPivl.alignment());
		assertTrue(thisPivl.institutionSpecified().equal(thatPivl.institutionSpecified()).isTrue());
		assertTrue(thisIVL.equal(thisIVL).isTrue());
		assertTrue(pivl5.equal(literal5).isTrue());
		assertTrue(literal5.equal(literal8).isFalse());


		assertEquals(literal8.period(), pivl8.period());
		assertEquals(literal8.phase(), pivl8.phase());
		assertEquals(literal8.alignment(), pivl8.alignment());
		assertEquals(literal8.institutionSpecified(), pivl8.institutionSpecified());
		assertEquals(literal8, pivl8);
		
		assertTrue("" + pivl5 + ".equal(" + uchipivl + ").isFalse()", pivl5.equal(uchipivl).isFalse());
		assertTrue("" + uchipivl + ".equal(" + pivl5 + ").isFalse()", uchipivl.equal(pivl5).isFalse());
		assertTrue("" + uchipivl + ".equal(" + guchipivl + ").isNull()", uchipivl.equal(guchipivl).isNull().isTrue());
		assertTrue("" + guchipivl + ".equal(" + uchipivl + ").isNull()", guchipivl.equal(uchipivl).isNull().isTrue());
	}
	
	public void test_containsElement() {
		// simple tests
		assertTrue(thisPivl.contains(thisLow).isTrue());
		assertTrue(thisPivl.contains(thisHigh).isTrue());
		assertTrue(thatPivl.contains(thatLow).isTrue());
		assertTrue(thatPivl.contains(thatHigh).isTrue());
		assertTrue(otherPivl.contains(otherLow).isTrue());
		assertTrue(otherPivl.contains(otherHigh).isTrue());
		
		// element > phase.low()
		assertTrue(thisPivl.contains(thatLow).isTrue());
		assertTrue(thisPivl.contains(thatHigh).isTrue());
		assertTrue(thatPivl.contains(thisHigh).isFalse()); 
		
		// element < phase.low()
		assertTrue(thatPivl.contains(thisLow).isFalse());
		
		// more tricky tests
		assertTrue(thatPivl.contains(otherLow).isTrue());
		assertTrue(thatPivl.contains(otherHigh).isTrue());
		assertTrue(thatPivl.contains(blehLow).isFalse());
		assertTrue(thatPivl.contains(blehHigh).isFalse());
		
		// alignment tests
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("202102130900")).isFalse());
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("201502131100")).isFalse());
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("202104130900")).isFalse());
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("202202130900")).isTrue());
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("200802131100")).isTrue());
		assertTrue(pivl7.contains(TSjuDateAdapter.valueOf("202204130900")).isFalse());
		assertTrue(pivl8.contains(TSjuDateAdapter.valueOf("202108130900")).isTrue());
		assertTrue(pivl8.contains(TSjuDateAdapter.valueOf("202103130900")).isFalse());
		assertTrue(pivl8.contains(TSjuDateAdapter.valueOf("202102131600")).isTrue());
		assertTrue(pivl8.contains(TSjuDateAdapter.valueOf("200401131600")).isFalse());
		assertTrue(pivl8.contains(TSjuDateAdapter.valueOf("200402151600")).isFalse());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200404271600")).isTrue());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200404201600")).isFalse());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200404161600")).isFalse());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200404291600")).isFalse());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200405111600")).isTrue());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200405251600")).isTrue());
		assertTrue(pivl9.contains(TSjuDateAdapter.valueOf("200605231600")).isTrue());
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200404151600")).isTrue());
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200504141600")).isTrue());
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200604131600")).isTrue());
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200704121600")).isTrue());
		// HAH! Proof that leap years work
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200804121600")).isTrue());
		assertTrue(pivl10.contains(TSjuDateAdapter.valueOf("200804131600")).isFalse());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200404151600")).isTrue());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200504141600")).isTrue());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200604131600")).isTrue());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200704121600")).isTrue());
		// HAH! Proof that leap years work
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200804121600")).isTrue());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200804131600")).isFalse());
		assertTrue(pivl11.contains(TSjuDateAdapter.valueOf("200801141600")).isFalse());
	}
	
	public void test_floor() {	
		assertTrue(REALdoubleAdapter.valueOf(5.0).equals(REALdoubleAdapter.valueOf(5.666).floor()));
		assertTrue(REALdoubleAdapter.valueOf(3.0).equals(REALdoubleAdapter.valueOf(3.266).floor()));
	}
	
	public void test_containsIVL() {
		assertTrue(thisPivl.contains(thisIVL).isTrue());
		assertTrue(thisPivl.contains(thatIVL).isTrue());
		assertTrue(thatPivl.contains(thisIVL).isFalse());
		assertTrue(otherPivl.contains(thisIVL).isFalse());
		assertTrue(otherPivl.contains(thatIVL).isFalse());
	}
	
	public void test_interleaves() {
		assertTrue(thisPivl.interleaves(thisPivl).isTrue());
		assertTrue(thatPivl.interleaves(thatPivl).isTrue());
		assertTrue(otherPivl.interleaves(otherPivl).isTrue());
		assertTrue(thisPivl.interleaves(anotherPivl).isTrue());
		assertTrue(thisPivl.interleaves(thatPivl).isFalse());
		assertTrue(thatPivl.interleaves(otherPivl).isFalse());
		assertTrue(thatPivl.interleaves(blehPivl).isTrue());
	}
	
	public void test_nextTo() {
		assertTrue(thatIVL.equal(thisPivl.nextTo(thisLow)).isTrue());
		assertTrue(thatIVL.equal(thisPivl.nextTo(thisHigh)).isTrue());
		assertTrue(thatIVL.equal(thisPivl.nextTo(two)).isFalse());
		assertTrue(thisIVL.equal(thisPivl.nextTo(one)).isTrue());
		assertTrue(i2.equal(pid.nextTo(TSjuDateAdapter.valueOf("200402101155"))).isTrue());
		assertTrue(i1.equal(pid2.nextTo(TSjuDateAdapter.valueOf("200402101155"))).isTrue());
		// alignment tests
		//System.out.println("pivl7="+pivl7+".nextTo("+TSjuDateAdapter.valueOf("202202130900")+")="+pivl7.nextTo(TSjuDateAdapter.valueOf("202202130900"))+".equal("+i10+")");
		//System.out.println(pivl7.nextTo(TSjuDateAdapter.valueOf("202202130900")).equal(i10));
		assertTrue(pivl7.nextTo(TSjuDateAdapter.valueOf("202202130900")).equal(i10).isTrue());
		assertTrue(pivl7.nextTo(TSjuDateAdapter.valueOf("200802131100")).equal(i11).isTrue());
		assertTrue(pivl7.nextTo(TSjuDateAdapter.valueOf("192402131100")).equal(i12).isTrue());
		assertEquals("" + pivl7 + ".nextTo(" + TSjuDateAdapter.valueOf("192404131100") + ")", i13, pivl7.nextTo(TSjuDateAdapter.valueOf("192404131100")));
		assertTrue(pivl7.nextTo(TSjuDateAdapter.valueOf("222504131100")).equal(i14).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("202108130900")).equal(i15).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("202103130900")).equal(i16).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("202102131600")).equal(i17).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("200401131600")).equal(i18).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("200402151600")).equal(i19).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("198502151600")).equal(i20).isTrue());
		assertTrue(pivl8.nextTo(TSjuDateAdapter.valueOf("198609151600")).equal(i21).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200404271600")).equal(i22).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200404201600")).equal(i22).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200404161600")).equal(i22).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200404291600")).equal(i23).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200405111600")).equal(i23).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200405251600")).equal(i24).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200605231600")).equal(i25).isTrue());
		assertTrue(pivl9.nextTo(TSjuDateAdapter.valueOf("200404041600")).equal(i19).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200404151600")).equal(i26).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200504141600")).equal(i27).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200604131600")).equal(i28).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200704121600")).equal(i29).isTrue());
		// HAH! Proof that leap years work
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200804121600")).equal(i30).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200804131600")).equal(i31).isTrue());
		assertTrue(pivl10.nextTo(TSjuDateAdapter.valueOf("200404021600")).equal(i32).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200404151600")).equal(i26).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200504141600")).equal(i27).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200604131600")).equal(i28).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200704121600")).equal(i29).isTrue());
		// HAH! Proof that leap years work
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200804121600")).equal(i30).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200804131600")).equal(i31).isTrue());
		assertTrue(pivl11.nextTo(TSjuDateAdapter.valueOf("200404021600")).equal(i32).isTrue());
	}
	
	public void test_nextAfter() {
		assertTrue(thisIVL.equal(thisPivl.nextAfter(thisLow)).isTrue());
		assertTrue(thisIVL.equal(thisPivl.nextAfter(thisHigh)).isTrue());
		assertTrue(i3.equal(thisPivl.nextAfter(one)).isTrue());
		assertTrue(thisIVL.equal(thisPivl.nextAfter(two)).isTrue());
		assertTrue(thisIVL.equal(thisPivl.nextAfter(three)).isTrue());
	}
	
	public void test_containsPIVL() {
		//assertTrue("thisPivl !contain thatPIVL!!",thisPivl.contains(thatPivl).isTrue());
		//assertTrue("thatPivl contains thisPIVL!!",thatPivl.contains(thisPivl).isFalse());
		//assertTrue("thisPivl contains mattPIVL!!",thisPivl.contains(mattpivl).isFalse());
		//assertTrue("thisPivl !contain ricePIVL!!",thisPivl.contains(ricepivl).isTrue());
		assertTrue(pivl6.contains(pivl5).isTrue());
	}
}

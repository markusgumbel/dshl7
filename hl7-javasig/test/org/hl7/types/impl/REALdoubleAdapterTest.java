package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.ANY;
import org.hl7.types.REAL;
import org.hl7.types.PQ;
import org.hl7.types.IVL;
import org.hl7.types.BL;
import org.hl7.types.ValueFactory;

public class REALdoubleAdapterTest extends TestCase {

	private static final ValueFactory VF = ValueFactory.getInstance();
	static REAL R(String s) {
		return VF.REALvalueOfLiteral(s);
	}

	final REAL real0_1 = ValueFactory.getInstance().REALvalueOfLiteral("0");
	final REAL real0_11 = ValueFactory.getInstance().REALvalueOfLiteral("0.");
	final REAL real0_2 = ValueFactory.getInstance().REALvalueOfLiteral("0.0");
	final REAL real0_3 = ValueFactory.getInstance().REALvalueOfLiteral("0.00");
	final REAL real0_4 = ValueFactory.getInstance().REALvalueOfLiteral("00.00");
	
	final REAL real0_5 = ValueFactory.getInstance().REALvalueOf(0);
	
	final REAL real250_1 = ValueFactory.getInstance().REALvalueOfLiteral("250");
	final REAL real250_2 = ValueFactory.getInstance().REALvalueOfLiteral("250.0");
	final REAL real250_3 = ValueFactory.getInstance().REALvalueOfLiteral("0250.0");
	final REAL real250_4 = ValueFactory.getInstance().REALvalueOfLiteral("0250.00");
	final REAL real250_9 = ValueFactory.getInstance().REALvalueOfLiteral("250.");
	
	final REAL real1_1 = ValueFactory.getInstance().REALvalueOfLiteral("1");
	final REAL real1_2 = ValueFactory.getInstance().REALvalueOfLiteral("1.0");
	final REAL real1_3 = ValueFactory.getInstance().REALvalueOfLiteral("1.00");

	final REAL real250_5 = ValueFactory.getInstance().REALvalueOf(250);
	final REAL real250_6 = ValueFactory.getInstance().REALvalueOf(250.0);
	final REAL real250_7 = ValueFactory.getInstance().REALvalueOf(0250.0);
	final REAL real250_8 = ValueFactory.getInstance().REALvalueOf(0250.00);

	final REAL real03_1 = ValueFactory.getInstance().REALvalueOfLiteral("0.3");
	final REAL real03_2 = ValueFactory.getInstance().REALvalueOfLiteral(".3");
	final REAL real03_3 = ValueFactory.getInstance().REALvalueOfLiteral(".30");
	final REAL real03_4 = ValueFactory.getInstance().REALvalueOfLiteral("0.33");
	final REAL real03_5 = ValueFactory.getInstance().REALvalueOfLiteral("0.303");
	
	final REAL real03_6 = ValueFactory.getInstance().REALvalueOf(0.3);
	final REAL real03_7 = ValueFactory.getInstance().REALvalueOf(.3);
	final REAL real03_8 = ValueFactory.getInstance().REALvalueOf(.30);
	final REAL real03_9 = ValueFactory.getInstance().REALvalueOf(0.33);
	final REAL real03_10 = ValueFactory.getInstance().REALvalueOf(0.303);
	
	final REAL real0254_1 = ValueFactory.getInstance().REALvalueOf(0.254);
	final REAL real0254_2 = ValueFactory.getInstance().REALvalueOf(.254);
	final REAL real0254_3 = ValueFactory.getInstance().REALvalueOf(00.254);
	final REAL real0254_4 = ValueFactory.getInstance().REALvalueOf(254e-3);
	final REAL real0254_5 = ValueFactory.getInstance().REALvalueOf(2540e-4);
	
	final REAL real0254_6 = ValueFactory.getInstance().REALvalueOfLiteral("0.254");
	final REAL real0254_7 = ValueFactory.getInstance().REALvalueOfLiteral(".254");
	final REAL real0254_8 = ValueFactory.getInstance().REALvalueOfLiteral("00.254");
	final REAL real0254_9 = ValueFactory.getInstance().REALvalueOfLiteral("254e-3");
	final REAL real0254_10 = ValueFactory.getInstance().REALvalueOfLiteral("2540e-4");

	final REAL real00254_e = ValueFactory.getInstance().REALvalueOfLiteral("254e-4");
	final REAL real00254_1 = ValueFactory.getInstance().REALvalueOfLiteral("254.e-4");
	final REAL real00254_2 = ValueFactory.getInstance().REALvalueOfLiteral("2.54e-2");

	final REAL realAvog = ValueFactory.getInstance().REALvalueOfLiteral("6.1234e24");		

	final PQ umol_L_6 = ValueFactory.getInstance().PQvalueOf("2","umol/L");
	final PQ umol_L_0_0 = ValueFactory.getInstance().PQvalueOf("1","umol/L");
	final IVL<PQ> umol_L_0_0_6 = ValueFactory.getInstance().IVLvalueOf(umol_L_0_0,umol_L_6,BLimpl.TRUE, BLimpl.TRUE);
	final REAL umol_L_0_0_6_low_value = umol_L_0_0_6.low().value();

	final REAL r_umol_L = ValueFactory.getInstance().PQvalueOf("1","mmol/L").canonical().value();
	final REAL r_umol_L_6 = ValueFactory.getInstance().REALvalueOfLiteral("2").times(r_umol_L);
	final REAL r_umol_L_0_0 = ValueFactory.getInstance().REALvalueOfLiteral("1").times(r_umol_L);
	final IVL<REAL> r_umol_L_0_0_6 = IVLimpl.valueOf(r_umol_L_0_0,r_umol_L_6,BLimpl.TRUE, BLimpl.TRUE);
	final REAL r_umol_L_0_0_6_low_value = r_umol_L_0_0_6.low();
	final REAL r_umol_b = ValueFactory.getInstance().REALvalueOfLiteral("602213670.e15"); // ("1.20442734E24");
	final REAL r_umol_a = ValueFactory.getInstance().REALvalueOfLiteral("60221367.E16"); // ("6.0221367E23");

	public void test_prec_minus() {
		assertEquals(2, real1_2.minus(real1_3).precision().intValue());
	}
	public void test_prec_minus2() {
		assertEquals(2, real1_3.minus(real1_2).precision().intValue());
	}
	public void test_prec_plus() {
		assertEquals(2, real1_2.plus(real1_3).precision().intValue());
	}
	public void test_prec_plus2() {
		assertEquals(2, real1_3.plus(real1_2).precision().intValue());
	}
	public void test_prec_plus3() {
		assertEquals(3, real0_3.plus(real0_2).precision().intValue());
	}
	public void test_prec_times() {
		assertEquals(2, real1_2.times(real1_3).precision().intValue());
	}
	public void test_prec_times2() {
		assertEquals(2, real1_3.times(real1_2).precision().intValue());
	}
	public void test_prec_times3() {
		assertEquals(2, real1_2.times(real1_1).precision().intValue());
	}
	public void test_prec_times4() {
		assertEquals(2, real1_2.times(REALdoubleAdapter.HALF).precision().intValue());
	}

	public void test_prec1() {
		//System.err.println("0.04 " + REALdoubleAdapter.valueOf(0.04,INTlongAdapter.TWO) + " " + REALdoubleAdapter.valueOf("0.04").precision());
		//System.err.println(REALdoubleAdapter.formatDouble(1.04, INTlongAdapter.valueOf(3)));
		//System.err.println(REALdoubleAdapter.formatDouble(0.04, INTlongAdapter.valueOf(1)));
		//System.err.println(REALdoubleAdapter.formatDouble(0.04, INTlongAdapter.valueOf(3)));
		//System.err.println(REALdoubleAdapter.formatDouble(0.04, INTlongAdapter.valueOf(2)));
		//System.err.println(REALdoubleAdapter.formatDouble(0.04, REALdoubleAdapter.INFINITE_PRECISION));
		//System.err.println(REALdoubleAdapter.formatDouble(0.04, REALdoubleAdapter.DOUBLE_PRECISION));
		assertEquals("0.04", REALdoubleAdapter.valueOf(0.04,INTlongAdapter.ONE).toString());
	}

	public void test_Avog1() {
		//System.err.println("XXXXXX: " +  umol_L_0_0_6);
		//System.err.println("XXXXXW: " +  umol_L_0_0_6.width());
		//System.err.println("XXXXXLVD: " +  umol_L_0_0_6_low_value.doubleValue());
		//System.err.println("XXXXXH: " +  umol_L_0_0_6.high());
		//System.err.println("XXXXXLVP: " +  umol_L_0_0_6_low_value.precision());
		assertEquals("61234.e20", realAvog.toString());		
	}

	public void test_Avog2() {
		//System.err.println("RXXXXX: " +  umol_L_0_0_6);
		//System.err.println("NNNNNNNP: " +  r_umol_a.minus(r_umol_b.times(REALdoubleAdapter.HALF)).precision());
		//System.err.println("RXXXXLVD: " +  r_umol_L_0_0_6_low_value.doubleValue());
		//System.err.println("RXXXXLVP: " +  r_umol_L_0_0_6_low_value.precision());
		//System.err.println("R2XXXLVP: " +  r_umol_L.precision());
		//System.err.println("R3XXXLVP: " +  r_umol_L_6.precision());
		//System.err.println("R4XXXLVP: " +  r_umol_L_0_0.precision());
		//System.err.println("R5XXXLVP: " +  r_umol_L_0_0.minus(r_umol_L_6).precision());
		//System.err.println("R51XXLVP: " +  r_umol_L_0_0.minus(r_umol_L_6).times(REALdoubleAdapter.HALF).precision());
		//System.err.println("R6XXXLVP: " +  r_umol_L_0_0.plus(r_umol_L_6).precision());
		assertEquals("61234.e20", realAvog.toString());		
	}

	public void test_Literals1() {		
		assertEquals("0", real0_1.toString());
	}
	public void test_Literals2() {		
		assertEquals("0.", real0_11.toString());
	}
	public void test_Literals3() {		
		assertEquals("0.0", real0_2.toString());
	}
	public void test_Literals4() {		
		assertEquals("0.00", real0_3.toString());
	}
	public void test_Literals5() {		
		assertEquals("0.00", real0_4.toString());
	}
	public void test_Literals6() {		
		assertEquals("250", real250_1.toString());
	}
	public void test_Literals7() {		
		assertEquals("250.0", real250_2.toString());
	}
	public void test_Literals8() {		
		assertEquals("250.0", real250_3.toString());
	}
	public void test_Literals9() {		
		assertEquals("250.00", real250_4.toString());
	}
	public void test_LiteralsA() {		
		assertEquals("250.", real250_9.toString());
	}
	public void test_LiteralsB() {		
		assertEquals("1", real1_1.toString());
	}
	public void test_LiteralsC() {		
		assertEquals("1.0", real1_2.toString());
	}
	public void test_LiteralsD() {		
		assertEquals("1.00", real1_3.toString());
	}
	public void test_LiteralsE() {		
		assertEquals("0.3", real03_1.toString());
	}
	public void test_LiteralsF() {		
		assertEquals("0.3", real03_2.toString());
	}
	public void test_LiteralsG() {		
		assertEquals("0.30", real03_3.toString());
	}
	public void test_LiteralsH() {		
		assertEquals("0.33", real03_4.toString());
	}
	public void test_LiteralsI() {		
		assertEquals("0.303", real03_5.toString());
	}
	public void test_LiteralsJ() {				
		assertEquals("0.254", real0254_6.toString());
	}
	public void test_LiteralsK() {		
		assertEquals("0.254", real0254_7.toString());
	}
	public void test_LiteralsL() {		
		assertEquals("0.254", real0254_8.toString());
	}
	public void test_LiteralsM() {		
		assertEquals("254e-3", real0254_9.toString());
	}
	public void test_LiteralsN() {		
		assertEquals("254e-3", real0254_10.toString());
	}
	public void test_LiteralsO() {		
		assertEquals("254e-4", real00254_e.toString());
	}
	public void test_LiteralsP() {		
		assertEquals(real00254_1.doubleValue(), 0.0254);
	}
	public void test_LiteralsQ() {		
		assertEqual(real00254_2, real00254_1);
	}
	public void test_LiteralsR() {		
		assertEqual(real00254_1, real00254_2);
	}
	public void test_LiteralsS() {		
		assertEquals("0.0254", real00254_1.toString());
	}

	public void test_LiteralsR1() {		
		String s = "12345.000";
		assertEquals(s, ValueFactory.getInstance().REALvalueOfLiteral(s).toString());
	}
	public void test_LiteralsR2() {		
		String s = "12345000.e1";
		assertEquals(s, ValueFactory.getInstance().REALvalueOfLiteral(s).toString());
	}
	public void test_LiteralsR3() {		
		assertEquals("123.e4", ValueFactory.getInstance().REALvalueOf(12345.e2,3).toString());
	}
	public void test_LiteralsR4() {		
		String s = "123.45000";
		assertEquals(s, ValueFactory.getInstance().REALvalueOfLiteral(s).toString());
	}
	public void test_LiteralsR5() {		
		assertEquals("123.", ValueFactory.getInstance().REALvalueOf(12345.e-2,3).toString());
	}
	public void test_LiteralsR6() {		
		assertEquals("0.00012345000", ValueFactory.getInstance().REALvalueOf(12345.e-8,8).toString());
	}
	public void test_LiteralsR7() {		
		assertEquals("0.00012345", ValueFactory.getInstance().REALvalueOf(12345.e-8,5).toString());
	}	
	public void test_LiteralsR8() {		
		assertEquals("0.0001235", ValueFactory.getInstance().REALvalueOf(1.2345e-4,4).toString());
	}

	private void assertEqual(ANY a, ANY b) {
		assertTrue(""+a+".equal("+b+")", a.equal(b).isTrue());
	}

	public void test_doubles() {
		assertEquals("0", real0_5.toString());
		
		assertEquals("250", real250_5.toString());
		assertEquals("250.000000000000", real250_6.toString());
		assertEquals("250.000000000000", real250_7.toString());
		assertEquals("250.000000000000", real250_8.toString());
		assertEquals("250", real250_5.toString());		

		assertEquals("0.300000000000000", real03_6.toString());
		assertEquals("0.300000000000000", real03_7.toString());
		assertEquals("0.300000000000000", real03_8.toString());
		assertEquals("0.330000000000000", real03_9.toString());
		assertEquals("0.303000000000000", real03_10.toString());

		assertEquals("0.254000000000000", real0254_1.toString());
		assertEquals("0.254000000000000", real0254_2.toString());
		assertEquals("0.254000000000000", real0254_3.toString());
		assertEquals("0.254000000000000", real0254_4.toString());
		assertEquals("0.254000000000000", real0254_5.toString());
	}

	public void test_p1() {
		assertEquals(4, REALdoubleAdapter.valueOf("2000.").precision().intValue());
	}

	public void test_p2() {
		assertEquals(1, REALdoubleAdapter.valueOf("2.e3").precision().intValue());
	}

	public void test_p3() {
		assertEquals(1, REALdoubleAdapter.valueOf("0.001").precision().intValue());
	}

	public void test_p4() {
		assertEquals(1, REALdoubleAdapter.valueOf("1.e-3").precision().intValue());
	}

	public void test_p5() {
		assertEquals(1, REALdoubleAdapter.valueOf("0.").precision().intValue());
	}

	public void test_p6() {
		assertEquals(2, REALdoubleAdapter.valueOf("0.0").precision().intValue());
	}

	public void test_p7() {
		assertEquals(2, REALdoubleAdapter.valueOf("000.0").precision().intValue());
	}

	public void test_p8() {
		assertEquals(3, REALdoubleAdapter.valueOf("0.00").precision().intValue());
	}

	public void test_p9() {
		assertEquals(3, REALdoubleAdapter.valueOf("4.10").precision().intValue());
	}

	public void test_p10() {
		assertEquals(3, REALdoubleAdapter.valueOf("4.09").precision().intValue());
	}

	public void test_p11() {
		assertEquals(2, REALdoubleAdapter.valueOf("4.1").precision().intValue());
	}

	public void test_p12() {
		assertEquals(1, REALdoubleAdapter.valueOf("0.04").precision().intValue());
	}

	//	public void test_precprop1() { assertEquals(R("25.e2"), R("1234").times(R("2.0"))); }
	public void test_precprop1s() { assertEquals(R("25.e2").toString(), R("1234").times(R("2.0")).toString()); }
	// public void test_precprop1_1() { assertEquals(R("24.e2"), R("1222").times(R("2.0"))); }
	public void test_precprop1_1s() { assertEquals(R("24.e2").toString(), R("1222").times(R("2.0")).toString()); }
	public void test_precprop2() { assertEquals(R("1500."), R("2000.").minus(R("500."))); }
	public void test_precprop2s() { assertEquals(R("1500.").toString(), R("2000.").minus(R("500.")).toString()); }
	public void test_precprop3() { assertEquals(R("1500."), R("2000.000").minus(R("500."))); }
	public void test_precprop3s() { assertEquals(R("1500.").toString(), R("2000.000").minus(R("500.")).toString()); }
	public void test_precprop4() { assertEquals(R("1500."), R("2000.").minus(R("500.000"))); }
	public void test_precprop4s() { assertEquals(R("1500.").toString(), R("2000.").minus(R("500.000")).toString()); }
	public void test_precprop5() { assertEquals(R("1500.00").toString(), R("2000.00").minus(R("500.000")).toString()); }
	public void test_precprop5s() { assertEquals(R("1500.00"), R("2000.00").minus(R("500.000"))); }
	public void test_precprop6() { assertEquals(R("0.04"), R("1000.39").minus(R("1000.35"))); }
	public void test_precprop6s() { assertEquals(R("0.04").toString(	), R("1000.39").minus(R("1000.35")).toString()); }
}

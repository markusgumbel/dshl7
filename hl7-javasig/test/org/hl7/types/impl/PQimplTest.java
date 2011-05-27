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

import org.hl7.types.ANY;
import org.hl7.types.INT;
import org.hl7.types.PQ;
import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;

/**
 * * A test suite that exercises {@link PQimpl}class.
 * 
 * @author Geoffry Roberts, Gunther Schadow
 */
public class PQimplTest extends TestCase
{
	REAL one = ValueFactory.getInstance().REALvalueOfLiteral("1");
	REAL ten = ValueFactory.getInstance().REALvalueOfLiteral("10");
	REAL hundred = ValueFactory.getInstance().REALvalueOfLiteral("100");
	REAL two = ValueFactory.getInstance().REALvalueOfLiteral("2");
	REAL three = ValueFactory.getInstance().REALvalueOfLiteral("3");

	INT powerTwo = INTlongAdapter.TWO;

	PQ oneDay = PQimpl.valueOf(one, "d");
	PQ oneDay2 =PQimpl.valueOf(one, "d2");
	PQ oneMeter = PQimpl.valueOf(one, "m");
	PQ oneCentimeter = PQimpl.valueOf(one, "cm");
	PQ hundredCentimeter = PQimpl.valueOf(hundred, "cm");
	PQ thirtysevenCelsius = PQimpl.valueOf("37.0", "Cel");
	PQ thirtysevenPointEightCelsius = PQimpl.valueOf("37.8", "Cel");
	PQ onehundredFahrenheit = PQimpl.valueOf("100.0", "[degF]");
	PQ ninetyeightPointSixFahrenheit = PQimpl.valueOf("98.6", "[degF]");
	PQ zeroKelvin = PQimpl.valueOf("0", "K");
	PQ oneFoot = PQimpl.valueOf(one, "[ft_i]");
	PQ oneInch = PQimpl.valueOf(one, "[in_i]");
	PQ twoPointFiveFourCentimeter = PQimpl.valueOf("254e-2", "cm");

	public void test_InputOutput() {
		String magnitudeString = "250.0";
		String unitString = "mg";
		PQ pq = PQimpl.valueOf(magnitudeString, unitString);
		assertEquals(pq.value().toString(), magnitudeString);
		//toREAL() and value() are not equivalent!!!!!, so the deprecated value() should roll back to regular function!!!!!
		//assertEquals(pq.toREAL().toString(), magnitudeString);
		assertEquals(pq.unit().toString(), unitString);

		assertEquals(thirtysevenCelsius.toString(), "37.0 Cel");
		assertEquals(onehundredFahrenheit.toString(), "100.0 [degF]");
	}

	public void test_InputOutput2() {
		String magnitudeString = "250";
		String unitString = "[in_i]";
		PQ pq = PQimpl.valueOf(magnitudeString, unitString);
		assertEquals(pq.value().toString(), magnitudeString);

		magnitudeString = "250.000";
		unitString = "[in_i]";
		pq = PQimpl.valueOf(magnitudeString, unitString);
		assertEquals(pq.value().toString(), magnitudeString);
	}

	public void test_oneDayTimesOne() {
		PQ oneDayTimesOne = oneDay.times(one);
		assertTrue(oneDayTimesOne.equal(oneDay).isTrue());
		assertFalse(oneDayTimesOne.equal(oneDay).isFalse());
	}

	public void test_temperatures() {
		//System.out.println(thirtysevenCelsius);
		//System.out.println(thirtysevenCelsius.expressedIn(onehundredFahrenheit));
		//System.out.println(onehundredFahrenheit+":"+onehundredFahrenheit.value()+":"+onehundredFahrenheit.value().doubleValue()+":"+onehundredFahrenheit.value().precision());
		//System.out.println(onehundredFahrenheit.canonical()+":"+onehundredFahrenheit.canonical().value()+":"+onehundredFahrenheit.canonical().value().doubleValue()+":"+onehundredFahrenheit.canonical().value().precision());
		//System.out.println(onehundredFahrenheit.expressedIn(thirtysevenCelsius));
		//System.out.println(ninetyeightPointSixFahrenheit);
		//System.out.println(ninetyeightPointSixFahrenheit.expressedIn(thirtysevenCelsius));
		//System.out.println(thirtysevenPointEightCelsius+":"+thirtysevenPointEightCelsius.value()+":"+thirtysevenPointEightCelsius.value().doubleValue()+":"+thirtysevenPointEightCelsius.value().precision());
		//System.out.println(thirtysevenPointEightCelsius.canonical()+":"+thirtysevenPointEightCelsius.canonical().value()+":"+thirtysevenPointEightCelsius.canonical().value().doubleValue()+":"+thirtysevenPointEightCelsius.canonical().value().precision());
		//System.out.println(thirtysevenPointEightCelsius.expressedIn(onehundredFahrenheit));
		assertEqual(thirtysevenCelsius, ninetyeightPointSixFahrenheit);
		assertEquals(onehundredFahrenheit.canonical().toString(), thirtysevenPointEightCelsius.canonical().toString());
	}

	public void test_oneDaySquare() {
		PQ oneDayTimesOneDay = oneDay.times(oneDay);
		PQ oneDaySquare = oneDay.power(powerTwo);

		assertTrue(oneDayTimesOneDay.equal(oneDay2).isTrue());
		assertFalse(oneDayTimesOneDay.equal(oneDay2).isFalse());

		assertTrue(oneDaySquare.equal(oneDay2).isTrue());
		assertFalse(oneDaySquare.equal(oneDay2).isFalse());
	}

	private void assertEqual(ANY a, ANY b) {
		assertTrue(""+a+".equal("+b+")", a.equal(b).isTrue());
	}

	public void test_inchFootCentimeter() {
		assertEqual(oneMeter,hundredCentimeter);
		//System.err.println("###" + oneInch + " v=" + oneInch.canonical().value().toString() + " m=" + oneInch.canonical().value().doubleValue());
		//System.err.println("###" + twoPointFiveFourCentimeter + " v=" + twoPointFiveFourCentimeter.canonical().value().toString() + " m=" + twoPointFiveFourCentimeter.canonical().value().doubleValue());
		assertEqual(oneInch,twoPointFiveFourCentimeter);
		assertEqual(oneFoot,oneInch.times(two.times(three).times(two)));
		assertEqual(oneFoot,twoPointFiveFourCentimeter.times(two.times(three).times(two)));
	}
}

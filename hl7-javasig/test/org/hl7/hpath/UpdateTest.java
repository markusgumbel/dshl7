package org.hl7.hpath;

import java.util.Arrays;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;

import org.hl7.meta.impl.DatatypeMetadataFactoryDatatypes;
import org.hl7.meta.impl.DatatypeMetadataFactoryImpl;
import org.hl7.xml.parser.StandaloneDataTypeContentHandler;

import org.hl7.types.ValueFactory;
import org.hl7.types.EN;
import org.hl7.types.PN;
import org.hl7.types.TS;
import org.hl7.types.TEL;
import org.hl7.types.BAG;
import org.hl7.rim.Person;
import org.hl7.rim.impl.PersonImpl;

public class UpdateTest extends TestCase {

	public static final String HL7_URI = "urn:hl7-org:v3";
	private PN _name;
	private TEL _telecom;
	private BAG<TEL> _telecoms;
	private Person _person;

	public void setUp() throws Exception {
		super.setUp();
		
		_name = (PN) StandaloneDataTypeContentHandler.parseValue(new ByteArrayInputStream(("<name xmlns=\"" + HL7_URI + "\">" + "<given>John</given> <given>W</given> <family>Doe</family></name>").getBytes()), DatatypeMetadataFactoryDatatypes.instance().PNTYPE);

		_telecom = (TEL) StandaloneDataTypeContentHandler.parseValue(new ByteArrayInputStream(("<telecom xmlns=\"" + HL7_URI + "\" value=\"tel:+1 317 123 4567\" use=\"WP\"/>").getBytes()), DatatypeMetadataFactoryDatatypes.instance().TELTYPE);
		
		_telecoms = (BAG<TEL>) StandaloneDataTypeContentHandler.parseValue(new ByteArrayInputStream(("<matt xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"" + HL7_URI + "\" xsi:type=\"BAG\"><a xsi:type=\"TEL\" value=\"tel:+1 317 123 4567\" use=\"WP\"/><a xsi:type=\"TEL\" value=\"tel:+1 317 321 7654\" use=\"HP\"/></matt>").getBytes()), DatatypeMetadataFactoryImpl.instance().create("BAG<TEL>"));

		_person = new PersonImpl();
	}

	private <T> T update(T context, String expressionString, Object value) {
		Expression expression = Expression.valueOf(expressionString);
		return (T)expression.update(context, value);
	}
	
	public void test001() {
		String expr = "#given[1].STasString";
		PN name = update(_name, expr, "Hans");
		assertTrue("same number of name parts", name.length() != _name.length());
	  String oldName = (String)Expression.valueOf(expr).evaluate(_name).iterator().next();
	  String newName = (String)Expression.valueOf(expr).evaluate(name).iterator().next();
		assertEquals(oldName, "John");		
		assertEquals(newName, "Hans");		
	}

	public void test002() {
		String expr = "[scheme.code.equal('tel').and(use.implies(CS:TelecommunicationAddressUse:WorkPlaceAddressUse))][1].address.STasString";
		TEL telecom = update(_telecom, expr, "+1 317 987 6543");
		assertEquals("same protocol", telecom.scheme(), _telecom.scheme());
	  String oldValue = (String)Expression.valueOf(expr).evaluate(_telecom).iterator().next();
	  String newValue = (String)Expression.valueOf(expr).evaluate(telecom).iterator().next();
		assertEquals(oldValue, "+1 317 123 4567");		
		assertEquals(newValue, "+1 317 987 6543");		
	}

	public void test003() {
		String expr = "[scheme.code.equal('tel').and(use.implies(CS:TelecommunicationAddressUse:WorkPlaceAddressUse))][1].address.STasString";
		BAG<TEL> oldValue = _telecoms;
		BAG<TEL> newValue = update(oldValue, expr, "+1 317 987 6543");
		assertEquals("same number of elements", newValue.size(), oldValue.size());
	  String oldPart = (String)Expression.valueOf(expr).evaluate(oldValue).iterator().next();
	  String newPart = (String)Expression.valueOf(expr).evaluate(newValue).iterator().next();
		assertEquals(oldPart, "+1 317 123 4567");		
		assertEquals(newPart, "+1 317 987 6543");		
	}

	public void test004() {
		String expr = "[scheme.code.equal('tel').and(use.implies(CS:TelecommunicationAddressUse:PrimaryHome))][1].address.STasString";
		BAG<TEL> oldValue = _telecoms;
		BAG<TEL> newValue = update(oldValue, expr, "+1 317 987 6543");
		assertEquals("same number of elements", newValue.size(), oldValue.size());
	  String oldPart = (String)Expression.valueOf(expr).evaluate(oldValue).iterator().next();
	  String newPart = (String)Expression.valueOf(expr).evaluate(newValue).iterator().next();
		assertEquals(oldPart, "+1 317 321 7654");		
		assertEquals(newPart, "+1 317 987 6543");		
	}

	public void no_test005() {
		String expr = "[scheme.code.equal('tel').and(use.implies(CS:TelecommunicationAddressUse:Pager))!$VALUE_FACTORY.TELvalueOf('tel:333',CS:TelecommunicationAddressUse:Pager,null)][1].address.STasString";
		BAG<TEL> oldValue = _telecoms;
		BAG<TEL> newValue = update(oldValue, expr, "+1 317 987 6543");
		assertEquals("same number of elements", newValue.size(), oldValue.size());
	  String oldPart = (String)Expression.valueOf(expr).evaluate(oldValue).iterator().next();
	  String newPart = (String)Expression.valueOf(expr).evaluate(newValue).iterator().next();
		assertEquals(oldPart, "333");		
		assertEquals(newPart, "+1 317 987 6543");		
	}

	public void test006() {
		String expr = "birthTime";
		Person oldObject = _person;
		TS someValue = ValueFactory.getInstance().TSvalueOfLiteral("19780506");
		oldObject.setBirthTime(someValue);
		TS newValue = ValueFactory.getInstance().TSvalueOfLiteral("19870605");
	  Person newObject = update(oldObject, expr, newValue);
		assertEquals(oldObject, newObject);
		assertEquals(newValue, newObject.getBirthTime());
	}

	public void test007() {
		String expr = "birthTime";
		Person oldObject = _person;
		oldObject.setBirthTime(null);
		TS newValue = ValueFactory.getInstance().TSvalueOfLiteral("19870605");
	  Person newObject = update(oldObject, expr, newValue);
		assertEquals(oldObject, newObject);
		assertEquals(newValue, newObject.getBirthTime());
	}

	public void test008() {
		String expr = "birthTime";
		Person oldObject = _person;
		oldObject.setBirthTime(null);
		TS newValue = ValueFactory.getInstance().TSvalueOfLiteral("19870605");
		Expression<Person, TS> exp = (Expression<Person, TS>)Expression.valueOf(expr);
		Evaluation<Person, TS> evl = exp.evaluate(oldObject);
		EvaluationIterator<Person, TS> i = evl.iterator();
		i.beginUpdate();
		assertFalse(i.hasNext());
		i.insert(newValue);
		Person newObject = i.finalizeUpdates();
		assertEquals(oldObject, newObject);
		assertEquals(newValue, newObject.getBirthTime());
	}

	/** This test currently fails. It's trying to update a name that doesn't exist. */
	public void no_test009() {
		String expr = "name[1]";
		Person oldObject = _person;
		oldObject.setName(null);
		PN newValue = _name;
	  Person newObject = update(oldObject, expr, newValue);
		assertEquals(oldObject, newObject);
		assertEquals(newValue, newObject.getName());
	}

	/** This test is the same as test009 but it does not fail because the name is not Java null but HL7 null.
			That way it gets itemized and the itemizerIterator can handle the update as adding to the name bag.
	*/
	public void test010() {
		String expr = "name[1]";
		Person oldObject = _person;
		oldObject.setName((BAG<EN>)ValueFactory.getInstance().nullValueOf("BAG", "NI"));
		PN newValue = _name;
	  Person newObject = update(oldObject, expr, newValue);
		assertEquals(oldObject, newObject);
		assertEquals(newValue, newObject.getName());
	}

	public void test011() {
		String expr = "name[1]#given[1].STasString";
		Person oldObject = _person;
		oldObject.setName((BAG<EN>)ValueFactory.getInstance().nullValueOf("BAG", "NI"));
	  String newValue = "Hans";
	  Person newObject = update(oldObject, expr, newValue);
	  String retrievedValue = (String)Expression.valueOf(expr).evaluate(newObject).iterator().next();
		assertEquals(oldObject, newObject);
		assertEquals(newValue, retrievedValue);
	}
}




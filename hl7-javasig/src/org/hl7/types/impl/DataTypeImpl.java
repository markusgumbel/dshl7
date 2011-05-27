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

import org.hl7.types.CS;
import org.hl7.types.DataType;

public class DataTypeImpl implements DataType {
	// -------------------------------------------------------------------------
	public static final DataTypeImpl AD = new DataTypeImpl("AD", "Postal Address");
	public static final DataTypeImpl ADXP = new DataTypeImpl("ADXP", "Address Part");
	public static final DataTypeImpl BAG = new DataTypeImpl("BAG", "Bag");
	public static final DataTypeImpl BIN = new DataTypeImpl("BIN", "Binary Data");
	public static final DataTypeImpl BL = new DataTypeImpl("BL", "Boolean");
	public static final DataTypeImpl CD = new DataTypeImpl("CD", "Concept Descriptor");
	public static final DataTypeImpl CE = new DataTypeImpl("CE", "Coded with Equivalents");
	public static final DataTypeImpl CR = new DataTypeImpl("CR", "Concept Role");
	public static final DataTypeImpl CS = new DataTypeImpl("CS", "Coded Simple Value");
	public static final DataTypeImpl CV = new DataTypeImpl("CV", "Coded Value");
	public static final DataTypeImpl ED = new DataTypeImpl("ED", "Encapsulated Data");
	public static final DataTypeImpl EIVL = new DataTypeImpl("EIVL", "Event-Related Periodic Interval of Time");
	public static final DataTypeImpl EN = new DataTypeImpl("EN", "Entity Name");
	public static final DataTypeImpl ENXP = new DataTypeImpl("ENXP", "Entity Name Part");
	// DEPRECATED!! USE QSET INSTEAD
	public static final DataTypeImpl GTS = new DataTypeImpl("GTS", "General Timing Specification");
	public static final DataTypeImpl II = new DataTypeImpl("II", "Instance Identifier");
	public static final DataTypeImpl INT = new DataTypeImpl("INT", "Integer Number");
	public static final DataTypeImpl IVL = new DataTypeImpl("IVL", "Interval");
	public static final DataTypeImpl LIST = new DataTypeImpl("LIST", "Sequence");
	public static final DataTypeImpl MO = new DataTypeImpl("MO", "Monetary Amount");
	public static final DataTypeImpl ON = new DataTypeImpl("ON", "Organization Name");
	public static final DataTypeImpl ONXP = new DataTypeImpl("ONXP", "Organization Name Part");
	public static final DataTypeImpl PIVL = new DataTypeImpl("PIVL", "Periodic Interval of Time");
	public static final DataTypeImpl PN = new DataTypeImpl("PN", "Person Name");
	public static final DataTypeImpl PNXP = new DataTypeImpl("PNXP", "Person Name Part");
	public static final DataTypeImpl PQ = new DataTypeImpl("PQ", "Physical Quantity");
	public static final DataTypeImpl PQR = new DataTypeImpl("PQR", "Physical Quantity Representation");
	public static final DataTypeImpl OID = new DataTypeImpl("OID", "ISO Object Identifier");
	public static final DataTypeImpl QSET = new DataTypeImpl("QSET", "Totally Ordered Set of Continuous Quantities");
	public static final DataTypeImpl QTY = new DataTypeImpl("QTY", "Abstract Type Quantity");
	public static final DataTypeImpl REAL = new DataTypeImpl("REAL", "Real Number");
	public static final DataTypeImpl RTO = new DataTypeImpl("RTO", "Ratio");
	public static final DataTypeImpl SC = new DataTypeImpl("SC", "Character String with Code");
	public static final DataTypeImpl SET = new DataTypeImpl("SET", "Set");
	public static final DataTypeImpl ST = new DataTypeImpl("ST", "Character String");
	public static final DataTypeImpl TEL = new DataTypeImpl("TEL", "Telecommunicatio Address");
	public static final DataTypeImpl TN = new DataTypeImpl("TN", "Trivial Name");
	public static final DataTypeImpl TNXP = new DataTypeImpl("TNXP", "Trivial Name Part");
	public static final DataTypeImpl TS = new DataTypeImpl("TS", "Point in Time");
	public static final DataTypeImpl UID = new DataTypeImpl("UID", "Unique Idetifier String");
	public static final DataTypeImpl URL = new DataTypeImpl("URL", "Universal Resource Locator");
	// -------------------------------------------------------------------------
	private final CS shortName_;
	private final CS longName_;
	private final String shortNameJ_;
	private final String longNameJ_;

	// -------------------------------------------------------------------------
	private DataTypeImpl(final String shortName, final String longName) {
		shortName_ = CSimpl.valueOf(shortName, "unknown");
		longName_ = CSimpl.valueOf(longName, "unknown");
		shortNameJ_ = shortName;
		longNameJ_ = longName;
	}

	// -------------------------------------------------------------------------
	public CS shortName() {
		return shortName_;
	}

	public CS longName() {
		return longName_;
	}

	public String shortNameJ() {
		return shortNameJ_;
	}

	public String longNameJ() {
		return longNameJ_;
	}

	public boolean equals(final DataType dataType) {
		return shortNameJ_.equals(dataType.shortNameJ());
	}
}

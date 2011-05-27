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
package org.hl7.types;

import java.util.EnumSet;
import junit.framework.TestCase;
import org.hl7.types.enums.ActMood;
import org.hl7.types.enums.ObservationInterpretation;
import org.hl7.types.enums.TelecommunicationAddressUse;
import org.hl7.types.impl.NullFlavorImpl;

/**
 * * A test suite that exercises
 * {@link CodeValueFactory}
 * class.
 *
 * @author Gunther Schadow
 */
public class CodeValueFactoryTest extends TestCase {

  CodeValueFactory codeValueFactory;
  CV icd9cmLeptospirosis;
  CV icd9cmLeptospirosis2;
  CV icd9cmLeptospirosisIcterohaemorrhagica;
  CV icd9cmBadcode;
  CV ucumPascal;
  CV ucumNewtonPerSquareMeter;
  CV ucumPoundPerSquareInch;
  CV nullFlavorNASK;
  CV nullFlavorNASK2;
  CV nullFlavorUNK;
  CV moodCodeRQO;
  CV moodCodeRQO2;
  CV moodCodeINT;
  CV moodCodeEVN;

  static final String UCUM_CODE_SYSTEM = "2.16.840.1.113883.6.8";
  static final String UCUM_PASCAL_CODE = "Pa";
  static final String UCUM_NEWTON_PER_SQUARE_METER_CODE = "N/m2";
  static final String UCUM_POUND_PER_SQUARE_INCH_CODE = "[lb_av]/[in_i]2";
  static final String ICD9CM_CODE_SYSTEM = "2.16.840.1.113883.6.2";
  static final String ICD9CM_CODE_BADCODE = "BADCODE";
  static final String ICD9CM_CODE_LEPTOSPIROSIS = "100";
  static final String ICD9CM_CODE_LEPTOSPIROSIS_ICTEROHAEMORRHAGICA = "100.0";
  static final String MOOD_CODE_SYSTEM = "2.16.840.1.113883.5.1001";
  static final String MOOD_CODE_EVN = "EVN";
  static final String MOOD_CODE_RQO = "RQO";
  static final String MOOD_CODE_INT = "INT";
  static final String NULL_FLAVOR_CODE_SYSTEM = "2.16.840.1.113883.5.1008";
  static final String NULL_FLAVOR_CODE_UNK = "UNK";
  static final String NULL_FLAVOR_CODE_NASK = "NASK";

  public CodeValueFactoryTest(String name) {
    super(name);
  }

  public void setUp() throws CodeValueFactory.Exception {
    codeValueFactory = CodeValueFactory.getInstance();

    org.hl7.types.impl.PQimpl.valueOf("1","kg");
    ucumPascal = codeValueFactory.valueOf(UCUM_PASCAL_CODE, UCUM_CODE_SYSTEM);
    ucumNewtonPerSquareMeter = codeValueFactory.valueOf(UCUM_NEWTON_PER_SQUARE_METER_CODE, UCUM_CODE_SYSTEM);
    ucumPoundPerSquareInch = codeValueFactory.valueOf(UCUM_POUND_PER_SQUARE_INCH_CODE, UCUM_CODE_SYSTEM);

    nullFlavorNASK = codeValueFactory.valueOf(NULL_FLAVOR_CODE_NASK, NULL_FLAVOR_CODE_SYSTEM);
    nullFlavorNASK2 = codeValueFactory.valueOf(NULL_FLAVOR_CODE_NASK, NULL_FLAVOR_CODE_SYSTEM);
    nullFlavorUNK = codeValueFactory.valueOf(NULL_FLAVOR_CODE_UNK, NULL_FLAVOR_CODE_SYSTEM);

    moodCodeRQO = codeValueFactory.valueOf(MOOD_CODE_RQO, MOOD_CODE_SYSTEM);
    moodCodeRQO2 = codeValueFactory.valueOf(MOOD_CODE_RQO, MOOD_CODE_SYSTEM);
    moodCodeINT = codeValueFactory.valueOf(MOOD_CODE_INT, MOOD_CODE_SYSTEM);
    moodCodeEVN = codeValueFactory.valueOf(MOOD_CODE_EVN, MOOD_CODE_SYSTEM);   

    icd9cmLeptospirosis = codeValueFactory.valueOf(ICD9CM_CODE_LEPTOSPIROSIS, ICD9CM_CODE_SYSTEM);
    icd9cmLeptospirosis2 = codeValueFactory.valueOf(ICD9CM_CODE_LEPTOSPIROSIS, ICD9CM_CODE_SYSTEM);
    icd9cmLeptospirosisIcterohaemorrhagica = codeValueFactory.valueOf(ICD9CM_CODE_LEPTOSPIROSIS_ICTEROHAEMORRHAGICA, ICD9CM_CODE_SYSTEM);

    try {
      icd9cmBadcode = codeValueFactory.valueOf(ICD9CM_CODE_BADCODE, ICD9CM_CODE_SYSTEM);
    } catch(CodeValueFactory.Exception x) {
      icd9cmBadcode = null;
    }
    assertTrue(icd9cmBadcode == null);

    EnumSet<TelecommunicationAddressUse> telecomUseCode = EnumSet.noneOf(TelecommunicationAddressUse.class);
  }
	
  public void tearDown() { }
	
  public void test_equal() {
    assertTrue(ucumPascal.equal(ucumNewtonPerSquareMeter).isTrue());
    assertTrue(ucumPascal.equal(ucumPoundPerSquareInch).isFalse());

    assertTrue(nullFlavorNASK == nullFlavorNASK2);
    assertTrue(nullFlavorNASK == NullFlavorImpl.NASK);
    assertTrue(nullFlavorNASK.equal(nullFlavorNASK2).isTrue());

    assertTrue(moodCodeRQO == moodCodeRQO2);
    assertTrue(moodCodeRQO == ActMood.Request);
    assertTrue(moodCodeRQO.equal(moodCodeRQO2).isTrue());

    assertFalse(icd9cmLeptospirosis.equal(icd9cmLeptospirosis).isFalse());
    assertTrue(icd9cmLeptospirosis.equal(icd9cmLeptospirosis2).isTrue());
    assertTrue(icd9cmLeptospirosis.equal(icd9cmLeptospirosisIcterohaemorrhagica).isFalse());
  }
	
  public void test_implies() {
    assertTrue(nullFlavorNASK.implies(nullFlavorUNK).isTrue());
    assertTrue(NullFlavorImpl.NASK.implies(nullFlavorUNK).isTrue());
    assertTrue(nullFlavorUNK.implies(NullFlavorImpl.NI).isTrue());

    assertTrue(moodCodeRQO.implies(ActMood.CompletionTrack).isTrue());
    assertTrue(ActMood.Request.implies(moodCodeINT).isTrue());
    assertTrue(moodCodeRQO.implies(moodCodeINT).isTrue());

    assertTrue(ObservationInterpretation.HighAlert.implies(ObservationInterpretation.NormalityAlert).isTrue());
    assertTrue(ObservationInterpretation.HighAlert.implies(ObservationInterpretation.NormalityHigh).isTrue());
    assertTrue(ObservationInterpretation.HighAlert.implies(ObservationInterpretation.Normal).isFalse());

    // assertTrue(icd9cmLeptospirosisIcterohaemorrhagica.implies(icd9cmLeptospirosis).isTrue());
  }
}

package org.hl7.types.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Jere Krischel
 * @author nradov
 */
public class PackageTests extends TestCase {
    public static Test suite() {
        final TestSuite suite =
                new TestSuite(PackageTests.class.getPackage().getName());
        suite.addTest(new TestSuite(ADImplTest.class));
        suite.addTest(new TestSuite(ADXPImplTest.class));
        suite.addTest(new TestSuite(ANYimplTest.class));
        suite.addTest(new TestSuite(BAGImplTest.class));
        suite.addTest(new TestSuite(BAGnullTest.class));
        suite.addTest(new TestSuite(BLImplTest.class));
        suite.addTest(new TestSuite(CDImplTest.class));
        suite.addTest(new TestSuite(CEImplTest.class));
        suite.addTest(new TestSuite(CSImplTest.class));
        suite.addTest(new TestSuite(CVImplTest.class));
        suite.addTest(new TestSuite(EDImplTest.class));
        suite.addTest(new TestSuite(ENImplTest.class));
        suite.addTest(new TestSuite(ENXPImplTest.class));
        suite.addTest(new TestSuite(IIImplTest.class));
        suite.addTest(new TestSuite(INTImplTest.class));
        suite.addTest(new TestSuite(IVLimplTest.class));
        suite.addTest(new TestSuite(MOimplTest.class));
        suite.addTest(new TestSuite(NullFlavorImplTest.class));
        suite.addTest(new TestSuite(ONImplTest.class));
        suite.addTest(new TestSuite(PIVLimplTest.class));
        suite.addTest(new TestSuite(PNImplTest.class));
        suite.addTest(new TestSuite(PQimplTest.class));
        suite.addTest(new TestSuite(QSETDifferenceImplTest.class));
        suite.addTest(new TestSuite(QSETIntersectionImplTest.class));
        suite.addTest(new TestSuite(QSETPeriodicHullImplTest.class));
        suite.addTest(new TestSuite(QSETSingularityImplTest.class));
        // suite.addTest(new TestSuite(QSETUnionImplTest.class));
        suite.addTest(new TestSuite(REALdoubleAdapterTest.class));
        suite.addTest(new TestSuite(RTOImplTest.class));
        suite.addTest(new TestSuite(SCImplTest.class));
        suite.addTest(new TestSuite(SETjuSetAdapterTest.class));
        suite.addTest(new TestSuite(STjlStringAdapterTest.class));
        suite.addTest(new TestSuite(TELimplTest.class));
        suite.addTest(new TestSuite(TNImplTest.class));
        suite.addTest(new TestSuite(TSImplTest.class));
        return suite;
    }
}

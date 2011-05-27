package org.hl7.xml;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PackageTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("org.hl7.xml");

        // Individual data type testing 
        suite.addTest(new TestSuite(TestMatt.class));
        suite.addTest(new TestSuite(ADTest.class));
        suite.addTest(new TestSuite(ADXPTest.class));
		suite.addTest(new TestSuite(CDTest.class));
		suite.addTest(new TestSuite(CETest.class));
		suite.addTest(new TestSuite(CSTest.class));
		suite.addTest(new TestSuite(CVTest.class));
        suite.addTest(new TestSuite(EDTest.class));
        suite.addTest(new TestSuite(ENTest.class));
        suite.addTest(new TestSuite(ENXPTest.class));
        suite.addTest(new TestSuite(IITest.class));
		suite.addTest(new TestSuite(INTTest.class));
		suite.addTest(new TestSuite(MOTest.class));
		suite.addTest(new TestSuite(ONTest.class));
        suite.addTest(new TestSuite(PNTest.class));
        suite.addTest(new TestSuite(PQTest.class));
        suite.addTest(new TestSuite(QSETTest.class));
        suite.addTest(new TestSuite(RTOTest.class));
        suite.addTest(new TestSuite(SCTest.class));
        suite.addTest(new TestSuite(STTest.class));
        suite.addTest(new TestSuite(TELTest.class));
        suite.addTest(new TestSuite(TNTest.class));
        suite.addTest(new TestSuite(TSTest.class));

        //This test needs lots of real work to succeed really see Ticket #5 
				//suite.addTest(new TestSuite(TestParseAndBuild.class));

        return suite;
    }

}

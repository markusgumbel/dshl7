package org.hl7.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Jere Krischel, Eric Chen
 */
public class AllTests extends TestCase
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("AllTests");
        suite.addTest(org.hl7.types.impl.PackageTests.suite());
        suite.addTest(org.hl7.util.PackageTests.suite());
        suite.addTest(org.regenstrief.util.PackageTests.suite());
        suite.addTest(org.hl7.hpath.PackageTests.suite());
        suite.addTest(org.hl7.xml.PackageTests.suite());
        suite.addTest(org.hl7.mif.PackageTests.suite());

//        suite.addTest(org.hl7.meta.impl.PackageTests.suite());
//        suite.addTest(org.hl7.xml.builder.PackageTests.suite());
//        suite.addTest(org.hl7.xml.acceptance.tests.PackageTests.suite());

//        suite.addTest(new TestSuite(Jdk15FeaturesTest.class));
        return suite;
    }   
}

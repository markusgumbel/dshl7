package org.hl7.hibernate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Jere Krischel
 */
public class PackageTests extends TestCase
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("org.hl7.hibernate");
        suite.addTest(new TestSuite(DomainPersistenceTest.class));
        return suite;
    }
}

package org.regenstrief.util;

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
      System.err.println("ASDFASDFASDFASDF");
        TestSuite suite = new TestSuite("org.regenstrief.util");
        suite.addTest(new TestSuite(RegexTokenizerTest.class));
        return suite;
    }
}

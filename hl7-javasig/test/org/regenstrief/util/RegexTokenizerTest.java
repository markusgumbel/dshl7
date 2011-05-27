package org.regenstrief.util;

import junit.framework.TestCase;

/**
 * @author Jere Krischel
 */
public class RegexTokenizerTest extends TestCase
{

  public void testSimplePatternNexting()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("a*b", "aaaabaaabaababb");
    assertEquals("aaaabaaabaababb", regexTokenizer.rest());
    assertTrue(regexTokenizer.next());
    assertEquals("aaaab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("aaab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("aab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("ab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("b", regexTokenizer.token());
    assertFalse(regexTokenizer.next());
  }
    
  public void testSimplePatternSeek()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("a*b", "ccccaaaabaaabaababb");
    assertEquals("ccccaaaabaaabaababb", regexTokenizer.rest());
    assertTrue(regexTokenizer.seek());
    assertEquals("cccc", regexTokenizer.skipped());
    assertEquals("aaaab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("aaab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("aab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("ab", regexTokenizer.token());
    assertTrue(regexTokenizer.next());
    assertEquals("b", regexTokenizer.token());
    assertFalse(regexTokenizer.next());
  }
  public void testGroupCount()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("(.*):(.*)", "domain:hl7.org");
    assertTrue(regexTokenizer.next());
    assertEquals("hl7.org", regexTokenizer.group(2));
    assertEquals("domain", regexTokenizer.group(1));
    assertEquals(2, regexTokenizer.groupCount());
  }

  public void testDrug()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("([A-z]+)", "drugname");
    assertTrue(regexTokenizer.next());
    assertEquals("drugname", regexTokenizer.group(1));
    assertEquals(1, regexTokenizer.groupCount());
  }

  public void testDrugAndDrug()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("([A-z]+)\\s+(?:and)\\s+([A-z]+)", "drugname and drug");
    assertTrue(regexTokenizer.next());
    assertEquals("drugname", regexTokenizer.group(1));
    assertEquals("drug", regexTokenizer.group(2));
    assertEquals(2, regexTokenizer.groupCount());
    
    regexTokenizer = new RegexTokenizer("([A-z]+)\\s*\\/{1}\\s*([A-z]+)", "drugname / drug");
    assertTrue(regexTokenizer.next());
    assertEquals("drugname", regexTokenizer.group(1));
    assertEquals("drug", regexTokenizer.group(2));
    assertEquals(2, regexTokenizer.groupCount());
  }

  public void testStrength()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("(\\d+)\\s*(mg){1}", "100 mg");
    assertTrue(regexTokenizer.next());
    assertEquals("100", regexTokenizer.group(1));
    assertEquals("mg", regexTokenizer.group(2));
    assertEquals(2, regexTokenizer.groupCount());
  }

  public void testStrengthRatio()  {
    RegexTokenizer regexTokenizer = new RegexTokenizer("(\\d+)\\s*(mg){1}\\s*:{1}\\s*(\\d+)\\s*(ml){1}", "5 mg : 100ml");
    assertTrue(regexTokenizer.next());
    assertEquals("5", regexTokenizer.group(1));
    assertEquals("mg", regexTokenizer.group(2));
    assertEquals("100", regexTokenizer.group(3));
    assertEquals("ml", regexTokenizer.group(4));
    assertEquals(4, regexTokenizer.groupCount());
    
    regexTokenizer = new RegexTokenizer("(\\d+)\\s*(mg){1}\\s*(?:\\/{1}|:{1})\\s*(\\d+)\\s*(ml){1}", "5 mg / 100ml");
    assertTrue(regexTokenizer.next());
    assertEquals("5", regexTokenizer.group(1));
    assertEquals("mg", regexTokenizer.group(2));
    assertEquals("100", regexTokenizer.group(3));
    assertEquals("ml", regexTokenizer.group(4));
    assertEquals(4, regexTokenizer.groupCount());
  }

 
}

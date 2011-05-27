package org.hl7.types.impl;

import junit.framework.TestCase;

/**
 * @author Jere Krischel
 */
public class NullFlavorImplTest extends TestCase
{
    public void testDisplayNameMethodWithInvalidFlavor()
    {
      /** An invalid NullFlavorImpl can no longer be instantiated!
        NullFlavorImpl nullFlavorImpl = new NullFlavorImpl((short) 32767);
        assertEquals(32767, nullFlavorImpl._mask);
        assertEquals(STnull.NA.hashCode(), nullFlavorImpl.displayName().hashCode());
      **/
    }
    public void testDisplayNameMethodWithNpFlavor()
    {
        NullFlavorImpl nullFlavorImpl = NullFlavorImpl.NP;
        assertEquals(STjlStringAdapter.valueOf("not present"), nullFlavorImpl.displayName());
    }
    public void testDisplayNameMethodWithNotANullFlavor()
    {
        NullFlavorImpl nullFlavorImpl = NullFlavorImpl.NOT_A_NULL_FLAVOR;
        assertEquals(STjlStringAdapter.valueOf("not a null flavor"), nullFlavorImpl.displayName());
    }
    public void testCodeMethodWithInvalidFlavor()
    {
      /** An invalid null flavor can no longer be instantiated
        NullFlavorImpl nullFlavorImpl = new NullFlavorImpl((short) 32767);
        assertEquals(32767, nullFlavorImpl._mask);
        assertEquals(STnull.NA.hashCode(), nullFlavorImpl.code().hashCode());
      */
    }
    
    public void testCodeMethodWithNpFlavor()
    {
        NullFlavorImpl nullFlavorImpl = NullFlavorImpl.NP;
        assertEquals(STjlStringAdapter.valueOf("NP"), nullFlavorImpl.code());
    }
    public void testCodeMethodWithNotANullFlavor()
    {
        NullFlavorImpl nullFlavorImpl = NullFlavorImpl.NOT_A_NULL_FLAVOR;
        assertEquals(STnull.NA.hashCode(), nullFlavorImpl.code().hashCode());
    }
    public void testConstructingWithZero()
    {
      /* An invalid null flavor can no longer be instantiated
        NullFlavorImpl nullFlavorImpl = new NullFlavorImpl((short) 0);
        assertEquals(0, nullFlavorImpl._mask);
        assertEquals(NullFlavorImpl.NA, nullFlavorImpl.nullFlavor());
      */
    }
    public void testConstructingWithNonZero()
    {
      /* An invalid null flavor can no longer be instantiated
        NullFlavorImpl nullFlavorImpl = new NullFlavorImpl((short) 28);
        assertEquals(28, nullFlavorImpl._mask);
        assertEquals(NullFlavorImpl.NOT_A_NULL_FLAVOR.hashCode(), nullFlavorImpl.nullFlavor().hashCode());

        nullFlavorImpl = new NullFlavorImpl((short) 1);
        assertEquals(1, nullFlavorImpl._mask);
        assertEquals(NullFlavorImpl.NOT_A_NULL_FLAVOR.hashCode(), nullFlavorImpl.nullFlavor().hashCode());
      */
    }

}


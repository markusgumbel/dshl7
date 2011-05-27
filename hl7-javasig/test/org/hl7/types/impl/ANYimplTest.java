package org.hl7.types.impl;

import junit.framework.TestCase;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CD;
import org.hl7.types.CR;
import org.hl7.types.ED;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;

/**
 * @author Jere Krischel
 */
public class ANYimplTest extends TestCase
{

    public void testConstructingWithNull()
    {
        ANYimpl anyImpl = new ANYimpl(null)
        {
            public BL equal(ANY x)
            {
                return null;
            }
        };
        assertEquals(NullFlavorImpl.NOT_A_NULL_FLAVOR.hashCode(), anyImpl.nullFlavor().hashCode());
    }

    public void testConstructingWithNullFlavor()
    {
        ANYimpl anyImpl = new ANYimpl(NullFlavorImpl.ASKU)
        {
            public BL equal(ANY x)
            {
                return null;
            }
        };
        assertEquals(NullFlavorImpl.ASKU.hashCode(), anyImpl.nullFlavor().hashCode());
    }

    public void testConstructingWithNullFlavorThatIsNotNullFlavorImpl()
    {
        NullFlavor nullFlavor = new NullFlavor()
        {
            public boolean impliesJ(NullFlavor nullFlavor)
            {
                return false;
            }

            public NullFlavor mostSpecificGeneralization(CD nullFlavor)
            {
                return null;
            }

            public ST code()
            {
                return null;
            }

            public ST displayName()
            {
                return null;
            }

            public UID codeSystem()
            {
                return null;
            }

            public ST codeSystemName()
            {
                return null;
            }

            public ST codeSystemVersion()
            {
                return null;
            }

            public ED originalText()
            {
                return null;
            }

            public LIST<CR> qualifier()
            {
                return null;
            }

            public SET<CD> translation()
            {
                return null;
            }

            public BL implies(CD x)
            {
                return null;
            }

            public NullFlavor nullFlavor()
            {
                return null;
            }

            public BL nonNull()
            {
                return null;
            }

            public BL isNull()
            {
                return null;
            }

            public BL notApplicable()
            {
                return null;
            }

            public BL unknown()
            {
                return null;
            }

            public BL other()
            {
                return null;
            }

            public BL equal(ANY x)
            {
                return null;
            }

            public boolean nonNullJ()
            {
                return false;
            }

            public boolean isNullJ()
            {
                return false;
            }

            public boolean notApplicableJ()
            {
                return false;
            }

            public boolean unknownJ()
            {
                return false;
            }

            public boolean otherJ()
            {
                return false;
            }
            
        };
        
        try
        {
            ANYimpl anyImpl = new ANYimpl(nullFlavor)
            {
                public BL equal(ANY x)
                {
                    return null;
                }
            };
            fail("Should have thrown ClassCastException.");
        }
        catch (ClassCastException success)
        {
        }
    }
    
}

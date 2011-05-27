package org.hl7.types.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.EN;
import org.hl7.types.ENXP;
import org.hl7.types.INT;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.NullFlavor;
import org.hl7.types.ST;
import org.hl7.types.TS;
import org.hl7.types.enums.EntityNamePartType;
import org.hl7.types.enums.EntityNameUse;

/**
 * This class defines unit test code against data type Entity Name ({@link EN}).
 * 
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: nradov $
 * @version Since HL7 SDK v1.2 revision $Revision: 15267 $ date $Date:
 *          2007-03-30 08:35:44 -0700 (Fri, 30 Mar 2007) $
 */
public class ENImplTest extends TestCase {
    // identical but do not share any member instance.
    private EN instance1 = ENimpl.valueOf(getElementList(false));
    private EN instance2 = ENimpl.valueOf(getElementList(false));
    
    private EN instance3 = ENimpl.valueOf(getElementList(true));
    private EN instance4 = ENimpl.valueOf(null);
    private EN instance5 = ENimpl.valueOf(null);
    
    private static final String FIRST_NAME = "John";
    private static final String MIDDLE_NAME = "Doe";
    private static final String LAST_NAME = "Smith";
    
    private List<ENXP> getElementList(final boolean allCap) {
        final List<ENXP> elementList = new ArrayList<ENXP>();
        if (allCap) {
            elementList
                    .add(ENXPimpl.valueOf(FIRST_NAME.toUpperCase(Locale.US)));
            elementList.add(ENXPimpl
                    .valueOf(MIDDLE_NAME.toUpperCase(Locale.US)));
            elementList.add(ENXPimpl.valueOf(LAST_NAME.toUpperCase(Locale.US)));
        } else {
            elementList.add(ENXPimpl.valueOf(FIRST_NAME));
            elementList.add(ENXPimpl.valueOf(MIDDLE_NAME));
            elementList.add(ENXPimpl.valueOf(LAST_NAME));
        }
        return elementList;
    }
    
    /**
     * Test the {@link ENimpl#equal(org.hl7.types.ANY)} method.
     */
    public void testEqual() {
        // try a different data type
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Test"));
            }
        }).equal(STjlStringAdapter.valueOf("Test")).isFalse());
        
        // try two values that are exactly the same
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }).equal(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        })).isTrue());
        
        // try two LISTs containing the same values but in a different order
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Prefix));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }).equal(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
            }
        })).isFalse());
        
        // try two values that are missing one value
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }).equal(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        })).isFalse());
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }).equal(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        })).isFalse());
        
        // try two values that have different use codes
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }, SETjuSetAdapter.valueOf(new ArrayList<CS>() {
            {
                add(EntityNameUse.Legal);
            }
        })).equal(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
            }
        }, SETjuSetAdapter.valueOf(new ArrayList<CS>() {
            {
                add(EntityNameUse.License);
            }
        }))).isTrue());
        
        // try two values that have different valid times
        assertTrue(ENimpl.valueOf(
                new ArrayList<ENXP>() {
                    {
                        add(ENXPimpl.valueOf("Nicholas",
                                EntityNamePartType.Given));
                        add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                        add(ENXPimpl
                                .valueOf("Radov", EntityNamePartType.Family));
                    }
                },
                IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("19700101000000.000"), TSjuDateAdapter
                        .valueOf("19701231235959.999"), BLimpl.TRUE)).equal(
                ENimpl.valueOf(new ArrayList<ENXP>() {
                    {
                        add(ENXPimpl
                                .valueOf("Radov", EntityNamePartType.Family));
                        add(ENXPimpl.valueOf("Nicholas",
                                EntityNamePartType.Given));
                        add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                    }
                }, IVLimpl.valueOf(BLimpl.TRUE, TSjuDateAdapter
                        .valueOf("20080101000000.000"), TSjuDateAdapter
                        .valueOf("20081231235959.999"), BLimpl.TRUE))).isTrue());
        
        // try a value that isn't an ENimpl
        assertTrue(ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        }).equal(new ENTest()).isTrue());
    }
    
    /**
     * Simple implementation of <code>EN</code> that is <em>not</em> an
     * <code>ENimpl</code>. This allows {@link ENImplTest#testEqual()} to
     * verify that the method under test relies only on the interface and is not
     * dependent on the implementation class.
     */
    /*
     * TODO: Replace this class with calling the
     * java.lang.reflect.Proxy.newProxyInstance(ClassLoader, Class[],
     * InvocationHandler) method instead. That would allow passing all method
     * calls dynamically through to an underlying EN object instead of having to
     * statically declare all the methods in a class. I tried that and it works
     * correctly in Eclipse, but when running a JUnit test from the command line
     * with Ant it fails with this error.
     * 
     * java.lang.IllegalArgumentException: interface org.hl7.types.EN is not
     * visible from class loader
     */
    private static class ENTest implements EN {
        private final EN en = ENimpl.valueOf(new ArrayList<ENXP>() {
            {
                add(ENXPimpl.valueOf("Nicholas", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Owen", EntityNamePartType.Given));
                add(ENXPimpl.valueOf("Radov", EntityNamePartType.Family));
            }
        });
        
        public BL nonEmpty() {
            return en.nonEmpty();
        }
        
        public BL unknown() {
            return en.unknown();
        }
        
        public boolean notApplicableJ() {
            return en.notApplicableJ();
        }
        
        public BL isNull() {
            return en.isNull();
        }
        
        public IVL<TS> useablePeriod() {
            return en.useablePeriod();
        }
        
        public IVL<TS> validTime() {
            return en.validTime();
        }
        
        public LIST<ENXP> tail() {
            return en.tail();
        }
        
        public BL isEmpty() {
            return en.isEmpty();
        }
        
        public INT length() {
            return en.length();
        }
        
        public Iterator<ENXP> iterator() {
            return en.iterator();
        }
        
        public boolean otherJ() {
            return en.otherJ();
        }
        
        public BL nonNull() {
            return en.nonNull();
        }
        
        public NullFlavor nullFlavor() {
            return en.nullFlavor();
        }
        
        public boolean nonNullJ() {
            return en.nonNullJ();
        }
        
        public BL other() {
            return en.other();
        }
        
        public boolean unknownJ() {
            return en.unknownJ();
        }
        
        public DSET<CS> use() {
            return en.use();
        }
        
        public ENXP head() {
            return en.head();
        }
        
        public BL equal(final ANY y) {
            return en.equal(y);
        }
        
        public BL notApplicable() {
            return en.notApplicable();
        }
        
        public boolean isNullJ() {
            return en.isNullJ();
        }
        
        public ST formatted() {
            return en.formatted();
        }
    }
    
    public void testEquals() {
        assertTrue(instance1.equals(instance2));
    }
    
    public void testNULLEquals() {
        assertTrue(instance4.equals(instance5));
    }
    
    public void testNotEquals() {
        assertFalse(instance1.equals(instance3));
    }
    
    /*
     * No hash code test since the underlying data structure uses java.util.List
     */
}
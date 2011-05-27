package org.hl7.meta.impl;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.hl7.meta.Datatype;
import org.hl7.meta.DatatypeMetadataFactory;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.meta.UnknownDatatypeException;
import org.hl7.types.TS;

/**
 * @author Jere Krischel
 */
public class DatatypeMetadataFactoryImplTest extends TestCase {
    private static final DatatypeMetadataFactory DM_FACTORY =
            DatatypeMetadataFactoryImpl.instance();
    
    public void testUnknownDataType() throws Exception {
        final Collection<String> badValues = new ArrayList<String>();
        // should have a comma after the first parameter
        badValues.add("RTO<MO.PQ>");
        // must have '>' immediately after the second parameter
        badValues.add("RTO<MO,PQ.>");
        // cannot have anything after all paramters are read
        badValues.add("TS.");
        
        for (String value : badValues) {
            try {
                DM_FACTORY.create(value);
                // should have a comma after the first parameter
                fail("Should have thrown "
                        + UnknownDatatypeException.class.getName() + " for \""
                        + value + "\"");
            } catch (final UnknownDatatypeException success) {
                // expected
            }
        }
    }
    
    public void testOnTheFlyTranslationOfGTS() throws Exception {
        final Datatype dt = DM_FACTORY.create("GTS");
        
        assertNotNull("create(\"GTS\") returned null", dt);
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        assertEquals("SET<TS>", dt.getFullName());
        
        final Datatype parameter = ((ParametrizedDatatype) dt).getParameter(0);
        assertNotNull("Parameter missing", parameter);
        assertEquals("TS", parameter.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter.getClass()
                .getName());
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a simple data type metadata can be created for
     * <code>TS<code>.
     */
    public void testSimpleDatatype1() throws Exception {
        final Datatype dt = DM_FACTORY.create(TS.class.getSimpleName());
        
        assertNotNull("create returned null", dt);
        assertEquals(SimpleDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        assertEquals(TS.class.getSimpleName(), dt.getFullName());
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a simple data type metadata can be created for
     * <code>QQQ<code> (should fail?).
     */
    public void testSimpleDatatype2() throws Exception {
        Datatype dt = DM_FACTORY.create("QQQ");
        
        assertNotNull("create(\"QQQ\") returned null", dt);
        assertEquals(SimpleDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        assertEquals("QQQ", dt.getFullName());
        
        dt = DM_FACTORY.createByXsiType("QQQ");
        
        assertNotNull("create(\"QQQ\") returned null", dt);
        assertEquals(SimpleDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        assertEquals("QQQ", dt.getFullName());
        
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a parametrized data type metadata can be created for
     * <code>UVP&lt;CD&gt;</code> or <code>UVP_CD</code>.
     */
    public void testParametrized1a() throws Exception {
        Datatype dt = DM_FACTORY.create("UVP<CD>");
        assertNotNull("create(\"UVP<CD>\") returned null", dt);
        assertEquals("UVP<CD>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        ParametrizedDatatype pdt = (ParametrizedDatatype) dt;
        
        assertEquals("UVP", pdt.getType());
        
        Datatype parameter = pdt.getParameter(0);
        assertNotNull("Parameter missing", parameter);
        assertEquals("CD", parameter.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter.getClass()
                .getName());
        
        dt = DM_FACTORY.createByXsiType("UVP_CD");
        
        assertNotNull("create(\"UVP_CD\") returned null", dt);
        assertEquals("UVP<CD>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        pdt = (ParametrizedDatatype) dt;
        
        assertEquals("UVP", pdt.getType());
        
        parameter = pdt.getParameter(0);
        assertNotNull("Parameter missing", parameter);
        assertEquals("CD", parameter.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter.getClass()
                .getName());
        
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a parametrized data type metadata can be created for
     * <code>LIST&lt;INT&gt;</code>. Ignores lots of insignificant spaces.
     */
    public void testParametrized1b() throws Exception {
        Datatype dt = DM_FACTORY.create(" LIST < INT   > ");
        assertNotNull("create(\" LIST < INT   > \") returned null", dt);
        assertEquals("LIST<INT>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        ParametrizedDatatypeImpl pdt = (ParametrizedDatatypeImpl) dt;
        
        assertEquals("LIST", pdt.getType());
        
        Datatype parameter = pdt.getParameter(0);
        assertNotNull("Parameter missing", parameter);
        assertEquals("INT", parameter.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter.getClass()
                .getName());
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a parametrized data type metadata can be created for
     * <code>RTO&lt;MO,PQ&gt;</code>.
     */
    public void testParametrized2() throws Exception {
        Datatype dt = DM_FACTORY.create("RTO<MO,PQ>");
        assertNotNull("create(\"RTO<MO,PQ>\") returned null", dt);
        assertEquals("RTO<MO,PQ>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        ParametrizedDatatype pdt = (ParametrizedDatatype) dt;
        
        assertEquals("RTO", pdt.getType());
        
        Datatype parameter1 = pdt.getParameter(0);
        assertNotNull("Parameter missing", parameter1);
        assertEquals("MO", parameter1.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter1.getClass()
                .getName());
        
        Datatype parameter2 = pdt.getParameter(1);
        assertNotNull("Parameter missing", parameter2);
        assertEquals("PQ", parameter2.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter2.getClass()
                .getName());
        
        dt = DM_FACTORY.createByXsiType("RTO_MO_PQ");
        assertNotNull("create(\"RTO_MO_PQ\") returned null", dt);
        assertEquals("RTO<MO,PQ>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        pdt = (ParametrizedDatatype) dt;
        
        assertEquals("RTO", pdt.getType());
        
        parameter1 = pdt.getParameter(0);
        assertNotNull("Parameter missing", parameter1);
        assertEquals("MO", parameter1.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter1.getClass()
                .getName());
        
        parameter2 = pdt.getParameter(1);
        assertNotNull("Parameter missing", parameter2);
        assertEquals("PQ", parameter2.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter2.getClass()
                .getName());
        
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks that a container data type metadata can be created for
     * <code>LIST&lt;RTO&lt;UVP&lt;PQ&gt;,INT&gt;&gt;</code>.
     */
    public void testComplex() throws Exception {
        Datatype dt = DM_FACTORY.create("LIST<RTO<UVP<PQ>,INT>>");
        assertNotNull("create(\"LIST<RTO<UVP<PQ>,INT>>\") returned null", dt);
        assertEquals("LIST<RTO<UVP<PQ>,INT>>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        ParametrizedDatatype pdtTop = (ParametrizedDatatype) dt;
        assertEquals("LIST", pdtTop.getType());
        
        Datatype parameter1Top = pdtTop.getParameter(0);
        assertNotNull("Parameter missing", parameter1Top);
        assertEquals("RTO<UVP<PQ>,INT>", parameter1Top.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), parameter1Top
                .getClass().getName());
        
        ParametrizedDatatype pdtLevel2 = (ParametrizedDatatype) parameter1Top;
        assertEquals("RTO", pdtLevel2.getType());
        
        Datatype parameter1Level2 = pdtLevel2.getParameter(0);
        assertNotNull("Parameter missing", parameter1Level2);
        assertEquals("UVP<PQ>", parameter1Level2.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), parameter1Level2
                .getClass().getName());
        
        Datatype parameter2Level2 = pdtLevel2.getParameter(1);
        assertNotNull("Parameter missing", parameter2Level2);
        assertEquals("INT", parameter2Level2.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter2Level2
                .getClass().getName());
        
        ParametrizedDatatype pdtLevel3 =
                (ParametrizedDatatype) parameter1Level2;
        assertEquals("UVP", pdtLevel3.getType());
        
        Datatype parameter1Level3 = pdtLevel3.getParameter(0);
        assertNotNull("Parameter missing", parameter1Level3);
        assertEquals("PQ", parameter1Level3.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter1Level3
                .getClass().getName());
        
    }
    
    // -------------------------------------------------------------------------
    /**
     * Checks the difficult to parse datatype spec
     * <code>RTO&lt;RTO&lt;PQ,MO&gt;,INT&gt;</code>.
     */
    public void testNestedParam2() throws UnknownDatatypeException {
        Datatype dt = DM_FACTORY.create("RTO<RTO<PQ,MO>,INT>");
        assertNotNull("create(\"RTO<RTO<PQ,MO>,INT>\") returned null", dt);
        assertEquals("RTO<RTO<PQ,MO>,INT>", dt.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), dt.getClass()
                .getName());
        
        ParametrizedDatatype pdtTop = (ParametrizedDatatype) dt;
        assertEquals("RTO", pdtTop.getType());
        
        Datatype parameter1Top = pdtTop.getParameter(0);
        assertNotNull("Parameter missing", parameter1Top);
        assertEquals("RTO<PQ,MO>", parameter1Top.getFullName());
        assertEquals(ParametrizedDatatypeImpl.class.getName(), parameter1Top
                .getClass().getName());
        
        Datatype parameter2Top = pdtTop.getParameter(1);
        assertNotNull("Parameter missing", parameter2Top);
        assertEquals("INT", parameter2Top.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter2Top
                .getClass().getName());
        
        ParametrizedDatatype pdtLevel2 = (ParametrizedDatatype) parameter1Top;
        assertEquals("RTO", pdtLevel2.getType());
        
        Datatype parameter1Level2 = pdtLevel2.getParameter(0);
        assertNotNull("Parameter missing", parameter1Level2);
        assertEquals("PQ", parameter1Level2.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter1Level2
                .getClass().getName());
        
        Datatype parameter2Level2 = pdtLevel2.getParameter(1);
        assertNotNull("Parameter missing", parameter2Level2);
        assertEquals("MO", parameter2Level2.getFullName());
        assertEquals(SimpleDatatypeImpl.class.getName(), parameter2Level2
                .getClass().getName());
        
    }
}

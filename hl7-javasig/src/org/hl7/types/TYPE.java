package org.hl7.types;

/**
 * <p>
 * A meta-type declared in order to allow the formal definitions to speak about
 * the data type of a value. Any data type defined in this specification is a
 * value of the type DataType.
 * </p>
 * 
 * <pre>
 * private type DataType alias TYPE specializes DataValue {
 *    CS  shortName;
 *    CS  longName;
 *    BL  implies(TYPE that);
 *    BL  compares(TYPE that);
 * };
 * </pre>
 * 
 * @author nradov
 * @see <a
 *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes_r2/datatypes_r2.htm#dt-TYPE"
 *      target="_" title="HL7 Version 7 Standard"></a>
 */
public interface TYPE extends ANY {
    /**
     * 
     * @return
     * @see <a
     *      href="http://www.hl7.org/v3ballot2007sep/html/infrastructure/datatypes_r2/datatypes_r2.htm#prop-DataType.shortName"
     *      target="_" title="HL7 Version 7 Standard">Short Name</a>
     */
    CS shortName();
    
    CS longName();
    
    BL implies(TYPE that);
    
    BL compares(TYPE that);
}

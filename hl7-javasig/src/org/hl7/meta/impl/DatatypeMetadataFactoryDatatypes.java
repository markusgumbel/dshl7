package org.hl7.meta.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hl7.meta.Datatype;
import org.hl7.meta.UnknownDatatypeException;

public class DatatypeMetadataFactoryDatatypes {

  public Datatype TSTYPE; 
  public Datatype QTYTYPE; 
  public Datatype CSTYPE; 
  public Datatype BLTYPE; 
  public Datatype OIDTYPE;
  public Datatype INTTYPE;
  public Datatype REALTYPE;
  public Datatype RTOTYPE;
  public Datatype PQTYPE;
  public Datatype MOTYPE;
  public Datatype ANYTYPE;
  public Datatype STTYPE;
  public Datatype ADTYPE;
  public Datatype ENTYPE;
  public Datatype PNTYPE;
  public Datatype ONTYPE;
  public Datatype CDTYPE;
  public Datatype CETYPE;
  public Datatype CVTYPE;
  public Datatype IITYPE;
  public Datatype TELTYPE;
  public Datatype SET_GENERIC_TYPE;
  public Datatype IVL_GENERIC_TYPE;
  public Datatype PIVL_GENERIC_TYPE;

	private List<Datatype> QTY_SUB_DATATYPES;

	private List<Datatype> ANY_SUB_DATATYPES;

	public final Map<Datatype, List<Datatype>> ABSTRACT_DATATYPES_MAP = new HashMap<Datatype, List<Datatype>>();

	public Set<Datatype> NAME_PART_DATATYPES;

  private static final DatatypeMetadataFactoryDatatypes INSTANCE = new DatatypeMetadataFactoryDatatypes();
  
  private DatatypeMetadataFactoryDatatypes() {
		if (INSTANCE == null) {
			init();
		}
  }
  
  private void init() {
    try {
      TSTYPE = DatatypeMetadataFactoryImpl.instance().create("TS");
      CSTYPE = DatatypeMetadataFactoryImpl.instance().create("CS");
      BLTYPE = DatatypeMetadataFactoryImpl.instance().create("BL");
      OIDTYPE =	DatatypeMetadataFactoryImpl.instance().create("OID");
      INTTYPE = DatatypeMetadataFactoryImpl.instance().create("INT");
      REALTYPE = DatatypeMetadataFactoryImpl.instance().create("REAL");
      RTOTYPE = DatatypeMetadataFactoryImpl.instance().create("RTO");
      PQTYPE = DatatypeMetadataFactoryImpl.instance().create("PQ");
      MOTYPE = DatatypeMetadataFactoryImpl.instance().create("MO");
      QTYTYPE = DatatypeMetadataFactoryImpl.instance().create("QTY");
      ANYTYPE = DatatypeMetadataFactoryImpl.instance().create("ANY");
			// ----------------  Eric Added -----------------------------------
      STTYPE =  DatatypeMetadataFactoryImpl.instance().create("ST");
      ADTYPE = DatatypeMetadataFactoryImpl.instance().create("AD");
      ENTYPE = DatatypeMetadataFactoryImpl.instance().create("EN");
      PNTYPE = DatatypeMetadataFactoryImpl.instance().create("PN");
      ONTYPE = DatatypeMetadataFactoryImpl.instance().create("ON");
      CDTYPE = DatatypeMetadataFactoryImpl.instance().create("CD");
      CETYPE = DatatypeMetadataFactoryImpl.instance().create("CE");
      CVTYPE = DatatypeMetadataFactoryImpl.instance().create("CV");
      IITYPE = DatatypeMetadataFactoryImpl.instance().create("II");
      TELTYPE = DatatypeMetadataFactoryImpl.instance().create("TEL");

      SET_GENERIC_TYPE = DatatypeMetadataFactoryImpl.instance().create("SET");
      IVL_GENERIC_TYPE = DatatypeMetadataFactoryImpl.instance().create("IVL");
      PIVL_GENERIC_TYPE = DatatypeMetadataFactoryImpl.instance().create("PIVL");

      QTY_SUB_DATATYPES = Arrays.asList(new Datatype[]{
        TSTYPE, MOTYPE, RTOTYPE, PQTYPE, INTTYPE, REALTYPE
      });

      ANY_SUB_DATATYPES = Arrays.asList(new Datatype[]{
        TSTYPE, MOTYPE, RTOTYPE, PQTYPE, INTTYPE, REALTYPE, CSTYPE, CDTYPE, CETYPE, CVTYPE, BLTYPE, STTYPE 
      });

      NAME_PART_DATATYPES = new HashSet<Datatype>(Arrays.asList(new Datatype[]{
        ADTYPE, ENTYPE, PNTYPE, ONTYPE
			}));

			ABSTRACT_DATATYPES_MAP.put(QTYTYPE, QTY_SUB_DATATYPES);
			ABSTRACT_DATATYPES_MAP.put(ANYTYPE, ANY_SUB_DATATYPES);

    } catch (UnknownDatatypeException ex) {
      new UnknownDatatypeException(ex.toString());
    }
  }
  
  public static DatatypeMetadataFactoryDatatypes instance() { return INSTANCE; }

}

/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */

package org.hl7.meta.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.hl7.meta.Datatype;
import org.hl7.meta.JavaIts;
import org.hl7.meta.ParametrizedDatatype;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.DSET;
import org.hl7.types.IVL;
import org.hl7.types.LIST;
import org.hl7.types.PIVL;
import org.hl7.types.QSET;
import org.hl7.types.QTY;
import org.hl7.types.RTO;
import org.hl7.types.SET;
import org.hl7.util.FactoryException;
import org.hl7.xml.DatatypePresenterBase;
import org.hl7.xml.DatatypePresenterFactory;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.parser.DynamicContentHandler;
import org.xml.sax.Attributes;

/**
 * Generic data type with one parameter, e.g. <code>LIST&lt;TEL&gt;</code> or
 * <code>UVP&lt;CD&gt;</code>, <code>IVL&lt;TS&gt;</code>.
 *
 * @author Skirmantas Kligys, modified by Gunther Schadow, Eric Chen
 *         <p/>
 *         I unified the versions with 1 or 2 parameters to one with n parameters.
 *         this reduces the number of special cases.
 */
public class ParametrizedDatatypeImpl
  extends DatatypeImpl
  implements ParametrizedDatatype {

  public static final Set<String> purePara = new HashSet<String>(Arrays.asList(new String[]{
    SET.class.getSimpleName(), BAG.class.getSimpleName(), LIST.class.getSimpleName()}));

  //-------------------------------------------------------------------------
  private final String type_;
  private final List<Datatype> parameter_;

  //-----------------------------------------------------------------------
  protected static final Logger LOGGER =
    Logger.getLogger(ParametrizedDatatypeImpl.class.getPackage().getName());

  //-------------------------------------------------------------------------
  public ParametrizedDatatypeImpl(String type, Datatype parameter) {
    super(type + '<' + parameter.getFullName() + '>');
    type_ = type;
    parameter_ = new ArrayList<Datatype>(1);
    parameter_.add(parameter);
  }

  //-------------------------------------------------------------------------
  public ParametrizedDatatypeImpl(String type, Datatype parameter1,
				  Datatype parameter2) {
    super(type + '<' + parameter1.getFullName() + ',' +
	  parameter2.getFullName() + '>');
    type_ = type;
    parameter_ = new ArrayList<Datatype>(2);
    parameter_.add(parameter1);
    parameter_.add(parameter2);
  }

  //-------------------------------------------------------------------------
  public ParametrizedDatatypeImpl(String type,
				  List<Datatype> parameter) {
    super(nameOf(type, parameter));
    type_ = type;
    parameter_ = parameter;
  }

  public Datatype diff() {
    Datatype paramType=this.getParameter(0);
    if (paramType.equals(DatatypeMetadataFactoryDatatypes.instance().TSTYPE)) 
      return DatatypeMetadataFactoryDatatypes.instance().PQTYPE;
    else 
      return paramType;
  }

  public boolean equals(Datatype otherDatatype) {
    return ((otherDatatype instanceof ParametrizedDatatype) &&
	    otherDatatype.getFullName().equals(fullName_));
  }

    /**
     * Descendant classes shall consider overiding this implementation.
     *
     * @return
     */
    public int hashCode()
    {
        /**
         * Implementation Rationale:
         * 1) get class.getName()'s hashCode;
         * 2) get FullName's hashCode;
         */
        int result = getClass().getName().hashCode();
        result = result * 31 + getFullName().hashCode();
        return result;
    }


  //-------------------------------------------------------------------------
  private static String nameOf(String type, List<Datatype> parameter) {
    StringBuffer fnbuf = new StringBuffer(type);
    fnbuf.append('<');
    Iterator<Datatype> iter = parameter.iterator();
    if (iter.hasNext())
      fnbuf.append(iter.next().getFullName());
    while (iter.hasNext()) {
      fnbuf.append(',');
      fnbuf.append(iter.next().getFullName());
    }
    fnbuf.append('>');
    return fnbuf.toString();
  }

  /**
   * is pure parametrized data type, SET, BAG and LIST only
   *
   * @return
   */
  public boolean isPure() {
    return purePara.contains(type_);
  }

  /**
   * @see org.hl7.meta.ParametrizedDatatype#getType()
   */
  public String getType() {
    return type_;
  }

  //-------------------------------------------------------------------------
  /**
   * @see org.hl7.meta.ParametrizedDatatype#getParameter()
   */
  public List<Datatype> getParameter() {
    return parameter_;
  }

  //-------------------------------------------------------------------------
  /**
   * @see org.hl7.meta.ParametrizedDatatype#getParameter()
   */
  public Datatype getParameter(int i) {
    return parameter_.get(i);
  }

  public DatatypeBuilder getBuilder(ANY value) throws FactoryException {
    /*************************************/
    try {
      if (! checkDataType(value)) {
				return DatatypePresenterFactory.getInstance().createPresenter(value).getBuilder();
				// TODO: a kluge fix if Class Type of value  is not matched to HMD expected CLass Type 
      }
    } catch (ClassNotFoundException e) {
      // do nothing
    }

    if (isPure()) {
      return DatatypePresenterFactory.getInstance().
	createPresenter(getType()).getBuilder();
    } else {
      return DatatypePresenterFactory.getInstance().
	createPresenter(getFullName()).getBuilder();
    }
  }
  //-------------------------------------------------------------------------
  /**
   * Returns a handler for the data type, uses the configured data type
   * handler factory.
   *
   * @param namespaceURI namespace of the XML element
   * @param localName    local name of the XML element
   * @param qName        qualified name of the XML element
   * @param atts         attributes of the XML element
   * @return an instance of a handler
   * @throws FactoryException if creating a handler fails
   */
  public DynamicContentHandler getHandler(final String namespaceURI,
					  final String localName,
					  final String qName,
					  final Attributes atts)
    throws FactoryException {

    if("SXCM".equals(type_)) {
      final Datatype datatype = parameter_.get(0);
      return datatype.getHandler(namespaceURI, localName, qName, atts);

    } else if(isPure()) {
      Datatype datatype = parameter_.get(0);
      String xsi = atts.getValue(DatatypePresenterBase.W3_XML_SCHEMA, "type");
      if(xsi != null) {
	try {
	  datatype = DatatypeMetadataFactoryImpl.instance().createByXsiType(xsi);
	} catch (Throwable /*UnknownDatatypeException*/ e) {
	  // do nothing
	  e.printStackTrace();
	}
      }

      return datatype.getHandler(namespaceURI, localName, qName, atts);

    } else {
      // No, xsi:type support yet; will need to unmangle the XML schema
      // type name into the datatype name.
      // Also, the approach of IVL, RTO is different with LIST, BAG, SET, because of lowClosed implementation etc

      DynamicContentHandler dynamicContentHandler =
	(DynamicContentHandler)
	DatatypePresenterFactory.getInstance().createPresenter(getFullName()).getContentHandler(namespaceURI, localName, qName, atts, this);

      return dynamicContentHandler;
    }
  }

  //    /**
  //     * Convert IVL ONLY related parametrized full name, such as IVL<TS> to IVLTS, IVL<INT> to IVLINT
  //     *
  //     * @param fullName
  //     * @return type name
  //     */
  //
  //    private String getTypeName(String fullName) {
  //        if (fullName.startsWith("IVL")) {
  //            String s = fullName.replace("<", "");
  //            return s.replace(">", "");
  //        }
  //        return fullName;
  //    }


  /**
   * check incoming value is validated against datatype
   * // TODO: need a re-design
   *
   * @param value
   * @return boolean
   * @throws ClassNotFoundException
   */

  public boolean checkDataType(final ANY value) throws ClassNotFoundException {
      final JavaIts javaIts_ = new JavaItsImpl();
      final ParametrizedDatatype pdt = this;
      Datatype parameter = pdt.getParameter(0);

      if (BAG.class.getSimpleName().equals(pdt.getType()) && (value instanceof BAG))
      {
          BAG bagValue = (BAG) value;
          for (Iterator<ANY> it = bagValue.iterator();
               it.hasNext();)
          {
              ANY element = it.next();
              // for instance: BAG< RTO<INT, INT> >
              if (parameter instanceof ParametrizedDatatype) return parameter.checkDataType(element);

              if (!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass()))
              {
                  return false;
              }
          }
          return true;
      }
      else if (LIST.class.getSimpleName().equals(pdt.getType()) && value instanceof LIST)
      {
          LIST<ANY> listValue = (LIST<ANY>) value;
          if (listValue.isNull().isTrue()) return true;
          for (Iterator<ANY> it = listValue.iterator(); it.hasNext();)
          {
              ANY element = it.next();
              // for instance: LIST< RTO<INT, INT> >
              if (parameter instanceof ParametrizedDatatype) return parameter.checkDataType(element);
              if (!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass()))
              {
                  return false;
              }
          }
          return true;
      }
      else if (IVL.class.getSimpleName().equals(pdt.getType()) && value instanceof IVL)
      {
          IVL ivlValue = (IVL) value;
          ANY element = null;
          if (ivlValue.isNull().isTrue()) return true;
					if (ivlValue.low().nonNull().isTrue())
						element = ivlValue.low();
          else if (ivlValue.high().nonNull().isTrue())
						element = ivlValue.high();
          else if (ivlValue.center().nonNull().isTrue())
						element = ivlValue.center();

          if ((element != null) && (!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass())))
          {
              return false;
          }
          return true;
      }
      else if (SET.class.getSimpleName().equals(pdt.getType()) && value instanceof SET)
      {
				  if(value.isNull().isTrue())
					  return true;
          else if (value instanceof IVL)
          {
              IVL ivlValue = (IVL) value;
              ANY element = null;
              if (ivlValue.low().nonNull().isTrue())
								element = ivlValue.low();
              else if (ivlValue.high().nonNull().isTrue())
								element = ivlValue.high();
              else if (ivlValue.center().nonNull().isTrue())
								element = ivlValue.center();
              if ((element != null) && (!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass())))
              {
                  return false;
              }
              return true;
          }
          else if (value instanceof PIVL)
          {

              PIVL pivlValue = (PIVL) value;
              ANY element = null;
              if (pivlValue.phase() != null) element = pivlValue.phase();
              if (pivlValue.period() != null) element = pivlValue.period();

              if ((element != null) && (!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass())))
              {
                  return false;
              }

              return true;
          }
          else if (value instanceof QSET)
          {
              // probable should do something here
              return true;
          }
          else if (value instanceof DSET)
					{
						for(ANY element : (DSET<ANY>)value) {
							if(parameter instanceof ParametrizedDatatype) // for instance: SET< RTO<INT, INT> >
								return parameter.checkDataType(element);
							if(!javaIts_.getDatatypeInterface(parameter).isAssignableFrom(element.getClass()))
								return false;
						}
						return true;
          } else 
						throw new AssertionError("a set which is not a DSET or QSET: " + value.getClass());
      } // end of if (pdt.getType().equals("SET") && value instanceof SET)
      else if (RTO.class.getSimpleName().equals(pdt.getType()) && value instanceof RTO)
      {
          RTO rtoValue = (RTO) value;
          QTY.diff numerator = rtoValue.numerator();
          QTY.diff denominator = rtoValue.denominator();

          Class numeratorClass = javaIts_.getDatatypeInterface(parameter);
          Class denominatorClass =
              javaIts_.getDatatypeInterface(pdt.getParameter(1));

          // Debug.
          LOGGER.finest("HMD: numerator " + numeratorClass.getName() +
              ", denominator " + denominatorClass.getName() + "\n" +
              "value: numerator " + numerator.getClass().getName() +
              ", denominator " + denominator.getClass().getName());

          return numeratorClass.isAssignableFrom(numerator.getClass()) &&
              denominatorClass.isAssignableFrom(denominator.getClass());
      } // enf of if (pdt.getType().equals("RTO") && value instanceof RTO)
      else
      {
          return false;
      }
  }

}






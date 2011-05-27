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

package org.hl7.types;

import org.hl7.types.impl.CodeValueFactoryImpl;
import org.hl7.types.impl.STnull;

/** Abstact base class for factories that create code values,
    i.e. values of type CV.

    Different implementations of the CodeValueFactory can subclass
    this factory to provide different method of resolving codeSystem
    uids. The factory is a singleton and the instance is obtained with
    the static method getInstance(), this might in the future retrieve
    a different class depending on preferences, properties, or
    classpath settings.

    However, this is hardly ever needed because the present default
    factory is already configurable so as to use different CV
    implementations for different code systems.
    @author Gunther Schadow
    @version $Revision: 13163 $
*/
public abstract class CodeValueFactory {

  /** Returns a code value factory instance. */
  public static CodeValueFactory getInstance() throws CodeValueFactory.ConfigurationException {
    return CodeValueFactoryImpl.getInstance();
  }

  /** Returns a code value factory instance. */
  public static CodeValueFactory instance() throws CodeValueFactory.ConfigurationException {
    return CodeValueFactoryImpl.getInstance();
  }

  /** This class wants to manage its instances and therefore does not
      permit rogue news. */
  protected CodeValueFactory() {
      super();
  }

  /* interface CodeValueFactory */

  /** Get a code value from code and codeSystemUID. At least code
      and code system UID must be provided, the other arguments are
      optional. */
  public abstract CV valueOf(ST code, UID codeSystemUID, ST codeSystemVersion, ST displayName, ST originalText) throws CodeValueFactory.Exception;

  /** Simplified interface to create a code value from just a java String */
  public CV valueOf(final String code, final String codeSystem) throws CodeValueFactory.Exception {
    return valueOf(ValueFactory.getInstance().STvalueOfLiteral(code), ValueFactory.getInstance().UIDvalueOfLiteral(codeSystem), STnull.NI, STnull.NI, STnull.NI);    
  }

  public static class Exception extends RuntimeException {
    public Exception() { super(); }
    public Exception(final String message) { super(message); }
    public Exception(final Throwable nested) { super(nested); }
    public Exception(final String message, final Throwable nested) { super(message, nested); }
  }
  /** Thrown if the code system is not known to the configuration. */
  public static class UnknownCodeSystemException extends CodeValueFactory.Exception {
    public UnknownCodeSystemException() { super(); }
    public UnknownCodeSystemException(final String message) { super(message); }
    public UnknownCodeSystemException(final Throwable nested) { super(nested); }
    public UnknownCodeSystemException(final String message, final Throwable nested) { super(message, nested); }
  }
  /** Thrown if the code is not valid for the given code system. */
  public static class InvalidCodeException extends CodeValueFactory.Exception {
    public InvalidCodeException() { super(); }
    public InvalidCodeException(final String message) { super(message); }
    public InvalidCodeException(final Throwable nested) { super(nested); }
    public InvalidCodeException(final String message, final Throwable nested) { super(message, nested); }
  }
  /** Thrown if some error occurs in the delegated implementation of the code value. */
  public static class DelegateException extends CodeValueFactory.Exception {
    public DelegateException() { super(); }
    public DelegateException(final String message) { super(message); }
    public DelegateException(final Throwable nested) { super(nested); }
    public DelegateException(final String message, final Throwable nested) { super(message, nested); }
  }
  /** Thrown if the CodeValueFactory itself encounters some error (e.g., configuration file is bad). */
  public static class ConfigurationException extends CodeValueFactory.Exception {
    public ConfigurationException() { super(); }
    public ConfigurationException(final String message) { super(message); }
    public ConfigurationException(final Throwable nested) { super(nested); }
    public ConfigurationException(final String message, final Throwable nested) { super(message, nested); }
  }
}

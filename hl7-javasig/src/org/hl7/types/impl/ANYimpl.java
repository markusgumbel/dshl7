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
package org.hl7.types.impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.UUID;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.NullFlavor;
import org.hl7.types.enums.SetOperator;

/**
 * Root class for all types in this implementation.
 */
public abstract class ANYimpl implements ANY, Serializable {
  private NullFlavor _nullFlavor = null;

  protected ANYimpl(final NullFlavor nf) {
    // XXX: we assume that nf is NullFlavorImpl, that may not be true always
    this._nullFlavor = nf;
  }

  public NullFlavor nullFlavor() {
    if (_nullFlavor == null) {
      _nullFlavor = NullFlavorImpl.NOT_A_NULL_FLAVOR;
    }
    return _nullFlavor;
  }

  public boolean isNullJ() {
    return isNull().isTrue();
  }

  public boolean nonNullJ() {
    return nonNull().isTrue();
  }

  public boolean notApplicableJ() {
    return notApplicable().isTrue();
  }

  public boolean unknownJ() {
    return unknown().isTrue();
  }

  public boolean otherJ() {
    return other().isTrue();
  }

  public BL isNull() {
    return nullFlavor().implies(NullFlavorImpl.NI);
  }

  public BL nonNull() {
    return nullFlavor().isNull();
  }

  public BL notApplicable() {
    return nullFlavor().implies(NullFlavorImpl.NA);
  }

  public BL unknown() {
    return nullFlavor().implies(NullFlavorImpl.UNK);
  }

  public BL other() {
    return nullFlavor().implies(NullFlavorImpl.OTH);
  }

  /**
   * We require that all inheritors of ANYimpl have their own equal function for all of its compatible types.
   * 
   * <p>
   * The equals function is not a no-brainer. THINK!
   * 
   * <p>
   * Equal must first test for type compatibility (not necessarily type-equality.) Be sure to test for the interface,
   * not the implementation type, as the same type may have different implementations.
   */
  public abstract BL equal(ANY x);

  /**
   * Unique object identifier for persistence. This is an assigned UUID, so that it will always work.
   */
  private String _internalId = null;

  public String getInternalId() {
    if (_internalId == null) {
      _internalId = UUID.randomUUID().toString();
    }
    return _internalId;
  }

  protected void setInternalId(final String value) {
    _internalId = value;
  }

  /**
   * Returns true if this.equal(that) is true or if the two are of identical (NULL value). Do NOT use this to save
   * yourself the work of typing x.equal().isTrue() or !x.equal().isFalse(), because these are not equivalent!
   * 
   * This here is used mostly for anything builtin, java, Hibernate, etc.
   * 
   */
  public boolean equals( Object that) {
    return (that instanceof ANY) && this.equal((ANY) that).isTrue();
  }
  /* FIXME: also need hashCode! */

	
  // MIXIN PROPERTIES
  // These properties are placed here because Java has no mixin (generic type extension) mechanism.
  // These properties are disabled unless a marker interface enables them in a subclass.
	
  private SetOperator _operator = null;
  /** Get the operator for QSET term components. This is only relevant during parsing. */
  public SetOperator getOperator() { return _operator; }
  /** Set the operator for QSET term components. This is only relevant during parsing. */
  public void setOperator(final SetOperator operator) { _operator = operator; }
    
    
  /**
   * An eclipse code generator is used to add an Externalizable 
   * implementation to the org.hl7 types.  Normally this implementation is
   * not checked into SVN.  However, the code generated for some of this
   * class's sub-classes did not work and had to be replaced with a 
   * hand-coded implementation that called super().  This method will be
   * replaced by the code generator due to the @generated tag below.
   * 
   * @throws UnsupportedOperationException - we want to fail fast if the
   * code generator has not been run on the classes in this package or
   * if this method is called in error. 
   *
   * @author jmoore
   * 
   * @generated
   */
  public void readExternal(final ObjectInput oi) throws IOException 
  {
    throw new UnsupportedOperationException("The Implement Externalizable Eclipse plugin has not been applied to this class.");
  }

  /**
   * An eclipse code generator is used to add an Externalizable 
   * implementation to the org.hl7 types.  Normally this implementation is
   * not checked into SVN.  However, the code generated for some of this
   * class's sub-classes did not work and had to be replaced with a 
   * hand-coded implementation that called super().  This method will be
   * replaced by the code generator due to the @generated tag below.
   * 
   * @throws UnsupportedOperationException - we want to fail fast if the
   * code generator has not been run on the classes in this package or
   * if this method is called in error. 
   *
   * @author jmoore
   * 
   * @generated
   */
  public void writeExternal(final ObjectOutput oo) throws IOException 
  {
    throw new UnsupportedOperationException("The Implement Externalizable Eclipse plugin has not been applied to this class.");
  }
    
  /**
   * Provides the actual implementation for all of the
   * <code>valueOf(String)</code> methods in the various null implementation
   * classes. This avoids the need for duplicate code. Each null
   * implementation class must contain a constant for each null flavor. The
   * necessary constant is retrieved at run time using reflection.
   * 
   * @param <T>
   *                data type implementation class.
   * @param nullImpl
   *                a class that extends <code>ANYimpl</code> and contains
   *                fields for each of the null types.
   * @param nullFlavor
   *                value of one of the <code>String</code> fields on
   *                {@link NullFlavorImpl}, which represent all of the
   *                standard null flavors.
   * @return value of the <code>public static final</code> field on the
   *         <code>nullImpl</code> class named <code>nullFlavor</code>.
   * @throws IllegalArgumentException
   *                 if <code>nullFlavor</code> does not represent a valid
   *                 null flavor.
   */
  // unchecked warnings are supressed so that we can cast the null flavor
  // object back to the target type
  @SuppressWarnings("unchecked")
  protected static <T extends ANYimpl> T nullValueOf(final Class<T> nullImpl,
						     final String nullFlavor) throws IllegalArgumentException {
    assert ANYimpl.class.isAssignableFrom(nullImpl) : "nullImpl must be a subclass of "
      + ANYimpl.class.getName();
    try {
      final Field nullField = nullImpl.getField(nullFlavor);
      assert nullField.getModifiers() == (Modifier.FINAL
					  | Modifier.STATIC | Modifier.PUBLIC) : "null flavor fields must be declared public static final";
      return (T) nullField.get(null);
    } catch (final NoSuchFieldException e) {
      throw new IllegalArgumentException("invalid null flavor "
					 + nullFlavor);
    } catch (final IllegalAccessException e) {
      // this should never occur unless someone accidentally changed one
      // of the public null fields to private
      throw new IllegalStateException(e);
    }
  }
}

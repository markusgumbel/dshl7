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
 * The Initial Developer of the Original Code is Gunther Schadow.
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.hibernate;

import java.util.List;

import org.hl7.types.LIST;
import org.hl7.types.impl.LISTjuListAdapter;
import org.hl7.types.impl.LISTnull;

/**
 * Access a LIST property and wrap/unwrap the underlying list.
 * 
 * @author Gunther Schadow
 */
public class WrappingListAccessor extends StrategicPropertyAccessor {

  private class LISTWrapper implements StrategicPropertyAccessor.FieldWrappingStrategy {

    public Object bean2DBValue(Object beanValue) {
      if (beanValue == null) return null;
      else if (beanValue instanceof LIST) {
	LIST listValue = (LIST) beanValue;
	if (listValue.isNull().isTrue())
	  return null;
	else if (listValue instanceof LISTjuListAdapter)
	  return ((LISTjuListAdapter) listValue)._hibernateUnwrap();
      }

      throw new RuntimeException("class not supported here: " + beanValue.getClass());
    }

    public Object db2BeanValue(Object dbValue) {
      return dbValue == null ? LISTnull.NI : LISTjuListAdapter._hibernateWrap((List) dbValue);
    }
  }
    
  @Override
  public StrategicPropertyAccessor.FieldWrappingStrategy getWrappingStrategy() {
    return new LISTWrapper();
  }
}

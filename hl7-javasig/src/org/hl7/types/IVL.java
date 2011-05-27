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

// FIXME: T should extend QTY, and hence IVL would not extend QSET<T> instead:
// FIXME: we need  IVL<T extends ORD> extends  SET<T>
// FIXME: we need QIVL<T extends QTY> extends QSET<T>, IVL<T>
// FIXME: we need DIVL<T extends ORD> extends DSET<T>, IVL<T>

public interface IVL<T extends QTY> extends QSET<T> {
  T       low();
  BL      lowClosed();
  T       high();
  BL      highClosed();
  /*T.diff*/ QTY.diff width();
  T       center();
  IVL<T>  hull(IVL<T> x);
  IVL<T>  intersection(IVL<T> x);

  BL      overlaps(IVL<T> x);
}

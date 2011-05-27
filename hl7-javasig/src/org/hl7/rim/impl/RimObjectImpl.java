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
package org.hl7.rim.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;
import org.hl7.hibernate.Hibernatable;
import org.hl7.rim.RimObject;
import org.hl7.types.CS;
import org.hl7.xml.parser.Extensible;
import org.hl7.xml.parser.TreeContentHandler;

/**
 * Base implementation for all RIM classes, implements <code>clone()</code>
 * method.
 *
 * @author  Jerry Joyce
 */
public class RimObjectImpl extends Observable implements RimObject, Cloneable, Extensible, Hibernatable
{
    private CS cloneCode;
    private CS type;

  //-------------------------------------------------------------------------
  /**
   * Implements cloning so that other classes can call it. 
   */
  public Object clone() throws CloneNotSupportedException {
		RimObjectImpl clone = (RimObjectImpl) super.clone();
		clone._extensionNodes = null;
		clone._internalVersionNumber = -1;
		clone._internalId = null;
		return clone;
	}
	
  /** Implements the org.hl7.xml.Extensible interface. */
  private ArrayList<TreeContentHandler.Node> _extensionNodes;

  /** Implements the org.hl7.xml.Extensible interface. */
  public List<TreeContentHandler.Node> getExtensionNodes() {
    return this._extensionNodes;
  }

  /** Implements the org.hl7.xml.Extensible interface. */
  public void addExtensionNode(TreeContentHandler.Node node) {
    if(this._extensionNodes == null)
      this._extensionNodes = new ArrayList<TreeContentHandler.Node>();
    this._extensionNodes.add(node);
  }

  /** Implements the org.hl7.xml.Extensible interface. */
  public void compactExtensionNodes() {
    if(this._extensionNodes != null)
      if(this._extensionNodes.isEmpty())
	this._extensionNodes = null;
      else
	this._extensionNodes.trimToSize();
  }

    // Clone code is used to associated this Rim object w/
    // a particular clone object (contains the tagname)
    public CS getCloneCode(){
        return cloneCode;
    }
    public void setCloneCode(CS value){
        this.cloneCode=value;
    }

   /**
   * Type code is used to support  XML ITS 'type' attribute
   *
   * FIXME: (GS) I don't believe this would work for objects created from scratch.
   *
   * @return type Code
   */

    public CS getType() {
      return type;
    }

    public void setType(CS type) {
        this.type = type;
    }

  
  /** Unique object identifier for persistence. This is an assigned UUID, so that it will always work. */
  private String _internalId = null;
  public String getInternalId() {
    if(_internalId == null)
      _internalId = UUID.randomUUID().toString();
    return _internalId;
  }
  public void setInternalId(String value) { _internalId = value; }

  private long _internalVersionNumber = -1;
  public long getInternalVersionNumber() { return _internalVersionNumber; }
  public void setInternalVersionNumber(long value) { _internalVersionNumber = value; }

  /** Equality is defined as identity of persistent objects and is guaranteed by the fact that UUID's are 
      automatically assigned. */
  public boolean equals(Object that) {
//    System.out.println("EQUALS: " + this + " =? " + that);

    if(this == that)
      return true;
    else if(that instanceof RimObjectImpl) {
//      System.out.println("BONGO!");
      return this.getInternalId().equals(((RimObjectImpl)that).getInternalId());
    } else {
//      System.out.println("BINGO!");
      return false;
    }
  }

  /** Hash code is defined for the persistence layer, hence it is the hashCode of a hidden identifier
      maintained by the persistence layer.
  */
  public int hashCode() {
    return getInternalId().hashCode();
  }

  public String toString() {
    return this.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + ":" + getInternalId().toString();
  }
}

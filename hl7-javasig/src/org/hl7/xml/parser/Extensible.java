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

package org.hl7.xml.parser;

import java.util.List;

/**
  An interface implemented by objects that are extensible with
  arbitrary XML node-sets.

  @see org.hl7.xml.TreeContentHandler
  @author Gunther Schadow, Regenstrief Institute
  @version $Id: Extensible.java 2965 2004-11-11 22:15:37Z echen $

  To implements this interface, you can copy the following code:

  <pre>
  private ArrayList<TreeContentHandler.Node> _extensionNodes;

  public List<TreeContentHandler.Node> getExtensionNodes() {
    return this._extensionNodes;
  }

  public void addExtensionNode(TreeContentHandler.Node node) {
    if(this._extensionNodes == null)
      this._extensionNodes = new ArrayList<TreeContentHandler.Node>();
    this._extensionNodes.add(node);
  }

  public void compactExtensionNodes() {
    if(this._extensionNodes != null)
      if(this._extensionNodes.isEmpty())
	this._extensionNodes = null;
      else
	this._extensionNodess.trimToSize();
  }
  </pre>

 */

public interface Extensible {
  List<TreeContentHandler.Node> getExtensionNodes();
  void addExtensionNode(TreeContentHandler.Node node);
  void compactExtensionNodes();
}




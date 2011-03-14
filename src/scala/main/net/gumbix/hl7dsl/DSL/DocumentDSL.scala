/*
Copyright 2010 the original author or authors.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package net.gumbix.hl7dsl.DSL

import org.hl7.rim.{RimObjectFactory, Document}
import org.hl7.types._
import net.gumbix.hl7dsl.helper.ImplicitDef._

/**
 * Wrapper Class for the RIM Class "Document"
 * @author Ahmet Gül
 */

class DocumentDSL(document: Document) extends ContextStructureDSL(document) {

  // TODO how do we tackle "root" clones?
  def this() = {
    this(RimObjectFactory.getInstance.createRimObject("Document").asInstanceOf[Document])
    cloneCode = ("clinicalDocument", "egal") // TODO required for marshalling
  }

  /**
   * @return TS
   */
  def copyTime: TS = document.getCopyTime

  def copyTime_=(v: TS) {
    document.setCopyTime(v)
  }

  /**
   * @return Document
   */
  def getDocument: Document = document
}

object DocumentDSL {

  implicit def asDocument(act: DocumentDSL): Document = act.getDocument

  def apply() = new DocumentDSL()
}
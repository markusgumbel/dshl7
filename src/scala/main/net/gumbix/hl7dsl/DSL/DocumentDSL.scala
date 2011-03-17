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
import org.hl7.util.MessageLoader

/**
 * Wrapper Class for the RIM Class "Document"
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

class DocumentDSL(document: Document) extends ContextStructureDSL(document) {
  def this() = {
    this (RimObjectFactory.getInstance.createRimObject("Document").asInstanceOf[Document])
    // TODO as CDA is often a root element it does not get a clone code
    // This is required by the RS framework though!
    cloneCode = ("cda", "egal")
  }

  def this(doc: String) = {
    this (MessageLoader.LoadMessage("POCD_HD000040", doc).asInstanceOf[Document])
  }

  /**
   * @return TS
   */
  def copyTime: TS = document.getCopyTime

  def copyTime_=(v: TS) {
    document.setCopyTime(v)
  }
}
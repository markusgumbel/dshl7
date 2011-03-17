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
package net.gumbix.hl7dsl.build

import org.hl7.meta.MessageType
import org.hl7.meta.mif.MessageTypeLoaderAdapter
import org.hl7.xml.builder.RimGraphXMLSpeaker
import org.hl7.rim.{RimObject}
import javax.xml.transform.sax.SAXSource
import org.hl7.xml.builder.RimGraphXMLSpeaker.InputSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.{Transformer, OutputKeys, TransformerFactory, Source}
import java.io.{ByteArrayOutputStream, FileOutputStream}

/**
 * Class to Build Message from Java SIG Demo in Scala
 * needs RimObject and not object as parameter
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */

object BuildMessage {

  @throws(classOf[Exception])
  def toXML(rim: RimObject, message: String) = {
    val speaker = new RimGraphXMLSpeaker()
    val jmtl = MessageTypeLoaderAdapter.getInstance
    val messageType = jmtl.loadMessageType(message)
    val transformer = TransformerFactory.newInstance.newTransformer()
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    val source =
    new SAXSource(speaker, new InputSource(rim, messageType.getRootClass))
    val os = new ByteArrayOutputStream()
    transformer.transform(source, new StreamResult(os))
    os.toString()
  }
}
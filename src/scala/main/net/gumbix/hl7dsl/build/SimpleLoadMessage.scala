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

import java.io._
import org.hl7.meta.MessageType
import org.hl7.meta.MessageTypeLoader
import org.hl7.meta.mif.MessageTypeLoaderAdapter
import org.hl7.util.ApplicationContext
import org.hl7.util.ApplicationContextImpl
import org.hl7.xml.parser.MessageContentHandler

/**
 * Class to load Message from Java SIG Demo in Scala
 * @author Ahmet Gül
 */

class SimpleLoadMessage {
    def LoadMessage(messagetypestr: String, message: String): Any = {
      var rim: Any = null
      
      var mtl:MessageTypeLoaderAdapter = MessageTypeLoaderAdapter.getInstance
      var messageType: MessageType = mtl.loadMessageType(messagetypestr)
      try {
        var f: File = new File(message)
        var in: FileInputStream = new FileInputStream(f)
        var ac: ApplicationContext = new ApplicationContextImpl
        rim = MessageContentHandler.parseMessage(ac, in, messageType)
      }
      catch {
        case e: Exception => {
          System.out.println("Cant load message file")
        }
      }
      return rim
    }
  }
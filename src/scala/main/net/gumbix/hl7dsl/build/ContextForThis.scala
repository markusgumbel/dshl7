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

import org.hl7.util.ApplicationContext
import org.hl7.xml.parser.MessageContentHandler
import org.hl7.hibernate.Persistence
import java.util.HashMap

/**
 * Class for Context from Java SIG Demo in Scala
 * @author Ahmet Gül (guel.ahmet@hotmail.de)
 */


class ContextForThis extends ApplicationContext {
  private var _settings: java.util.Map[String, Any]  = null//new HashMap[_, _](System.getProperty())

  def getSetting[T](name: String, defaultValue: T): T = {
    var result: T = _settings.get(name).asInstanceOf[T]
    if (result == null) defaultValue
    else result
  }

  def setSetting(name: String, value: Any): Unit = {
    _settings.put(name, value)
  }

  def getPersistence: Persistence = {
    Persistence.instance
  }

  def getSetting(name: String) = {
    _settings.get(name).asInstanceOf
  }
}
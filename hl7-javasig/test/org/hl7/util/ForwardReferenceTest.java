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
package org.hl7.util;

import java.util.HashMap;
import java.util.Map;

public class ForwardReferenceTest {
  private static Map<String,Thing> MAP = new HashMap<String,Thing>();
  private Thing _value;

  static interface Thing {
    String toString();
  }
  
  static class ThingImpl implements Thing {
    private String _value;
    ThingImpl(String value) { _value = value; }
    public String toString() { return _value; }
  }
  
  ForwardReferenceTest(String name) {
    _value = ForwardReferenceTool.get(MAP, name, Thing.class, new ForwardReferenceTool.Replacer<Thing>() { public void replace(Thing value) { _value = value; } });
  }
  ForwardReferenceTest(String name, String value) {
    _value = new ThingImpl(value);
    ForwardReferenceTool.put(MAP, name, _value);
  }
  public String toString() {
    return _value.toString();
  }
  static void show() {
    System.out.println(MAP.toString());
  }
  
  public static void main(String args[]) {
    new ForwardReferenceTest("one");
    show();
    new ForwardReferenceTest("two");
    show();
    new ForwardReferenceTest("three");
    show();
    new ForwardReferenceTest("one","1");
    show();
    new ForwardReferenceTest("two","2");
    show();
    new ForwardReferenceTest("three","3");
    show();
  }
}

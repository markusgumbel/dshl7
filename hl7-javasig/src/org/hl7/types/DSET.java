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

/** A discrete set, which is a set of discrete elements which can be
    enumerated one by one. This is what most programmers understand under 
    a set. It is quite common but not the only one. It makes sense to
		distinguish as its own class besides QSET, because we can then 
		clarify that the iterator actually returns elements. 

		<p>This makes iterating over a DSET a lot easier:

		<pre>
		SET&lt;II> idset = ...;

		for(II id : idset) {
		  ...
		}
		</pre>
*/
public interface DSET<T extends ANY> extends SET<T>, Iterable<T> {
    // serves only to group together other interfaces
}

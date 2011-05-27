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
 * Portions created by Initial Developer are Copyright (C) 2002-2007
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s):
 */
package org.hl7.merger;

import java.util.LinkedList;
import java.util.List;
import org.hl7.types.ANY;
import org.hl7.types.BAG;
import org.hl7.types.BL;
import org.hl7.types.II;
import org.hl7.types.CD;
import org.hl7.types.QSET;
import org.hl7.types.DSET;
import org.hl7.types.QTY;
import org.hl7.types.ValueFactory;

/** Bunch of methods to merge attribute content. */
public class MergerTool {
	static CD merge(CD master, CD change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse())
			if(master == null || master.isNull().isTrue())
				return change;
			else if(change.equal(master).isTrue()) {
				if(change.displayName().isNull().isTrue() && master.displayName().isNull().isFalse())
					return master;
				else
					return change;				
			} else if(change.implies(master).isTrue())
				return change;
		  else if(checkForConsistency)
				throw new ConsistencyCheckException(propertyName, master, change);
			else
				return change;
		else
			return master;
	}

	static QSET<QTY> merge(QSET<QTY> master, QSET<QTY> change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse() && change.isEmpty().isFalse())
			if(master == null || master.isNull().isTrue() || master.isEmpty().isTrue())
				return change;
			else if(!change.contains(master).isFalse()) // allow broadening
				return change;	
			else if(!master.contains(change).isFalse()) // allow narrowing
				return change;	
			else if(checkForConsistency)
				throw new ConsistencyCheckException(propertyName, master, change);
			else
				return change;
		else
			return master;
	}

	static BL merge(BL master, BL change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse())
			if(master == null || master.isNull().isTrue())
				return change;
			else if(change.equal(master).isTrue())
				return master;
		  else if(checkForConsistency)
				throw new ConsistencyCheckException(propertyName, master, change);
			else
				return change;
		else
			return master;
	}

	static BAG<ANY> merge(BAG<ANY> master, BAG<ANY> change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse() && change.isEmpty().isFalse())
			if(master == null || master.isNull().isTrue() || master.isEmpty().isTrue())
				return change;
			else {
				List<ANY> list = new LinkedList<ANY>();
				for(ANY el : master)
					list.add(el);
				for(ANY el : change)
					if(!master.contains(el).isZero().isFalse())
						list.add(el);
				return ValueFactory.getInstance().BAGvalueOf(list);
			}
		else
			return master;
	}

	static DSET<ANY> merge(DSET<ANY> master, DSET<ANY> change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse() && change.isEmpty().isFalse())
			if(master == null || master.isNull().isTrue() || master.isEmpty().isTrue())
				return change;
			else {
				List<ANY> list = new LinkedList<ANY>();
				for(ANY el : master)
					list.add(el);
				for(ANY el : change)
					if(!master.contains(el).isFalse())
						list.add(el);
				return ValueFactory.getInstance().DSETvalueOf(list);
			}
		else
			return master;
	}

	static ANY merge(ANY master, ANY change, boolean checkForConsistency, String propertyName) throws ConsistencyCheckException {
		if(change != null && change.isNull().isFalse())
			if(master == null || master.isNull().isTrue())
				return change;
			else if(change.equal(master).isTrue())
				return master;
		  else if(checkForConsistency)
				throw new ConsistencyCheckException(propertyName, master, change);
			else
				return change;
		else
			return master;
	}

 	static final class ConsistencyCheckException extends Exception { 
		ConsistencyCheckException(String propertyName, Object master, Object change) {
			super("inconsistency in " + propertyName + " was " + master.toString() + " would become " + change.toString());
		}
	}
}

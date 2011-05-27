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
package org.hl7.types.impl;

import org.hl7.types.BL;
import org.hl7.types.NullFlavor;
import org.hl7.types.ORD;

/**
 * Minimal implementation of ORD that implements some of the non-primitive operations.
 */
public abstract class ORDimpl extends ANYimpl implements ORD {
	protected ORDimpl(final NullFlavor nf) {
		super(nf);
	}

	/**
	 * Suggest to make compares primitive in many cases, because this is an expensive test.
	 */
	public BL compares(final ORD that) {
		return this.lessOrEqual(that).or(that.lessOrEqual(this));
	}

	public abstract BL lessOrEqual(ORD that);

	public BL lessThan(final ORD that) {
		return this.lessOrEqual(that).and(this.equal(that).not());
	}

	public BL greaterOrEqual(final ORD that) {
		return that.lessOrEqual(this);
	}

	public BL greaterThan(final ORD that) {
		return that.lessThan(this);
	}

	public int compareTo(final ORD that) {
		return this.lessThan(that).isTrue() ? -1 : (this.equal(that).isTrue() ? 0 : 1);
	}
};

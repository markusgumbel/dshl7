/*
 * Copyright (c) 1998-2003 The Regenstrief Institute.  All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Written by Gunther Schadow.
 *
 * $Id: tanTimes100.java 4607 2006-10-18 19:43:42Z crosenthal $
 */

package org.regenstrief.ucum.functions;

import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;
import org.regenstrief.ucum.FunctionPair;

public final class tanTimes100 implements FunctionPair {    
	static final REAL HUNDRED = ValueFactory.getInstance().REALvalueOfLiteral("100");
	static final REAL ONE_HUNDREDTH = HUNDRED.inverted();
	static final REAL REAL_NULL = (REAL)ValueFactory.getInstance().nullValueOf("REAL", "NI");
	public REAL f_to (REAL x) { 
		try {
			System.err.println("f_to " + x + " " + x.doubleValue() + " " + Math.tan(x.doubleValue()) + " " 
												 + ValueFactory.getInstance().REALvalueOf(Math.tan(x.doubleValue())).times(HUNDRED));
			return ValueFactory.getInstance().REALvalueOf(Math.tan(x.doubleValue())).times(HUNDRED);
		} catch(Exception ex) {
			return REAL_NULL;
		}
	}
  public REAL f_from(REAL x) { 
		try {
			System.err.println("f_from " + x + " " + x.doubleValue() + " " + x.times(ONE_HUNDREDTH) + " " 
												 + ValueFactory.getInstance().REALvalueOf(Math.atan(x.times(ONE_HUNDREDTH).doubleValue())));
			return ValueFactory.getInstance().REALvalueOf(Math.atan(x.times(ONE_HUNDREDTH).doubleValue()));
		} catch(Exception ex) {
			return REAL_NULL;
		}
	}
}

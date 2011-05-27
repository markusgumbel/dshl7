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
 * $Id: degF.java 4607 2006-10-18 19:43:42Z crosenthal $
 */

package org.regenstrief.ucum.functions;

import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;
import org.regenstrief.ucum.FunctionPair;

public final class degF implements FunctionPair {    
	static final REAL NINE = ValueFactory.getInstance().REALvalueOfLiteral("9");
	static final REAL FIVE = ValueFactory.getInstance().REALvalueOfLiteral("5");
	static final REAL THIRTYTWO = ValueFactory.getInstance().REALvalueOfLiteral("32");
  static final REAL ABSOLUTE = Cel.ABSOLUTE.times(NINE).dividedBy(FIVE).plus(THIRTYTWO); // ValueFactory.getInstance().REALvalueOfLiteral("-459.67");
  public REAL f_to  (REAL x) { return x.plus(ABSOLUTE); }
  public REAL f_from(REAL x) { return x.minus(ABSOLUTE); }
}

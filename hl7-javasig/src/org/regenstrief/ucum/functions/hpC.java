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
 * $Id: hpC.java 4607 2006-10-18 19:43:42Z crosenthal $
 */

package org.regenstrief.ucum.functions;

import org.hl7.types.REAL;
import org.hl7.types.ValueFactory;
import org.regenstrief.ucum.FunctionPair;

public final class hpC implements FunctionPair {    

  static final REAL HUNDRED  = ValueFactory.getInstance()
    .REALvalueOfLiteral("100");

  static final REAL LN_HUNDRED = HUNDRED.log();

  public REAL f_to  (REAL x) { 
    return x.log().dividedBy(LN_HUNDRED).negated(); 
  }

  public REAL f_from(REAL x) { 
    return HUNDRED.power(x.negated()); 
  }
}

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
 * $Id: FunctionPair.java 2762 2004-03-25 23:03:34Z schadow $
 */

package org.regenstrief.ucum;

import org.hl7.types.REAL;

public interface FunctionPair
{
  REAL f_to  (REAL x);
  REAL f_from(REAL x);
}

package org.hl7.rim.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hl7.rim.RimObject;

/**
 * An implementation of AssociationSet as an ordered list.
 * However, the order should be not important?  Maybe a Set is more
 * appropriate, Gunther
 * 
 * @author Skirmantas Kligys and Gunther Schadow
 *
 * As I said in the AssociationSet interface, we make this real
 * simple to get going. ArrayList is fine.
 */
public class AssociationSetImpl<T extends RimObject> extends ArrayList<T> implements Set<T>, List<T> {
  
}

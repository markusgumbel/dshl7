/**
 * Conducts participations and actrelations down the RIM graph
 * Also conducts the subject of record in CDA to all nested observations
 * @author Gunther Schadow and Peter Hendler 12/29/2007
 */

package org.hl7.merger;

import org.hl7.xml.parser.DynamicContentHandlerBase;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.hl7.types.enums.*;
import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.impl.*;

import java.util.Collection;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.hl7.util.ApplicationContext;
import org.hl7.meta.Attribute;
import org.hl7.meta.Feature;
import org.hl7.rim.*;
import org.hl7.rim.impl.ActImpl;
import org.hl7.rim.RimObject;
import org.hl7.rim.Role;
import org.hl7.rim.impl.ParticipationImpl;
import org.hl7.types.enums.*;

public class ContextConductionMerger<C extends RimObject, T extends RimObject>
		implements Merger<C, T> {

	/**
	 * @param args
	 */

	public static Object patientSubject = null;
	public org.hl7.rim.Participation mypart = null;
	public ApplicationContext appCntx = null;
	private Stack<Participation> partStack = new Stack<Participation>();
	private Stack<Act> contextStack = new Stack<Act>();
	private Context contx = new Context();
	public static HashMap<ApplicationContext, Stack<Act>> _stack_map = new HashMap<ApplicationContext, Stack<Act>>();

	/**
	 * If this is not null, those are the collection elements that we are just
	 * in the middle of parsing.
	 */
	private Collection<T> _currentCollection = null;

	private class Context extends ActImpl implements Act {
		private Context parent;

		public void setParent(Context par) {
			parent = par;
		}// setter

		public Context getParent() {
			return parent;
		}// getter
	};// Context class

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void endMessage() {
		synchronized (ContextConductionMerger._stack_map) {
			ContextConductionMerger._stack_map.clear();
		}// synchronized
	}

	public ContextConductionMerger(ApplicationContext applicationContext) {
		appCntx = applicationContext;
	}

	public ContextConductionMerger() {
	}

	protected static final Logger LOGGER = Logger.getLogger("org.hl7.merger");

	public boolean isStaticallyApplicable(Object object) {
		// Where's the best place to do this?
		if (ContextConductionMerger._stack_map.get(appCntx) == null) {
			ContextConductionMerger._stack_map.put(appCntx, contextStack);
			ContextConductionMerger._stack_map.put(appCntx, contextStack);
		}// if
		return (object != null && object instanceof Act);
	}

	public boolean isApplicable(C object, Object value) {
		// not sure why I do this

		return (object != null && object instanceof Act && value instanceof RimObject);
	}

	void storePart(List<Participation> partList) {
		// get the subject or the patient
		for (Participation part : partList) {
			BL recTargBL = part.getTypeCode().implies(
					ParticipationType.RecordTarget);
			BL partTargSubjBL = part.getTypeCode().implies(
					ParticipationType.ParticipationTargetSubject);
			boolean recTarg = recTargBL.isTrue();
			boolean partTargSubj = partTargSubjBL.isTrue();
			if (recTarg || partTargSubj) {
				// clone before storing it.
				Participation mypart = null;
				try {
					mypart = (Participation) part.clone();
					// it gets stored if it's a recTarg or if it's a
					// partTargSubj
					// only if
					// getContextControlCode().implies(ContextControlCode.Propagating)

					if (mypart != null) {
						boolean propagating = mypart.getContextControlCode()
								.implies(ContextControl.Propagating).isTrue();
						if (recTarg || propagating) {

							synchronized (partStack) {
								partStack.push(mypart);
								// ContextConductionMerger.patientSubject =
								// mypart;
							}// sychronized
						} // if recTarg or propagating
					}// if mypart not null
				} catch (Exception e) {
					e.printStackTrace();
				}

			}// if the participation is a subject
		}// for loop, after this everthing is in the partStack
		// now put that into our Context and store that

		for (Participation part : partStack) {
			contx.addParticipation(part);
			// maybe set parent here but who is parent?
		}// for loop loading into Context
	}// storePart

	void storeRelations(List<ActRelationship> relations) {
		// see if they are to be conducted and if they are
		// put them in our contx variable then store them.
		if (relations != null && !relations.isEmpty()) {
			for (ActRelationship actrel : relations) {
				boolean propagating = false;
				boolean conduction = false;
				try {
					propagating = actrel.getContextControlCode().implies(
							ContextControl.Propagating).isTrue();
					conduction = actrel.getContextConductionInd().isTrue();
				} catch (Exception e) {
				}
				if (propagating && conduction) {
					contx.addOutboundRelationship(actrel);
				}// if it's good to store
			}// for loop loading all the actRelationships into local Context

		}// if we have anything to do

	}// storeRelations

	public C finish(C object, Locator loc) {
		// check if there is a current collection pending that needs to be
		// flushed
		// this is where the conduction is actually conducted
		if (_currentCollection != null)

			_currentCollection = null;

		if (object instanceof Act) {
			Act act = (Act) object; // called obs because of original code
			Participation mypart = null;
			Act context = null;
			try {
				// we are going to get our Context that was stored earlier
				synchronized (ContextConductionMerger._stack_map) {
					context = ContextConductionMerger._stack_map.get(appCntx)
							.peek();
				}// synchronized





				context: 
					for (Participation contextParticipation : context
						.getParticipation()) {
					if (contextParticipation.getContextControlCode().implies(
							ContextControl.Propagating).isTrue() || 
							contextParticipation.getTypeCode().implies(ParticipationType
									.RecordTarget).isTrue()) {
						if(act.getParticipation()!= null ){
						for (Participation targetParticipation : act.getParticipation()) {
							if ((targetParticipation.getTypeCode().implies(
									contextParticipation.getTypeCode())
									.isTrue() || targetParticipation
									.getContextControlCode().implies(
											ContextControl.Overriding).isTrue())) {
								continue context; // move to the next one
							}// if cases where you shouldn't add
							
						}// inner for which cycles through the ones you dont add
						}// if there are any participations at all
						Participation partClone = (Participation)contextParticipation.clone();
						act.addParticipation(partClone);
						
					}// outer if
					
				}// outer for

				List<ActRelationship> goodRels = context
						.getOutboundRelationship();
				if (goodRels != null && !goodRels.isEmpty()) {
					for (ActRelationship rel : goodRels) {
						if (object instanceof Act && object != null
								&& rel != null
								&& rel instanceof ActRelationship) {
							// Gunther needs to figure out the test here
							// of whether or not to add this to the target
							if (act.getOutboundRelationship() == null) {
								ActRelationship aRel = (ActRelationship) rel
										.clone();
								act.addOutboundRelationship(aRel);
								System.out
										.println("--------- added ActRelationship");
							}// very bad test needs fixing
						}// rel not null
					}// ActRelationship for loop
				}// goodrels not empty

				// stack could be empty
			} catch (java.util.EmptyStackException es) {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}// if we have an observation
		return object;
	}

	public C merge(C object, Feature feature, T value, Locator loc) {

		System.out.println("ininininininin merge");

		// if participations store them.
		// my adding context really occurs in finish
		Act act = (Act) object;
		List<Participation> partList = act.getParticipation();
		boolean noParts = partList == null;
		if (!noParts) {
			storePart(partList);
		}// there are participations, save them in contx
		List<ActRelationship> actRelList = act.getOutboundRelationship();
		boolean noRelations = actRelList == null;
		if (!noRelations) {
			storeRelations(actRelList);
		}// there are relations store them in contx
		// now that we've returned from storing both
		// the participations and the outboundRelationships,
		// store it all in static variable
		synchronized (ContextConductionMerger._stack_map) {
			contextStack.push(contx);
			ContextConductionMerger._stack_map.put(appCntx, contextStack);
		}// synchronized
		return object;

	}

}

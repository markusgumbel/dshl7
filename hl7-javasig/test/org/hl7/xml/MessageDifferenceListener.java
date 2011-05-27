package org.hl7.xml;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

/**
 * Description of the class
 *
 * @author OWNER: Eric Chen
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since HL7 SDK v1.2
 *          revision    $Revision: 5073 $
 *          date        $Date: 2006-12-21 09:57:03 -0500 (Thu, 21 Dec 2006) $
 */
public class MessageDifferenceListener implements DifferenceListener {
    private static final String LOGID = "$RCSfile$";
    public static String RCSID = "$Header$";

    // variables used for info / debug purposes.
    private int[] founddiffs = new int[22];
    private int[] loggeddiffs = new int[22];
    private int[] ingnoreddiffs = new int[22];
    private int foundcount = 0;
    private int loggedcount = 0;
    private int ingnoredcount = 0;
    private String[] diffdescriptions = new String[22];

    // Differences that we DO NOT care about.
    private static final int[] IGNORE_VALUES = new int[] {
        // ignore "sequence of child nodes" differences.
        DifferenceConstants.CHILD_NODELIST_SEQUENCE.getId(),
        // ignore "number of element attributes" differences.
        // we don't care if there are 4 in the output and only 2 in the control document.
        // it will still check the value of all attributes in the control document.
        DifferenceConstants.ELEMENT_NUM_ATTRIBUTES.getId(),
        // ignore "sequence of attribute" differences.
        DifferenceConstants.ATTR_SEQUENCE_ID,
    };

    private boolean isIgnoredDifference(Difference difference) {
        int differenceId = difference.getId();
        for (int i=0; i < IGNORE_VALUES.length; ++i) {
            if (differenceId == IGNORE_VALUES[i]) {
                return true;
            }
        }
        if(difference.getId()==DifferenceConstants.ATTR_NAME_NOT_FOUND_ID){
            // ignore differences where it cannot find "schemaLocation"
            if("schemaLocation".equalsIgnoreCase(difference.getControlNodeDetail().getValue())){
                return true;
            }
        }
        return false;
    }

    public int differenceFound(Difference difference) {
        founddiffs[difference.getId()]++;
        foundcount++;
        diffdescriptions[difference.getId()] = difference.getDescription();
        if (isIgnoredDifference(difference)) {
            ingnoreddiffs[difference.getId()]++;
            ingnoredcount++;
            return RETURN_IGNORE_DIFFERENCE_NODES_IDENTICAL;
        } else {
            loggeddiffs[difference.getId()]++;
            loggedcount++;
            return RETURN_ACCEPT_DIFFERENCE;
        }
    }

    public void printSummary(){
        System.out.println("Differences found: " + foundcount);
        for (int i = 0; i < founddiffs.length; i++) {
            int founddiff = founddiffs[i];
            if(founddiff >0){
                System.out.println("\tDiffid: " + i + " Diffsfound: " + founddiff + " Description: " + diffdescriptions[i]);
            }
        }
        System.out.println("Differences ignored: " + ingnoredcount);
        for (int i = 0; i < ingnoreddiffs.length; i++) {
            int ingnoreddiff = ingnoreddiffs[i];
            if(ingnoreddiff >0){
                System.out.println("\tDiffid: " + i + " Diffsfound: " + ingnoreddiff + " Description: " + diffdescriptions[i]);
            }
        }
        System.out.println("Differences logged: " + loggedcount);
        for (int i = 0; i < loggeddiffs.length; i++) {
            int loggeddiff = loggeddiffs[i];
            if(loggeddiff >0){
                System.out.println("\tDiffid: " + i + " Diffsfound: " + loggeddiff + " Description: " + diffdescriptions[i]);
            }
        }
    }

    /**
     * Do nothing
     * @see DifferenceListener#skippedComparison(org.w3c.dom.Node, org.w3c.dom.Node)
     */
    public void skippedComparison(Node control, Node test) {
    }
}

/**
 * HISTORY      : $Log$
 * HISTORY      : Revision 1.1  2005/11/21 20:46:48  echen
 * HISTORY      : Add Test Case for Parse and Build
 * HISTORY      :
 */

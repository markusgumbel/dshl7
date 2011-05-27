package org.hl7.xml;

import java.util.List;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameQualifier;

/**
 * This class defines common test utility functions.
 *
 * @author Scott Jiang
 */
public class CommonTestUtils
{
	/**
	 * Logging constant used to identify source of log entry, that could be later used to create
	 * logging mechanism to uniquely identify the logged class.
	 */
	private static final String LOGID = "$RCSfile$";

	/**
	 * String that identifies the class version and solves the serial version UID problem.
	 * This String is for informational purposes only and MUST not be made final.
	 *
	 * @see <a href="http://www.visi.com/~gyles19/cgi-bin/fom.cgi?file=63">JBuilder vice javac serial version UID</a>
	 */
	public static String RCSID = "$Header$";

	protected static CommonTestUtils instance = new CommonTestUtils();

	public static final CommonTestUtils getInstance()
	{
		return instance;
	}

	public DetailedDiff doDiff(String control, String test, ElementNameQualifier qualifier) throws Exception
	{
		Diff diff = new Diff(control, test);
		MessageDifferenceListener differenceListener = new MessageDifferenceListener();
		DetailedDiff detailedDiff = new DetailedDiff(diff);
		detailedDiff.overrideDifferenceListener(differenceListener);
		detailedDiff.overrideElementQualifier(qualifier);
		List<Difference> diffList = (List<Difference>) detailedDiff.getAllDifferences();

		// print debug information.
		printDifferences(diffList);
		//differenceListener.printSummary();
		return detailedDiff;
	}

	protected void printDifferences(List<Difference> diffList)
	{
		for (int i = 0; i < diffList.size(); i++)
		{
			Difference difference = diffList.get(i);
			System.out.println("difference = " + i);
			System.out.println("\tdifference.getId = " + difference.getId());
			System.out.println("\tdifference.getDescription = " + difference.getDescription());
			System.out.println("\tdifference.isRecoverable = " + difference.isRecoverable());
			System.out.println("\tdifference.toString = " + difference.toString());
		}
	}

}
/**
 * HISTORY      : $Log$
 * HISTORY      : Revision 1.1  2006/05/18 15:51:25  sjiang
 * HISTORY      : Utility Class.
 * HISTORY      :
 */

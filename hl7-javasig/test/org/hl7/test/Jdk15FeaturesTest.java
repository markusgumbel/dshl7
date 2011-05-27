package org.hl7.test;

/**
 * @author Jere Krischel
 */

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import junit.framework.TestCase;

public class Jdk15FeaturesTest extends TestCase
{
    public enum MainMenu
    {
        FILE, EDIT, FORMAT, VIEW
    }

    public void testStaticImports()
    {
        out.println("I didn't need to say System.out");
    }

    public Jdk15FeaturesTest(String arg0)
    {
        super(arg0);
    }

    List<String> getGenericStringList()
    {
        List<String> list = new ArrayList<String>();
        list.add(new String("Hello world!"));
        list.add(new String("Good bye!"));
        // if you uncommented the next line, you'd get a compiler exception
        // list.add(new Integer(95));
        return list;
    }

    public void testGenerics()
    {
        Iterator<String> i = getGenericStringList().iterator();
        while (i.hasNext())
        {
            String item = i.next();
            assertTrue(item.equals("Hello world!") || item.equals("Good bye!"));
        }
    }

    public void testEnhancedForLoop()
    {
        for (String str : getGenericStringList())
        {
            assertTrue(str.equals("Hello world!") || str.equals("Good bye!"));
        }

        int[] array = { 1, 2, 3, 4, 5 };
        int sum = 0;
        for (int i : array)
        {
            sum += i;
        }
        assertEquals(15, sum);
    }

    public void testAutoboxingUnboxing()
    {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0, 59); // didn't have to cast 59 to Integer
        int total = list.get(0); // didn't have to cast back to int
        assertEquals(59, total);

        List<Double> doubleList = new ArrayList<Double>();
        doubleList.add(0, 59.59);
        double totalDoublePrimitive = doubleList.get(0);
        Double totalDouble = doubleList.get(0);
        assertEquals(59.59, totalDouble.doubleValue());
        assertEquals(59.59, totalDoublePrimitive);
    }

    public void testTypesafeEnumerations()
    {
        for (MainMenu menu : MainMenu.values())
        {
            assertTrue(menu.toString().equals("FILE") || menu.toString().equals("EDIT") || menu.toString().equals("FORMAT") || menu.toString().equals("VIEW"));
        }

        for (MainMenu menu : MainMenu.values())
        {
            switch (menu)
            {
            case FILE:
                assertEquals("FILE", menu.toString());
                break;
            case EDIT:
                assertEquals("EDIT", menu.toString());
                break;
            case FORMAT:
                assertEquals("FORMAT", menu.toString());
                break;
            case VIEW:
                assertEquals("VIEW", menu.toString());
                break;
            }
        }
    }

    public void testVariableArguments()
    {
        assertEquals("TestTest", getFirstTwoStringsTogether("Test", "Test"));
        assertEquals("TestTest", getFirstTwoStringsTogether("Test", "Test", "Test"));
    }

    String getFirstTwoStringsTogether(String... strings)
    {
        return strings[0] + strings[1];
    }

    public void testFormattedOutput()
    {
        out.printf("%s %3d", "Joe", 1333);
    }

    public void testEnhancedInput()
    {
        // Scanner reader = new Scanner(System.in);
        // int n = reader.nextInt();
    }
}

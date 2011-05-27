/*
 * Created on Nov 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.hl7.xml;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Compare two xml file to see any missed element, missed transformed elements, and missed attribute
 * @author WuYe
 */
public class ElementTest {
	public Vector missedElement = new Vector();
	public Vector wrongElement = new Vector();
	public Vector rightElement = new Vector();

	public Vector missedAttribute = new Vector();
	public Vector missedAttributeP = new Vector();
	public int totalLevel = 0;
	String[] knownBug={
			"originalUnit",
			"originalValue",
		    "operator", //IVL
			"value",    //IVL
			"xsi:schemaLocation"
            };

	public boolean isKnownBug(String inputString) {
		for(int i=0; i<knownBug.length;i++) {
			if (knownBug[i].equals(inputString)) return true;
		}
		return false;
	}

	public Document readFile(String fileName) throws Exception {
        if (fileName == null) {
                throw new Exception("Wrong filename for readFile()");
        }
        return readFile(new File(fileName));
	}
	public Document readFile(File file) throws Exception {
	    Document doc;

        try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                //dbf.setValidating(true);
                DocumentBuilder db = dbf.newDocumentBuilder();

                doc = db.parse(file);

                return doc;
        }
        catch(SAXParseException ex) {
                throw(ex);
        }
        catch(SAXException ex) {
                Exception x = ex.getException();
                throw ((x==null) ? ex : x);
        }
    }
	public void printParent(Node node, int i) {
		if (node.getParentNode() == null) {
			totalLevel = i;
			return;
		}
		else {
			printParent(node.getParentNode(), i+1);
			System.out.println(node.getNodeName());
			for (int j=i; j<totalLevel;j++)
				System.out.print("   ");
		}
	}
	public void addMissedElement(Node node) {
		for(int i=0; i<missedElement.size();i++) {
			if (((String)missedElement.elementAt(i)).equals(node.getNodeName())) return;
		}
		totalLevel = 0;
		printParent(node, 0);
		System.out.println("");
		missedElement.add(node.getNodeName());
	}
	public void addWrongElement(Node node, Node rightNode) {
		for(int i=0; i<wrongElement.size();i++) {
			if (((String)wrongElement.elementAt(i)).equals(node.getNodeName())
					&& ((String)rightElement.elementAt(i)).equals(rightNode.getNodeName())) return;
		}
		wrongElement.add(node.getNodeName());
		rightElement.add(rightNode.getNodeName());
/*		printParent(node, 0);
		System.out.println("");
		printParent(rightNode, 0);
		System.out.println("");*/
	}
	public void printMissedElement(){
		System.out.println("==========================Missed Elements==================");
		for(int i=0; i<missedElement.size();i++) {
			System.out.println(i + "  " +((String)missedElement.elementAt(i)));
		}
		System.out.println("==========================Miss Transformed Elements ==================");
		for(int i=0; i<wrongElement.size();i++) {
			System.out.println(i + "  " +((String)wrongElement.elementAt(i)) + " ==> " + ((String)rightElement.elementAt(i)));
		}
	}
	public void cleanMissedElement() {
		missedElement.removeAllElements();
		wrongElement.removeAllElements();
		rightElement.removeAllElements();
}

	public void addMissedAttribute(String node, Node source) {
		for(int i=0; i<missedAttribute.size();i++) {
//			if (((String)missedAttribute.elementAt(i)).equals(node)&& (((Node)missedAttributeP.elementAt(i)).getNodeName().equals(source.getNodeName()))) return;
			if (((String)missedAttribute.elementAt(i)).equals(node)) return;
		}
//		totalLevel = 0;
//		printParent(source, 0);

		if (isKnownBug(node)) return;

		missedAttribute.add(node);
		missedAttributeP.add(source);
	}

	public void printMissedAttribute(){
		System.out.println("==========================Missed Attributes==================");
		for(int i=0; i<missedAttribute.size();i++) {
			if (i==0 ||(!((Node)missedAttributeP.elementAt(i)).getNodeName().equals(((Node)missedAttributeP.elementAt(i-1)).getNodeName())) ) {
				System.out.println("\n\nThe following attributes of Element <" + ((Node)missedAttributeP.elementAt(i)).getNodeName() + "> are missing:");
				totalLevel = 0;
				printParent((Node)missedAttributeP.elementAt(i), 0);
				System.out.println();
			}
			System.out.println("   (" + i + ") Attribute (" +((String)missedAttribute.elementAt(i))+ ") of Element <" + ((Node)missedAttributeP.elementAt(i)).getNodeName() + ">");
		}
	}
	public void cleanMissedAttribute() {
			missedAttribute.removeAllElements();
			missedAttributeP.removeAllElements();
	}


	public void configure() throws Exception{
	  	int totalNode,totalTestCases=0;
	  	int currentTestCase=0;
	  	int currentIndex = 0;
        NodeList testNodeList=null;

        Properties props = new Properties();
        props.load(new FileInputStream("hl7.properties"));

        for (int j =1;j<=(props.size()/2);j++) {
        	String sourceFileName = props.getProperty("Source"+j);
        	String resultFileName = props.getProperty("Result"+j);
        	String xsdFileName = props.getProperty("XSD"+j);
        	Node sourceRootNode = readFile(sourceFileName);
        	Node repRootNode = readFile(resultFileName);
        	System.out.println("===================== FILE " + j + ": "+ sourceFileName + "=====================");
        	cleanMissedElement();
        	cleanMissedAttribute();
        	compareElementNode(sourceRootNode, repRootNode);
    		printMissedElement();
    		printMissedAttribute();
        }
/*      for (int j =1;j<=(props.size()/3);j++) {
        for (int j =1;j<=1;j++) {
        	String sourceFileName = props.getProperty("Source"+j);
        	String resultFileName = props.getProperty("Result"+j);
        	String xsdFileName = props.getProperty("XSD"+j);
        	Node repRootNode = readFile(resultFileName);
        	System.out.println("===================== FILE " + j + ": "+ sourceFileName + "=====================");
        	Element rootElement = ((Element)(repRootNode.getChildNodes().item(0)));
        	//rootElement.removeAttribute("xmlns:xsi");
        	rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        	rootElement.setAttribute("xsi:noNamespaceSchemaLocation",xsdFileName);
        	outputXML((Document)repRootNode,"c:/a.xml");
        	schemaValidation("file:///c:/a.xml");
        }
*/
	  }
	public void configure(String sourceFileName, String resultFileName) throws Exception{
	  	int totalNode,totalTestCases=0;
	  	int currentTestCase=0;
	  	int currentIndex = 0;
        NodeList testNodeList=null;

        Node sourceRootNode = readFile(sourceFileName);
        Node repRootNode = readFile(resultFileName);
        compareElementNode(sourceRootNode, repRootNode);
		printMissedElement();
		printMissedAttribute();
	  }
	public void compareElementNode(Node source, Node result) {
		NodeList sourceChildNodes = source.getChildNodes();
		NodeList resultChildNodes = result.getChildNodes();

		Vector sourceChildElementNodes = new Vector();
		int jj =0;
		for (int i=0; i<sourceChildNodes.getLength();i++) {
			if (((Node)(sourceChildNodes.item(i))).getNodeType()== Node.ELEMENT_NODE) {
				//System.out.println((jj++)+" source name "+((Node)(sourceChildNodes.item(i))).getNodeName());
				sourceChildElementNodes.add(((Node)(sourceChildNodes.item(i))));
			}
		}
		jj = 0;
		Vector resultChildElementNodes = new Vector();
		for (int i=0; i<resultChildNodes.getLength();i++) {
			if (((Node)(resultChildNodes.item(i))).getNodeType()== Node.ELEMENT_NODE) {
				//System.out.println((jj++)+" source name "+((Node)(resultChildNodes.item(i))).getNodeName());
				resultChildElementNodes.add(((Node)(resultChildNodes.item(i))));
			}
		}

		int hr = sourceChildElementNodes.size();
		int vt = resultChildElementNodes.size();

		if (vt == 0) {
			for(int k=1;k<=hr;k++)
				addMissedElement(((Node)sourceChildElementNodes.elementAt(k-1)));
		}

		int [][]  alignment = new int[hr+1][vt+1];
		int [][]  alignmentp = new int[hr+1][vt+1];
		int [] hr_flag = new int[hr+1];
		int [] vt_flag = new int[vt+1];

		for (int i=0;i<=hr;i++) {
			alignment[i][0] = i;
			hr_flag[i] = 0;
		}
		for (int i=0;i<=vt;i++) {
			alignment[0][i] = i;
			vt_flag[i] = 0;
		}


		alignment[0][0] = 0;
		for (int i=1;i<=hr;i++) {
			for (int j=1;j<=vt;j++) {
				int s = 0;
				if (((Node)sourceChildElementNodes.elementAt(i-1)).getNodeName().equals(((Node)resultChildElementNodes.elementAt(j-1)).getNodeName())) {
					s = 0;
				}
				else s = 1;
				int Hi_1j_1_sij = alignment[i-1][j-1] + s;
				alignment[i][j] = min3(Hi_1j_1_sij, alignment[i-1][j]+1,alignment[i][j-1]+1);
				if (alignment[i][j] == Hi_1j_1_sij) alignmentp[i][j] = 1;
				else if (alignment[i][j] == alignment[i-1][j]+1) alignmentp[i][j] = 2;
				else alignmentp[i][j] = 3;
			}
		}
		int hrIndex = hr;
		int vtIndex = vt;

		while (hrIndex != 0 && vtIndex != 0) {
			if (alignmentp[hrIndex][vtIndex] == 1) {
				if (((Node)sourceChildElementNodes.elementAt(hrIndex-1)).getNodeName().equals(((Node)resultChildElementNodes.elementAt(vtIndex-1)).getNodeName())) {
				}
				else {
					hr_flag[hrIndex-1] = -1;
					vt_flag[vtIndex-1] = -1;
				}
				hrIndex --;
				vtIndex --;
				continue;
			}
			if (alignmentp[hrIndex][vtIndex] == 2) {
				hr_flag[hrIndex-1] = -1;
				hrIndex --;
				continue;
			}
			if (alignmentp[hrIndex][vtIndex] == 3) {
				vt_flag[vtIndex-1] = -1;
				vtIndex --;
				continue;
			}
		}

		for (int i=0;i<=hr;i++) {
			if (hr_flag[i] == -1) {
				for(int j=0;j<=vt;j++) {
					if (vt_flag[j] == -1) {
						if (((Node)sourceChildElementNodes.elementAt(i)).getNodeName().equals(((Node)resultChildElementNodes.elementAt(j)).getNodeName())) {
							hr_flag[i] = j;
							vt_flag[j] = i;
							break;
						}
					}
				}
			}
		}

		hrIndex = hr;
		vtIndex = vt;

		while (hrIndex != 0 && vtIndex != 0) {
			if (alignmentp[hrIndex][vtIndex] == 1) {
				if (((Node)sourceChildElementNodes.elementAt(hrIndex-1)).getNodeName().equals(((Node)resultChildElementNodes.elementAt(vtIndex-1)).getNodeName())) {
					compareElementNode(((Node)sourceChildElementNodes.elementAt(hrIndex-1)),((Node)resultChildElementNodes.elementAt(vtIndex-1)));
				}
				else {
					if (hr_flag[hrIndex-1] == -1) {
						addWrongElement(((Node)sourceChildElementNodes.elementAt(hrIndex-1)),((Node)resultChildElementNodes.elementAt(vtIndex-1)));
						compareElementNode(((Node)sourceChildElementNodes.elementAt(hrIndex-1)),((Node)resultChildElementNodes.elementAt(vtIndex-1)));
					}
					else {
						compareElementNode(((Node)sourceChildElementNodes.elementAt(hrIndex-1)),((Node)resultChildElementNodes.elementAt(hr_flag[hrIndex-1])));
					}
				}
				hrIndex --;
				vtIndex --;
				continue;
			}
			if (alignmentp[hrIndex][vtIndex] == 2) {
				if (hr_flag[hrIndex-1] == -1) {
					addMissedElement(((Node)sourceChildElementNodes.elementAt(hrIndex-1)));
				}
				else {
					compareElementNode(((Node)sourceChildElementNodes.elementAt(hrIndex-1)),((Node)resultChildElementNodes.elementAt(hr_flag[hrIndex-1])));
				}
				hrIndex --;
				continue;
			}
			if (alignmentp[hrIndex][vtIndex] == 3) {
				vtIndex --;
				continue;
			}
		}
		compareAttributeNode(source,result);
	}
	public void compareAttributeNode(Node source, Node result) {

		if (source.getNodeType()!= Node.ELEMENT_NODE) return;
		if (result.getNodeType()!= Node.ELEMENT_NODE) return;

		NamedNodeMap sourceChildNodes = source.getAttributes();
		NamedNodeMap resultChildNodes = result.getAttributes();

		Vector sourceChildAttibuteNodes = new Vector();
		int jj =0;
		for (int i=0; i<sourceChildNodes.getLength();i++) {
			Attr attribute = (Attr)sourceChildNodes.item(i);
			sourceChildAttibuteNodes.add(((String)(attribute.getName())));
		}
		jj = 0;
		Vector resultChildAttributeNodes = new Vector();
		for (int i=0; i<resultChildNodes.getLength();i++) {
			Attr attribute = (Attr)resultChildNodes.item(i);
			resultChildAttributeNodes.add(((String)(attribute.getName())));
		}

		int hr = sourceChildAttibuteNodes.size();
		int vt = resultChildAttributeNodes.size();

		for(int i=0; i<hr; i++) {
			int flag = 0;
			for (int j=0;j<vt;j++) {
				if (((String)sourceChildAttibuteNodes.elementAt(i)).equals(((String)resultChildAttributeNodes.elementAt(j)))) {
					flag = 1;
					break;
				}
			}
			if (flag != 1){
				addMissedAttribute(((String)sourceChildAttibuteNodes.elementAt(i)),source);
			}
		}
	}

	public int min3(int a1, int a2, int a3) {
		if (a1 < a2) {
			if (a1 < a3) {
				return a1;
			}
			else {
				return a3;
			}
		}
		else {
			if (a2 < a3) {
				return a2;
			}
			else {
				return a3;
			}
		}
	}
	public String getNameAttribute(Node node){
	  	NamedNodeMap attributes = node.getAttributes();
   		for(int k=0; k<attributes.getLength(); k++) {
		      Attr attribute = (Attr)attributes.item(k);
		      if (attribute.getName().equals("name")) {
		      	return attribute.getNodeValue();
		      }
   		}
   		return "";
	  }
/*	public void schemaValidation(String xmlFile) {
		try {
		DOMParser parser = new DOMParser();

	    parser.setFeature("http://xml.org/sax/features/validation", true );
	    parser.setFeature("http://apache.org/xml/features/validation/schema", true );
	    parser.setFeature("http://apache.org/xml/features/validation/schema-full-checking",true);


	    // parse the document
	    System.out.println("Start");
	    InputSource is = new InputSource(xmlFile);
	    parser.parse( is );
	    System.out.println("end");
		}catch(Exception e) {
			System.out.println(e);
		}
	}*/
	public void outputXML(Document domDoc, String testFileName)
       throws JDOMException, IOException {
       // Create new DOMBuilder, using default parser
       DOMBuilder builder = new DOMBuilder();
       org.jdom.Document jdomDoc = builder.build(domDoc);

       XMLOutputter outputter = new XMLOutputter();
       File file = new File(testFileName);
       FileWriter writer = new FileWriter(file);
       outputter.output(jdomDoc,writer);
       writer.close();
   }
    public static void main(String[] args) throws Exception {
        ElementTest elementTest = new ElementTest();
//        elementTest.configure("C:\\Projects\\javaSIG\\etc\\PORR_MT040001.xml",
//                "C:\\Projects\\javaSIG\\out\\PORR_MT040001_out.xml");
		if (args.length == 2)
			elementTest.configure(args[0],args[1]);
		else
			elementTest.configure();
    }
}


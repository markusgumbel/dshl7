package org.hl7.xml;

/**
 * $Header$ <p/> ****************************************************************** COPYRIGHT NOTICE
 * ****************************************************************** The contents of this file are subject to the
 * Health Level-7 Public License Version 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.hl7.org/HPL/ <p/> Software distributed under the License
 * is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License. <p/> The Original Code is all this file. <p/>
 * The Initial Developer of the Original Code is . Portions created by Initial Developer are Copyright (C) 2002-2004
 * Health Level Seven, Inc. All Rights Reserved. <p/> Contributor(s): <p/>
 * ********************************************************************************* <p/> /** base class for datype
 * presenter
 * 
 * @author OWNER: Eric Chen Date: Jun 5, 2005
 * @author LAST UPDATE: $Author: crosenthal $
 * @version $Revision: 5676 $
 * @date $$Date: 2007-04-04 16:51:25 -0400 (Wed, 04 Apr 2007) $
 */
public abstract class DatatypePresenterBase implements DatatypePresenter {
	public static final String ATTR_NULL_FLAVOR = "nullFlavor";
	public static final String HL7_URI = "urn:hl7-org:v3";
	public static final String W3_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema-instance";
	// Content can not match to XML attributes, only to XML text node
	public static final String TEXT = "inlineText";
	// subClass
	public static final String SUB_TYPE = "subClass";
}
/**
 * HISTORY : $Log$ HISTORY : Revision 1.2 2005/06/05 18:31:13 echen HISTORY : Create DatatypePresenterBase, all the
 * datatype presenter classes extend from Base class instead of DatatypePresnter interface HISTORY : HISTORY : Revision
 * 1.1 2005/06/05 18:27:29 echen HISTORY : Create DatatypePresenterBase, all the datatype presenter classes extend from
 * Base class instead of DatatypePresnter interface HISTORY :
 */

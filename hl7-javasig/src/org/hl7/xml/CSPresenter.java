/* The contents of this file are subject to the Health Level-7 Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the
 * License at http://www.hl7.org/HPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and
 * limitations under the License.
 *
 * The Original Code is all this file.
 *
 * The Initial Developer of the Original Code is .
 * Portions created by Initial Developer are Copyright (C) 2002-2004 
 * Health Level Seven, Inc. All Rights Reserved.
 *
 * Contributor(s): 
 */
package org.hl7.xml;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.hl7.meta.Datatype;
import org.hl7.types.CS;
import org.hl7.types.DSET;
import org.hl7.types.SET;
import org.hl7.types.ST;
import org.hl7.types.UID;
import org.hl7.types.ValueFactory;
import org.hl7.types.impl.CSimpl;
import org.hl7.types.impl.CSnull;
import org.hl7.types.impl.SETjuSetAdapter;
import org.hl7.xml.builder.BuilderException;
import org.hl7.xml.builder.DatatypeBuilder;
import org.hl7.xml.builder.RimGraphXMLSpeaker;
import org.hl7.xml.parser.SimpleTypeContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This little guy knows how to build CS values.
 * 
 * FIXME: we need to use the domains to assign the default code system etc.
 * 
 * FIXME: defaults are not configurable.
 */
public class CSPresenter extends DatatypePresenterBase {
	// Keep static String constants as public protection
	public static final String ATTR_CODE = "code";
	public static final String ATTR_DISPLAY_NAME = "displayName";
	public static final String ATTR_CODE_SYSTEM = "codeSystem";
	public static final String ATTR_CODE_SYSTEM_NAME = "codeSystemName";
	public static final String ATTR_CODE_SYSTEM_VERSION = "codeSystemVersion";

	private static final CSPresenter INSTANCE = new CSPresenter();

	private static class CSContentHandler extends SimpleTypeContentHandler {
		CS _result = null;

		protected void notifyActivation(Attributes atts) {
			String nullFlavorString = atts.getValue(DatatypePresenterBase.ATTR_NULL_FLAVOR);
			if (nullFlavorString != null) {
				_result = CSnull.valueOf(nullFlavorString);
			} else {
				ST code = ValueFactory.getInstance().STvalueOfLiteral(atts.getValue(ATTR_CODE));
				UID codeSystem = ValueFactory.getInstance().UIDvalueOfLiteral(atts.getValue(ATTR_CODE_SYSTEM));
				ST displayName = ValueFactory.getInstance().UIDvalueOfLiteral(atts.getValue(ATTR_CODE_SYSTEM));
				ST codeSystemName = ValueFactory.getInstance().STvalueOfLiteral(atts.getValue(ATTR_CODE_SYSTEM_NAME));
				ST codeSystemVersion = ValueFactory.getInstance().STvalueOfLiteral(atts.getValue(ATTR_CODE_SYSTEM_VERSION));
				_result = CSimpl.valueOf(code, codeSystem, displayName, codeSystemName, codeSystemVersion);
			}
		}

		protected Object getResult() {
			return (_result == null) ? CSnull.NI : _result;
		}
	}

	protected static class CSBuilder implements DatatypeBuilder<CS> {
		public void build(RimGraphXMLSpeaker.ContentBuilder builder, CS value, String localName) throws BuilderException {
			try {
				CS cs = (CS) value;
				if (builder.nullValueHandled(value, localName))
					return;
				builder.addAttribute(ATTR_CODE, cs.code());
				// builder.addAttribute(ATTR_CODE_SYSTEM, cs.codeSystem());
				// builder.addAttribute(ATTR_CODE_SYSTEM_NAME, cs.codeSystemName());
				// builder.addAttribute(ATTR_CODE_SYSTEM_VERSION, cs.codeSystemVersion());
				builder.startElement(localName);
				builder.endElement(localName);
			} catch (SAXException ex) {
				throw new BuilderException(ex);
			}
		}

		public void buildStructural(RimGraphXMLSpeaker.ContentBuilder builder, CS value, String localName)
				throws BuilderException {
			CS csValue = (CS) value;
			if (value.nonNull().isTrue())
				builder.addAttribute(localName, csValue.code().toString());
			// else: a null flavor here is just not output
		}

		/* Only handles DSET<CS>. */
		public void buildAttribute(RimGraphXMLSpeaker.ContentBuilder builder, SET<? extends CS> value, String localName)
				throws BuilderException {
			if (value == null || value.isNullJ() || value.isEmpty().isTrue()) {
				return;
			}
			final SETjuSetAdapter valueSet = (SETjuSetAdapter) value;
			final StringBuffer sb = new StringBuffer();
			boolean first = true;
			for (Iterator<CS> it = valueSet.iterator(); it.hasNext(); first = false) {
				CS component = it.next();
				if (!first) {
					sb.append(' ');
				}
				sb.append(component.code().toString());
			}
			if (sb.length() > 0) {
				builder.addAttribute(localName, sb.toString());
			}
		}
	}

	public static CS getValue(Object valueString) {
		return (valueString == null) ? CSimpl.valueOf(null, "unknown") : CSimpl.valueOf(valueString.toString(), "unknown");
	}

	public static DSET<CS> parseList(String value, String codeSystem) {
		if (value == null)
			return null;
		Set<CS> set = new HashSet<CS>();
		for (StringTokenizer st = new StringTokenizer(value); st.hasMoreTokens();) {
			String token = st.nextToken();
			CS cs = CSimpl.valueOf(token, codeSystem);
			set.add(cs);
		}
		return set.isEmpty() ? null : SETjuSetAdapter.valueOf(set);
	}

	private CSPresenter() {
	    super();
	}

	public static CSPresenter instance() {
		return INSTANCE;
	}

	public ContentHandler getContentHandler(String namespaceURI, String localName, String qName, Attributes atts,
			Datatype datatype) {
		return new CSContentHandler();
	}

	public DatatypeBuilder getBuilder() {
		return new CSBuilder();
	}
}

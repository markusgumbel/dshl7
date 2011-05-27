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
package org.hl7.types.impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashSet;
import java.util.Set;

import org.hl7.types.ANY;
import org.hl7.types.BL;
import org.hl7.types.CS;
import org.hl7.types.ST;

/**
 * Adapter for java.net.URL to the org.hl7.types.URL interface.
 * 
 * FIXME: There are a couple of quirks:
 *  - the j.n.URL has no way of getting at an opaque part (i.e. everything after the scheme, and I'm not sure we can
 * reassemble that from the URL accessors.
 *  - given that it's so hard to get at the opaque part, the equals implementation may not be ideal.
 */
public class URLjnURLAdapter extends ANYimpl implements org.hl7.types.URL {
	java.net.URL _data;
	private static final Set<String> KNOWN_PROTOCOLS = new HashSet<String>();
	static {
		KNOWN_PROTOCOLS.add("tel");
		KNOWN_PROTOCOLS.add("fax");
		KNOWN_PROTOCOLS.add("modem");
		KNOWN_PROTOCOLS.add("mllp");
		KNOWN_PROTOCOLS.add("nfs");
		KNOWN_PROTOCOLS.add("telnet");
	}
	private static final URLStreamHandler FAKE_STREAM_HANDLER = new URLStreamHandler() {
		@Override
        protected URLConnection openConnection(URL url) throws IOException {
			throw new UnsupportedOperationException("Method not implemented.");
		}
	};

    private static final java.net.URL url(final String url) throws MalformedURLException
    {        
        final int endProtocol = url.indexOf(':');
	if(endProtocol > -1){
	  final String protocol = url.substring(0, endProtocol);
	  if (KNOWN_PROTOCOLS.contains(protocol))
	    {
	      // Be tollerant of protocol:host... instead of protocol://host...
	      int endProtocolSep = endProtocol + 1;
	      while (url.length() >= endProtocolSep && url.charAt(endProtocolSep) == '/')
		{
		  endProtocolSep++;
		}
	      final String afterProtocol = url.substring(endProtocolSep);
	      final java.net.URL javaUrl = new URL(protocol, "", -1, afterProtocol, FAKE_STREAM_HANDLER);
	      return javaUrl;
	    }
	}
	final java.net.URL javaUrl = new java.net.URL(url.toString());
	return javaUrl;
    }

    protected URLjnURLAdapter(final java.net.URL data) {
		super(null);
		if (data == null) {
            throw new IllegalArgumentException();
        } else {
            this._data = data;
        }
	}

	protected URLjnURLAdapter(final org.hl7.types.URL url) {
		super(null);
		if(url instanceof URLjnURLAdapter) {
            _data = ((URLjnURLAdapter)url)._data;
        } else {
			try {
				_data = new java.net.URL(url.toString());
			}
			catch (final MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	protected URLjnURLAdapter(final String data) {
		super(null);
		try {
			if (data == null) {
                throw new IllegalArgumentException();
            } else {
                this._data = url(data);
            }
		} catch (final java.net.MalformedURLException x) {
			throw new IllegalArgumentException(x.getMessage());
		}
	}

	public static org.hl7.types.URL valueOf(final java.net.URL data) {
		if (data == null) {
            return URLnull.NI;
        } else {
            return new URLjnURLAdapter(data);
        }
	}

	java.net.URL tojnURL() {
		return _data;
	}

	@Override
    public final BL equal(final ANY that) {
		if (that instanceof org.hl7.types.URL) {
			if (that instanceof URLjnURLAdapter) {
				return BLimpl.valueOf(this._data.equals(((URLjnURLAdapter) that)._data));
			} else {
				final org.hl7.types.URL thatURL = (org.hl7.types.URL) that;
				return this.scheme().equal(thatURL.scheme()).and(this.address().equal(thatURL.address()));
			}
		} else {
            return BLimpl.FALSE;
        }
	}

	public CS scheme() {
		if (this.isNullJ() && _data == null) {
            return CSnull.NA;
        } else {
			return CSimpl.valueOf(_data.getProtocol(), "2.16.840.1.113883.5.143");
			// FIXME: code system!!!
		}
	}

	public ST address() {
		if (this.isNullJ() && _data == null) {
            return STnull.NA;
        } else {
			final String urlstring = _data.toString();
			final String scheme = _data.getProtocol();
			return STjlStringAdapter.valueOf(urlstring.substring(scheme.length() + 1));
		}
	}

	@Override
    public String toString() {
		return _data.toString();
	}
    
    /**
     * An eclipse code generator is used to add an Externalizable 
     * implementation to the org.hl7 types.  Normally this implementation is
     * not checked into SVN.  However, the code generated for this class
     * did not work and had to be replaced with a hand-coded implementation.
     * 
     * @author jmoore
     *
     * @hand-coded 
     */
    @Override
    public void readExternal(final ObjectInput oi) throws IOException
    {
        super.readExternal(oi);
        try
        {
            final String url = oi.readUTF();
            _data = url(url);
        }
        catch (final Throwable t)
        {
            final IOException ioe = new IOException(t.getMessage());
            ioe.initCause(t);
            throw ioe;
        }
    }

    /**
     * An eclipse code generator is used to add an Externalizable 
     * implementation to the org.hl7 types.  Normally this implementation is
     * not checked into SVN.  However, the code generated for this class
     * did not work and had to be replaced with a hand-coded implementation.
     *
     * @author jmoore
     * 
     * @hand-coded
     */
    @Override
    public void writeExternal(final ObjectOutput oo) throws IOException
    {
        super.writeExternal(oo);
        try
        {
            final String url = _data.toExternalForm();
            oo.writeUTF(url);
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
            final IOException ioe = new IOException(t.getMessage());
            ioe.initCause(t);
            throw ioe;
        }
    }
}

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
* The Initial Developer of the Original Code is Gunther Schadow.
* Portions created by Initial Developer are Copyright (C) 2002-2004 
* Health Level Seven, Inc. All Rights Reserved.
*
* Contributor(s): 
*
* $Id: MessageTypeLoaderAdapter.java 7377 2007-09-26 19:28:36Z gschadow $
*/
package org.hl7.meta.mif;

import org.hl7.meta.LoaderException;
import org.hl7.meta.MessageTypeLoader;

/**
 * A factory that manufactures a {@link org.hl7.meta.MessageTypeLoader} instance.
 *
 * @author Gunther Schadow
 */
public class MessageTypeLoaderAdapter implements MessageTypeLoader {

  // Singleton
  private MessageTypeLoaderAdapter() { }
  private static MessageTypeLoaderAdapter _instance = new MessageTypeLoaderAdapter();
  public static MessageTypeLoaderAdapter getInstance() { return _instance; }

  /**
   * Loads the message type metadata and returns the reference to the
   * metadata object for the message type.  Note that referenced CMETs are
   * not loaded automatically, but only on demand when the corresponding
   * association is actually traversed.
   *
   * @param messageTypeName  the message type ID
   * @return the message type metadata
   * @throws LoaderException  if loading fails
   */
  public MessageTypeAdapter loadMessageType(String messageTypeName) throws LoaderException {
    try {
      MessageTypeAdapter mta = MessageTypeAdapter.getMessageType(messageTypeName);
      //System.err.println("MTA:"+mta);
      //com.geocities.sbridges_geo.debug.Inspector.inspect(mta);
      return mta;
    } catch(Exception x) {
      throw new LoaderException(x);
    }
  }

  /**
   * Loads the CMET metadata and returns the reference to the
   * metadata object for the message type.  Note that referenced CMETs are
   * not loaded automatically, but only on demand when the corresponding
   * association is actually traversed.
   *
   * @param messageTypeName  the message type ID
   * @return the message type metadata
   * @throws LoaderException  if loading fails
   */
  public MessageTypeAdapter loadCmet(String messageTypeName) throws LoaderException {
    // for now this is just shortcut with loadMessageType, but once we
    // support all that header stuff, it may again separate.
    return loadMessageType(messageTypeName);
  }
  
  /**
   * A hook to closes DB connections if they are open, but not here.
   */
  public void close() { }
}

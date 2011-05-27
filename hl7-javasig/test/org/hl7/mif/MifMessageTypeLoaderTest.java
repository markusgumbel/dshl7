/**
 * <!-- LICENSE_TEXT_START -->
 * $Header$
 * <p/>   
 * ******************************************************************
 * COPYRIGHT NOTICE
 * ******************************************************************
 *
 * The caAdapter Software License, Version 1.2
 *
 * Copyright 2005 SAIC. This software was developed in conjunction with the National Cancer Institute. To the extent government employees are co-authors, any rights in such works are subject to Title 17 of the United States Code, section 105.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the Copyright Notice above, this list of conditions, and the disclaimer of Article 3, below. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
 *
 * "This product includes software developed by the SAIC and the National Cancer Institute."
 * If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself, wherever such third-party acknowledgments normally appear.
 *
 * 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
 * 
 * 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
 *
 * 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT, THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

package org.hl7.mif;

import junit.framework.TestCase;
import org.hl7.meta.CloneClass;
import org.hl7.meta.MessageType;
import org.hl7.meta.mif.MessageTypeLoaderAdapter;

/**
 * Description of the class
 *
 * @author OWNER: Eric Chen
 * @author LAST UPDATE $Author: crosenthal $
 * @version Since caAdapter v1.3
 *          revision    $Revision: 5652 $
 *          date        $Date: 2007-03-30 11:35:44 -0400 (Fri, 30 Mar 2007) $
 */
public class MifMessageTypeLoaderTest extends TestCase
{
    public void testLoad150003() throws Exception
    {
        MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter.getInstance();
        MessageType messageType = jmtl.loadMessageType("COCT_MT150003");
        assertNotNull(messageType);
        CloneClass rootClass = messageType.getRootClass();
        assertNotNull(rootClass);
        assertEquals(rootClass.getName(), "Organization");
    }

    public void testLoad710000() throws Exception
    {
        MessageTypeLoaderAdapter jmtl = MessageTypeLoaderAdapter.getInstance();
        MessageType messageType = jmtl.loadMessageType("COCT_MT710000");
        assertNotNull(messageType);
        CloneClass rootClass = messageType.getRootClass();
        assertNotNull(rootClass);
        assertEquals(rootClass.getName(), "Place");
    }

}

/**
 * HISTORY      : $Log$
 * HISTORY      : Revision 1.3  2006/05/04 16:04:32  echen
 * HISTORY      : un-deprecated CMET id
 * HISTORY      :
 * HISTORY      : Revision 1.2  2006/04/27 14:20:43  echen
 * HISTORY      : Support for COCT_MT150003 CMET
 * HISTORY      :
 * HISTORY      : Revision 1.1  2006/04/26 21:47:07  echen
 * HISTORY      : Add MifMessageTypeLoaderTest
 * HISTORY      :
*/

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

import junit.framework.TestCase;

import org.hl7.types.TEL;
import org.hl7.types.TS;

/**
 * * A test suite that exercises {@link TELimpl} class.
 * 
 * @author Peter Hendler
 */
public class TELimplTest extends TestCase {
    private static TS TS_1 = TSjuDateAdapter.valueOf(1);
    
    private static final String ADDR = "tel:12341231234";
    
    private static final TEL TEL_1 =
            TELimpl.valueOf(ADDR, "HP", IVLimpl.valueOf(BLimpl.TRUE, TS_1,
                    TS_1, BLimpl.TRUE));
    private static final TEL TEL_2 = TELimpl.valueOf(ADDR);
    
    public void test_equal() throws Exception {
        assertTrue(TEL_1.equal(TEL_2).isTrue());
        assertTrue((TEL_1.address()).equal(TEL_2.address()).isTrue());
        assertFalse(TEL_1.equal(TEL_2).isFalse());
        assertFalse((TEL_1.address()).equal(TEL_2.address()).isFalse());
    }
    
    public void testValueOf() {
        /*
         * TODO: we really should have some asserts here. Someone who knows how
         * to extract info out of a TEL object should write some asserts to
         * verify parsing.
         */
        TELimpl.valueOf("tel:+1(317)630-7960");
        TELimpl.valueOf("tel:+358-555-1234567");
        TELimpl.valueOf("fax:+358.555.1234567");
        TELimpl.valueOf("modem:+3585551234567;type=v32b?7e1;type=v110");
        TELimpl.valueOf("tel:+358-555-1234567;postd=pp22");
        TELimpl.valueOf("tel:0w003585551234567;phone-context=+3585551234");
        TELimpl
                .valueOf("tel:+1234567890;phone-context=+1234;vnd.company.option=foo");
        
        TELimpl.valueOf("mailto:chris@example.com");
        TELimpl.valueOf("mailto:infobot@example.com?subject=current-issue");
        TELimpl.valueOf("mailto:infobot@example.com?body=send%20current-issue");
        TELimpl
                .valueOf("mailto:infobot@example.com?body=send%20current-issue%0D%0Asend%20index");
        TELimpl
                .valueOf("mailto:foobar@example.com?In-Reply-To=%3c3469A91.D10AF4C@example.com");
        TELimpl
                .valueOf("mailto:majordomo@example.com?body=subscribe%20bamboo-l");
        TELimpl.valueOf("mailto:joe@example.com?cc=bob@example.com&body=hello");
        TELimpl
                .valueOf("mailto:?to=joe@example.com&cc=bob@example.com&body=hello");
        
        TELimpl
                .valueOf("http://www.google.com/search?hl=en&ie=UTF-8&oe=UTF-8&q=miserable+failure");
        TELimpl.valueOf("http://abc.com:80/~smith/home.html");
        TELimpl.valueOf("http://ABC.com/%7Esmith/home.html");
        TELimpl.valueOf("http://ABC.com:/%7esmith/home.html");
        
        TELimpl.valueOf("ftp://@host.com/");
        TELimpl.valueOf("ftp://host.com/");
        TELimpl.valueOf("ftp://foo:@host.com/");
        
        TELimpl.valueOf("mllp://host.com:1234/");
        TELimpl.valueOf("mllp://192.168.0.1:1234/");
        
        TELimpl.valueOf("file://vms.host.edu/disk$user/my/notes/note12345.txt");
        TELimpl.valueOf("file:///disk$user/my/notes/note12345.txt");
        
        TELimpl.valueOf("nfs://server.com//a/b/c/d/e/f");
        TELimpl.valueOf("nfs://server.com:8392/d/e/f");
        
        TELimpl.valueOf("telnet://user:password@host.com:9023/");
        TELimpl.valueOf("telnet://user:password@host.com/");
    }
}

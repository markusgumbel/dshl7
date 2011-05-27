package org.hl7.util;

import java.util.Map;
import junit.framework.TestCase;
import org.hl7.types.UID;
import org.hl7.types.impl.DomainMapImpl;

/**
 * @author Jere Krischel
 */
public class OidLoaderTest extends TestCase
{
    public void testPropertiesFileFound()
    {
			Map<String,UID> domainMap = DomainMapImpl.getInstance();
			assertEquals("2.16.840.1.113883.5.1057", domainMap.get("ContextControl").toString());
    }

}

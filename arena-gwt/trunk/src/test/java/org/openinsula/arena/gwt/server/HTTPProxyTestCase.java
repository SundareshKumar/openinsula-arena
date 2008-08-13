package org.openinsula.arena.gwt.server;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HTTPProxyTestCase {

	@Test
	public void testBuildQuery() {
		Map<String, String> query = new HashMap<String, String>();

		query.put("testeA", "123");

		assertEquals("?testeA=123", HTTPProxy.buildQuery(query));

		query.put("testeB", "456");

		assertEquals("?testeA=123&testeB=456", HTTPProxy.buildQuery(query));
	}

}

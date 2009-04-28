package org.openinsula.arena.appengine.gwt.client.util;

import org.openinsula.arena.appengine.gwt.client.AbstractGwtTestCase;

public class AssertGwtTestCase extends AbstractGwtTestCase {

	public void testIsTrue() {
		Assert.isTrue(true);

		try {
			Assert.isTrue(false, "Message");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Message", e.getMessage());
		}
	}

}

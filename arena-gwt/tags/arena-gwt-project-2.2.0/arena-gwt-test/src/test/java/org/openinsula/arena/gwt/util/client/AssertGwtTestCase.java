package org.openinsula.arena.gwt.util.client;


public class AssertGwtTestCase extends AbstractUtilGwtTestCase {

	public void testIsTrue() {
		Assert.isTrue(true);
		
		try {
			Assert.isTrue(false, "Message");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Message", e.getMessage());
		}
	}
	
}

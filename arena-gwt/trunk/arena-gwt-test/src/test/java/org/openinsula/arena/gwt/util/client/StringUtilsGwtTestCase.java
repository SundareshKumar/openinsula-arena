package org.openinsula.arena.gwt.util.client;


public class StringUtilsGwtTestCase extends AbstractUtilGwtTestCase {

	public void testHasText() {
		assertTrue(StringUtils.hasText("e"));
		assertFalse(StringUtils.hasText(""));
		assertFalse(StringUtils.hasText(" "));
		assertFalse(StringUtils.hasText(null));
	}
	
	public void testHasLength() {
		assertTrue(StringUtils.hasLength(" "));
		assertTrue(StringUtils.hasLength("e"));
		assertFalse(StringUtils.hasLength(""));
		assertFalse(StringUtils.hasLength(null));
	}

}

package org.openinsula.arena.gwt.json.client;

import junit.framework.TestCase;

public class JsonVOTest extends TestCase {

	public void testInvalidType() {
		try {
			new JsonVO(null) {
			};
			fail("IllegalArgumentException expected!");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new JsonVO("") {
			};
			fail("IllegalArgumentException expected!");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			new JsonVO("   ") {
			};
			fail("IllegalArgumentException expected!");
		} catch (IllegalArgumentException e) {
		}
		
	}

	public void testAlreadyCastedJson() {
		JsonVO mock = new JsonVO("mock") {
		};
		
		String json = "{\"mock\":{}}";
		assertSame(json, mock.castJson(json));
	}
	
	public void testCastJson() {
		JsonVO mock = new JsonVO("mock") {
		};
		
		String json = "{\"mockField\":{}}";
		String expected = "{\"mock\":{\"mockField\":{}}}";
		String actual = mock.castJson(json);
		
		assertEquals(expected, actual);
		assertSame(actual, mock.castJson(actual));
	}

}
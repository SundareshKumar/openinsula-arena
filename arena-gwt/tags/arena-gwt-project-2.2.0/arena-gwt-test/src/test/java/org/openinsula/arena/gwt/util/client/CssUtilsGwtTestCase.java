package org.openinsula.arena.gwt.util.client;

public class CssUtilsGwtTestCase extends AbstractUtilGwtTestCase {

	public void testEmptyConstructor() {
		CssUtils css = new CssUtils();
		assertEquals("", css.toString());
		
		css = new CssUtils(null);
		assertEquals("", css.toString());

		css = new CssUtils("");
		assertEquals("", css.toString());
		
		css = new CssUtils("     ");
		assertEquals("", css.toString());
	}
	
	public void testValidConstructor() {
		CssUtils css = new CssUtils("field");
		assertEquals("field", css.toString());
		
		css = new CssUtils("field text field    error");
		assertEquals("field text error", css.toString());
	}
	
	public void testSetStyle() {
		CssUtils css = new CssUtils();
		css.setStyle(null);
		assertEquals("", css.toString());
		
		css.setStyle("");
		assertEquals("", css.toString());
		
		css.setStyle("  ");
		assertEquals("", css.toString());
		
		css.setStyle("  field");
		assertEquals("field", css.toString());
		
		css.setStyle("  field  field field  ");
		assertEquals("field", css.toString());
		
		css.setStyle(" field text   field  error");
		assertEquals("field text error", css.toString());
	}
	
	public void testAddRule() {
		CssUtils css = new CssUtils();
		
		css.addRule("field");
		String cachedCss = css.toString();
		assertEquals("field", cachedCss);
		
		css.addRule("field");
		assertSame(cachedCss, css.toString());
		
		css.addRule("   field ");
		assertSame(cachedCss, css.toString());
		
		css.addRule(null);
		assertSame(cachedCss, css.toString());
		
		css.addRule("");
		assertSame(cachedCss, css.toString());
		
		css.addRule("  ");
		assertSame(cachedCss, css.toString());
		
		css.addRule("   error ");
		assertEquals("field error", css.toString());
	}
	
	public void testDropRule() {
		CssUtils css = new CssUtils();
		String cachedCss = css.toString();
		
		css.dropRule(null);
		assertSame(cachedCss, css.toString());
		
		css.dropRule("");
		assertSame(cachedCss, css.toString());
		
		css.dropRule("  ");
		assertSame(cachedCss, css.toString());
		
		css.dropRule("field");
		assertSame(cachedCss, css.toString());
		
		css.setStyle("field text");
		css.dropRule("field");
		assertEquals("text", css.toString());
		
		css.dropRule("unknown");
		assertEquals("text", css.toString());
		
		css.dropRule("text");
		assertEquals("", css.toString());
	}
	
	public void testHasRule() {
		CssUtils css = new CssUtils();
		assertFalse(css.hasRule("field"));
		
		css.setStyle("field");
		assertFalse(css.hasRule(null));
		assertFalse(css.hasRule(""));
		assertFalse(css.hasRule(" "));
		
		assertTrue(css.hasRule("field"));
		assertTrue(css.hasRule(" field "));
	}
	
}

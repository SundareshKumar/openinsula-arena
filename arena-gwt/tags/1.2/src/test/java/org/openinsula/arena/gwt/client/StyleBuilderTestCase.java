package org.openinsula.arena.gwt.client;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StyleBuilderTestCase {

	@Test
	public void testStyleBuilderConstructor() {
		StyleBuilder styleBuilder = new StyleBuilder();
		assertEquals("", styleBuilder.toString());

		styleBuilder = new StyleBuilder("rebola");
		assertEquals("rebola-", styleBuilder.toString());

		styleBuilder = new StyleBuilder("rebola", "Teste");
		assertEquals("rebola-Teste", styleBuilder.toString());
	}

	@Test
	public void testStyleBuilderSetters() {
		StyleBuilder styleBuilder = new StyleBuilder();
		styleBuilder.setRootComponent("Teste");
		assertEquals("Teste", styleBuilder.toString());

		styleBuilder.setRootComponent("Teste2");
		assertEquals("Teste2", styleBuilder.toString());

		styleBuilder.setModulePrefix("rebola");
		assertEquals("rebola-Teste2", styleBuilder.toString());
	}

	@Test
	public void testAddRule() {
		StyleBuilder styleBuilder = new StyleBuilder("rebola", "Teste");
		assertEquals("rebola-TesteRule", styleBuilder.addRule("Rule"));
		assertEquals("rebola-TesteRule", styleBuilder.toString());
		assertEquals("rebola-TesteRuleRule2", styleBuilder.addRule("Rule2"));
	}

	@Test
	public void testDropRule() {
		StyleBuilder styleBuilder = new StyleBuilder("rebola", "Teste");
		styleBuilder.dropRule();
		assertEquals("rebola-Teste", styleBuilder.toString());

		styleBuilder.addRule("Rule1");
		styleBuilder.addRule("Rule2");
		styleBuilder.addRule("Rule3");

		styleBuilder.dropRule();
		assertEquals("rebola-TesteRule1Rule2", styleBuilder.toString());
		styleBuilder.dropAllRules();
		assertEquals("rebola-Teste", styleBuilder.toString());
	}

	@Test
	public void testGetRule() {
		StyleBuilder styleBuilder = new StyleBuilder();
		assertEquals("Teste", styleBuilder.getRule("Teste"));

		styleBuilder.setRootComponent("Root");
		assertEquals("RootTeste", styleBuilder.getRule("Teste"));

		styleBuilder.setModulePrefix("Module");
		styleBuilder.setSeparator("#");
		assertEquals("Module#RootTeste2", styleBuilder.getRule("Teste2"));
	}

}

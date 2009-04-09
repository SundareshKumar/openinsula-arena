package org.openinsula.arena.lang.string;

import static org.junit.Assert.*;

import org.junit.Test;


public class StringUtilsTestCase {

	@Test
	public void testStringUtilsFunctions() {
		assertEquals("", StringUtils.getDigitsOnly(null));
		assertEquals("", StringUtils.getInitialUpperCase(null));
		assertEquals("", StringUtils.getNonDigitsOnly(null));
		assertEquals("", StringUtils.getStringWithoutAccent(null));

		assertEquals("", StringUtils.getDigitsOnly(""));
		assertEquals("", StringUtils.getDigitsOnly("teste de string"));
		assertEquals("1234567890", StringUtils.getDigitsOnly(".123a4567-890."));
		assertEquals("1234567890", StringUtils.getDigitsOnly("1234567-890"));
		assertEquals("1", StringUtils.getDigitsOnly("eipqweu1weiwq./sad"));
		assertEquals("7", StringUtils.getDigitsOnly("7"));

		assertEquals("O Rato Roeu A Roupa Do Rei De Roma", StringUtils.getInitialUpperCase("o rato roeu a roupa do rei de roma"));

		assertEquals("", StringUtils.getNonDigitsOnly(""));
		assertEquals("teste de string", StringUtils.getNonDigitsOnly("teste de string"));
		assertEquals(".a-.", StringUtils.getNonDigitsOnly(".123a4567-890."));
		assertEquals("-", StringUtils.getNonDigitsOnly("1234567-890"));
		assertEquals("eipqweuweiwq./sad", StringUtils.getNonDigitsOnly("eipqweu1weiwq./sad"));
		assertEquals("", StringUtils.getNonDigitsOnly("7"));

		assertEquals("", StringUtils.getStringWithoutAccent(""));
		assertEquals("A", StringUtils.getStringWithoutAccent("A"));
		assertEquals("aAaAocaoieEuUCc", StringUtils.getStringWithoutAccent("ãÃáÁoçáóíéÉúÚÇç"));
		assertEquals("aeiouaeiouaoCc", StringUtils.getStringWithoutAccent("aeiouáéíóúãõÇç"));
	}

	@Test
	public void testGetValidChars() {
		assertEquals("abc 123&%", StringUtils.getValidChars("abc 123&%^"));
		assertEquals("a@bc 123&%", StringUtils.getValidChars("a@<>bc 123&%^"));
	}

}

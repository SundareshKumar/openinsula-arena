package org.openinsula.arena.validator;

import junit.framework.TestCase;

public class StringUtilsTestCase extends TestCase {
	public void testStringUtilsFunctions() {
		assertEquals("", StringUtils.getDigitsOnly(null));
		assertEquals("", StringUtils.getDigitsOnly(null, ' '));
		assertEquals("", StringUtils.getInitialUpperCase(null));
		assertEquals("", StringUtils.getNonDigitsOnly(null));
		assertEquals("", StringUtils.getStringWithoutAccent(null));

		assertEquals("", StringUtils.getDigitsOnly(""));
		assertEquals("", StringUtils.getDigitsOnly("teste de string"));
		assertEquals("1234567890", StringUtils.getDigitsOnly(".123a4567-890."));
		assertEquals("1234567890", StringUtils.getDigitsOnly("1234567-890"));
		assertEquals("1", StringUtils.getDigitsOnly("eipqweu1weiwq./sad"));
		assertEquals("7", StringUtils.getDigitsOnly("7"));

		assertEquals("123123123,67", StringUtils.getDigitsOnly("123.123.123,67", ','));
		assertEquals("123.123.12367", StringUtils.getDigitsOnly("123.123.123,67", '.'));
		assertEquals("123123,67", StringUtils.getDigitsOnly("oiueqdas12wq3.12asd3,6asd7a", ','));

		assertEquals("O Rato Roeu A Roupa Do Rei De Roma", StringUtils
				.getInitialUpperCase("o rato roeu a roupa do rei de roma"));
		assertEquals("O Rato	Roeu A Roupa Do Rei De Roma", StringUtils
				.getInitialUpperCase("o rato	roeu a roupa do rei de roma"));

		assertEquals("", StringUtils.getNonDigitsOnly(""));
		assertEquals("teste de string", StringUtils.getNonDigitsOnly("teste de string"));
		assertEquals(".a-.", StringUtils.getNonDigitsOnly(".123a4567-890."));
		assertEquals("-", StringUtils.getNonDigitsOnly("1234567-890"));
		assertEquals("eipqweuweiwq./sad", StringUtils.getNonDigitsOnly("eipqweu1weiwq./sad"));
		assertEquals("", StringUtils.getNonDigitsOnly("7"));

		assertEquals("", StringUtils.getStringWithoutAccent(""));
		assertEquals("A", StringUtils.getStringWithoutAccent("A"));
		assertEquals("aAaAocaoieEuU", StringUtils.getStringWithoutAccent("„√·¡oÁ·ÛÌÈ…˙⁄"));
		assertEquals("aeiouaeiouao", StringUtils.getStringWithoutAccent("aeiou·ÈÌÛ˙„ı"));
	}
}

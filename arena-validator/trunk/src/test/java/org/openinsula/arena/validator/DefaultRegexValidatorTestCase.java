package org.openinsula.arena.validator;

import org.openinsula.arena.validator.DefaultRegexValidator;
import org.openinsula.arena.validator.Validator;

import junit.framework.TestCase;

public class DefaultRegexValidatorTestCase extends TestCase {
	public void testValidateEmailRegex() {
		Validator validator = new DefaultRegexValidator(".+@.+\\..+");
		assertTrue(validator.validate("eu@eu.com"));
		assertTrue(validator.validate("a@abc.com"));
		assertTrue(validator.validate("a@a.c"));
		assertTrue(validator.validate("a@123.b"));
		assertFalse(validator.validate("tt@t."));
		assertFalse(validator.validate("@c.bbb"));
		assertFalse(validator.validate("a@a"));
	}

	public void testValidateDigitRegex() {
		Validator validator = new DefaultRegexValidator("\\d*");
		assertTrue(validator.validate(""));
		assertTrue(validator.validate("1"));
		assertTrue(validator.validate("123123123123"));
		assertTrue(validator.validate("0000000000"));
		assertFalse(validator.validate("O0"));
		assertFalse(validator.validate("ab12"));
		assertFalse(validator.validate("12b"));
	}
}

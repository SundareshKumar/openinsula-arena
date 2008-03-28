package org.openinsula.arena.validator;

import junit.framework.TestCase;

public class DefaultDateValidatorTestCase extends TestCase {
	public void testValidate() {
		Validator validator = new DefaultDateValidator();

		assertTrue(validator.validate("04/12/1978"));
		assertTrue(validator.validate("25/04/1980"));
		assertTrue(validator.validate("20/11/2001"));
		assertTrue(validator.validate("31/03/2005"));
		assertFalse(validator.validate("39/32/2010"));
		assertFalse(validator.validate("29/02/2010"));
		assertFalse(validator.validate("asgdfd"));
		assertFalse(validator.validate("10/10/2000a"));
		// assertFalse(validator.validate(new
		// SimpleDateFormat("dd/MM/yyyy").parse("31/04/2010")));
		// assertFalse(validator.validate(new
		// SimpleDateFormat("dd/MM/yyyy").parse("12/30/78")));
		assertFalse(validator.validate(null));
	}
}

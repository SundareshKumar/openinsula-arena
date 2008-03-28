package org.openinsula.arena.validator;

import org.openinsula.arena.validator.DefaultCnpjValidator;
import org.openinsula.arena.validator.Validator;

import junit.framework.TestCase;

public class DefaultCnpjValidatorTestCase extends TestCase {
	public void testValidate() {
		Validator validator = new DefaultCnpjValidator();
		assertTrue(validator.validate("06.305.901/0001-78"));
		assertTrue(validator.validate("06305901000178"));
		assertTrue(validator.validate("23.144.170/0001-45"));
		assertTrue(validator.validate("23144170000145"));
		assertTrue(validator.validate("46.868.328/0001-25"));
		assertTrue(validator.validate("42.767.194/0001-03"));
		assertTrue(validator.validate("58.647.246/0001-30"));
		assertTrue(validator.validate("37.961.612/0001-50"));
		assertTrue(validator.validate("43.256.675/0001-09"));
		assertTrue(validator.validate("24.152.237/0001-56"));
		assertTrue(validator.validate("60.871.888/0001-60"));
		assertTrue(validator.validate("66.845.982/0001-20"));
		assertTrue(validator.validate("06.074.614/0001-02"));
		assertTrue(validator.validate("06074614000102"));
		assertFalse(validator.validate("06074614000112"));
		assertFalse(validator.validate("06074614000113"));
		assertFalse(validator.validate(null));
		assertFalse(validator.validate(""));
		assertFalse(validator.validate("000"));
		assertFalse(validator.validate("00000000000000000000"));
		assertFalse(validator.validate("00000-./.-000000000000000"));

	}
}

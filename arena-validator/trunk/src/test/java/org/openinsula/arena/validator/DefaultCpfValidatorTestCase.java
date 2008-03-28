package org.openinsula.arena.validator;

import org.openinsula.arena.validator.DefaultCpfValidator;
import org.openinsula.arena.validator.Validator;

import junit.framework.TestCase;

public class DefaultCpfValidatorTestCase extends TestCase {
	public void testValidate() {
		Validator validator = new DefaultCpfValidator();
		assertTrue(validator.validate("005.333.839-19"));
		assertTrue(validator.validate("00533383919"));
		assertTrue(validator.validate("030.405.039-36"));
		assertTrue(validator.validate("03040503936"));
		assertTrue(validator.validate("046.428.359-03"));
		assertTrue(validator.validate("04642835903"));
		assertTrue(validator.validate("023.750.169-47"));
		assertTrue(validator.validate("02375016947"));
		assertTrue(validator.validate("855.826.525-90"));
		assertTrue(validator.validate("669.712.265-00"));
		assertFalse(validator.validate("123-123.123"));
		assertFalse(validator.validate("02375016"));
		assertFalse(validator.validate("02375016947123"));
		assertFalse(validator.validate(null));
		assertFalse(validator.validate(""));
		assertFalse(validator.validate("0"));
		assertFalse(validator.validate("00"));
		assertFalse(validator.validate("000000000000"));
		assertFalse(validator.validate("00000000000"));
		assertFalse(validator.validate("111.111.111-11"));
		assertFalse(validator.validate("22222222222"));
		assertFalse(validator.validate("333.333.333-33"));
		assertFalse(validator.validate("44444444444"));
		assertFalse(validator.validate("555.555.555-55"));
		assertFalse(validator.validate("66666666666"));
		assertFalse(validator.validate("777.777.777-77"));
		assertFalse(validator.validate("88888888888"));
		assertFalse(validator.validate("999.999.999-99"));
	}
}

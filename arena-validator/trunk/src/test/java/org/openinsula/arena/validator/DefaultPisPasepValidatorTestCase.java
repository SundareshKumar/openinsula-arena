package org.openinsula.arena.validator;

import org.openinsula.arena.validator.DefaultPisPasepValidator;
import org.openinsula.arena.validator.Validator;

import junit.framework.TestCase;

public class DefaultPisPasepValidatorTestCase extends TestCase {
	public void testValidate() {
		Validator validator = new DefaultPisPasepValidator();
		assertTrue(validator.validate(null));
		assertFalse(validator.validate("ASDASDASD"));
		assertTrue(validator.validate("124.39635.08-3"));
		assertTrue(validator.validate("18000505504"));
		assertTrue(validator.validate("126.23422.15.1"));
		assertTrue(validator.validate("12098383977"));
		assertTrue(validator.validate("127.33642.17-2"));
		assertTrue(validator.validate("126.22002.16.7"));
		assertTrue(validator.validate("12228859410"));
		assertTrue(validator.validate("12648652142"));
		assertTrue(validator.validate("123.45410.32.0"));
		assertFalse(validator.validate("123.45410.32.3"));
		assertFalse(validator.validate("123.32983.32.3"));
		assertTrue(validator.validate("170.591.296.75"));
		assertTrue(validator.validate("107.624.829.55"));
		assertTrue(validator.validate("124.90836.61.9"));
		assertFalse(validator.validate("00000000000"));
		assertFalse(validator.validate("00000000001"));
		assertFalse(validator.validate("00000000002"));
		assertFalse(validator.validate("00000000003"));
		assertFalse(validator.validate("00000000004"));
		assertFalse(validator.validate("00000000005"));
		assertFalse(validator.validate("00000000006"));
		assertFalse(validator.validate("00000000007"));
		assertFalse(validator.validate("00000000008"));
		assertFalse(validator.validate("00000000009"));
	}
}

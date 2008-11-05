package org.openinsula.arena.gwt.client.ui.form.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class RegexpFormItemValidatorTestCase {

	@Test
	public void testEmail() {
		assertTrue("fabiano@insula.com.br".matches(RegexpFormItemValidator.EMAIL));
		assertTrue("teste@insula.com.br".matches(RegexpFormItemValidator.EMAIL));
		assertFalse("fabiano@.br".matches(RegexpFormItemValidator.EMAIL));
		assertFalse("..@.br".matches(RegexpFormItemValidator.EMAIL));
		assertFalse("fabiano@insula..br".matches(RegexpFormItemValidator.EMAIL));
		assertFalse("".matches(RegexpFormItemValidator.EMAIL));
	}

	@Test
	public void testNumerico() {
		assertTrue("123".matches(RegexpFormItemValidator.SOMENTE_NUMEROS));
		assertTrue("".matches(RegexpFormItemValidator.SOMENTE_NUMEROS));
		assertFalse("123a".matches(RegexpFormItemValidator.SOMENTE_NUMEROS));
		assertFalse("123.".matches(RegexpFormItemValidator.SOMENTE_NUMEROS));
		assertFalse("1 2 3".matches(RegexpFormItemValidator.SOMENTE_NUMEROS));
	}

	@Test
	public void testData() {
		assertTrue("27/12/1983".matches(RegexpFormItemValidator.DATA));
		assertTrue("01/01/1980".matches(RegexpFormItemValidator.DATA));
		assertTrue("1/12/2001".matches(RegexpFormItemValidator.DATA));
		assertFalse("27/13/1983".matches(RegexpFormItemValidator.DATA));
		assertFalse("32/12/1983".matches(RegexpFormItemValidator.DATA));
		assertFalse("ab/12/2001".matches(RegexpFormItemValidator.DATA));
		assertFalse(" 1/12/2001".matches(RegexpFormItemValidator.DATA));
		assertFalse("0/12/2001".matches(RegexpFormItemValidator.DATA));
		assertTrue("29/02/2008".matches(RegexpFormItemValidator.DATA));
		assertFalse("29/02/2007".matches(RegexpFormItemValidator.DATA));
	}

	@Test
	public void testMoeda() {
		assertTrue("1.00".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("0.01".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("0.00".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("100.00".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("100.00".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("100,00".matches(RegexpFormItemValidator.MOEDA));
		assertTrue("100.000".matches(RegexpFormItemValidator.MOEDA));
		assertFalse("0.10.00".matches(RegexpFormItemValidator.MOEDA));
		assertFalse("a100.00".matches(RegexpFormItemValidator.MOEDA));
		assertFalse("#100.00".matches(RegexpFormItemValidator.MOEDA));
	}

}

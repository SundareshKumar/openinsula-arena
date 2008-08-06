package org.openinsula.arena.gwt.client.math;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalUtilsTestCase {

	@Test
	public void testIsZero() {
		BigDecimal zero = BigDecimalUtils.ZERO;
		BigDecimal value1 = new BigDecimal("0.00");
		BigDecimal value2 = new BigDecimal("0");

		assertTrue(BigDecimalUtils.isZero(zero));
		assertTrue(BigDecimalUtils.isZero(value1));
		assertTrue(BigDecimalUtils.isZero(value2));
		assertFalse(BigDecimalUtils.isZero(null));
	}

	@Test
	public void testIsNegative() {
		BigDecimal zero = BigDecimalUtils.ZERO;
		BigDecimal value1 = new BigDecimal("-0.01");
		BigDecimal value2 = new BigDecimal("-1");

		assertFalse(BigDecimalUtils.isNegative(null));
		assertFalse(BigDecimalUtils.isNegative(zero));
		assertTrue(BigDecimalUtils.isNegative(value1));
		assertTrue(BigDecimalUtils.isNegative(value2));
	}

	@Test
	public void testIsPositive() {
		BigDecimal zero = BigDecimalUtils.ZERO;
		BigDecimal value1 = new BigDecimal("0.01");
		BigDecimal value2 = new BigDecimal("1");

		assertFalse(BigDecimalUtils.isPositive(null));
		assertFalse(BigDecimalUtils.isPositive(zero));
		assertTrue(BigDecimalUtils.isPositive(value1));
		assertTrue(BigDecimalUtils.isPositive(value2));
	}

	@Test
	public void testNullSafeEquals() {
		assertTrue(BigDecimalUtils.nullSafeEquals(null, null));

		BigDecimal value1 = new BigDecimal("0.00");
		BigDecimal value2 = new BigDecimal("0");
		BigDecimal value3 = new BigDecimal("0.01");

		assertFalse(BigDecimalUtils.nullSafeEquals(null, value3));
		assertTrue(BigDecimalUtils.nullSafeEquals(value1, value1));
		assertTrue(BigDecimalUtils.nullSafeEquals(value1, value2));
		assertTrue(BigDecimalUtils.nullSafeEquals(BigDecimalUtils.ZERO, value1));
		assertFalse(BigDecimalUtils.nullSafeEquals(value1, value3));
	}

	@Test
	public void testNewBigDecimal() {

		try {
			BigDecimalUtils.newBigDecimal(null);
			fail();
		} catch (NumberFormatException e) {
		}

		try {
			BigDecimalUtils.newBigDecimal("");
			fail();
		} catch (NumberFormatException e) {
		}

		assertEquals(new BigDecimal("-1000.00"), BigDecimalUtils.newBigDecimal("-1.000,00   "));
		assertEquals(new BigDecimal("1000.00"), BigDecimalUtils.newBigDecimal(" + 1,000,00"));
		assertEquals(new BigDecimal("1000.00"), BigDecimalUtils.newBigDecimal("1 000,0 0"));
		assertEquals(new BigDecimal("1000.00"), BigDecimalUtils.newBigDecimal("1000.00"));
		assertEquals(new BigDecimal("1000.0"), BigDecimalUtils.newBigDecimal("1000,0"));
		assertEquals(new BigDecimal("1000"), BigDecimalUtils.newBigDecimal("1000."));
		assertEquals(new BigDecimal("1000"), BigDecimalUtils.newBigDecimal("1000,"));
	}

}

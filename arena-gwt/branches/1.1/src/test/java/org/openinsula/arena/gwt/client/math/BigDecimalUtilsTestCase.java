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

}

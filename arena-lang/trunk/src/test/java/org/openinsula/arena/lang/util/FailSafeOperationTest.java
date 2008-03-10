package org.openinsula.arena.lang.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class FailSafeOperationTest {

	@Test
	public void testDoTry() {

		BigDecimal result = new FailSafeOperation<BigDecimal>() {

			@Override
			protected BigDecimal tryBody() throws Throwable {
				return new BigDecimal((String) null);
			}

		}.doTry();

		assertNull(result);
	}

	@Test
	public void testCustomCatch() {

		BigDecimal result = new FailSafeOperation<BigDecimal>() {

			@Override
			protected BigDecimal tryBody() throws Throwable {
				return new BigDecimal((String) null);
			}

			@Override
			protected BigDecimal doCatch(Throwable throwable) {
				return BigDecimal.ZERO;
			}

		}.doTry();

		assertEquals(BigDecimal.ZERO, result);
	}

	@Test
	public void testDoCatchWithException() {

		try {
			new FailSafeOperation<BigDecimal>(true) {

				@Override
				protected BigDecimal tryBody() throws Throwable {
					return new BigDecimal((String) null);
				}

			}.doTry();
			
			fail("IllegalArgumentException expected");

		}
		catch (RuntimeException exc) {
			
		}
	}

}

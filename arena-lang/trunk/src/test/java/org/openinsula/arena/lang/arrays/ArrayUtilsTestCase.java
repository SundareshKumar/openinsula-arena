package org.openinsula.arena.lang.arrays;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilsTestCase {

	@Test
	public void testDoWithAll() {
		Integer[] mock = { 1, 2, 3 };

		ArrayCallback<Integer, String> callback = new ArrayCallback<Integer, String>() {
			StringBuilder sb = new StringBuilder();

			@Override
			public String getResult() {
				return sb.toString();
			}

			@Override
			public void execute(Integer current, int arraySize) {
				sb.append(current);
			}

		};

		String actual = ArrayUtils.doWithAll(mock, callback);
		String expected = "123";

		assertEquals(expected, actual);
	}

	@Test
	public void testSumAll() {
		Double[] mock = { 0.5, 1.49, 3.01 };
		assertEquals(5.00, ArrayUtils.sumAll(mock), 0.00);
	}

	@Test
	public void testMultiplyAll() {
		Integer[] mock = { 2, 2, 2, 2, 2 };
		assertEquals(32.0, ArrayUtils.multiplyAll(mock), 0.0);
	}
}

package org.openinsula.arena.lang.math.counter;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTest {

	@Test
	public void testMinimalCounter() {
		Counter counter = Counter.createDefaultCounter(1, 1, 1);
		
		Integer[][] expected = new Integer[][] {
				{1}
		};
		
		int i = 0;
		for (Integer[] c : counter) {
			assertArrayEquals(expected[i++], c);
		}
		
		assertTrue(i > 0);
	}
	
	@Test
	public void testTwoDigitsCounter() {
		Counter counter = Counter.createDefaultCounter(2, 1);
		
		Integer[][] expected = new Integer[][] {
				{0,0}, {0,1}, {1,0}, {1,1}
		};
		
		int i = 0;
		for (Integer[] c : counter) {
			assertArrayEquals(expected[i++], c);
		}
		
		assertTrue(i > 0);
	}
	
	@Test
	public void testMinimalIrregularDigitsCounter() {
		Counter counter = Counter.createCounter(2, new IrregularLimitsCounterDelegate(new Integer[] {0,0}));
		
		Integer[][] expected = new Integer[][] {
				{0,0}
		};
		
		int i = 0;
		for (Integer[] c : counter) {
			assertArrayEquals(expected[i++], c);
		}
		
		assertTrue(i > 0);
	}
	
	@Test
	public void testIrregularDigitsCounter() {
		Counter counter = Counter.createCounter(2, new IrregularLimitsCounterDelegate(new Integer[] {1,1}));
		
		Integer[][] expected = new Integer[][] {
				{0,0},{0,1},{1,0},{1,1}
		};
		
		int i = 0;
		for (Integer[] c : counter) {
			assertArrayEquals(expected[i++], c);
		}
		
		assertTrue(i > 0);
	}

}

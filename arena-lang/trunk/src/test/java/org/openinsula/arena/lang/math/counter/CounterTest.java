package org.openinsula.arena.lang.math.counter;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTest {

	@Test
	public void testDefaultCounter() {
		Counter counter = Counter.createDefaultCounter(2, 1);
		
		Integer[][] expected = new Integer[][] {
				{0,0}, {0,1}, {1,0}, {1,1}
		};
		
		int i = 0;
		for (Integer[] c : counter) {
			assertArrayEquals(expected[i++], c);
		}
	}

}

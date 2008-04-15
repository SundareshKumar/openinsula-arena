/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena-Lang.
 *
 *  Arena-Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang.math.counter;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTestCase {

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

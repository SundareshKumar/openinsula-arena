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
package org.openinsula.arena.lang.security;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomGeneratorTest {

	@Test
	public void testGenerateNumberAsString() {
		for (int i = 0; i < 1000; i++) {
			assertTrue(RandomGenerator.generateNumberAsString(7).length() == 7);
		}
		for (int i = 0; i < 1000; i++) {
			assertTrue(RandomGenerator.generateNumberAsString(4).length() == 4);
		}
		for (int i = 0; i < 1000; i++) {
			assertTrue(RandomGenerator.generateNumberAsString(12).length() == 12);
		}
	}

	@Test
	public void testGenerateNumberAsLong() {
		for (int i = 0; i < 1000; i++) {
			long number = RandomGenerator.generateNumberAsLong(7);
			assertTrue(number >= 0);
			assertTrue(number < 10000000);
		}
		for (int i = 0; i < 1000; i++) {
			long number = RandomGenerator.generateNumberAsLong(4);
			assertTrue(number >= 0);
			assertTrue(number < 10000);
		}
		for (int i = 0; i < 1000; i++) {
			long number = RandomGenerator.generateNumberAsLong(12);
			assertTrue(number >= 0);
			assertTrue(number < 1000000000000L);
		}
	}

}

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
package org.openinsula.arena.lang.math.distribution;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class DistributionTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Test
	public void testCorrectMinimalSingleDistribution() {
		Distribution distribution = new Distribution(1, 1);

		if (logger.isDebugEnabled()) {
			logger.debug(distribution);
		}

		Integer[] expected = new Integer[] { 1 };
		int i = 0;
		boolean correct = true;
		
		
		for (Entry<Integer> entry : distribution) {
			
			for (Integer entryValue : entry) {
				correct &= expected[i++] == entryValue;
			}
			
		}
		
		assertTrue(i > 0);
		assertTrue(correct);
		
	}
	
	@Test
	public void testCorrectSingleDistribution() {
		Distribution distribution = new Distribution(6, 1);

		if (logger.isDebugEnabled()) {
			logger.debug(distribution);
		}

		Integer[] expected = new Integer[] { 6 };
		int i = 0;
		
		for (Entry<Integer> entry : distribution) {
			
			for (Integer entryValue : entry) {
				assertTrue(expected[i++] == entryValue);
			}
			
		}
		
	}
	
	@Test
	public void testCorrectDistribution() {
		Distribution distribution = new Distribution(6, 2);

		if (logger.isDebugEnabled()) {
			logger.debug(distribution);
		}

		Integer[] expected = new Integer[] { 1, 5, 2, 4, 3, 3 };
		int i = 0;
		
		for (Entry<Integer> entry : distribution) {
			
			for (Integer entryValue : entry) {
				assertTrue(expected[i++] == entryValue);
			}
			
		}
		
	}

	@Test
	public void testInvalidDistribution() {
		try {
			new Distribution(0, 0);
			fail();

		} catch (IllegalArgumentException exc) {
		}

		try {
			new Distribution(1, 2);
			fail();

		} catch (IllegalArgumentException exc) {
		}

		try {
			new Distribution(1, 0);
			fail();

		} catch (IllegalArgumentException exc) {
		}

		try {
			new Distribution(1, -1);
			fail();

		} catch (IllegalArgumentException exc) {
		}
	}

}

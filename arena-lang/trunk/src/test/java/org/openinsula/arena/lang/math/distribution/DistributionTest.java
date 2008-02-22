package org.openinsula.arena.lang.math.distribution;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class DistributionTest {

	protected final Log logger = LogFactory.getLog(getClass());

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

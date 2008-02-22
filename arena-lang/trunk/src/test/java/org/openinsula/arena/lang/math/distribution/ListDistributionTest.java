package org.openinsula.arena.lang.math.distribution;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class ListDistributionTest {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testGenerate() {
		List<Integer> itens = Arrays.asList(1,2,3,4,5);
		
		ListDistribution<Integer> distr1 = new ListDistribution<Integer>(itens);
		List<List<Integer>> list1 = distr1.distribute(1);
		
		ListDistribution<Integer> distr2 = new ListDistribution<Integer>(itens);
		List<List<Integer>> list2 = distr2.distribute(4);
		
		if (logger.isDebugEnabled()) {
			logger.debug("List 1: " + list1);
			logger.debug("List 2: " + list2);
		}
		
	}

}

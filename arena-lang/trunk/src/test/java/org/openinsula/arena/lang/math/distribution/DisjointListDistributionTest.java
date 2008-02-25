package org.openinsula.arena.lang.math.distribution;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class DisjointListDistributionTest {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testDistribute() {
		List<Integer> itens = Arrays.asList(1,2,3,4);
		
		DisjointListDistribution<Integer> listDistribution = new DisjointListDistribution<Integer>(itens);
		listDistribution.distribute(1, 3);
		
		List<?>[][] expected = new List<?>[][] {
				{
					Arrays.asList(1),
					Arrays.asList(2,3,4)
				},
				{
					Arrays.asList(2),
					Arrays.asList(1,3,4)
				},
				{
					Arrays.asList(3),
					Arrays.asList(1,2,4)
				},
				{
					Arrays.asList(4),
					Arrays.asList(1,2,3)
				},
		};
		
		int i = 0;
		for (List<Integer>[] distro : listDistribution) {
			if (logger.isInfoEnabled()) {
				logger.info(Arrays.toString(distro));
			}
			
			int j = 0;
			
			for (List<Integer> list : distro) {
				assertEquals(expected[i][j++], list);
			}
			i++;
		}
	}

}

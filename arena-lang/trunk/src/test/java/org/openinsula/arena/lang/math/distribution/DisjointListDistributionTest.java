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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class DisjointListDistributionTest {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testSingleDistribute() {
		List<Integer> itens = Arrays.asList(1,2,3,4);
		
		DisjointListDistribution<Integer> listDistribution = new DisjointListDistribution<Integer>(itens);
		listDistribution.distribute(4);
		
		List<?>[][] expected = new List<?>[][] {
				{
					itens
				}
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

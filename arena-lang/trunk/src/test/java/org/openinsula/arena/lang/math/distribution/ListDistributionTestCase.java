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

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.openinsula.arena.lang.util.LogUtil;

public class ListDistributionTestCase {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testGenerate() {
		List<Integer> itens = Arrays.asList(1,2,3,4,5);
		
		ListDistribution<Integer> distr1 = new ListDistribution<Integer>(itens);
		List<List<Integer>> list1 = distr1.distribute(5);
		
		ListDistribution<Integer> distr2 = new ListDistribution<Integer>(itens);
		List<List<Integer>> list2 = distr2.distribute(4);
		
		LogUtil.debug(logger, "List 1: %s%nList 2: %s", list1, list2);
	}

}

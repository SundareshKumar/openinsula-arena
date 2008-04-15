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
package org.openinsula.arena.lang.math;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math.util.MathUtils;
import org.springframework.util.Assert;

public class MathFunctions {

	public static long numberOfPossibleCombinations(final int n, final int p) {
		return MathUtils.factorial(n) / (MathUtils.factorial(p) * MathUtils.factorial(Math.abs(n - p)));
	}

	public static boolean disjoint(final Collection<?>... collections) {
		Assert.notEmpty(collections);
		
		for (Collection<?> collection : collections) {
			Assert.notNull(collection);
		}
		
		Assert.isTrue(collections.length > 1);
		
		Collection<?> unionCollection = collections[0];

		for (int i = 1; i < collections.length; i++) {
			Collection<?> collection = collections[i];
			
			if (Collections.disjoint(unionCollection, collection)) {
				unionCollection = CollectionUtils.union(unionCollection, collection);
				
			} else {
				return false;
			}
		}
		
		return true;
	}

}

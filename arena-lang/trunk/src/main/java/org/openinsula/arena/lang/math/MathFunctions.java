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

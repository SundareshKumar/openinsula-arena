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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.openinsula.arena.lang.arrays.ArrayUtilities;
import org.openinsula.arena.lang.math.counter.Counter;
import org.openinsula.arena.lang.math.counter.CounterIterator;
import org.springframework.util.Assert;

public class Distribution implements Iterable<Entry<Integer>>{

	private final int numberOfElements;
	
	private Collection<Entry<Integer>> distribution;
	
	public Distribution(final int numberOfElements, final int slots) {
		Assert.isTrue(numberOfElements > 0, "numberOfElements must be greater than 0 (zero)");
		Assert.isTrue(slots <= numberOfElements && slots > 0, "slots must be positive and lesser or equals numberOfElements");
		
		this.numberOfElements = numberOfElements;
		
		distribute(slots);
	}

	private void distribute(final int slots) {
		CounterIterator iterator = createCounterIterator(slots);
		distribution = iterator.getEntryCollection();
	}
	
	private CounterIterator createCounterIterator(final int digits) {
		Counter c = Counter.createDefaultCounter(digits, 1, numberOfElements);
		
		CounterIterator counterIterator = new CounterIterator(c) {

			@Override
			protected Entry<Integer> createEntry(final Integer[] counterCombination) {
				return new SortedListEntry<Integer>(counterCombination);
			}

			@Override
			protected boolean isValid(final Integer[] counterCombination) {
				return ArrayUtilities.sumAll(counterCombination) == numberOfElements;
			}

			@Override
			protected Collection<Entry<Integer>> prepareEntryCollection() {
				return new LinkedHashSet<Entry<Integer>>();
			}
			
		};
		
		return counterIterator;
	}

	@Override
	public Iterator<Entry<Integer>> iterator() {
		return distribution.iterator();
	}
	
}

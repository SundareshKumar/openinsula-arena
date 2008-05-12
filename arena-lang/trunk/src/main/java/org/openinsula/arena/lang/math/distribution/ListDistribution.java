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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.lang.math.counter.Counter;
import org.openinsula.arena.lang.math.counter.CounterIterator;
import org.springframework.util.Assert;

public class ListDistribution<T> {
	protected final Log logger = LogFactory.getLog(getClass());

	private List<T> referenceList;

	public ListDistribution(final List<T> list) {
		setReferenceList(list);
	}

	public void setReferenceList(final List<T> list) {
		Assert.notNull(list, "Reference list required!");

		referenceList = Collections.unmodifiableList(list);

		if (logger.isDebugEnabled()) {
			logger.debug("Reference list: " + referenceList);
		}
	}

	public List<List<T>> distribute(final int sampleSize) {
		Assert.isTrue(sampleSize <= referenceList.size(), "sampleSize must be lesser or equals collection's size");

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Distribution size: %d itens per list", referenceList.size()));
		}

		List<List<T>> distributionList = new ArrayList<List<T>>();

		if (sampleSize == referenceList.size()) {
			distributionList.add(new ArrayList<T>(referenceList));

		} else {

			CounterIterator counterIterator = createCounterIterator(sampleSize);
			Collection<Entry<Integer>> entries = counterIterator.getEntryCollection();

			for (Entry<Integer> entry : entries) {
				List<T> distribution = new ArrayList<T>();

				for (Integer idx : entry) {
					T item = referenceList.get(idx);
					distribution.add(item);
				}

				distributionList.add(distribution);
			}
		}

		return distributionList;
	}

	private CounterIterator createCounterIterator(final int sampleSize) {
		Counter counter = Counter.createDefaultCounter(sampleSize, referenceList.size() - 1);
		CounterIterator counterIterator = new CounterIterator(counter) {

			@Override
			protected Entry<Integer> createEntry(Integer[] counterCombination) {
				return new SortedListEntry<Integer>(counterCombination);
			}

			@Override
			protected boolean isValid(Integer[] counterCombination) {
				for (int i = 0; i < counterCombination.length - 1; i++) {
					for (int j = i + 1; j < counterCombination.length; j++) {
						if (counterCombination[i].equals(counterCombination[j])) {
							return false;
						}
					}
				}

				return true;
			}

			@Override
			protected Collection<Entry<Integer>> prepareEntryCollection() {
				return new LinkedHashSet<Entry<Integer>>();
			}

		};

		return counterIterator;
	}

}

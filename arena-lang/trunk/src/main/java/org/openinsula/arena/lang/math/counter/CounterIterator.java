package org.openinsula.arena.lang.math.counter;

import java.util.Collection;
import java.util.Collections;

import org.openinsula.arena.lang.math.distribution.Entry;

public abstract class CounterIterator {
	private final Counter counter;

	private final Collection<Entry<Integer>> entryCollection;

	public CounterIterator(final Counter counter) {
		this.counter = counter;

		entryCollection = prepareEntryCollection();
	}

	public Collection<Entry<Integer>> getEntryCollection() {

		if (entryCollection.isEmpty()) {

			for (Integer[] c : counter) {

				if (isValid(c)) {
					entryCollection.add(createEntry(c.clone()));
				}
			}
		}

		return Collections.unmodifiableCollection(entryCollection);
	}
	
	protected abstract Collection<Entry<Integer>> prepareEntryCollection();

	protected abstract Entry<Integer> createEntry(Integer[] counterCombination);

	protected abstract boolean isValid(Integer[] counterCombination);

}

package org.openinsula.arena.lang.math.counter;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.openinsula.arena.lang.math.distribution.Entry;
import org.openinsula.arena.lang.math.distribution.ListEntry;

public class SequencialCounterIterator extends CounterIterator {

	public SequencialCounterIterator(final Counter counter) {
		super(counter);
	}

	@Override
	protected Entry<Integer> createEntry(final Integer[] counterCombination) {
		return new ListEntry<Integer>(counterCombination);
	}

	@Override
	protected Collection<Entry<Integer>> prepareEntryCollection() {
		return new LinkedHashSet<Entry<Integer>>();
	}
	
	@Override
	protected boolean isValid(final Integer[] counterCombination) {
		return true;
	}

}

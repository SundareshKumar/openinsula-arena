package org.openinsula.arena.lang.math.distribution;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortedListEntry<T extends Comparable<? super T>> extends Entry<T> {
	
	public SortedListEntry(final T... valuesArray) {
		super(valuesArray);
	}

	@Override
	protected Collection<T> prepareValues(final List<T> referenceValues) {
		Collections.sort(referenceValues);
		return referenceValues;
	}

}

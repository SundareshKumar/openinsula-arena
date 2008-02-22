package org.openinsula.arena.lang.math.distribution;

import java.util.Collection;
import java.util.List;

public class ListEntry<T> extends Entry<T> {

	public ListEntry(final T... valuesArray) {
		super(valuesArray);
	}

	@Override
	protected Collection<T> prepareValues(final List<T> referenceValues) {
		return referenceValues;
	}

}

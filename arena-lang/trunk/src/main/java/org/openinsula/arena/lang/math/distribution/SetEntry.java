package org.openinsula.arena.lang.math.distribution;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class SetEntry<T> extends Entry<T> {
	
	public SetEntry(final T... valuesArray) {
		super(valuesArray);
	}

	@Override
	protected Collection<T> prepareValues(final List<T> referenceValues) {
		return new LinkedHashSet<T>(referenceValues);
	}

}

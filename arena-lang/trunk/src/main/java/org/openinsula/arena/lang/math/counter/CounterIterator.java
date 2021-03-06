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

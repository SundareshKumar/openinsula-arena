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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.lang.math.MathFunctions;
import org.openinsula.arena.lang.math.counter.Counter;
import org.openinsula.arena.lang.math.counter.CounterIterator;
import org.openinsula.arena.lang.math.counter.IrregularLimitsCounterDelegate;
import org.openinsula.arena.lang.math.counter.SequencialCounterIterator;
import org.springframework.util.Assert;

public class DisjointListDistribution<T> implements Iterable<List<T>[]> {
	protected final Log logger = LogFactory.getLog(getClass());

	private List<T> referenceList;

	private final List<List<T>[]> distributionList;

	public DisjointListDistribution(final List<T> list) {
		distributionList = new ArrayList<List<T>[]>();

		setReferenceList(list);
	}

	public void setReferenceList(final List<T> list) {
		Assert.notNull(list, "Reference list required!");

		this.referenceList = Collections.unmodifiableList(list);

		if (logger.isDebugEnabled()) {
			logger.debug("Reference list: " + this.referenceList);
		}
	}

	@SuppressWarnings("unchecked")
	public void distribute(final int... samples) {

		if (isSame(samples)) {

			if (logger.isDebugEnabled()) {
				logger.debug("Returning cached distribution");
			}

			return;
		}

		distributionList.clear();

		if (samples.length == 1 && samples[0] == referenceList.size()) {
			List<?>[] resultEntry = new ArrayList<?>[1];
			resultEntry[0] = new ArrayList<T>(referenceList);
			distributionList.add((List<T>[]) resultEntry);

		} else {

			List<List<T>>[] distros = createListDistributions(samples);

			Collection<Entry<Integer>> combinations = generateAllListCombinations(distros);

			for (Entry<Integer> entry : combinations) {
				List<?>[] resultEntry = new ArrayList<?>[samples.length];
				int i = 0;

				for (Integer idx : entry) {
					resultEntry[i] = distros[i].get(idx);
					i++;
				}

				if (MathFunctions.disjoint(resultEntry)) {
					distributionList.add((List<T>[]) resultEntry);
				}
			}
		}
	}

	private boolean isSame(final int... samples) {

		if (!distributionList.isEmpty()) {
			List<T>[] singleDistro = distributionList.get(0);

			if (singleDistro.length == samples.length) {

				for (int i = 0; i < samples.length; i++) {

					if (samples[i] != singleDistro[i].size()) {
						return false;
					}
				}
				return true;

			} else {
				return false;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private List<List<T>>[] createListDistributions(final int... samples) {
		List<?>[] distros = new List<?>[samples.length];

		ListDistribution<T> distro = new ListDistribution<T>(referenceList);

		for (int i = 0; i < distros.length; i++) {
			distros[i] = distro.distribute(samples[i]);
		}

		return (List<List<T>>[]) distros;
	}

	private Collection<Entry<Integer>> generateAllListCombinations(final List<?>[] distros) {
		Counter counter = createCounter(distros);
		CounterIterator counterIterator = new SequencialCounterIterator(counter);

		return counterIterator.getEntryCollection();
	}

	private Counter createCounter(final List<?>[] distros) {
		int digits = distros.length;

		Integer[] maxIdxValues = new Integer[digits];

		for (int i = 0; i < digits; i++) {
			maxIdxValues[i] = distros[i].size() - 1;
		}

		return Counter.createCounter(digits, new IrregularLimitsCounterDelegate(maxIdxValues));
	}

	@Override
	public Iterator<List<T>[]> iterator() {
		return distributionList.iterator();
	}

}

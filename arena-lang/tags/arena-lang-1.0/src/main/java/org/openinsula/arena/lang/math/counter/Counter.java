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

import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class Counter implements Iterable<Integer[]> {
	protected final Log logger = LogFactory.getLog(getClass());

	private final Integer[] counter;

	private CounterDelegate delegate;

	private final Iterator<Integer[]> iterator = new Iterator<Integer[]>() {

		@Override
		public boolean hasNext() {
			return delegate.hasNext(counter);
		}

		@Override
		public Integer[] next() {

			for (int i = counter.length - 1; i >= 0; i--) {
				if (!delegate.isMaximumValue(counter[i], i)) {
					counter[i]++;
					break;

				}
				else {
					counter[i] = delegate.getMinimumValue(i);
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug(Arrays.toString(counter));
			}

			return counter;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	};

	public static Counter createDefaultCounter(final int digits, final int maxValue) {
		return createDefaultCounter(digits, 0, maxValue);
	}

	public static Counter createDefaultCounter(final int digits, final int minValue, final int maxValue) {
		return new Counter(digits, new DefaultCounterDelegate(minValue, maxValue));
	}

	public static Counter createCounter(final int digits, final CounterDelegate delegate) {
		return new Counter(digits, delegate);
	}

	private Counter(final int digits, final CounterDelegate delegate) {
		Assert.notNull(delegate, "CounterDelegate required!");

		counter = new Integer[digits];

		this.delegate = delegate;

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Counter created! digits: %d, delegate: %s", digits, delegate));
		}

		reset();
	}

	public void reset() {
		delegate.reset(counter);
		counter[counter.length - 1]--;
	}

	public void setDelegate(final CounterDelegate validator) {
		this.delegate = validator;
	}

	@Override
	public Iterator<Integer[]> iterator() {
		return iterator;
	}

}

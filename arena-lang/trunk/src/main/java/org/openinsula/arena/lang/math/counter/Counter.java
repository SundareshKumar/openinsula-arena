package org.openinsula.arena.lang.math.counter;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class Counter implements Iterable<Integer[]> {
	protected final Log logger = LogFactory.getLog(getClass());

	private final Integer[] counter;

	private boolean isFirst = true;

	private CounterDelegate delegate;

	private final Iterator<Integer[]> iterator = new Iterator<Integer[]>() {

		@Override
		public boolean hasNext() {
			return delegate.hasNext(counter);
		}

		@Override
		public Integer[] next() {
			if (!isFirst) {

				for (int i = counter.length - 1; i >= 0; i--) {
					if (!delegate.isMaximumValue(counter[i], i)) {
						counter[i]++;
						break;

					} else {
						counter[i] = 0;
					}
				}

			} else {
				isFirst = false;
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
	}

	public void setDelegate(final CounterDelegate validator) {
		this.delegate = validator;
	}

	@Override
	public Iterator<Integer[]> iterator() {
		return iterator;
	}

}

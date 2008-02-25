package org.openinsula.arena.lang.math.counter;

import java.util.Arrays;

import org.springframework.util.Assert;

public class DefaultCounterDelegate implements CounterDelegate {

	protected int minValue;
	protected int maxValue;
	
	public DefaultCounterDelegate(final int maxValue) {
		this(0, maxValue);
	}
	
	public DefaultCounterDelegate(final int minValue, final int maxValue) {
		Assert.isTrue(minValue >= 0 && minValue <= maxValue);
		
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public boolean hasNext(final Integer[] counter) {
		for (Integer integer : counter) {
			if (integer != maxValue) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isMaximumValue(final int value, final int index) {
		return value == maxValue;
	}

	@Override
	public void reset(final Integer[] counter) {
		Arrays.fill(counter, minValue);		
	}

}

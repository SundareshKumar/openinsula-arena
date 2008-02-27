package org.openinsula.arena.lang.math.counter;

import java.util.Arrays;

import org.springframework.util.Assert;

public class IrregularLimitsCounterDelegate implements CounterDelegate {

	protected Integer[] minValues;
	protected Integer[] maxValues;
	
	public IrregularLimitsCounterDelegate(final Integer[] maxValues) {
		Assert.notEmpty(maxValues);
		
		minValues = new Integer[maxValues.length];
		Arrays.fill(minValues, 0);
		
		this.maxValues = maxValues.clone();
	}
	
	public IrregularLimitsCounterDelegate(final Integer[] minValues, final Integer[] maxValues) {
		Assert.notEmpty(minValues);
		Assert.notEmpty(maxValues);
		
		this.minValues = minValues.clone();
		this.maxValues = maxValues.clone();
	}
	
	@Override
	public boolean hasNext(final Integer[] counter) {
		return !Arrays.deepEquals(counter, maxValues);
	}

	@Override
	public boolean isMaximumValue(final int value, final int index) {
		return value == maxValues[index];
	}

	@Override
	public void reset(final Integer[] counter) {
		for (int i = 0; i < counter.length; i++) {
			counter[i] = minValues[i];
		}
	}

	@Override
	public int getMinimumValue(final int index) {
		return minValues[index];
	}

}

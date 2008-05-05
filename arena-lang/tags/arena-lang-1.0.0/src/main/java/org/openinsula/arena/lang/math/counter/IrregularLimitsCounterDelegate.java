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

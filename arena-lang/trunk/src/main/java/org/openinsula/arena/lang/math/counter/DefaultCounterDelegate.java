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

	@Override
	public int getMinimumValue(final int index) {
		return minValue;
	}

}

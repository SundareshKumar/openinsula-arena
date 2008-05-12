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
package org.openinsula.arena.lang.arrays;

public abstract class BasicArithmeticArrayCallback<T extends Number> implements ArrayCallback<T,Double> {
	private double accumulator = 0.0;
	boolean firstParameter = true;

	@Override
	public final void execute(final T current, final int arraySize) {
		accumulator = doBasicArithmeticOperation(current.doubleValue(), accumulator);
		firstParameter = false;
	}

	@Override
	public Double getResult() {
		return accumulator;
	}

	protected abstract double doBasicArithmeticOperation(final double current, final double accumulator);

}

class SumArrayCallback<T extends Number> extends BasicArithmeticArrayCallback<T> {

	@Override
	protected double doBasicArithmeticOperation(final double current, final double accumulator) {
		return current + accumulator;
	}

}

class MultiplyArrayCallback<T extends Number> extends BasicArithmeticArrayCallback<T> {

	@Override
	protected double doBasicArithmeticOperation(final double current, final double accumulator) {
		return firstParameter ? current : current * accumulator;
	}

}


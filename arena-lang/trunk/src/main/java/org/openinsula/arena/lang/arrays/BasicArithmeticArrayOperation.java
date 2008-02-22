package org.openinsula.arena.lang.arrays;

public abstract class BasicArithmeticArrayOperation<T extends Number> implements ArrayOperation<T,Double> {
	private double accumulator = 0.0;

	@Override
	public final void execute(final T current, final int arraySize) {
		accumulator = doBasicArithmeticOperation(current.doubleValue(), accumulator);
	}
	
	@Override
	public Double getResult() {
		return accumulator;
	}
	
	protected abstract double doBasicArithmeticOperation(final double current, final double accumulator);

}

class SumArrayOperation<T extends Number> extends BasicArithmeticArrayOperation<T> {

	@Override
	protected double doBasicArithmeticOperation(final double current, final double accumulator) {
		return current + accumulator;
	}
	
}

class SubtractArrayOperation<T extends Number> extends BasicArithmeticArrayOperation<T> {

	@Override
	protected double doBasicArithmeticOperation(final double current, final double accumulator) {
		return current - accumulator;
	}
	
}

class MultiplyArrayOperation<T extends Number> extends BasicArithmeticArrayOperation<T> {

	@Override
	protected double doBasicArithmeticOperation(final double current, final double accumulator) {
		return current * accumulator;
	}
	
}


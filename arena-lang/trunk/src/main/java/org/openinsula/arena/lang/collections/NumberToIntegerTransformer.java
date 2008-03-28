package org.openinsula.arena.lang.collections;

public class NumberToIntegerTransformer<T extends Number> extends GenericTransformer<T, Integer> {

	@Override
	protected Integer genericTransform(T input) {
		return input == null ? null : input.intValue();
	}

}

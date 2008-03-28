package org.openinsula.arena.lang.collections;

public class ToStringTransformer<I> extends GenericTransformer<I, String> {

	@Override
	protected String genericTransform(I input) {
		return input == null ? null : input.toString();
	}

}

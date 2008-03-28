package org.openinsula.arena.lang.collections;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Eduardo Rebola
 * @author Fernando Maeda
 */
public abstract class CollectionUtilities {

	@SuppressWarnings("unchecked")
	public static <I, O> Collection<O> transform(Collection<I> input, GenericTransformer<I, O> transformer) {
		try {
			Collection<O> result = input.getClass().newInstance();

			for (I inputItem : input) {
				result.add(transformer.genericTransform(inputItem));
			}

			return result;
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <I, O> List<O> transform(List<I> input, GenericTransformer<I, O> converter) {
		return (List<O>) transform((Collection<I>) input, converter);
	}

	@SuppressWarnings("unchecked")
	public static <I, O> Set<O> transform(Set<I> input, GenericTransformer<I, O> converter) {
		return (Set<O>) transform((Collection<I>) input, converter);
	}

}

package org.openinsula.arena.lang.collections;

import org.apache.commons.collections.Transformer;

/**
 * Added {@link Transformer} generics (JDK 5 or above required) support. Used by
 * {@link CollectionUtilities}.
 * 
 * @see CollectionUtilities
 * @see Transformer
 * @author Eduardo Rebola
 * 
 * @param <I> source type
 * @param <O> target type
 */
public abstract class GenericTransformer<I, O> implements Transformer {

	protected abstract O genericTransform(I input);

	@SuppressWarnings("unchecked")
	@Override
	public final Object transform(Object input) {
		return genericTransform((I) input);
	}

}

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
package org.openinsula.arena.lang.collections;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Eduardo Rebola
 * @author Fernando Maeda
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

	@SuppressWarnings("unchecked")
	public static <I, O> Collection<O> transform(final Collection<I> input, final GenericTransformer<I, O> transformer) {
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
	public static <I, O> List<O> transform(final List<I> input, final GenericTransformer<I, O> converter) {
		return (List<O>) transform((Collection<I>) input, converter);
	}

	@SuppressWarnings("unchecked")
	public static <I, O> Set<O> transform(final Set<I> input, final GenericTransformer<I, O> converter) {
		return (Set<O>) transform((Collection<I>) input, converter);
	}

}

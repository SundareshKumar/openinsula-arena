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

import java.util.ArrayList;
import java.util.List;

/**
 * @author rebola
 *
 */
public abstract class ArrayUtils {

	@SuppressWarnings("unchecked")
	@Deprecated
	public static <T> List<T> asList(final Object[] array) {
		List<T> result = new ArrayList<T>(array.length);

		for (Object obj : array) {
			result.add((T) obj);
		}

		return result;
	}

	public static <T,R> R doWithAll(final T[] array, final ArrayCallback<T,R> closure) {
		for (T t : array) {
			closure.execute(t, array.length);
		}

		return closure.getResult();
	}

	public static <T extends Number> double sumAll(final T[] array) {
		return doWithAll(array, new SumArrayCallback<T>());
	}

	public static <T extends Number> double multiplyAll(final T[] array) {
		return doWithAll(array, new MultiplyArrayCallback<T>());
	}

}

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
package org.openinsula.arena.lang.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class GenericsUtils {

	private static final Log logger = LogFactory.getLog(GenericsUtils.class);
	
	public static Class<?> getSuperclassGenericType(final Class<?> superClass) {
		return getSuperclassGenericType(superClass, 0);
	}

	public static Class<?> getSuperclassGenericType(final Class<?> superClass, final int position) {

		Class<?> entityClass = null;

		final Type type = superClass.getGenericSuperclass();

		if (type instanceof ParameterizedType) {
			final ParameterizedType paramType = (ParameterizedType) type;
			final Object result = paramType.getActualTypeArguments()[position];

			if (result instanceof GenericArrayType) {
				final Class<?> arrayType = ((GenericArrayType) result).getGenericComponentType().getClass();

				entityClass = Array.newInstance(arrayType, 0).getClass();

			} else if (result instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) result).getRawType();

			} else {
				try {
					entityClass = (Class<?>) result;
				} catch (RuntimeException exc) {
					entityClass = null;
				}
			}
		}
		
		if (entityClass == null && logger.isWarnEnabled()) {
			logger.warn("Could not determine generic type of " + superClass);
		}
		
		return entityClass;
	}
	
}

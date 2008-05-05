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

import java.lang.reflect.Method;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public abstract class VisitorUtils {

	public static Object accept(final Object visited, final Object visitor) {
		return accept(visited, visitor, "visit");
	}
	
	public static Object accept(final Object visited, final Object visitor, final String visitMethodName) {
		Assert.notNull(visited, "Visited object required!");
		Assert.notNull(visitor, "Visitor object required!");
		Assert.hasText(visitMethodName, "Visitor 'visit' methodName missing!");
		
		Method method = ReflectionUtils.findMethod(
				visitor.getClass(),
				visitMethodName,
				new Class<?>[] { visited.getClass() });
		
		if (method != null) {
			return ReflectionUtils.invokeMethod(
					method, 
					visitor, 
					new Object[] { visited });
		}
		
		throw new IllegalArgumentException("Visitor method [" + visitMethodName + "] not found!");
	}
	
}

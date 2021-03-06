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
package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.lang.reflection.GenericsUtils;

public class GenericsUtilsTestCase {

	@Test
	public void testGetSuperclassGenericType() {
		assertSame(Integer.class, GenericsUtils.getSuperclassGenericType(IntegerGrandchild.class));
		assertNull(GenericsUtils.getSuperclassGenericType(new ConcreteGenericChild<Integer>().getClass()));
	}

}

abstract class Generic<T> {
	
}

abstract class GenericChild<T> extends Generic<T> {
	
}

class ConcreteGenericChild<T> extends Generic<T> {
	
}

class IntegerGrandchild extends GenericChild<Integer> {
	
}

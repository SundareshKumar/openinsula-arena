package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.lang.reflection.GenericsUtils;

public class GenericsUtilsTest {

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

package org.openinsula.arena.lang.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtilities {

	@SuppressWarnings("unchecked")
	public static <T> List<T> asList(final Object[] array) {
		List<T> result = new ArrayList<T>(array.length);

		for (Object obj : array) {
			result.add((T) obj);
		}

		return result;
	}
	
	public static <T,R> R doWithAll(final T[] array, final ArrayOperation<T,R> closure) {
		for (T t : array) {
			closure.execute(t, array.length);
		}
		
		return closure.getResult();
	}
	
	public static <T extends Number> double sumAll(final T[] array) {
		return doWithAll(array, new SumArrayOperation<T>()); 
	}
	
	public static <T extends Number> double subtractAll(final T[] array) {
		return doWithAll(array, new SubtractArrayOperation<T>()); 
	}
	
	public static <T extends Number> double multiplyAll(final T[] array) {
		return doWithAll(array, new MultiplyArrayOperation<T>()); 
	}
	
}

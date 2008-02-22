package org.openinsula.arena.lang.arrays;

public interface ArrayOperation<T,R> {
	
	void execute(T current, int arraySize);
	
	R getResult();
	
}

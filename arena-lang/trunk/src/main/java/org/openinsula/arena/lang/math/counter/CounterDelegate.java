package org.openinsula.arena.lang.math.counter;

public interface CounterDelegate {

	boolean hasNext(Integer[] counter);
	
	void reset(Integer[] counter);
	
	boolean isMaximumValue(int value, int index);
	
}

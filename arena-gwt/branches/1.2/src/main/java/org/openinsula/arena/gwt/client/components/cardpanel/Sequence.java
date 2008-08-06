package org.openinsula.arena.gwt.client.components.cardpanel;

public interface Sequence<T> {

	T next(T baseObject);
	
	T previous(T baseObject);
	
	void add(T object);
	
}

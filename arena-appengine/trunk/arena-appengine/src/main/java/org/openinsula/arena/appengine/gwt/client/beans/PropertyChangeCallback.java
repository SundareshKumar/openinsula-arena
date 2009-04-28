package org.openinsula.arena.appengine.gwt.client.beans;

public interface PropertyChangeCallback<T> {
	
	void onChange(T oldValue, T newValue);

}

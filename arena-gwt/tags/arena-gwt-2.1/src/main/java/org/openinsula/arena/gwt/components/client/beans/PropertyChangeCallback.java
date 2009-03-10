package org.openinsula.arena.gwt.components.client.beans;

public interface PropertyChangeCallback<T> {
	
	void onChange(T oldValue, T newValue);

}

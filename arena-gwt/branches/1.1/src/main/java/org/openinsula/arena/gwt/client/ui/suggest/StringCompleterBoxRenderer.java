package org.openinsula.arena.gwt.client.ui.suggest;

public class StringCompleterBoxRenderer<T> implements CompleterBoxRenderer<T> {

	public String render(T object) {
		return object.toString();
	}
	
}

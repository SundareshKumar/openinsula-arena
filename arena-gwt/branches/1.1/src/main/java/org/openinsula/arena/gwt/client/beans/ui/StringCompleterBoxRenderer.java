package org.openinsula.arena.gwt.client.beans.ui;

public class StringCompleterBoxRenderer<T> implements CompleterBoxRenderer<T> {

	public String render(T object) {
		return object.toString();
	}
	
}

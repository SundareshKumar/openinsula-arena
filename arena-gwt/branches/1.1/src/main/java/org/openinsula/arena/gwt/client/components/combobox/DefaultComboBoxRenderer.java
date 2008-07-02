package org.openinsula.arena.gwt.client.components.combobox;

public class DefaultComboBoxRenderer<T> implements ComboBoxRenderer<T>{

	public String asString(T object) {
		return object.toString();
	}
	
}

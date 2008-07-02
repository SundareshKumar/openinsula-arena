package org.openinsula.arena.gwt.client.components.combobox;


public interface ComboBoxModel<T> {

	int getSize();
	
	void setSelectedItem(T item);
	
	T getSelectedItem();
	
	T getItem(int index);
	
	int indexOf(T item);
	
	void addDataListener(DataListener listener);
	
}

package org.openinsula.arena.gwt.client.components.combobox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DefaultComboBoxModel<T> extends AbstractComboBoxModel<T> {

	private List<T> items = new ArrayList<T>();
	
	private T selectedItem;
	
	public DefaultComboBoxModel(T ... values) {
		this(Arrays.asList(values));
	}
	
	public DefaultComboBoxModel(Collection<T> values) {
		items = new ArrayList<T>(values);
	}
	
	public DefaultComboBoxModel() {
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
		fireDataChanged();
	}

	public T getItem(int index) {
		return items.get(index);
	}

	public T getSelectedItem() {
		return selectedItem;
	}

	public int getSize() {
		return items.size();
	}

	public int indexOf(T item) {
		return items.indexOf(item);
	}

	public void setSelectedItem(T item) {
		selectedItem = item;
	}
	
}

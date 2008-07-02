package org.openinsula.arena.gwt.client.components.combobox;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComboBoxModel<T> implements ComboBoxModel<T> {

	private List<DataListener> listeners = new ArrayList<DataListener>();
	
	protected void fireDataChanged() {
		for (DataListener listener : listeners) {
			listener.onDataChanged();
		}
	}
	
	public void addDataListener(DataListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
}

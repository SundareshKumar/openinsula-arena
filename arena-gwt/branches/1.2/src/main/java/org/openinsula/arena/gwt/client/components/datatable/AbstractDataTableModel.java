package org.openinsula.arena.gwt.client.components.datatable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataTableModel<T> implements DataTableModel<T> {

	private List<T> values = new ArrayList<T>();
	
	private final List<DataTableModelListener> listeners = new ArrayList<DataTableModelListener>();
	
	protected abstract Object getValueAtColumn(T bean, int column);
	
	protected abstract String[] getColumnNames();
	
	public String getColumnName(int col) {
		return getColumnNames()[col];
	}

	protected void fireTableDataChanged() {
		for (DataTableModelListener listener : listeners) {
			listener.onDataModelChange();
		}
	}

	public Object getValueAt(int row, int col) {
		if (values == null) {
			return null;
		}
		T rowObject = values.get(row);
		return getValueAtColumn(rowObject, col);
	}

	public void setItens(List<T> values) {
		this.values = values; 
		fireTableDataChanged();
	}

	public void addDataTableModelListener(DataTableModelListener listener) {
		listeners.add(listener);
	}

	public List<DataTableModelListener> getDataTableModelListeners() {
		return listeners;
	}

	public void removeAllDataTableModelListeners() {
		listeners.clear();
	}

	public int getColumnCount() {
		return getColumnNames().length;
	}

	public int getRowCount() {
		return (values != null) ? values.size() : 0;
	}
	
	public T getItem(int rowIndex) {
		return values.get(rowIndex);
	}
	
}

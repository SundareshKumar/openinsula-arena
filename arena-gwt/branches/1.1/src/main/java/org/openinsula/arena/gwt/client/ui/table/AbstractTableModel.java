package org.openinsula.arena.gwt.client.ui.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractTableModel<T> implements TableModel<T> {

	private final List<TableModelListener<T>> listeners;

	private RowSorter<T> rowSorter;

	private List<T> values;

	private final String[] header;

	public AbstractTableModel(final String headerColumn, final String... headerColumns) {
		if (headerColumn == null) {
			throw new IllegalArgumentException("Header text expected");
		}

		if (headerColumns == null || headerColumns.length == 0) {
			header = new String[] { headerColumn };
		} else {
			header = new String[headerColumns.length + 1];
			header[0] = headerColumn;

			for (int i = 0; i < headerColumns.length; i++) {
				header[i + 1] = headerColumns[i];
			}
		}

		listeners = new ArrayList<TableModelListener<T>>();
	}

	public void setRowSorter(final RowSorter<T> rowSorter) {
		this.rowSorter = rowSorter;
	}

	public final void setValues(final List<T> values) {
		this.values = values;
		notifyListeners();
	}

	private void notifyListeners() {
		for (TableModelListener<T> listener : listeners) {
			listener.onTableModelChange(this);
		}
	}

	public boolean addTableModelListener(final TableModelListener<T> listener) {
		if (!listeners.contains(listener)) {
			return listeners.add(listener);
		}
		return false;
	}

	public boolean removeTableModelListener(final TableModelListener<T> listener) {
		return listeners.remove(listener);
	}

	public T getEntityAt(final int rowIndex) {
		return values.get(rowIndex);
	}

	public String[] getHeader() {
		return header;
	}

	public int getRowCount() {
		return values.size();
	}

	public final void sortValues(final int columnIndex) {
		Comparator<T> comparator = (rowSorter == null) ? null : rowSorter.getComparator(columnIndex);

		if (comparator != null) {
			Collections.sort(values, comparator);
			notifyListeners();
		}
	}

	public boolean isColumnSortable(final int columnIndex) {
		return rowSorter != null;
	}

}

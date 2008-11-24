package org.openinsula.arena.gwt.client.components.gridpanel;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataGridModel<R, C> implements DataGridModel<R, C> {

	private List<DataGridModelListener> listeners;

	private List<R> rowDataList;

	private List<C> columnDataList;

	public DefaultDataGridModel(List<R> rowDataList, List<C> columnDataList) {
		setData(rowDataList, columnDataList);
	}

	public DefaultDataGridModel() {
		this(new ArrayList<R>(), new ArrayList<C>());
	}

	public void setData(List<R> rowDataList, List<C> columnDataList) {
		this.rowDataList = rowDataList;
		this.columnDataList = columnDataList;
		fireDataChanged();
	}

	public void setColumns(List<C> columnDataList) {
		this.columnDataList = columnDataList;
		fireDataChanged();
	}

	public void setRows(List<R> rowDataList) {
		this.rowDataList = rowDataList;
		fireDataChanged();
	}

	protected void fireDataChanged() {
		for (DataGridModelListener listener : listeners()) {
			listener.onDataChange();
		}
	}

	private List<DataGridModelListener> listeners() {
		if (listeners == null) {
			listeners = new ArrayList<DataGridModelListener>();
		}
		return listeners;
	}

	public List<C> getColumnList() {
		return columnDataList;
	}

	public Entry<R, C> getEntryAt(final int row, final int col) {
		return new Entry<R, C>() {
			public R getRowValue() {
				return rowDataList.get(row);
			}

			public C getColumnValue() {
				return columnDataList.get(col);
			}
		};
	}

	public C getColumnValueAt(int index) {
		return columnDataList.get(index);
	}

	public R getRowValueAt(int index) {
		return rowDataList.get(index);
	}

	public List<R> getRowList() {
		return rowDataList;
	}

	public void addDataGridListener(DataGridModelListener listener) {
		listeners().add(listener);
	}

	public void removeDataGridListener(DataGridModelListener listener) {
		listeners().remove(listener);
	}

	public void clear() {
		columnDataList = new ArrayList<C>();
		rowDataList = new ArrayList<R>();
		fireDataChanged();
	}

}

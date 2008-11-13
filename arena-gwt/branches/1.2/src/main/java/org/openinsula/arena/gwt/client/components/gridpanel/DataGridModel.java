package org.openinsula.arena.gwt.client.components.gridpanel;

import java.util.List;

public interface DataGridModel<R, C> {

	public interface Entry<R, C> {
		R getRowValue();
		C getColumnValue();
	}

	List<R> getRowList();

	List<C> getColumnList();

	Entry<R, C> getEntryAt(int row, int col);

	void addDataGridListener(DataGridModelListener listener);

	void removeDataGridListener(DataGridModelListener listener);

	void setData(List<R> rowDataList, List<C> columnDataList);

	R getRowValueAt(int index);

	C getColumnValueAt(int index);

}

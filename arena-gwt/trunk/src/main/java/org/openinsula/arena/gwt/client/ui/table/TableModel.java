package org.openinsula.arena.gwt.client.ui.table;

public interface TableModel<T> {

	String[] getHeader();

	T getEntityAt(int rowIndex);

	String getValueAt(T entity, int columnIndex);

	void sortValues(int columnIndex);

	boolean isColumnSortable(int columnIndex);

	int getRowCount();

	boolean addTableModelListener(TableModelListener listener);

	boolean removeTableModelListener(TableModelListener listener);

}

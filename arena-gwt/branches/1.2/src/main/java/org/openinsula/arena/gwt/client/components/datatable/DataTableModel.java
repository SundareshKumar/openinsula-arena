package org.openinsula.arena.gwt.client.components.datatable;

import java.util.List;

public interface DataTableModel<T> {

	void setItens(List<T> values);
	
	Object getValueAt(int row, int col);
	
	int getRowCount();
	
	int getColumnCount();
	
	String getColumnName(int col);
	
	List<DataTableModelListener> getDataTableModelListeners();
	
	void addDataTableModelListener(DataTableModelListener listener);
	
	void removeAllDataTableModelListeners();
	
	T getItem(int rowIndex);
	
}

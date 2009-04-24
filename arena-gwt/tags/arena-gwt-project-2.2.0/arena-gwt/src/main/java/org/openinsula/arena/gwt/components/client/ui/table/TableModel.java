package org.openinsula.arena.gwt.components.client.ui.table;

/**
 * @author Lucas K Mogari
 */
public interface TableModel {

	public int getRowCount();

	public int getColumnCount();

	public Object getHeaderValueAt(int columnIndex);

	public Object getValueAt(int rowIndex, int columnIndex);

	public void addTableModelListener(TableModelListener listener);

	public void removeTableModelListener(TableModelListener listener);

}

package org.openinsula.arena.appengine.gwt.client.table;

/**
 * @author Lucas K Mogari
 */
public interface TableModel {

	public int getRowCount();

	public int getColumnCount();

	public Object getHeaderValueAt(int columnIndex);

	public Object getValueAt(int rowIndex, int columnIndex);

	public void setValueAt(int rowIndex, int columnIndex, Object value);

	public void addTableModelListener(TableModelListener listener);

	public void removeTableModelListener(TableModelListener listener);

}

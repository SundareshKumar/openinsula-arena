package org.openinsula.arena.gwt.client.ui.table;

import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

/**
 * This class helps the formatting process over the table rows
 * 
 * @author Eduardo V. Bruno
 */
public abstract class AbstractTableFormatter {

	protected abstract void format(CellFormatter cellFormatter, int row);

	public AbstractTableFormatter(Table<?> table) {
		this(table, true);
	}
	
	/**
	 * 
	 * @param table
	 * @param skipHeaders 
	 */
	public AbstractTableFormatter(Table<?> table, boolean skipHeaders) {
		
		if (table == null) {
			throw new IllegalArgumentException("the table can not be null");
		}
		
		if (!skipHeaders) {
			format(table.getCellFormatter(), 0);
		}
		
		TableModel<?> tableModel = table.getTableModel();
		
		if (tableModel == null) {
			return;
		}
		
		for (int row = 1; row <= tableModel.getRowCount(); row++) {
			format(table.getCellFormatter(), row);
		}
	}
}

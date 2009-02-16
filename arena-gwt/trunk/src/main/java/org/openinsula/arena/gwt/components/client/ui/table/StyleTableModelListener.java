package org.openinsula.arena.gwt.components.client.ui.table;

import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;


/**
 * Table with style options.
 * 
 * @author Lucas K Mogari
 */
public class StyleTableModelListener implements TableModelListener {

	private static final String LIST_GRID_CELL = "listGridCell";

	private static final String LIST_GRID_EVEN_ROW = "listGridEvenRow";

	private static final String LIST_GRID_ODD_ROW = "listGridOddRow";

	private static final String LIST_GRID_HEADER_ROW = "listGridHeaderRow";

	private final Table<? extends HTMLTable> table;

	public StyleTableModelListener(final Table<? extends HTMLTable> table) {
		this.table = table;
	}

	public void tableChanged(final TableModelEvent e) {
		final int firstRow = e.getFirstRow();
		final int lastRow = e.getLastRow();

		switch (e.getType()) {
		case TableModelEvent.INSERT:
			for (int i = firstRow; i <= lastRow; i++) {
				setRowStyle(i + 1);
			}
			break;

		case TableModelEvent.UPDATE:
			setTableStyle();
			break;
		}
	}

	private void setTableStyle() {
		final HTMLTable htmlTable = table.getWidget();
		final TableModel tableModel = table.getModel();
		final CellFormatter cellFormatter = htmlTable.getCellFormatter();

		htmlTable.setStyleName("StyleTable");
		
		htmlTable.getRowFormatter().setStyleName(0, LIST_GRID_HEADER_ROW);

		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			cellFormatter.setStyleName(0, i, LIST_GRID_CELL);
		}

		for (int i = 1; i <= tableModel.getRowCount(); i++) {
			setRowStyle(i);
		}
	}

	private void setRowStyle(final int row) {
		final HTMLTable htmlTable = table.getWidget();
		final boolean odd = row % 2 == 0;
		final CellFormatter cellFormatter = htmlTable.getCellFormatter();
		final int columnCount = table.getModel().getColumnCount();

		htmlTable.getRowFormatter().setStyleName(row, odd ? LIST_GRID_ODD_ROW : LIST_GRID_EVEN_ROW);

		for (int i = 0; i < columnCount; i++) {
			cellFormatter.setStyleName(row, i, LIST_GRID_CELL);
		}
	}

}

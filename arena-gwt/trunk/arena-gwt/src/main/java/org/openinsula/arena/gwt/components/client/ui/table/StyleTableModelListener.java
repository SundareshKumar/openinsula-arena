package org.openinsula.arena.gwt.components.client.ui.table;

import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

/**
 * Table with style options.
 * 
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public class StyleTableModelListener implements TableModelListener {

	public static enum Styles {
		CELL("listGridCell"), 
		EVEN_ROW("listGridEvenRow"), 
		ODD_ROW("listGridOddRow"), 
		HEADER("listGridHeaderRow"), 
		TABLE("StyleTable");

		public final String styleName;

		private Styles(final String styleName) {
			this.styleName = styleName;
		}

		public static Styles rowStyleFor(final int row) {
			return row % 2 == 0 ? EVEN_ROW : ODD_ROW;
		}

		@Override
		public String toString() {
			return styleName;
		}
	}

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
		final HTMLTable htmlTable = table.getTable();
		final TableModel tableModel = table.getModel();
		final CellFormatter cellFormatter = htmlTable.getCellFormatter();

		htmlTable.setStyleName(Styles.TABLE.styleName);

		htmlTable.getRowFormatter().setStyleName(0, Styles.HEADER.styleName);

		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			cellFormatter.setStyleName(0, i, Styles.CELL.styleName);
		}

		for (int i = 1; i <= tableModel.getRowCount(); i++) {
			setRowStyle(i);
		}
	}

	private void setRowStyle(final int row) {
		final HTMLTable htmlTable = table.getTable();
		final CellFormatter cellFormatter = htmlTable.getCellFormatter();
		final int columnCount = table.getModel().getColumnCount();
		
		htmlTable.getRowFormatter().setStyleName(row, Styles.rowStyleFor(row).styleName);

		for (int i = 0; i < columnCount; i++) {
			cellFormatter.getElement(row, i).setPropertyString("valign", "middle");
			cellFormatter.setStyleName(row, i, Styles.CELL.styleName);
		}
	}

}

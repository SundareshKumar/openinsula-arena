package org.openinsula.arena.gwt.client.user.ui.table;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.table.client.overrides.Grid;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractGridTable<T extends Grid> extends Table<T> {

	public AbstractGridTable() {
	}

	public AbstractGridTable(TableModel tableModel) {
		super(tableModel);
	}

	public void tableChanged(TableModelEvent e) {
		final int firstRow = e.getFirstRow();
		final int tableModelRowCount = getModel().getRowCount();
		final int gridRowCount = getWidget().getRowCount();
		final int column = e.getColumn();
		int lastRow = e.getLastRow();

		switch (e.getType()) {
		case TableModelEvent.INSERT:
			if (lastRow >= tableModelRowCount) {
				lastRow = tableModelRowCount - 1;
			}

			if (gridRowCount == 0) {
				updateAll();
			}
			else if (firstRow + 1 >= gridRowCount) {
				appendRows(firstRow, lastRow, column);
			}
			else {
				insertRows(firstRow, lastRow, column);
			}
			break;

		case TableModelEvent.UPDATE:
			updateAll();
			break;

		case TableModelEvent.DELETE:
			if (lastRow >= tableModelRowCount) {
				lastRow = tableModelRowCount;
			}

			if (gridRowCount == 1) {
				updateAll();
			}
			else if (firstRow + 2 >= gridRowCount) {
				resizeToDelete(firstRow, lastRow);
			}
			else {
				final int newLastRow = getWidget().getRowCount() - (lastRow - firstRow) - 2;

				for (int i = firstRow; i < newLastRow; i++) {
					updateRow(i, column);
				}

				resizeToDelete(firstRow, lastRow);
			}
			break;
		}
	}

	void appendRows(int firstRow, int lastRow, int column) {
		resizeToInsert(firstRow, lastRow);

		for (int i = firstRow; i <= lastRow; i++) {
			updateRow(i, column);
		}
	}

	void insertRows(int firstRow, int lastRow, int column) {
		final int newRowSize = resizeToInsert(firstRow, lastRow);

		for (int i = firstRow; i <= newRowSize; i++) {
			updateRow(i, column);
		}
	}

	private int resizeToDelete(int firstRow, int lastRow) {
		final Grid table = getWidget();
		final int newRowSize = table.getRowCount() - (lastRow - firstRow) - 1;

		table.resizeRows(newRowSize);

		resizeColumnsIfNecessary();

		return newRowSize;
	}

	private int resizeToInsert(int firstRow, int lastRow) {
		final Grid table = getWidget();
		final int newRowSize = table.getRowCount() + lastRow - firstRow + 1;

		table.resizeRows(newRowSize);

		resizeColumnsIfNecessary();

		return newRowSize;
	}

	private void resizeColumnsIfNecessary() {
		final Grid table = getWidget();
		final int tableModelColumnCount = getModel().getColumnCount();

		if (table.getColumnCount() != tableModelColumnCount) {
			table.resizeColumns(tableModelColumnCount);
		}
	}

	void updateAll() {
		final int columnCount = getModel().getColumnCount();
		final int rowCount = getModel().getRowCount();

		getWidget().clear();
		getWidget().resize(rowCount + 1, columnCount);

		updateHeader(TableModelEvent.ALL_COLUMNS);

		for (int i = 0; i < rowCount; i++) {
			updateRow(i, TableModelEvent.ALL_COLUMNS);
		}
	}

	private void updateHeader(int column) {
		switch (column) {
		case TableModelEvent.ALL_COLUMNS:
			final int columnCount = getModel().getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				final Object obj = getModel().getHeaderValueAt(i);

				setValue(0, i, obj);
			}
			break;

		default:
			final Object obj = getModel().getHeaderValueAt(column);

			setValue(0, column, obj);
			break;
		}
	}

	private void updateRow(int rowIndex, int column) {
		switch (column) {
		case TableModelEvent.ALL_COLUMNS:
			final int columnCount = getModel().getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				final Object obj = getModel().getValueAt(rowIndex, i);

				setValue(rowIndex + 1, i, obj);
			}
			break;

		default:
			final Object obj = getModel().getValueAt(rowIndex, column);

			setValue(rowIndex + 1, column, obj);
			break;
		}
	}

	private void setValue(int rowIndex, int column, Object obj) {
		if (obj == null) {
			return;
		}

		if (obj instanceof Widget) {
			getWidget().setWidget(rowIndex, column, (Widget) obj);
		}
		else if (obj instanceof String) {
			getWidget().setText(rowIndex, column, obj.toString());
		}
	}

}

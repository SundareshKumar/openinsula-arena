package org.openinsula.arena.appengine.gwt.client.table;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractGridTable<T extends Grid> extends Table<T> {

	public AbstractGridTable() {
	}

	public AbstractGridTable(final TableModel tableModel) {
		super(tableModel);
	}

	public void tableChanged(final TableModelEvent e) {
		final int firstRow = e.getFirstRow();
		final int tableModelRowCount = getModel().getRowCount();
		final int gridRowCount = getTable().getRowCount();
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
			if (lastRow < tableModelRowCount - 1) {
				updateRows(firstRow, lastRow);
			}
			else {
				updateAll();
			}
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
				final int newLastRow = getTable().getRowCount() - (lastRow - firstRow) - 2;

				for (int i = firstRow; i < newLastRow; i++) {
					updateRow(i, column);
				}
				resizeToDelete(firstRow, lastRow);
			}
			break;
		}
	}

	void appendRows(final int firstRow, final int lastRow, final int column) {
		resizeToInsert(firstRow, lastRow);

		for (int i = firstRow; i <= lastRow; i++) {
			updateRow(i, column);
		}
	}

	void insertRows(final int firstRow, final int lastRow, final int column) {
		final int newRowSize = resizeToInsert(firstRow, lastRow);

		for (int i = firstRow; i <= newRowSize; i++) {
			updateRow(i, column);
		}
	}

	private int resizeToDelete(final int firstRow, final int lastRow) {
		final Grid table = getTable();
		final int newRowSize = table.getRowCount() - (lastRow - firstRow) - 1;

		table.resizeRows(newRowSize);

		resizeColumnsIfNecessary();

		return newRowSize;
	}

	private int resizeToInsert(final int firstRow, final int lastRow) {
		final Grid table = getTable();
		final int newRowSize = table.getRowCount() + lastRow - firstRow + 1;

		table.resizeRows(newRowSize);

		resizeColumnsIfNecessary();

		return newRowSize;
	}

	private void resizeColumnsIfNecessary() {
		final Grid table = getTable();
		final int tableModelColumnCount = getModel().getColumnCount();

		if (table.getColumnCount() != tableModelColumnCount) {
			table.resizeColumns(tableModelColumnCount);
		}
	}

	private void updateRows(final int firstRow, final int lastRow) {
		for (int i = firstRow; i <= lastRow; i++) {
			updateRow(i, TableModelEvent.ALL_COLUMNS);
		}
	}

	void updateAll() {
		final int columnCount = getModel().getColumnCount();
		final int rowCount = getModel().getRowCount();

		getTable().clear();
		getTable().resize(rowCount + 1, columnCount);

		updateHeader(TableModelEvent.ALL_COLUMNS);
		updateRows(0, rowCount - 1);
	}

	private void updateHeader(final int column) {
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

	private void updateRow(final int rowIndex, final int column) {
		final TableModel model = getModel();

		switch (column) {
		case TableModelEvent.ALL_COLUMNS:
			final int columnCount = model.getColumnCount();

			for (int i = 0; i < columnCount; i++) {
				final Object obj = model.getValueAt(rowIndex, i);

				setValue(rowIndex + 1, i, obj);
			}
			break;

		default:
			final Object obj = model.getValueAt(rowIndex, column);

			setValue(rowIndex + 1, column, obj);
			break;
		}
	}

	private void setValue(final int rowIndex, final int column, final Object obj) {
		if (obj == null) {
			return;
		}

		if (obj instanceof Widget) {
			getTable().setWidget(rowIndex, column, (Widget) obj);
		}
		else if (obj instanceof String) {
			getTable().setText(rowIndex, column, obj.toString());
		}
	}

	@Override
	public void tableDataUpdated() {
	}

}

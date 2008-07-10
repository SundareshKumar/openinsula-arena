package org.openinsula.arena.gwt.client.ui.table;

import org.openinsula.arena.gwt.client.StyleBuilder;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

public class Table<T> extends Composite implements TableModelListener {
	private final StyleBuilder style = new StyleBuilder("sandBox", "Table");

	private TableListener<T> tableListener;

	private final TableModel<T> tableModel;

	private final FlexTable table;

	// these 3 fields are quick references to avoid repetitive getter invocation
	private final RowFormatter rowFormatter;
	private final CellFormatter cellFormatter;
	private final int columnCount;

	public Table(final TableModel<T> tableModel) {
		this.tableModel = tableModel;
		columnCount = tableModel.getHeader().length;

		table = new FlexTable();
		rowFormatter = table.getRowFormatter();
		cellFormatter = table.getCellFormatter();

		initWidget(table);
		prepareTable();

		this.tableModel.addTableModelListener(this);
	}

	public void onTableDataChange() {
		updateTable();
	}

	public void setTableListener(final TableListener<T> selectionListener) {
		this.tableListener = selectionListener;
	}

	public TableModel<T> getTableModel() {
		return tableModel;
	}

	private void prepareTable() {
		setStyleName(style.toString());
		setWidth("100%");

		rowFormatter.setStyleName(0, style.addRule("Header"));
		cellFormatter.setStyleName(0, 0, style.getRule("FirstColumm"));
		cellFormatter.setStyleName(0, columnCount - 1, style.getRule("LastColumn"));

		for (int column = 0; column < columnCount; column++) {
			table.setText(0, column, tableModel.getHeader()[column]);

			if (tableModel.isColumnSortable(column)) {
				cellFormatter.addStyleName(0, column, style.getRule("SortableColumn"));
			}
		}

		style.dropRule();

		table.addTableListener(new com.google.gwt.user.client.ui.TableListener() {
			public void onCellClicked(final SourcesTableEvents sender, final int row, final int cell) {
				if (row == 0) {
					tableModel.sortValues(cell);
				} else if (tableListener != null) {

					// -1 to ignore header
					tableListener.onRowSelect(tableModel.getEntityAt(row - 1), row);
				}
			}
		});
	}

	private void updateTable() {
		int newRowCount = tableModel.getRowCount();

		for (int row = 0; row < newRowCount; row++) {
			T item = tableModel.getEntityAt(row);

			for (int column = 0; column < columnCount; column++) {
				// header is never updated
				table.setText(row + 1, column, tableModel.getValueAt(item, column));
			}
		}

		// if there are exceeding rows in the table, start removing from this index.
		int firstRowToRemoveIndex = newRowCount + 1;

		int rowsExceeded = table.getRowCount() - firstRowToRemoveIndex;

		while (rowsExceeded-- > 0) {
			table.removeRow(firstRowToRemoveIndex);
		}

		renderRows();
	}

	private void renderRows() {
		int lastColumnIndex = columnCount - 1;

		String rowStyleName = style.addRule("Row");
		String firstColumnStyleName = style.getRule("FirstColumn");
		String lastColumnStyleName = style.getRule("LastColumn");
		String evenRowStyleName = style.getRule("Even");
		String oddRowStyleName = style.getRule("Odd");

		for (int row = 1, rowCount = table.getRowCount(); row < rowCount; row++) {
			rowFormatter.setStyleName(row, rowStyleName);
			cellFormatter.addStyleName(row, 0, firstColumnStyleName);
			cellFormatter.addStyleName(row, lastColumnIndex, lastColumnStyleName);
			rowFormatter.addStyleName(row, (row % 2 == 0) ? oddRowStyleName : evenRowStyleName);
		}

		style.dropRule();
	}

	protected void finalize() throws Throwable {
		tableModel.removeTableModelListener(this);
	}

}

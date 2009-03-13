package org.openinsula.arena.gwt.components.client.ui.table;

import java.util.EventObject;

/**
 * @author Lucas K Mogari
 */
public class TableModelEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public static final int INSERT = 1;

	public static final int UPDATE = 0;

	public static final int DELETE = -1;

	public static final int ALL_COLUMNS = -1;

	private final int type;

	private final int firstRow;

	private final int lastRow;

	private final int column;

	public TableModelEvent(TableModel source) {
		this(source, 0, Integer.MAX_VALUE, ALL_COLUMNS, UPDATE);
	}

	public TableModelEvent(TableModel source, int row) {
		this(source, row, row, ALL_COLUMNS, UPDATE);
	}

	public TableModelEvent(TableModel source, int firstRow, int lastRow) {
		this(source, firstRow, lastRow, ALL_COLUMNS, UPDATE);
	}

	public TableModelEvent(TableModel source, int firstRow, int lastRow, int column) {
		this(source, firstRow, lastRow, column, UPDATE);
	}

	public TableModelEvent(TableModel source, int firstRow, int lastRow, int column, int type) {
		super(source);

		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.column = column;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public int getColumn() {
		return column;
	}

}

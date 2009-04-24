package org.openinsula.arena.gwt.components.client.ui.table;

public interface TableSelectionListener {

	/**
	 * Fired when all rows are deselected.
	 * 
	 * @param sender the source of the event
	 */
	void onAllRowsDeselected();

	/**
	 * Fired when a single row is deselected. This method will not fire when all
	 * rows are deselected. In that case, use the onAllRowsDeselected events.
	 * 
	 * @param sender the source of the event
	 * @param row the row index
	 */
	void onRowDeselected(int row);

	/**
	 * Fired when one or more rows are selected.
	 * 
	 * @param sender the source of the event
	 * @param firstRow the row index of the first row
	 * @param numRows the number of selected rows
	 */
	void onRowSelected(int rowSelected);

}

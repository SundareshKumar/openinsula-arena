package org.openinsula.arena.gwt.client.user.ui.table;

import com.google.gwt.widgetideas.table.client.SelectionGrid;

/**
 * @author Lucas K Mogari
 */
public class SelectionGridTable extends AbstractGridTable<SelectionGrid> {

	public SelectionGridTable() {
	}

	public SelectionGridTable(TableModel tableModel) {
		super(tableModel);
	}

	@Override
	protected SelectionGrid createNewTable() {
		return new SelectionGrid();
	}

}

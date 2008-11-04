package org.openinsula.arena.gwt.components.client.table;

import com.google.gwt.widgetideas.table.client.overrides.Grid;

/**
 * @author Lucas K Mogari
 */
public class GridTable extends AbstractGridTable<Grid> {

	public GridTable() {
	}

	public GridTable(TableModel tableModel) {
		super(tableModel);
	}

	@Override
	protected Grid createNewTable() {
		return new Grid();
	}

}

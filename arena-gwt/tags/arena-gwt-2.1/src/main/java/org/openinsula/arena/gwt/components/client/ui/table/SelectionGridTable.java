package org.openinsula.arena.gwt.components.client.ui.table;

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

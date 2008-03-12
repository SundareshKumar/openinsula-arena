package org.openinsula.arena.echo2.component.model;

import nextapp.echo2.app.table.TableModel;

public interface SortableTableModel extends TableModel {
	public abstract boolean isSortable(int column);
	
	public abstract void sortTable(int column, boolean order);
}

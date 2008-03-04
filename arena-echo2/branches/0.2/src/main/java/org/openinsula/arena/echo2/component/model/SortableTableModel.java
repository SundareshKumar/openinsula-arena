package org.openinsula.arena.echo2.component.model;

public interface SortableTableModel {
	public abstract boolean isSortable(int column);
	
	public abstract void sortTable(int column, boolean order);
}

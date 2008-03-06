package org.openinsula.arena.echo2.component.model.container.impl;

public abstract class UpdatableContainerTableModel<T> extends ButtonContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	@Override
	public void fireTableDataChanged() {
		updateTableItems();
		super.fireTableDataChanged();
	}

	public abstract void updateTableItems();
	
}

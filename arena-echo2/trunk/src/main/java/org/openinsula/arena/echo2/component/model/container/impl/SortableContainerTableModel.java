package org.openinsula.arena.echo2.component.model.container.impl;

import java.util.Collections;
import java.util.Comparator;

public class SortableContainerTableModel<T> extends PageableContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	public SortableContainerTableModel() {
		super();
	}

	public SortableContainerTableModel(boolean permitDuplicates, boolean substituteDuplicate) {
		super(permitDuplicates, substituteDuplicate);
	}

	/**
	 * Execute the sorting comparator and activate the changes in the table.
	 * @param columnIndex
	 * @param comparator
	 */
	public void sortItems(int columnIndex, Comparator<T> comparator) {
		if (logger.isDebugEnabled()) {
			logger.debug("Sorting table:" + this.getClass());
		}

		if (comparator != null) {
			Collections.sort(getItems(), comparator);
			fireTableDataChanged();
		} else {
			logger.warn("Tried to sort the tableModel with a Null Comparator. Would have caused a NullPointerException.");
		}
	}

}

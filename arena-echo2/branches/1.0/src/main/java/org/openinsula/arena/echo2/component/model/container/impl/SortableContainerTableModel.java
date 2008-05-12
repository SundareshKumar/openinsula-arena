package org.openinsula.arena.echo2.component.model.container.impl;

import java.util.Collections;
import java.util.Comparator;

public class SortableContainerTableModel<T> extends PageableContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	public void sortItems(Comparator<T> comparator) {
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

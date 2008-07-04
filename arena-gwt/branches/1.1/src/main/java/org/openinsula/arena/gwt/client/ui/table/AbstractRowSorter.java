package org.openinsula.arena.gwt.client.ui.table;

import java.util.Comparator;

public abstract class AbstractRowSorter<T> implements RowSorter<T> {
	private int lastColumnSelected;

	private int columnSelected = -1;

	private boolean ascendingOrder;

	private Comparator<T> comparator;

	public AbstractRowSorter() {
		comparator = new Comparator<T>() {

			public int compare(final T o1, final T o2) {
				return AbstractRowSorter.this.compare(o1, o2, columnSelected, ascendingOrder);
			}

		};
	}

	public final Comparator<T> getComparator(final int columnIndex) {
		lastColumnSelected = columnSelected;
		columnSelected = columnIndex;

		if (lastColumnSelected != columnSelected) {
			ascendingOrder = true;
		} else {
			ascendingOrder = !ascendingOrder;
		}

		return comparator;
	}

	protected abstract int compare(T o1, T o2, int columnIndex, boolean asc);

}

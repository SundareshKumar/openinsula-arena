package org.openinsula.arena.gwt.client.ui.table;

import java.util.Comparator;

public interface RowSorter<T> {

	Comparator<T> getComparator(final int columnIndex);

}
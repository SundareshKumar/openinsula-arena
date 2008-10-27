package org.openinsula.arena.gwt.client.ui.table;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public abstract class BeanTableModel implements TableModel {

	private List beans = new LinkedList();

	private final List<TableModelListener> listeners = new LinkedList<TableModelListener>();

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public boolean add(Object obj) {
		final int index = beans.size();
		final boolean b = beans.add(obj);

		if (b) {
			fireItemsInserted(index, index);
		}

		return b;
	}

	public void add(int index, Object obj) {
		beans.add(index, obj);

		fireItemsInserted(index, index);
	}

	public boolean add(Collection objs) {
		final int index = beans.size();
		final boolean b = beans.addAll(objs);

		if (b) {
			fireItemsInserted(index, index + objs.size() - 1);
		}

		return b;
	}

	public boolean add(int index, Collection objs) {
		final boolean b = beans.addAll(index, objs);

		if (b) {
			fireItemsInserted(index, index + objs.size() - 1);
		}
		return b;
	}

	public boolean remove(Object obj) {
		final int index = beans.indexOf(obj);
		final boolean b = beans.remove(obj);

		if (b) {
			fireItemsDeleted(index, index);
		}
		return b;
	}

	public <T> T remove(int index) {
		final Object obj = beans.remove(index);

		if (obj != null) {
			fireItemsDeleted(index, index);
		}

		return (T) obj;
	}

	public <T> T get(int index) {
		return (T) beans.get(index);
	}

	public int indexOf(Object obj) {
		return beans.indexOf(obj);
	}

	public void clear() {
		beans.clear();

		fireTableDataChanged();
	}

	public int getRowCount() {
		return beans.size();
	}

	public void fireTableDataChanged() {
		fireTableChanged(new TableModelEvent(this));
	}

	public void fireItemsInserted(int firstRow, int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT));
	}

	public void fireItemsDeleted(int firstRow, int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.DELETE));
	}

	public void fireTableChanged(TableModelEvent e) {
		for (final TableModelListener listener : listeners) {
			listener.tableChanged(e);
		}
	}

	public <T> List<T> getBeans() {
		return beans;
	}

	public void setItems(List items) {
		beans = items;

		fireTableDataChanged();
	}

}

package org.openinsula.arena.appengine.gwt.client.table;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.appengine.gwt.client.util.CollectionUtils;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public abstract class BeanTableModel implements TableModel {

	private final List beans = new LinkedList();

	private final List<TableModelListener> listeners = new LinkedList<TableModelListener>();

	protected abstract String[] titles();

	public void addTableModelListener(final TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(final TableModelListener listener) {
		listeners.remove(listener);
	}

	public boolean add(final Object obj) {
		final int index = beans.size();
		final boolean b = beans.add(obj);

		if (b) {
			fireItemsInserted(index, index);
		}

		return b;
	}

	public void add(final int index, final Object obj) {
		beans.add(index, obj);

		fireItemsInserted(index, index);
	}

	public boolean add(final Collection objs) {
		final int index = beans.size();
		final boolean b = beans.addAll(objs);

		if (b) {
			fireItemsInserted(index, index + objs.size() - 1);
		}

		return b;
	}

	public boolean add(final int index, final Collection objs) {
		final boolean b = beans.addAll(index, objs);

		if (b) {
			fireItemsInserted(index, index + objs.size() - 1);
		}
		return b;
	}

	public boolean remove(final Object obj) {
		final int index = beans.indexOf(obj);
		final boolean b = beans.remove(obj);

		if (b) {
			fireItemsDeleted(index, index);
		}
		return b;
	}

	public <T> T remove(final int index) {
		final Object obj = beans.remove(index);

		if (obj != null) {
			fireItemsDeleted(index, index);
		}

		return (T) obj;
	}

	public <T> T get(final int index) {
		return (T) beans.get(index);
	}

	public int indexOf(final Object obj) {
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

	public void fireItemsInserted(final int firstRow, final int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.INSERT));
	}

	public void fireItemsDeleted(final int firstRow, final int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.DELETE));
	}

	public void fireRowsUpdated(final int firstRow, final int lastRow) {
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,
				TableModelEvent.UPDATE));
	}

	public void fireTableChanged(final TableModelEvent e) {
		for (final TableModelListener listener : listeners) {
			listener.tableChanged(e);
		}
	}

	public <T> List<T> getBeans() {
		return beans;
	}

	public void setItems(final List items) {
		beans.clear();

		if (!CollectionUtils.isEmpty(items)) {
			beans.addAll(items);
		}

		fireTableDataChanged();
	}

	public int getColumnCount() {
		return titles().length;
	}

	public Object getHeaderValueAt(final int columnIndex) {
		return titles()[columnIndex];
	}

	@Override
	public void setValueAt(int rowIndex, int intColumnIndex, Object value) {
	}

}

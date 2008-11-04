package org.openinsula.arena.gwt.components.client.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class DefaultListBoxModel<T> implements ListBoxModel<T> {

	protected final List<ListBoxModelListener> listeners;

	private Comparator<T> comparator;

	private List<T> values;

	private final List<T> selection;

	public DefaultListBoxModel() {
		this(null);
	}

	public DefaultListBoxModel(final Comparator<T> comparator) {
		this.comparator = comparator;
		this.selection = new ArrayList<T>();

		listeners = new ArrayList<ListBoxModelListener>();
	}

	public void addListBoxModelListener(final ListBoxModelListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);

			fireOnListDataChange();
		}
	}

	public void removeListBoxModelListener(final ListBoxModelListener listener) {
		listeners.remove(listener);
	}

	public void setValues(final List<T> values) {
		this.values = values;

		if (this.values == null || !this.values.containsAll(selection)) {
			setSelectedItems(null);
		}

		sort();
		fireOnListDataChange();
	}

	public void setComparator(final Comparator<T> comparator) {
		this.comparator = comparator;

		if (sort()) {
			fireOnListDataChange();
		}
	}

	private boolean sort() {
		final boolean willSort = comparator != null && values != null && values.size() > 1;

		if (willSort) {
			Collections.sort(values, comparator);
		}

		return willSort;
	}

	private void fireOnListDataChange() {
		for (final ListBoxModelListener listener : listeners) {
			listener.dataChanged();
			listener.selectionChanged();
		}
	}

	private void fireOnListSelectionChange() {
		for (final ListBoxModelListener listener : listeners) {
			listener.selectionChanged();
		}
	}

	public T getElementAt(final int index) {
		if (values == null) {
			throw new NoSuchElementException("Model is Empty");
		}

		return values.get(index);
	}

	public int indexOf(final T element) {
		if (values == null) {
			return -1;
		}

		return values.indexOf(element);
	}

	public Collection<T> getSelectedItems() {
		return Collections.unmodifiableCollection(selection);
	}

	public T getSelectedItem() {
		return selection.isEmpty() ? null : selection.get(0);
	}

	public void setSelectedItems(final Collection<T> selection) {
		this.selection.clear();

		if (selection != null && !selection.isEmpty()) {
			this.selection.addAll(selection);
		}

		fireOnListSelectionChange();
	}

	public void setSelectedItem(final T selection) {
		this.selection.clear();

		if (selection != null) {
			this.selection.add(selection);
		}

		fireOnListSelectionChange();
	}

	public int getSize() {
		return values == null ? 0 : values.size();
	}

	public String getValue(int index) {
		final T t = getElementAt(index);
		return String.valueOf(t);
	}

}

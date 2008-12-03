package org.openinsula.arena.gwt.client.ui.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;

public class DefaultListBoxModel<T> implements ListBoxModel<T> {

	protected final List<ListBoxModelListener> listeners;

	private Comparator<T> comparator;

	private List<T> values;

	private final List<T> selection;

	public DefaultListBoxModel() {
		this(null, (T[]) null);
	}

	public DefaultListBoxModel(Comparator<T> comparator) {
		this(comparator, (T[]) null);
	}

	public DefaultListBoxModel(List<T> list) {
		this(null, list);
	}

	public DefaultListBoxModel(T ... values) {
		this(null, values);
	}

	public DefaultListBoxModel(final Comparator<T> comparator, T ... values) {
		this(comparator, (values != null ? Arrays.asList(values) : null));
	}

	public DefaultListBoxModel(Comparator<T> comparator, List<T> list) {
		this.selection = new ArrayList<T>();
		this.comparator = comparator;
		this.values = list;

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
		boolean willSort = comparator != null && values != null && values.size() > 1;

		if (willSort) {
			Collections.sort(values, comparator);
		}

		return willSort;
	}

	private void fireOnListDataChange() {
		for (ListBoxModelListener listener : listeners) {
			listener.onListDataChange();
			listener.onListSelectionChange();
		}
	}

	private void fireOnListSelectionChange() {
		for (ListBoxModelListener listener : listeners) {
			listener.onListSelectionChange();
		}
	}

	public T getElementAt(final int index) {
		return values().get(index);
	}

	public int indexOf(final T element) {
		return values().indexOf(element);
	}

	public Collection<T> getSelectedItems() {
		return Collections.unmodifiableCollection(selection);
	}

	public Collection<T> getValues() {
		return Collections.unmodifiableCollection(values);
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
		return values().size();
	}

	public boolean remove(T item) {
		GWT.log("Removendo um item do BeanListBox -> Item: " + item.toString(), null);

		boolean result = values().remove(item);
		fireOnListDataChange();

		return result;
	}

	private List<T> values() {
		if (values == null) {
			values = new ArrayList<T>();
		}

		return values;
	}
}

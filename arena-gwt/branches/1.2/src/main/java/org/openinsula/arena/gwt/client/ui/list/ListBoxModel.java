package org.openinsula.arena.gwt.client.ui.list;

import java.util.Collection;

public interface ListBoxModel<T> {

	int getSize();

	void setSelectedItems(Collection<T> selection);

	void setSelectedItem(T selection);

	Collection<T> getSelectedItems();

	T getSelectedItem();

	T getElementAt(int index);

	int indexOf(T element);

	void addListBoxModelListener(ListBoxModelListener listener);

	void removeListBoxModelListener(ListBoxModelListener listener);

	String renderBean(T bean);

}

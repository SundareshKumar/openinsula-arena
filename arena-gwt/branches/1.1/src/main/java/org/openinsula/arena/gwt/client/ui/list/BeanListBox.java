package org.openinsula.arena.gwt.client.ui.list;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.Widget;

public class BeanListBox<T> extends Composite implements ListBoxModelListener, SourcesFocusEvents {

	private final ListBox listBox;

	private final ListBoxModel<T> model;

	private boolean emptySelectionAllowed = true;

	public BeanListBox(final ListBoxModel<T> listBoxModel) {
		this(new ListBox(), listBoxModel);
	}

	public BeanListBox(final ListBox listBox, final ListBoxModel<T> listBoxModel) {
		this.listBox = listBox;
		clearListBox();

		this.listBox.addChangeListener(new ChangeListener() {

			public void onChange(final Widget sender) {
				onListBoxChange();
			}

		});

		initWidget(listBox);

		this.model = listBoxModel;
		this.model.addListBoxModelListener(this);
	}

	public void setEmptySelectionAllowed(final boolean nullAllowed) {
		this.emptySelectionAllowed = nullAllowed;
	}

	private void clearListBox() {
		listBox.clear();

		if (emptySelectionAllowed) {
			listBox.addItem("");
		}
	}

	private void onListBoxChange() {

		if (listBox.isMultipleSelect()) {
			Collection<T> selection = new ArrayList<T>();

			for (int i = 1, rows = listBox.getItemCount(); i < rows; i++) {
				if (listBox.isItemSelected(i)) {
					selection.add(model.getElementAt(i - 1));
				}
			}
			model.setSelectedItems(selection);

		} else {
			int idx = listBox.getSelectedIndex();

			if (emptySelectionAllowed) {
				model.setSelectedItem(idx < 1 ? null : model.getElementAt(idx - 1));
			} else {
				model.setSelectedItem(idx == -1 ? null : model.getElementAt(idx));
			}

		}
	}

	public void onListDataChange() {
		clearListBox();

		int rows = model.getSize();

		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				listBox.addItem(model.renderBean(model.getElementAt(i)));
			}
		}
	}

	public void onListSelectionChange() {
		if (listBox.isMultipleSelect()) {
			Collection<T> selectedItems = model.getSelectedItems();

			for (int i = 0, rows = model.getSize(); i < rows; i++) {

				if (emptySelectionAllowed) {
					listBox.setItemSelected(i + 1, selectedItems.contains(model.getElementAt(i)));
				} else {
					listBox.setItemSelected(i, selectedItems.contains(model.getElementAt(i)));
				}

			}
		} else {
			T selectedItem = model.getSelectedItem();
			int idx = selectedItem == null ? 0 : model.indexOf(selectedItem) + 1;

			if (emptySelectionAllowed) {
				idx = selectedItem == null ? 0 : model.indexOf(selectedItem) + 1;
			} else {
				idx = selectedItem == null ? -1 : model.indexOf(selectedItem);
			}

			listBox.setSelectedIndex(idx);
		}
	}

	protected void finalize() throws Throwable {
		model.removeListBoxModelListener(this);
	}

	public void addFocusListener(final FocusListener listener) {
		listBox.addFocusListener(listener);
	}

	public void removeFocusListener(final FocusListener listener) {
		listBox.removeFocusListener(listener);
	}
}
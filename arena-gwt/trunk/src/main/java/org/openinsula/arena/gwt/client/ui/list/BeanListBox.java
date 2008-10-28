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

	private final ListBox listBox = new ListBox();

	private ListBoxModel<T> model;

	private boolean emptySelectionAllowed = true;

	public BeanListBox() {
		this(null);
	}

	public BeanListBox(ListBoxModel<T> listBoxModel) {
		listBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				onListBoxChange();
			}
		});

		initWidget(listBox);

		setModel(listBoxModel);
	}

	public ListBoxModel<T> getModel() {
		return model;
	}

	public void setModel(ListBoxModel<T> model) {
		final ListBoxModel<T> oldModel = this.model;

		if (oldModel == null || !oldModel.equals(model)) {
			this.model = model;

			if (oldModel != null) {
				oldModel.removeListBoxModelListener(this);
			}

			model.addListBoxModelListener(this);
		}
	}

	public void setEmptySelectionAllowed(boolean nullAllowed) {
		if (this.emptySelectionAllowed != nullAllowed) {
			this.emptySelectionAllowed = nullAllowed;
			dataChanged();
			selectionChanged();
		}
	}

	private void clearListBox() {
		listBox.clear();

		if (emptySelectionAllowed) {
			listBox.addItem("");
		}
	}

	private void onListBoxChange() {
		if (listBox.isMultipleSelect()) {
			final Collection<T> selection = new ArrayList<T>();

			int listBoxIdx = 0;
			int modelIdx = 0;

			if (emptySelectionAllowed) {
				listBoxIdx++;
			}

			for (final int rows = listBox.getItemCount(); listBoxIdx < rows; listBoxIdx++, modelIdx++) {
				if (listBox.isItemSelected(listBoxIdx)) {
					selection.add(model.getElementAt(modelIdx));
				}
			}

			model.setSelectedItems(selection);

		}
		else {
			final int idx = listBox.getSelectedIndex();

			if (emptySelectionAllowed) {
				model.setSelectedItem(idx < 1 ? null : model.getElementAt(idx - 1));
			}
			else {
				model.setSelectedItem(idx == -1 ? null : model.getElementAt(idx));
			}

		}
	}

	public void dataChanged() {
		clearListBox();

		final int rows = model.getSize();

		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				listBox.addItem(model.getValue(i));
			}
		}
	}

	public void selectionChanged() {
		if (listBox.isMultipleSelect()) {
			final Collection<T> selectedItems = model.getSelectedItems();

			for (int i = 0, j = emptySelectionAllowed ? 1 : 0, rows = model.getSize(); i < rows; i++, j++) {
				listBox.setItemSelected(j, selectedItems.contains(model.getElementAt(i)));
			}
		}
		else {
			final T selectedItem = model.getSelectedItem();
			final int idx = model.indexOf(selectedItem);
			final int diff = emptySelectionAllowed ? 1 : 0;
			listBox.setSelectedIndex(idx + diff);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		model.removeListBoxModelListener(this);
	}

	public void addFocusListener(FocusListener listener) {
		listBox.addFocusListener(listener);
	}

	public void removeFocusListener(FocusListener listener) {
		listBox.removeFocusListener(listener);
	}

}

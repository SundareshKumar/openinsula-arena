package org.openinsula.arena.gwt.client.ui.list;

import java.util.ArrayList;
import java.util.Collection;

import org.openinsula.arena.gwt.client.ui.FocusComposite;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class BeanListBox<T> extends FocusComposite implements ListBoxModelListener {

	private final ListBox listBox;

	private final ListBoxModel<T> model;

	private boolean emptySelectionAllowed = true;
	
	private ListBoxCellRender<T> cellRender = new ListBoxCellRender<T>() {
		public String render(T item) {
			return item.toString();
		}
	};

	public BeanListBox(final ListBoxModel<T> listBoxModel) {
		this(new ListBox(), listBoxModel);
	}

	public BeanListBox(final ListBox listBox, final ListBoxModel<T> listBoxModel) {
		this.listBox = listBox;

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
		if (this.emptySelectionAllowed != nullAllowed) {
			this.emptySelectionAllowed = nullAllowed;
			onListDataChange();
			onListSelectionChange();
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
			Collection<T> selection = new ArrayList<T>();

			int listBoxIdx = 0;
			int modelIdx = 0;

			if (emptySelectionAllowed) {
				listBoxIdx++;
			}

			for (int rows = listBox.getItemCount(); listBoxIdx < rows; listBoxIdx++, modelIdx++) {
				if (listBox.isItemSelected(listBoxIdx)) {
					selection.add(model.getElementAt(modelIdx));
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
				listBox.addItem(cellRender.render(model.getElementAt(i)));
			}
		}
	}

	public void onListSelectionChange() {
		if (listBox.isMultipleSelect()) {
			Collection<T> selectedItems = model.getSelectedItems();

			for (int i = 0, j = emptySelectionAllowed ? 1 : 0, rows = model.getSize(); i < rows; i++, j++) {
				listBox.setItemSelected(j, selectedItems.contains(model.getElementAt(i)));
			}
		} else {
			T selectedItem = model.getSelectedItem();
			int idx = model.indexOf(selectedItem);
			int diff = emptySelectionAllowed ? 1 : 0;
			listBox.setSelectedIndex(idx + diff);
		}
	}

	public void setSelectedItem(T item) {
		model.setSelectedItem(item);
	}

	public T getSelectedItem() {
		int selectedIndex = listBox.getSelectedIndex() -1;
		if (selectedIndex >= 0) {
			return model.getElementAt(selectedIndex);
		}

		return null;
	}

	public void setSelectedIndex(int index) {
		listBox.setSelectedIndex(index);
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

	public ListBox getListBox() {
		return listBox;
	}

	public ListBoxModel<T> getModel() {
		return model;
	}
	
	public ListBoxCellRender<T> getCellRender() {
		return cellRender;
	}

	public void setCellRender(ListBoxCellRender<T> cellRender) {
		this.cellRender = cellRender;
		onListBoxChange();
	}

}
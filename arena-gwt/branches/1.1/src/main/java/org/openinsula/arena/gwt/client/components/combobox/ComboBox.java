package org.openinsula.arena.gwt.client.components.combobox;

import com.google.gwt.user.client.ui.ListBox;

public class ComboBox<T> extends ListBox implements DataListener {

	private ComboBoxModel<T> model;
	
	private ComboBoxRenderer<T> render = new DefaultComboBoxRenderer<T>();
	
	public ComboBoxRenderer<T> getRender() {
		return render;
	}

	public void setRender(ComboBoxRenderer<T> render) {
		this.render = render;
	}

	public ComboBox() {
		setMultipleSelect(false);
	}
	
	public void setModel(ComboBoxModel<T> model) {
		model.addDataListener(this);
		this.model = model;
		onDataChanged();
	}
	
	public void setSelectedItem(T item) {
		model.setSelectedItem(item);
		int selectedIndex = model.indexOf(item);
		setSelectedIndex(selectedIndex);
	}
	
	public T getSelectedItem() {
		return model.getSelectedItem();
	}

	private void renderItens() {
		clearItems();
		for (int i = 0; i < model.getSize(); i++) {
			addItem(render.asString(model.getItem(i)));
		}
	}

	private void clearItems() {
		for (int i = 0; i < getItemCount(); i++) {
			removeItem(i);
		}
	}

	public void onDataChanged() {
		renderItens();
	}
	
}

package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.list.BeanListBox;

public class BeanListBoxNotNullFormItemValidator<T> implements FormItemValidator<BeanListBox<T>> {

	public String getInvalidValueMessage() {
		return "Campo obrigatório";
	}

	public boolean validate(BeanListBox<T> widget) {
		return widget.getSelectedItem() != null;
	}

}

package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.TextBoxBase;

public class NotNullFormItemValidator<T extends TextBoxBase> implements FormItemValidator<T> {

	public String getInvalidValueMessage() {
		return "Campo obrigatório";
	}

	public boolean validate(T widget) {
		return widget.getText().trim().length() > 0;
	}



}

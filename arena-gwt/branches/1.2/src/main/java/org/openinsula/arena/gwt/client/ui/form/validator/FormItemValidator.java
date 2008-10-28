package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.user.client.ui.Widget;

public interface FormItemValidator<W extends Widget> {

	void validate(W widget, ValidatorChain<W> chain, ValidatorAction action);

	void setFormItem(FormItem<W> formItem);

	String getInvalidValueMessage();

}

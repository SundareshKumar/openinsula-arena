package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.user.client.ui.Widget;

public interface FormItemValidatorNew<W extends Widget> {

	void validate(W widget, ValidatorChain<W> chain);

	void setFormItem(FormItem<W> formItem);

	String getInvalidValueMessage();

}

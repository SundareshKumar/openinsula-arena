package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.user.client.ui.Widget;

public abstract class SyncFormItemValidator<W extends Widget> implements FormItemValidatorNew<W> {

	private FormItem<W> formItem;

	protected abstract boolean evaluate(W widget);

	public void setFormItem(FormItem<W> formItem) {
		this.formItem = formItem;
	}

	public void validate(W widget, ValidatorChain<W> chain) {
		if (evaluate(widget)) {
			chain.doChain(widget);
		} else {
			formItem.setValid(false);
			formItem.setErrorMessage(getInvalidValueMessage());
			formItem.refresh();
		}
	}

}

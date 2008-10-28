package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public abstract class SyncFormItemValidator<W extends Widget> implements FormItemValidator<W> {

	private FormItem<W> formItem;

	protected abstract boolean evaluate(W widget);

	public void setFormItem(FormItem<W> formItem) {
		this.formItem = formItem;
	}

	public void validate(W widget, ValidatorChain<W> chain, ValidatorAction action) {
		GWT.log("validator do tipo: " + getClass().getName(), null);
		if (evaluate(widget)) {
			if (chain.isLastNode()) {
				GWT.log("lastNode", null);
				action.onSuccess();
			} else {
				chain.doChain(widget, action);
			}
		} else {
			formItem.setValid(false);
			formItem.setErrorMessage(getInvalidValueMessage());
			formItem.refresh();
			action.onFail();
		}
	}

}

package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public abstract class SyncFormItemValidator<W extends Widget> implements FormItemValidator<W> {

	private FormItem<W> formItem;

	protected abstract void evaluate(W widget, EvaluateCallback callback);

	public void setFormItem(FormItem<W> formItem) {
		this.formItem = formItem;
	}

	public void validate(final W widget, final ValidatorChain<W> chain, final ValidatorAction action) {
		GWT.log("validator do tipo: " + getClass().getName(), null);

		evaluate(widget, new EvaluateCallback() {
			public void fail() {
				formItem.setValid(false);
				formItem.setErrorMessage(getInvalidValueMessage());
				action.onFail();
			}

			public void success() {
				if (chain.isLastNode()) {
					GWT.log("executou onSuccess() no syncFormItemValidator", null);
					action.onSuccess();
					formItem.setValid(true);
				} else {
					chain.doChain(widget, action);
				}
			}
		});
		formItem.refresh();
	}

}

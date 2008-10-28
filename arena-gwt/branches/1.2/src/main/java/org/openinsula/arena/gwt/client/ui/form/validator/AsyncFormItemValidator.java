package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public abstract class AsyncFormItemValidator<W extends Widget, T> implements FormItemValidator<W>, AsyncCallback<T> {

	private FormItem<W> formItem;

	private ValidatorChain<W> validatorChain;

	private W widget;

	private ValidatorAction action;

	protected abstract boolean evaluateResult(T result);

	protected abstract void validate(W widget, AsyncCallback<T> callback);

	public void validate(W widget, ValidatorChain<W> chain, ValidatorAction action) {
		validatorChain = chain;
		this.widget = widget;
		this.action = action;
		validate(widget, this);
	}

	public void onSuccess(T result) {
		boolean valid = evaluateResult(result);
		if (valid) {
			if (validatorChain.isLastNode()) {
				action.onSuccess();
			} else {
				validatorChain.doChain(widget, action);
			}
		}
		else {
			formItem.setErrorMessage(getInvalidValueMessage());
			action.onFail();
		}
		formItem.setValid(valid);
		formItem.refresh();
	}

	public void setFormItem(FormItem<W> formItem) {
		this.formItem = formItem;
	}

	public void onFailure(Throwable caught) {
	}

}

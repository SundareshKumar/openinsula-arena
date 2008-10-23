package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public abstract class AsyncFormItemValidator<W extends Widget, T> implements FormItemValidatorNew<W>, AsyncCallback<T> {

	private FormItem<W> formItem;

	private ValidatorChain<W> validatorChain;

	private W widget;

	protected abstract boolean evaluateResult(T result);

	protected abstract void validate(W widget, AsyncCallback<T> callback);

	public void validate(W widget, ValidatorChain<W> chain) {
		validatorChain = chain;
		this.widget = widget;
		validate(widget, this);
	}

	public void onSuccess(T result) {
		boolean valid = evaluateResult(result);
		if (valid) {
			validatorChain.doChain(widget);
		}
		else {
			formItem.setErrorMessage(getInvalidValueMessage());
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

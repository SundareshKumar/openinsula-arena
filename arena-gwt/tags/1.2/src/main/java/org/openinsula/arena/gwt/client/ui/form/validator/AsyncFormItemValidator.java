package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public abstract class AsyncFormItemValidator<W extends Widget, T> implements FormItemValidator<W>, AsyncCallback<T> {

	private FormItem<W> formItem;

	private ValidatorChain<W> validatorChain;

	private W widget;

	private ValidatorAction action;

	protected abstract void evaluateResult(T result, EvaluateCallback callback);

	protected abstract void validate(W widget, AsyncCallback<T> callback);

	public void validate(W widget, ValidatorChain<W> chain, ValidatorAction action) {
		validatorChain = chain;
		this.widget = widget;
		this.action = action;
		validate(widget, this);
	}

	public void onSuccess(T result) {
		GWT.log("executando onSuccess() do asyncCallback", null);
		evaluateResult(result, new EvaluateCallback() {
			public void fail() {
				formItem.setErrorMessage(getInvalidValueMessage());
				action.onFail();
				formItem.setValid(false);
			}
			public void success() {
				if (validatorChain.isLastNode()) {
					GWT.log("isLastNode()... executando onSuccess() do action do AsyncFormItemValidator, action: " + action.getClass().getName(), null);
					action.onSuccess();
					formItem.setValid(true);
				} else {
					validatorChain.doChain(widget, action);
				}
			}
		});
		formItem.refresh();
	}

	public void setFormItem(FormItem<W> formItem) {
		this.formItem = formItem;
	}

	public void onFailure(Throwable caught) {
	}

}

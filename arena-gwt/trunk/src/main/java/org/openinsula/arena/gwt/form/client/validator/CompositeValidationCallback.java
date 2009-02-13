package org.openinsula.arena.gwt.form.client.validator;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidationCallback implements ValidationCallback {
	
	private final List<ValidationCallback> callbackList = new ArrayList<ValidationCallback>();

	public CompositeValidationCallback() {
	}
	
	public CompositeValidationCallback(final ValidationCallback validationCallback) {
		addCallback(validationCallback);
	}
	
	public CompositeValidationCallback addCallback(final ValidationCallback callback) {
		if (callback != null) {
			callbackList.add(callback);
		}
		return this;
	}
	
	public void onFail(final String message, final Throwable error) {
		for (ValidationCallback callback : callbackList) {
			callback.onFail(message, error);
		}
	}

	public void onSuccess(final String message) {
		for (ValidationCallback callback : callbackList) {
			callback.onSuccess(message);
		}
	}
	
}

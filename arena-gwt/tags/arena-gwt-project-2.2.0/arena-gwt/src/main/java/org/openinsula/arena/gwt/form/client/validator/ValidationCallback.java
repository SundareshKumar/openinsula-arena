package org.openinsula.arena.gwt.form.client.validator;

public interface ValidationCallback {
	
	void onSuccess(String message);
	
	void onFail(String message, Throwable error);

}

package org.openinsula.arena.appengine.gwt.client.forms.validator;

public interface ValidationCallback {
	
	void onSuccess(String message);
	
	void onFail(String message, Throwable error);

}

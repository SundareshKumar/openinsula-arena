package org.openinsula.arena.gwt.spring.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;

public class SimpleTextCallback implements AsyncCallback {
	private final HasText widget;

	public SimpleTextCallback(final HasText hasTextWidget) {
		if (hasTextWidget == null) {
			throw new IllegalArgumentException("Widget required!");
		}

		this.widget = hasTextWidget;
	}

	public void onFailure(final Throwable error) {
		widget.setText(getFailureMessage(error));
	}

	protected String getFailureMessage(final Throwable error) {
		return error.getMessage();
	}

	public void onSuccess(final Object value) {
		widget.setText(getSuccessMessage(value));
	}

	protected String getSuccessMessage(final Object returnedObject) {
		return (returnedObject == null ? "" : returnedObject.toString());
	}

}

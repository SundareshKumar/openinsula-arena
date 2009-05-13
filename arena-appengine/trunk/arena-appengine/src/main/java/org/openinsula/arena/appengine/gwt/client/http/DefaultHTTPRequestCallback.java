package org.openinsula.arena.appengine.gwt.client.http;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class DefaultHTTPRequestCallback extends HTTPRequestCallback {

	private final boolean treatEmptyResponseAsFault;

	private boolean isDone;

	public DefaultHTTPRequestCallback() {
		this(true);
	}

	public DefaultHTTPRequestCallback(final boolean treatEmptyResponseAsFault) {
		this.treatEmptyResponseAsFault = treatEmptyResponseAsFault;
	}

	@Override
	public void onSuccess() {
		if (!responseHasText()) {
			if (treatEmptyResponseAsFault) {
				updateError(new IllegalStateException("Empty response not expected"));
				onError();
			}
		}
		else {
			String json = getFaultMessageResponse();

			if (json != null) {
				isDone = true;
				updateError(new IllegalStateException(json));
				onError();
			}
			else {
				json = getSuccessMessageResponse();

				if (json != null) {
					isDone = true;
					onSuccess(json);
				}
			}
		}

	}

	protected void onSuccess(final String message) {
	}

	protected final boolean isDone() {
		return isDone;
	}

	private String getFaultMessageResponse() {
		try {
			JSONValue json = JSONParser.parse(getResponse().getText());
			return json.isObject().get("fault").isString().stringValue();
		}
		catch (Exception e) {
			return null;
		}
	}

	private String getSuccessMessageResponse() {
		try {
			JSONValue json = JSONParser.parse(getResponse().getText());
			return json.isObject().get("success").isString().stringValue();
		}
		catch (Exception e) {
			return null;
		}
	}

}

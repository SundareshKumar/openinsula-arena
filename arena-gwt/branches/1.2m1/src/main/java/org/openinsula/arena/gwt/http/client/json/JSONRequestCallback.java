package org.openinsula.arena.gwt.http.client.json;

import org.openinsula.arena.gwt.http.client.HttpRequestCallback;

import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public abstract class JSONRequestCallback extends HttpRequestCallback {

	@Override
	protected void onSuccess(final Response response) {
		doWithJSON(JSONParser.parse(response.getText()));
	}

	protected abstract void doWithJSON(JSONValue value);

}

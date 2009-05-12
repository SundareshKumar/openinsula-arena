package org.openinsula.arena.appengine.gwt.client.json;

import org.openinsula.arena.appengine.gwt.client.http.HTTPRequestCallback;

import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * @author Eduardo Rebola
 */
public abstract class JSONCallback extends HTTPRequestCallback {

	@Override
	public final void onSuccess() {
		String responseText = getResponse().getText();

		if (responseText == null || "null".equals(responseText)) {
			onSuccess(JSONNull.getInstance());
		}
		else {
			JSONValue parse = JSONParser.parse(responseText);
			onSuccess(parse);
		}
	}

	public abstract void onSuccess(final JSONValue json);

}

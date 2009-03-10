package org.openinsula.arena.gwt.components.client.util;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * @author Jo�o Galli
 * 
 */
public class JSONObjectBuilder {

	private JSONObject object;

	public JSONObjectBuilder() {
	}

	public JSONObject getJSONObject() {
		if (object == null) {
			object = new JSONObject();
		}
		return object;
	}

	public JSONObjectBuilder put(final String key, final String value) {
		if (value != null) {
			getJSONObject().put(key, new JSONString(value).isString());
		}
		return this;
	}

	public JSONObjectBuilder put(final String key, final Boolean value) {
		if (value != null) {
			getJSONObject().put(key, JSONBoolean.getInstance(value));
		}
		return this;
	}

	public JSONObjectBuilder put(final String key, final Number value) {
		if (value != null) {
			getJSONObject().put(key, new JSONNumber(value.doubleValue()).isNumber());
		}
		return this;
	}

}

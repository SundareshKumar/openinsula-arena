package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;

import org.openinsula.arena.gwt.components.client.util.StringUtils;

public abstract class JsonVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient String _jsonType;

	protected abstract String getJsonPrefix();

	private String getJsonType() {
		if (this._jsonType == null) {
			String jsonType = getJsonPrefix();

			if (!StringUtils.hasText(jsonType)) {
				throw new IllegalArgumentException("jsonType is required!");
			}

			this._jsonType = "{\"" + jsonType + "\":";
		}
		return this._jsonType;
	}

	public final String castJson(final String json) {
		if (json.startsWith(getJsonType())) {
			return json;
		}

		return getJsonType() + json + "}";
	}

	public final String removeJsonCast(final String json) {
		if (json.startsWith(getJsonType())) {
			return json.substring(getJsonType().length(), json.length() - 1);
		}
		return json;
	}

}

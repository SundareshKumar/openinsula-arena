package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;

import org.openinsula.arena.gwt.components.client.util.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public abstract class JsonVO implements Serializable {

	@XStreamOmitField
	private String jsonPrefix;

	public JsonVO(final String jsonType) {
		setJsonType(jsonType);
	}
	
	private void setJsonType(final String jsonType) {
		if (!StringUtils.hasText(jsonType)) {
			throw new IllegalArgumentException("jsonType is required!");
		}
		
		this.jsonPrefix = "{\"" + jsonType + "\":";
	}
	
	public final String castJson(final String json) {
		if (json.startsWith(jsonPrefix)) {
			return json;
		}
		
		return jsonPrefix + json + "}";
	}
	
}

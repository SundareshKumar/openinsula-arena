package org.openinsula.arena.gwt.http.client.json;

import org.openinsula.arena.gwt.http.client.RequestBuilder;
import org.openinsula.arena.gwt.http.client.rest.AbstractRestServiceHandler;

import com.google.gwt.json.client.JSONValue;

public class JSONRestServiceHandler extends AbstractRestServiceHandler {

	public boolean matches(final String url, final Object data) {
		return data instanceof JSONValue;
	}

	@Override
	protected void afterHandle(final RequestBuilder requestBuilder) {
		super.afterHandle(requestBuilder);
		requestBuilder.setHeader("Content-type", "application/json");
	}

}

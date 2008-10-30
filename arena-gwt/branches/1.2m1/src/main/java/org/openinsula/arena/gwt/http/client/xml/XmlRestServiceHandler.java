package org.openinsula.arena.gwt.http.client.xml;

import org.openinsula.arena.gwt.http.client.RequestBuilder;
import org.openinsula.arena.gwt.http.client.rest.AbstractRestServiceHandler;

import com.google.gwt.user.client.Element;
import com.google.gwt.xml.client.Document;

public class XmlRestServiceHandler extends AbstractRestServiceHandler {

	public boolean matches(final String url, final Object data) {
		return data instanceof Document || data instanceof Element;
	}

	@Override
	protected void afterHandle(final RequestBuilder requestBuilder) {
		super.afterHandle(requestBuilder);
		requestBuilder.setHeader("Content-Type", "application/xml");
	}

}

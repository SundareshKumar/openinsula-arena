package org.openinsula.arena.gwt.client.rest.xml;

import org.openinsula.arena.gwt.client.rest.HttpRequestCallback;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 */
public abstract class XmlRequestCallback extends HttpRequestCallback {

	protected abstract void onXmlParsed(Document document);

	public void onResponseReturned(Response response) {
		final String text = response.getText();
		final Document document = XMLParser.parse(text);

		onXmlParsed(document);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.HttpResponseListener#onResponseError
	 * (com.google.gwt.http.client.Response)
	 */
	public void onResponseError(Response response) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.http.client.RequestCallback#onError(com.google.gwt.http
	 * .client.Request, java.lang.Throwable)
	 */
	public void onError(Request request, Throwable exception) {
	}

}

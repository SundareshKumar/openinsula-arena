package org.openinsula.arena.gwt.http.client.xml;

import org.openinsula.arena.gwt.http.client.HttpRequestCallback;

import com.google.gwt.http.client.Response;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public abstract class XmlRequestCallback extends HttpRequestCallback {

	protected final void onSuccess(final Response response) {
		doWithXml(XMLParser.parse(response.getText()));
	}

	protected abstract void doWithXml(Document document);

}

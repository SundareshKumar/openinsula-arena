package org.openinsula.arena.gwt.client.rest.xml;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * @author Lucas K Mogari
 */
public abstract class XmlUtils {

	public static Element createTextElement(Document document, String name, String value) {
		final Element element = document.createElement(name);

		element.appendChild(document.createTextNode(value));

		return element;
	}

}

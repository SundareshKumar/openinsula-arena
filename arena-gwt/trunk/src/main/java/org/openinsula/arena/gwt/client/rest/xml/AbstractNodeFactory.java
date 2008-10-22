package org.openinsula.arena.gwt.client.rest.xml;

import org.openinsula.arena.gwt.client.rest.xml.atom.Text;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractNodeFactory implements NodeFactory {

	private Document document;

	protected Element createElement(String tagName) {
		return document.createElement(tagName);
	}

	protected Element createTextElement(String tagName, String value) {
		final Element element = document.createElement(tagName);

		element.appendChild(document.createTextNode(value));
		return element;
	}

	protected Element createTextElement(String tagName, Text text) {
		final Element textElement = createTextElement(tagName, text.getValue());

		if (text.getType() != null) {
			textElement.setAttribute("type", text.getType());
		}
		return textElement;
	}

	protected Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}

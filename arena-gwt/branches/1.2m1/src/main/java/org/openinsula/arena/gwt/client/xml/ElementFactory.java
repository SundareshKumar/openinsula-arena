package org.openinsula.arena.gwt.client.xml;

import org.openinsula.arena.gwt.client.rest.xml.atom.Text;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * @author Lucas K Mogari
 */
public class ElementFactory implements NodeFactory {

	private Document document;

	public ElementFactory() {
	}

	public ElementFactory(Document document) {
		this.document = document;
	}

	public Element createElement(String tagName) {
		return document.createElement(tagName);
	}

	public Element createTextElement(String tagName, String value) {
		final Element element = document.createElement(tagName);

		element.appendChild(document.createTextNode(value));
		return element;
	}

	public Element createTextElement(String tagName, Text text) {
		final Element textElement = createTextElement(tagName, text.getValue());

		if (text.getType() != null) {
			textElement.setAttribute("type", text.getType());
		}
		return textElement;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}

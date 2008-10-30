package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.CDATASection;
import com.google.gwt.xml.client.Comment;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * @author Lucas K Mogari
 */
public class NodeFactorySupport {

	private Document document;

	public NodeFactorySupport() {
	}

	public NodeFactorySupport(Document document) {
		this.document = document;
	}

	protected Element createElement(String tagName) {
		return document.createElement(tagName);
	}

	protected Element createTextElement(String tagName, String value) {
		final Element element = document.createElement(tagName);

		element.appendChild(document.createTextNode(value));
		return element;
	}

	protected Comment createComment(String comment) {
		return document.createComment(comment);
	}

	protected CDATASection createCDATASection(String CDATASection) {
		return document.createCDATASection(CDATASection);
	}

	Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}

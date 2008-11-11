package org.openinsula.arena.gwt.serialization.client.xml;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class XMLSerializerImpl implements XMLSerializer {

	private static Map<String, XMLSerializerImpl> SERIALIZABLE_TYPES;

	private static Map<String, XMLSerializerImpl> serializableTypes() {
		if (SERIALIZABLE_TYPES == null) {
			SERIALIZABLE_TYPES = new HashMap<String, XMLSerializerImpl>();
		}
		return SERIALIZABLE_TYPES;
	}

	protected void addType(final String name, final XMLSerializerImpl obj) {
		serializableTypes().put(name, obj);
	}

	protected XMLSerializerImpl getType(final String name) {
		return serializableTypes().get(name);
	}

	protected XMLSerializerImpl() {
	}

	public static String getTypeName(final XMLSerializable obj) {
		String typeName = obj.getClass().getName();
		char[] simpleName = typeName.substring(typeName.lastIndexOf('.') + 1).toCharArray();
		simpleName[0] = Character.toLowerCase(simpleName[0]);

		return String.valueOf(simpleName);
	}

	public final String toXML(final XMLSerializable obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Cannot serialize null objects!");
		}

		// String typeName = getTypeName(obj);
		Document document = XMLParser.createDocument();
		// Element documentElement = document.createElement(typeName);
		// document.appendChild(documentElement);

		return toXML(document, null, obj);
	}

	public String toXML(final Document document, final Element documentElement, final XMLSerializable obj) {
		if (obj != null) {
			String typeName = getTypeName(obj);
			getType(typeName).toXML(document, documentElement, obj);
		}
		return document.toString();
	}

	public final XMLSerializable fromXML(final String xml) {
		if (xml == null) {
			throw new IllegalArgumentException("XML cannot be null!");
		}

		return fromXML(XMLParser.parse(xml).getDocumentElement());
	}

	public XMLSerializable fromXML(final Element xml) {
		return getType(xml.getNodeName()).fromXML(xml);
	}

	public XMLSerializable fromXML(final String typeName, final Element xml) {
		return getType(typeName).fromXML(xml);
	}

	protected void writeSimpleAttribute(final String tagName, final Object value, final Element parent,
			final Document document) {

		if (value != null) {
			Element child = document.createElement(tagName);
			parent.appendChild(child);
			child.appendChild(document.createTextNode(String.valueOf(value)));
		}
	}

}

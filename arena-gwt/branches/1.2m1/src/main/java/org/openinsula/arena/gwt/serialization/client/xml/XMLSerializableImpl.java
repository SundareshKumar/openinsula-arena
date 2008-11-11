package org.openinsula.arena.gwt.serialization.client.xml;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

public interface XMLSerializableImpl {

	void toXML(Document document, Element element, XMLSerializable obj);

	XMLSerializable fromXML(Element element);
}

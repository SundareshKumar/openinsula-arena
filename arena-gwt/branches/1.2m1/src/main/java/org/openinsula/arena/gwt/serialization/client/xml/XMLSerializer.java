package org.openinsula.arena.gwt.serialization.client.xml;

public interface XMLSerializer {

	String toXML(XMLSerializable obj);

	public XMLSerializable fromXML(String xml);

}

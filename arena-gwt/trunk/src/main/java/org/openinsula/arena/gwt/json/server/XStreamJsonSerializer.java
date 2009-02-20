package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.openinsula.arena.gwt.json.client.JsonRemoteSerializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamJsonSerializer implements JsonRemoteSerializer {

	private final XStream xstream;

	private final Set<Class<? extends Serializable>> processedTypesCache;

	public XStreamJsonSerializer() {
		xstream = new XStream(new JettisonMappedXmlDriver());
		processedTypesCache = new HashSet<Class<? extends Serializable>>();
	}

	private Class<? extends Serializable> registerTypeIfNecessary(final Serializable template) {
		Class<? extends Serializable> typeClass = template.getClass();

		if (processedTypesCache.add(typeClass)) {
			xstream.processAnnotations(typeClass);
		}

		return typeClass;
	}

	public String toJson(final Serializable jsonObject) {
		if (jsonObject == null) {
			return null;
		}

		registerTypeIfNecessary(jsonObject);

		return xstream.toXML(jsonObject);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T fromJson(final String json, final T template) {
		registerTypeIfNecessary(template);
		return (T) xstream.fromXML(json);
	}

}

package org.openinsula.arena.appengine.gwt.server.json;

import java.io.Serializable;

import org.openinsula.arena.appengine.gwt.client.json.JsonListWrapper;
import org.openinsula.arena.appengine.gwt.client.json.JsonRemoteSerializer;
import org.openinsula.arena.appengine.gwt.client.json.VoFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamJsonSerializer implements JsonRemoteSerializer {

	private final XStream xstream;

	public XStreamJsonSerializer() {
		this(null);
	}

	public XStreamJsonSerializer(final VoFactory factory) {
		xstream = new XStream(new JettisonMappedXmlDriver());
		registerBundledTypes();

		if (factory != null) {
			xstream.processAnnotations(factory.getTypes());
		}
	}

	public String toJson(final Serializable jsonObject) {
		if (jsonObject == null) {
			return null;
		}

		String castedJson = xstream.toXML(jsonObject);

		String result = removeJsonCast(getAliasFor(jsonObject), castedJson);

		return result;
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T fromJson(final String json, final T template) {
		String finalJson = castJson(getAliasFor(template), json);

		Object obj = xstream.fromXML(finalJson);

		return (T) obj;
	}

	private void registerBundledTypes() {
		xstream.processAnnotations(JsonListWrapper.class);
	}

	private String getAliasFor(final Serializable jsonObject) {
		return xstream.getMapper().serializedClass(jsonObject.getClass());
	}

	String castJson(final String jsonAlias, final String jsonString) {
		String prefix = String.format("{\"%s\":", jsonAlias);

		if (jsonString.startsWith(prefix)) {
			return jsonString;
		}

		return String.format("%s%s}", prefix, jsonString);
	}

	String removeJsonCast(final String jsonAlias, final String jsonString) {
		String prefix = String.format("{\"%s\":", jsonAlias);

		if (jsonString.startsWith(prefix)) {
			return jsonString.substring(prefix.length(), jsonString.length() - 1);
		}

		return jsonString;
	}

}

package org.openinsula.arena.appengine.gwt.server.json;

import java.io.Serializable;
import java.nio.charset.Charset;

import org.openinsula.arena.appengine.gwt.client.json.JsonListWrapper;
import org.openinsula.arena.appengine.gwt.client.json.JsonRemoteSerializer;
import org.openinsula.arena.appengine.gwt.client.json.VoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamJsonSerializer implements JsonRemoteSerializer {

	private final XStream xstream;

	private final Logger logger = LoggerFactory.getLogger(getClass());

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

		if (logger.isDebugEnabled()) {
			logger.debug("original JSON OUTPUT: {}", result);
			logger.debug("UTF-8 JSON OUTPUT: {}", assertUnicode(result));
		}

		return assertUnicode(result);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T fromJson(final String json, final T template) {
		String finalJson = castJson(getAliasFor(template), json);

		if (logger.isDebugEnabled()) {
			logger.debug("original JSON INPUT: {}", finalJson);
			logger.debug("UTF-8 JSON INPUT: {}", assertUnicode(finalJson));
		}

		//		Object obj = xstream.fromXML(assertUnicode(finalJson));
		Object obj = xstream.fromXML(finalJson);

		return (T) obj;
	}

	private void registerBundledTypes() {
		xstream.processAnnotations(JsonListWrapper.class);
	}

	private String getAliasFor(final Serializable jsonObject) {
		return xstream.getMapper().serializedClass(jsonObject.getClass());
	}

	private String assertUnicode(final String value) {
		return new String(value.getBytes(Charset.forName("UTF-8")));
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

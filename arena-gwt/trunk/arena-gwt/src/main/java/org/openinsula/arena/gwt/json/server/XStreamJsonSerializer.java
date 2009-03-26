package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;

import org.openinsula.arena.gwt.json.client.JsonListWrapper;
import org.openinsula.arena.gwt.json.client.JsonRemoteSerializer;
import org.openinsula.arena.gwt.json.client.VoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamJsonSerializer implements JsonRemoteSerializer {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final XStream xstream;

	public XStreamJsonSerializer() {
		this(null);
	}
	
	public XStreamJsonSerializer(final VoFactory factory) {
		xstream = new XStream(new JettisonMappedXmlDriver());
		registerBundledTypes();
		
		if (factory != null) {
			xstream.processAnnotations(factory.getTypes());
		} else if (logger.isWarnEnabled()) {
			logger.warn("VoFactory is null, so it will be ignored");
		}
	}

	public String toJson(final Serializable jsonObject) {
		if (jsonObject == null) {
			logger.warn("jsonObject was null. Serialization skipped.");
			return null;
		}
		
		String castedJson = xstream.toXML(jsonObject);
		logger.debug("castedJson= {}", castedJson);
		
		String result = removeJsonCast(getAliasFor(jsonObject), castedJson);
		logger.debug("result= {}", result);
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T fromJson(final String json, final T template) {
		String finalJson = castJson(getAliasFor(template), json);

		Object obj = xstream.fromXML(finalJson);

		if (obj instanceof JsonListWrapper) {
			JsonListWrapper<T> jsonList = (JsonListWrapper<T>) obj;

			T wrappedTemplate = ((JsonListWrapper<T>) template).getTemplate();
			jsonList.setTemplate(wrappedTemplate);
		}

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

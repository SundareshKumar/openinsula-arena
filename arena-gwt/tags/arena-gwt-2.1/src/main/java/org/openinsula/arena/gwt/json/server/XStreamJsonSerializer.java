package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.json.client.HasJsonTypes;
import org.openinsula.arena.gwt.json.client.JsonListWrapper;
import org.openinsula.arena.gwt.json.client.JsonRemoteSerializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamJsonSerializer implements JsonRemoteSerializer {

	private final XStream xstream;

	private final Map<Class<?>, String> processedTypesCacheMap;

	public XStreamJsonSerializer() {
		xstream = new XStream(new JettisonMappedXmlDriver());
		processedTypesCacheMap = new HashMap<Class<?>, String>();

		registerBundledTypes();
	}

	private void registerBundledTypes() {
		registerTypeIfNecessary(new JsonListWrapper<Serializable>());
	}

	/**
	 * @return JSON type prefix (e.g: {"person":)
	 */
	private String registerTypeIfNecessary(final Object template) {
		return registerTypeIfNecessary(template.getClass());
	}
	
	private String registerTypeIfNecessary(final Class<?> templateType) {
		if (!processedTypesCacheMap.containsKey(templateType)) {
			xstream.processAnnotations(templateType);
			String jsonTypeAlias = xstream.getMapper().serializedClass(templateType);
			processedTypesCacheMap.put(templateType, "{\"" + jsonTypeAlias + "\":");
		}

		return processedTypesCacheMap.get(templateType);
	}

	public String toJson(final Serializable jsonObject) {
		if (jsonObject == null) {
			return null;
		}

		String jsonTypePrefix = registerTypeIfNecessary(jsonObject);
		String castedJson = xstream.toXML(jsonObject);

		return removeJsonCast(jsonTypePrefix, castedJson);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T fromJson(final String json, final T template) {
		String jsonType = registerTypeIfNecessary(template);

		String finalJson = castJson(jsonType, json);

		if (template instanceof HasJsonTypes) {
			HasJsonTypes hasJonTypes = (HasJsonTypes) template;
			
			for (Class<?> t : hasJonTypes.getTypes()) {
				registerTypeIfNecessary(t);
			}
		}
		
		if (template instanceof JsonListWrapper) {
			JsonListWrapper<T> jsonList = (JsonListWrapper<T>) template;
			registerTypeIfNecessary(jsonList.getTemplate());
		}

		Object obj = xstream.fromXML(finalJson);

		if (obj instanceof JsonListWrapper) {
			JsonListWrapper<T> jsonList = (JsonListWrapper<T>) obj;

			T wrappedTemplate = ((JsonListWrapper<T>) template).getTemplate();
			jsonList.setTemplate(wrappedTemplate);
		}

		return (T) obj;
	}

	String castJson(final String jsonTypePrefix, final String jsonString) {
		if (jsonString.startsWith(jsonTypePrefix)) {
			return jsonString;
		}

		return jsonTypePrefix + jsonString + "}";
	}

	String removeJsonCast(final String jsonTypePrefix, final String jsonString) {
		if (jsonString.startsWith(jsonTypePrefix)) {
			return jsonString.substring(jsonTypePrefix.length(), jsonString.length() - 1);
		}
		return jsonString;
	}

}

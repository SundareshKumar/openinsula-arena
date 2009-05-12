package org.openinsula.arena.appengine.gwt.client.http;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.http.client.URL;

public class RequestDataBuilder {

	private final StringBuilder sb = new StringBuilder();
	
	// TODO test
	public String buildRequestData(final Map<String, Object> requestDataMap) {
		if (requestDataMap == null || requestDataMap.isEmpty()) {
			return null;
		}

		for (Entry<String, Object> entry : requestDataMap.entrySet()) {
			addParameter(entry.getKey(), entry.getValue());
		}
		
		String result = sb.toString();
		
		if (result.charAt(0) == '&') {
			result = result.substring(1);
		}
		
		return result;
	}
	
	/**
	 * Parameters must not be null
	 */
	private void addParameter(final String key, final Object value) {
		if (value instanceof Collection) {
			sb.append('&').append(key).append('=');
			
			Collection<?> c = (Collection<?>) value;
			
			for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();
				sb.append(URL.encodeComponent(String.valueOf(obj)));
				
				if (iterator.hasNext()) {
					sb.append(',');
				}
			}
			
		} else if (value.getClass().isArray()) {
			sb.append('&').append(key).append('=');
			
			Object[] c = (Object[]) value;
			sb.append(URL.encodeComponent(String.valueOf(c[0])));

			for (int i = 1; i < c.length; i++) {
				sb.append(',');
				sb.append(URL.encodeComponent(String.valueOf(c[i])));
			}
		} else if (value != null) {
			sb.append('&').append(key).append('=').append(URL.encodeComponent(String.valueOf(value)));
		}
		
	}
	
}

package org.openinsula.arena.gwt.components.client.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openinsula.arena.gwt.components.client.util.ObjectUtils;

/**
 * Simple {@link java.beans.PropertyChangeSupport} implementation for GWT.
 * 
 * @author Eduardo Rebola
 * 
 */
public class PropertyChangeSupport {

	private final LazyProperty<Map<String, Object>> properties;

	private final LazyProperty<Map<String, PropertyChangeCallback<?>>> callbackMap;

	public PropertyChangeSupport() {
		properties = new LazyProperty<Map<String, Object>>() {
			protected Map<String, Object> createProperty() {
				return new HashMap<String, Object>();
			}
		};

		callbackMap = new LazyProperty<Map<String, PropertyChangeCallback<?>>>() {
			protected Map<String, PropertyChangeCallback<?>> createProperty() {
				return new HashMap<String, PropertyChangeCallback<?>>();
			}
		};
	}

	public void addPropertyChangeCallback(final String property, final PropertyChangeCallback<?> callback) {
		if (callback != null) {
			callbackMap.get().put(property, callback);
		}
		else {
			Map<String, PropertyChangeCallback<?>> map = callbackMap.get(false);
			if (map != null && map.containsKey(property)) {
				map.remove(property);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public boolean changeProperty(final String name, final Object value) {
		Object oldValue = getProperty(name);
		PropertyChangeCallback<Object> callback = (PropertyChangeCallback<Object>) getCallback(name);

		if (!ObjectUtils.nullSafeEquals(oldValue, value)) {
			if (value == null) {
				return removeProperty(name);
			}
			
			properties.get().put(name, value);

			if (callback != null) {
				callback.onChange(oldValue, value);
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean removeProperty(final String name) {
		Map<String, Object> map = properties.get(false);
		
		if (map == null) {
			return false;
		}

		Object oldValue = map.remove(name);

		if (oldValue != null) {
			PropertyChangeCallback<Object> callback = (PropertyChangeCallback<Object>) getCallback(name);

			if (callback != null) {
				callback.onChange(oldValue, null);
			}
			return true;
		}
		return false;
	}

	public <T> T getProperty(final String name) {
		return nullSafeGet(properties, name);
	}

	public PropertyChangeCallback<?> getCallback(final String property) {
		return nullSafeGet(callbackMap, property);
	}

	@SuppressWarnings("unchecked")
	public void broadcast() {
		Map<String, PropertyChangeCallback<?>> map = callbackMap.get(false);
		
		if (map != null) {
			for (Entry<String, PropertyChangeCallback<?>> entry : map.entrySet()) {
				PropertyChangeCallback<Object> callback = (PropertyChangeCallback<Object>) entry.getValue();
				Object value = getProperty(entry.getKey());
				callback.onChange(null, value);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T nullSafeGet(final LazyProperty<? extends Map> lazyMap, final Object key) {
		if (lazyMap.get(false) == null) {
			return null;
		}

		return (T) lazyMap.get(false).get(key);
	}

}

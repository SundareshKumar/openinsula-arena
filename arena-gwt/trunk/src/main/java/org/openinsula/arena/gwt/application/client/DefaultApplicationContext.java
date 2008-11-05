package org.openinsula.arena.gwt.application.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lucas K Mogari
 */
class DefaultApplicationContext implements ApplicationContext {

	private final Map<String, Object> attributes = new HashMap<String, Object>();

	private final List<ContextAttributeListener> listeners = new LinkedList<ContextAttributeListener>();

	public void setAttribute(String name, Object attribute) {
		final List<ContextAttributeListener> listeners = new LinkedList<ContextAttributeListener>(this.listeners);

		if (attribute == null) {
			attributes.remove(name);

			for (final ContextAttributeListener listener : listeners) {
				listener.attributeRemoved(name, attribute);
			}
		}
		else {
			final Object oldAttribute = attributes.get(name);

			if (oldAttribute == null) {
				attributes.put(name, attribute);

				for (final ContextAttributeListener listener : listeners) {
					listener.attributeAdded(name, attribute);
				}
			}
			else if (!oldAttribute.equals(attribute)) {
				attributes.put(name, attribute);

				for (final ContextAttributeListener listener : listeners) {
					listener.attributeReplaced(name, oldAttribute, attribute);
				}
			}
		}
	}

	public void addContextAttributeListener(ContextAttributeListener listener) {
		listeners.add(listener);
	}

	public void removeContextAttributeListener(ContextAttributeListener listener) {
		listeners.remove(listener);
	}

	public String getStringAttribute(String name) {
		final Object attribute = getAttribute(name);
		return attribute instanceof String ? (String) attribute : null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T) attributes.get(name);
	}

	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

}

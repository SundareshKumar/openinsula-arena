package org.openinsula.arena.gwt.application.client.context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lucas K Mogari
 */
public class DefaultApplicationContext implements ApplicationContext {

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

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T) attributes.get(name);
	}

	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	public <T> void loadAttribute(String name, AttributeLoadingNotifier<T> attributeLoadNotifier) {
		final T attribute = this.<T> getAttribute(name);

		if (attribute == null) {
			addContextAttributeListener(new SettingApplicationAttributeListener<T>(name, attributeLoadNotifier));
		}
		else {
			attributeLoadNotifier.notifyAttributeLoaded(attribute);
		}
	}

	public String getStringAttribute(String name) {
		final Object attribute = getAttribute(name);
		return attribute instanceof String ? (String) attribute : null;
	}

	private final class SettingApplicationAttributeListener<T> extends ContextAttributeAdapter {

		private final String attributeName;

		private final AttributeLoadingNotifier<T> attributeLoadNotifier;

		public SettingApplicationAttributeListener(String attributeName,
				AttributeLoadingNotifier<T> attributeLoadNotifier) {
			this.attributeName = attributeName;
			this.attributeLoadNotifier = attributeLoadNotifier;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void attributeAdded(String name, Object attribute) {
			if (attributeName.equals(name)) {
				removeContextAttributeListener(this);

				attributeLoadNotifier.notifyAttributeLoaded((T) attribute);
			}
		}

	}

}

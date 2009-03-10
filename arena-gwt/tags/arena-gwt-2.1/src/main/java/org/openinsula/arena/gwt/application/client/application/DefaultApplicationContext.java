package org.openinsula.arena.gwt.application.client.application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openinsula.arena.gwt.components.client.ui.utils.EqualsUtils;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

/**
 * @author Lucas K Mogari
 */
public class DefaultApplicationContext implements ApplicationContext {

	private final Map<String, Object> attributes = new HashMap<String, Object>();

	private final List<ApplicationAttributeListener> listeners = new LinkedList<ApplicationAttributeListener>();

	public void setAttribute(final String name, final Object attribute) {
		final List<ApplicationAttributeListener> listeners = new LinkedList<ApplicationAttributeListener>(
				this.listeners);

		if (attribute == null) {
			attributes.remove(name);

			DeferredCommand.addCommand(new Command() {
				public void execute() {
					for (final ApplicationAttributeListener listener : listeners) {
						listener.attributeRemoved(name, attribute);
					}
				}
			});
		}
		else {
			final Object oldAttribute = attributes.get(name);

			if (oldAttribute == null) {
				attributes.put(name, attribute);

				DeferredCommand.addCommand(new Command() {
					public void execute() {
						for (final ApplicationAttributeListener listener : listeners) {
							listener.attributeAdded(name, attribute);
						}
					}
				});
			}
			else if (EqualsUtils.isDifferent(oldAttribute, attribute)) {
				attributes.put(name, attribute);

				DeferredCommand.addCommand(new Command() {
					public void execute() {
						for (final ApplicationAttributeListener listener : listeners) {
							listener.attributeReplaced(name, oldAttribute, attribute);
						}
					}
				});
			}
		}
	}

	public void addApplicationAttributeListener(ApplicationAttributeListener listener) {
		listeners.add(listener);
	}

	public void removeApplicationAttributeListener(ApplicationAttributeListener listener) {
		listeners.remove(listener);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T) attributes.get(name);
	}

	public Set<String> getAttributeNames() {
		return attributes.keySet();
	}

	public <T> void loadAttribute(String attributeName, AttributeLoadNotifier<T> attributeLoadNotifier) {
		final ApplicationContext applicationContext = Application.getInstance().getContext();
		final T attribute = applicationContext.<T> getAttribute(attributeName);

		if (attribute == null) {
			applicationContext.addApplicationAttributeListener(new SettingApplicationAttributeListener<T>(
					attributeName, attributeLoadNotifier));
		}
		else {
			attributeLoadNotifier.notifyAttributeLoaded(attribute);
		}
	}

	private final class SettingApplicationAttributeListener<T> extends ApplicationAttributeAdapter {

		private final String attributeName;

		private final AttributeLoadNotifier<T> attributeLoadNotifier;

		public SettingApplicationAttributeListener(String attributeName, AttributeLoadNotifier<T> attributeLoadNotifier) {
			this.attributeName = attributeName;
			this.attributeLoadNotifier = attributeLoadNotifier;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void attributeAdded(String name, Object attribute) {
			if (attributeName.equals(name)) {
				Application.getInstance().getContext().removeApplicationAttributeListener(this);

				attributeLoadNotifier.notifyAttributeLoaded((T) attribute);
			}
		}

	}

}

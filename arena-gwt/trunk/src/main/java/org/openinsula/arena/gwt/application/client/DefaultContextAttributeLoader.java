package org.openinsula.arena.gwt.application.client;

/**
 * @author Lucas K Mogari
 */
class DefaultContextAttributeLoader implements ContextAttributeLoader {

	public <T> void loadAttribute(String attributeName, AttributeLoadingNotifier<T> attributeLoadNotifier) {
		final ApplicationContext applicationContext = Application.getInstance().getContext();
		final T attribute = applicationContext.<T> getAttribute(attributeName);

		if (attribute == null) {
			applicationContext.addContextAttributeListener(new SettingApplicationAttributeListener<T>(attributeName,
					attributeLoadNotifier));
		}
		else {
			attributeLoadNotifier.notifyAttributeLoaded(attribute);
		}
	}

	private final class SettingApplicationAttributeListener<T> extends NullContextAttributeListener {

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
				Application.getInstance().getContext().removeContextAttributeListener(this);

				attributeLoadNotifier.notifyAttributeLoaded((T) attribute);
			}
		}

	}

}

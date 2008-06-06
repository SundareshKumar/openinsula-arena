package org.openinsula.arena.gwt.client.remote;

public abstract class LookupAction {
	final Class<?> serviceClass;

	/**
	 * @param serviceClass AsyncService class
	 */
	public LookupAction(final Class<?> serviceClass) {
		assert serviceClass != null;
		this.serviceClass = serviceClass;
	}

	public final Object lookup() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Object service = serviceLocator.serviceCache.get(serviceClass);

		if (service == null) {
			service = createService();
			serviceLocator.serviceCache.put(serviceClass, service);
		}

		return service;
	}

	protected abstract Object createService();

	public int hashCode() {
		return serviceClass.hashCode();
	}

	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		return serviceClass == ((LookupAction) obj).serviceClass;
	}
}

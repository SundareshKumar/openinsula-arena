package org.openinsula.arena.gwt.client.remote;

import java.util.HashMap;
import java.util.Map;

public final class ServiceLocator {
	private static ServiceLocator instance;

	final Map<Class<?>, Object> serviceCache;

	final Map<Class<?>, LookupAction> lookupMap;

	private ServiceLocator() {
		serviceCache = new HashMap<Class<?>, Object>();
		lookupMap = new HashMap<Class<?>, LookupAction>();
	}

	public static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}

		return instance;
	}

	public void addService(final LookupAction... serviceLookupAction) {
		if (serviceLookupAction != null) {
			for (LookupAction lookupAction : serviceLookupAction) {
				lookupMap.put(lookupAction.serviceClass, lookupAction);
			}
		}
	}

	public <T> T getService(final Class<T> asyncServiceClass) {
		LookupAction lookupAction = lookupMap.get(asyncServiceClass);

		if (lookupAction == null) {
			throw new IllegalArgumentException("No service found for " + asyncServiceClass.getName());
		}

		return (T) lookupAction.lookup();
	}

}

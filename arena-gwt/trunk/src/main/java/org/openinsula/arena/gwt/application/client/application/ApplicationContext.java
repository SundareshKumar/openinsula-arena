package org.openinsula.arena.gwt.application.client.application;

import java.util.Set;

/**
 * @author Lucas K Mogari
 */
public interface ApplicationContext {

	public void setAttribute(String name, Object attribute);

	public <T> T getAttribute(String name);

	public Set<String> getAttributeNames();

	public void addApplicationAttributeListener(ApplicationAttributeListener listener);

	public void removeApplicationAttributeListener(ApplicationAttributeListener listener);

	public <T> void loadAttribute(String attributeName, AttributeLoadNotifier<T> attributeLoadNotifier);

}

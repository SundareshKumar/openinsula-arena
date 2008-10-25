package org.openinsula.arena.gwt.client.application;

/**
 * @author Lucas K Mogari
 */
public interface ContextAttributeLoader {

	public <T> void loadAttribute(String attributeName, AttributeLoadingNotifier<T> attributeLoadNotifier);

}

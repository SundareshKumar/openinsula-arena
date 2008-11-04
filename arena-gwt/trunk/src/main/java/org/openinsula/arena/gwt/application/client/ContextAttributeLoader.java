package org.openinsula.arena.gwt.application.client;

/**
 * @author Lucas K Mogari
 */
public interface ContextAttributeLoader {

	public <T> void loadAttribute(String attributeName, AttributeLoadingNotifier<T> attributeLoadNotifier);

}

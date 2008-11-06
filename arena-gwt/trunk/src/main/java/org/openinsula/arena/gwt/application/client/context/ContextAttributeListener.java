package org.openinsula.arena.gwt.application.client.context;

/**
 * @author Lucas K Mogari
 */
public interface ContextAttributeListener {

	public void attributeAdded(String name, Object attribute);

	public void attributeRemoved(String name, Object oldAttribute);

	public void attributeReplaced(String name, Object oldAttribute, Object newAttribute);

}
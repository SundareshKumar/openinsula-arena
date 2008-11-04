package org.openinsula.arena.gwt.application.client;

import java.util.Set;

/**
 * @author Lucas K Mogari
 */
public interface ApplicationContext {

	public void setAttribute(String name, Object attribute);

	public <T> T getAttribute(String name);

	public Set<String> getAttributeNames();

	public void addContextAttributeListener(ContextAttributeListener listener);

	public void removeContextAttributeListener(ContextAttributeListener listener);

}

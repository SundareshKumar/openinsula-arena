package org.openinsula.arena.gwt.components.client.util.value;

/**
 * @author Lucas K Mogari
 */
public interface ValueHandler<T> {

	public void setValue(T obj, Object value);

	public <V> V getValue(T obj);

}

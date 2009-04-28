package org.openinsula.arena.appengine.gwt.client.beans;


/**
 * @author Eduardo Rebola
 *
 * @param <T> Wrapped Property type. Generally heavyweight objects that demands a 
 * carefully lifecycle control.
 */
public abstract class LazyProperty<T> {

	protected T property;

	protected abstract T createProperty();

	/**
	 * What to do just BEFORE setting <code>null</code> on property.
	 * @param property Wrapped lazy property about to be removed.
	 */
	protected void beforeRemove(final T property) {
		
	}
	
	/**
	 * Create if necessary
	 * @return
	 */
	public final T get() {
		return get(true);
	}
	
	public T get(final boolean create) {
		if (property == null && create) {
			property = createProperty();
		}
		return property;
	}
	
	public final void remove() {
		if (property != null) {
			beforeRemove(property);
		}
		property = null;
	}
	
}

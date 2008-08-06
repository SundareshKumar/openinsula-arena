package org.openinsula.arena.gwt.client.beans;

import java.util.EventObject;

/**
 * Based on Sun implementation.
 *
 * @author Eduardo Rebola
 * @see java.beans.PropertyChangeEvent
 */
public class PropertyChangeEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the property that changed. May be null, if not known.
	 */
	public final String propertyName;

	/**
	 * New value for property. May be null if not known.
	 */
	public final Object newValue;

	/**
	 * Previous value for property. May be null if not known.
	 */
	public final Object oldValue;

	/**
	 * Constructs a new <code>PropertyChangeEvent</code>.
	 *
	 * @param source
	 *            The bean that fired the event.
	 * @param propertyName
	 *            The programmatic name of the property that was changed.
	 * @param oldValue
	 *            The old value of the property.
	 * @param newValue
	 *            The new value of the property.
	 */
	public PropertyChangeEvent(final Object source, final String propertyName, final Object oldValue, final Object newValue) {
		super(source);
		this.propertyName = propertyName;
		this.newValue = newValue;
		this.oldValue = oldValue;
	}

}

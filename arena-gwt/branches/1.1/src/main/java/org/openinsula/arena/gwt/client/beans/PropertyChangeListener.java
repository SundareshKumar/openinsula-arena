package org.openinsula.arena.gwt.client.beans;

import java.util.EventListener;

/**
 * A "PropertyChange" event gets fired whenever a bean changes a "bound"
 * property. You can register a PropertyChangeListener with a source bean so as
 * to be notified of any bound property updates.
 *
 * <p>
 * Based on Sun's implementation
 *
 * @author Eduardo Rebola
 * @see java.beans.PropertyChangeListener
 */
public interface PropertyChangeListener extends EventListener {

	/**
	 * This method gets called when a bound property is changed.
	 *
	 * @param evt
	 *            A PropertyChangeEvent object describing the event source and
	 *            the property that has changed.
	 */

	void propertyChange(PropertyChangeEvent evt);

}
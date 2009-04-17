package org.openinsula.arena.gwt.client.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Based on Sun's implementation.
 *
 * @author Eduardo Rebola
 * @see java.beans.PropertyChangeSupport
 */
public class PropertyChangeSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	public final Object source;

	private final Set<PropertyChangeListener> listeners = new HashSet<PropertyChangeListener>();
	private final Map<String, Set<PropertyChangeListener>> propertyListeners = new HashMap<String, Set<PropertyChangeListener>>();

	public PropertyChangeSupport(final Object sourceBean) {
		if (sourceBean == null) {
			throw new NullPointerException();
		}
		source = sourceBean;
	}

	public void addPropertyChangeListener(final PropertyChangeListener ... listener) {
		addPropertyChangeListener(null, listener);
	}

	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener ... listener) {
		atLeastOneListener(listener);

		Set<PropertyChangeListener> listeners = propertyName == null ? this.listeners : nullSafeGetPropertyChangeListeners(propertyName);

		for (PropertyChangeListener propertyChangeListener : listener) {
			if (!listeners.contains(listener)) {
				listeners.add(propertyChangeListener);
			}
		}
	}

	public void removeAllPropertyChangeListeners() {
		listeners.clear();
		propertyListeners.clear();
	}

	public void removePropertyChangeListener(final PropertyChangeListener... listener) {
		removePropertyChangeListener(null, listener);
	}

	public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener... listener) {
		atLeastOneListener(listener);

		Set<PropertyChangeListener> listeners = propertyName == null ? this.listeners : propertyListeners.get(propertyName);

		if (listeners != null) {
			for (PropertyChangeListener propertyChangeListener : listener) {
				listeners.remove(propertyChangeListener);
			}
		}
	}

	public Set<PropertyChangeListener> getAllPropertyChangeListeners() {
		Set<PropertyChangeListener> returnList = new HashSet<PropertyChangeListener>();
		returnList.addAll(listeners);

		for (Set<PropertyChangeListener> propertyChangeListeners : propertyListeners.values()) {
			returnList.addAll(propertyChangeListeners);
		}

		return returnList;
	}

	public Set<PropertyChangeListener> getPropertyChangeListeners() {
		return getPropertyChangeListeners(null);
	}

	public Set<PropertyChangeListener> getPropertyChangeListeners(final String propertyName) {
		if (propertyName == null) {
			return listeners;
		}

		return nullSafeGetPropertyChangeListeners(propertyName);
	}

	public <T> void firePropertyChange(final T oldValue, final T newValue) {
		firePropertyChange(null, oldValue, newValue);
	}

	public <T> void firePropertyChange(final String propertyName, final T oldValue, final T newValue) {
		if (!nullSafeEquals(oldValue, newValue)) {

			for (PropertyChangeListener l : listeners) {
				l.propertyChange(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
			}

			if (propertyName != null) {
				Set<PropertyChangeListener> propertyChangeListeners = propertyListeners.get(propertyName);

				if (propertyChangeListeners != null) {
					for (PropertyChangeListener l : propertyChangeListeners) {
						l.propertyChange(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
					}
				}
			}

		}
	}

	public boolean hasListeners() {
		return hasListeners(null);
	}

	public boolean hasListeners(final String propertyName) {

		if (!listeners.isEmpty()) {
			return true;
		}

		if (propertyName != null) {
			Set<PropertyChangeListener> list = propertyListeners.get(propertyName);
			return list != null && !list.isEmpty();
		}

		return false;
	}

	private boolean nullSafeEquals(final Object o1, final Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}

		return o1.equals(o2);
	}

	private void atLeastOneListener(final PropertyChangeListener... listener) throws IllegalArgumentException {
		if (listener == null || listener.length == 0) {
			throw new IllegalArgumentException("At least ONE PropertyChangeListener is required!");
		}
	}

	 private Set<PropertyChangeListener> nullSafeGetPropertyChangeListeners(final String propertyName) {
		Set<PropertyChangeListener> returnList = propertyListeners.get(propertyName);

		if (returnList == null) {
			returnList = new HashSet<PropertyChangeListener>();
			propertyListeners.put(propertyName, returnList);
		}

		return returnList;
	}
}

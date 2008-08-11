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

	public Object source;

	private final Set<PropertyChangeListener> listeners = new HashSet<PropertyChangeListener>();

	private final Map<String, Set<PropertyChangeListener>> propertyListeners = new HashMap<String, Set<PropertyChangeListener>>();

	public PropertyChangeSupport(Object sourceBean) {
		if (sourceBean == null) {
			throw new NullPointerException();
		}
		source = sourceBean;
	}

	public void addPropertyChangeListener(PropertyChangeListener... listener) {
		addPropertyChangeListener(null, listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener... listener) {
		atLeastOneListener(listener);

		final Set<PropertyChangeListener> listeners = propertyName == null ? this.listeners
				: nullSafeGetPropertyChangeListeners(propertyName);

		for (final PropertyChangeListener propertyChangeListener : listener) {
			if (!listeners.contains(propertyChangeListener)) {
				listeners.add(propertyChangeListener);
			}
		}
	}

	public void removeAllPropertyChangeListeners() {
		listeners.clear();
		propertyListeners.clear();
	}

	public void removePropertyChangeListener(PropertyChangeListener... listener) {
		removePropertyChangeListener(null, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener... listener) {
		atLeastOneListener(listener);

		final Set<PropertyChangeListener> listeners = propertyName == null ? this.listeners : propertyListeners
				.get(propertyName);

		if (listeners != null) {
			for (final PropertyChangeListener propertyChangeListener : listener) {
				listeners.remove(propertyChangeListener);
			}
		}
	}

	public Set<PropertyChangeListener> getAllPropertyChangeListeners() {
		final Set<PropertyChangeListener> returnList = new HashSet<PropertyChangeListener>();
		returnList.addAll(listeners);

		for (final Set<PropertyChangeListener> propertyChangeListeners : propertyListeners.values()) {
			returnList.addAll(propertyChangeListeners);
		}

		return returnList;
	}

	public Set<PropertyChangeListener> getPropertyChangeListeners() {
		return getPropertyChangeListeners(null);
	}

	public Set<PropertyChangeListener> getPropertyChangeListeners(String propertyName) {
		if (propertyName == null) {
			return listeners;
		}

		return nullSafeGetPropertyChangeListeners(propertyName);
	}

	public <T> void firePropertyChange(T oldValue, T newValue) {
		firePropertyChange(null, oldValue, newValue);
	}

	public <T> void firePropertyChange(String propertyName, T oldValue, T newValue) {
		if (!nullSafeEquals(oldValue, newValue)) {

			for (final PropertyChangeListener l : listeners) {
				l.propertyChange(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
			}

			if (propertyName != null) {
				final Set<PropertyChangeListener> propertyChangeListeners = propertyListeners.get(propertyName);

				if (propertyChangeListeners != null) {
					for (final PropertyChangeListener l : propertyChangeListeners) {
						l.propertyChange(new PropertyChangeEvent(source, propertyName, oldValue, newValue));
					}
				}
			}

		}
	}

	public boolean hasListeners() {
		return hasListeners(null);
	}

	public boolean hasListeners(String propertyName) {

		if (!listeners.isEmpty()) {
			return true;
		}

		if (propertyName != null) {
			final Set<PropertyChangeListener> list = propertyListeners.get(propertyName);
			return list != null && !list.isEmpty();
		}

		return false;
	}

	private boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}

		return o1.equals(o2);
	}

	private void atLeastOneListener(PropertyChangeListener... listener) throws IllegalArgumentException {
		if (listener == null || listener.length == 0) {
			throw new IllegalArgumentException("At least ONE PropertyChangeListener is required!");
		}
	}

	private Set<PropertyChangeListener> nullSafeGetPropertyChangeListeners(String propertyName) {
		Set<PropertyChangeListener> returnList = propertyListeners.get(propertyName);

		if (returnList == null) {
			returnList = new HashSet<PropertyChangeListener>();
			propertyListeners.put(propertyName, returnList);
		}

		return returnList;
	}

}

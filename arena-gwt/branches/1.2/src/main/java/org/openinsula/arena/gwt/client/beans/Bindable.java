package org.openinsula.arena.gwt.client.beans;

/**
 * @author Eduardo Rebola
 *
 */
public abstract class Bindable {
	private transient PropertyChangeSupport changes;

	public Bindable() {
		super();
	}

	public void addChangeListener(final PropertyChangeListener listener) {
		changes().addPropertyChangeListener(listener);
	}

	public void removeChangeListener(final PropertyChangeListener listener) {
		changes().removePropertyChangeListener(listener);
	}

	public void removeAllListeners() {
		changes().removeAllPropertyChangeListeners();
	}

	protected PropertyChangeSupport changes() {
		if (changes == null) {
			changes = new PropertyChangeSupport(this);
		}

		return changes;
	}

}
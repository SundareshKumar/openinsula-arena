package org.openinsula.arena.gwt.client.state;

import java.util.Collection;
import java.util.Collections;

import org.openinsula.arena.gwt.client.beans.Bindable;
import org.openinsula.arena.gwt.client.beans.PropertyChangeListener;

public abstract class CollectionStateProperty<T> extends Bindable implements ClientStateProperty<Collection<T>> {
	protected Collection<T> model;
	protected final String propertyName;

	private boolean resoucesControl = false;

	public CollectionStateProperty(final String propertyName) {
		this.propertyName = propertyName;
	}

	public void setResoucesControl(final boolean resoucesControl) {
		this.resoucesControl = resoucesControl;
	}

	public Collection<T> get() {
		return Collections.unmodifiableCollection(model);
	}

	public void set(final Collection<T> values) {
		model = values;
		changes().firePropertyChange(propertyName, null, model);
	}

	public void addChangeListener(final PropertyChangeListener listener) {
		preloadResources();
		changes().addPropertyChangeListener(propertyName, listener);
	}

	public void removeChangeListener(final PropertyChangeListener listener) {
		changes().removePropertyChangeListener(propertyName, listener);
		freeResources();
	}

	public void removeAllListeners() {
		super.removeAllListeners();
		freeResources();
	}

	private void freeResources() {
		if (resoucesControl && changes().getPropertyChangeListeners(propertyName).isEmpty()) {
			dropCollection();
		}
	}

	/**
	 * Default implementation set <code>null</code> to Collection. Override when <code>resoucesControl</code> is <code>true</code>
	 */
	public void dropCollection() {
		model = null;
	}

	private void preloadResources() {
		if (resoucesControl && changes().getPropertyChangeListeners(propertyName).isEmpty()) {
			loadCollection();
		}
	}

	/**
	 * Default implementation does nothing. Override when <code>resoucesControl</code> is <code>true</code>
	 */
	public void loadCollection() {

	}

}

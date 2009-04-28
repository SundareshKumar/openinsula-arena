package org.openinsula.arena.appengine.gwt.client.forms;

import org.openinsula.arena.appengine.gwt.client.beans.PropertyChangeSupport;

import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractUIModel<T extends UIModelRenderer> implements UIModel<T> {

	private final PropertyChangeSupport model;

	private T renderer;

	private Widget renderedModelCache;

	public AbstractUIModel() {
		model = new PropertyChangeSupport();
	}

	public void setRenderer(final T renderer) {
		this.renderer = renderer;
		attachRenderer(renderer, model);
	}

	public final T getRenderer() {
		return renderer;
	}

	protected void attachRenderer(final T renderer, final PropertyChangeSupport model) {
	}

	protected final void setProperty(final String propertyName, final Object propertyValue) {
		model.changeProperty(propertyName, propertyValue);
	}

	@SuppressWarnings("unchecked")
	protected final <E> E getProperty(final String propertyName) {
		return (E) model.getProperty(propertyName);
	}

	public Widget toWidget() {
		if (renderedModelCache == null) {
			renderedModelCache = renderer.renderModel();
		}
		return renderedModelCache;
	}

}

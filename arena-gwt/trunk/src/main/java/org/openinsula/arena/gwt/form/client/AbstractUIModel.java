package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.components.client.beans.PropertyChangeSupport;

import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractUIModel<T extends UIModelRenderer> implements UIModel<T> {

	private final PropertyChangeSupport model;

	private T renderer;

	private Widget renderedModelCache;

	public AbstractUIModel() {
		model = new PropertyChangeSupport();
	}

	public final void setRenderer(final T renderer) {
		if (renderer == null) {
			throw new IllegalArgumentException("Renderer required!");
		}
		attachRenderer(renderer, model);
		this.renderer = renderer;
	}

	public final T getRenderer() {
		return renderer;
	}

	protected void attachRenderer(final T renderer, final PropertyChangeSupport model) {
	}

	protected final void setProperty(final String propertyName, final Object propertyValue) {
		model.changeProperty(propertyName, propertyValue);
	}

	protected final <E> E getProperty(final String propertyName) {
		return model.getProperty(propertyName);
	}

	public Widget toWidget() {
		if (renderedModelCache == null) {
			renderedModelCache = renderer.renderModel();
		}
		return renderedModelCache;
	}

}

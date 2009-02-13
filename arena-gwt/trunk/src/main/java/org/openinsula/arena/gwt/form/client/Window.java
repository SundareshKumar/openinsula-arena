package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.components.client.beans.PropertyChangeCallback;
import org.openinsula.arena.gwt.components.client.beans.PropertyChangeSupport;

public class Window extends AbstractUIModel<WindowRenderer> {

	private static final String TITLE_PROPERTY = "title";

	private static final String BODY_PROPERTY = "body";

	Window(final WindowRenderer renderer) {
		setRenderer(renderer);
	}

	@Override
	protected void attachRenderer(final WindowRenderer renderer, final PropertyChangeSupport model) {

		model.addPropertyChangeCallback(TITLE_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onTitleChange(oldValue, newValue);
			}
		});

		model.addPropertyChangeCallback(BODY_PROPERTY, new PropertyChangeCallback<UIModel<?>>() {
			public void onChange(final UIModel<?> oldValue, final UIModel<?> newValue) {
				renderer.onBodyChange(oldValue, newValue);
			}
		});

	}

	public Window title(final String title) {
		setProperty(TITLE_PROPERTY, title);
		return this;
	}

	public String title() {
		return getProperty(TITLE_PROPERTY);
	}

	public Window body(final UIModel<?> model) {
		setProperty(BODY_PROPERTY, model);
		return this;
	}

	public UIModel<?> body() {
		return getProperty(BODY_PROPERTY);
	}

}

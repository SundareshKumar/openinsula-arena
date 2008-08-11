package org.openinsula.arena.gwt.client.ui;

import org.openinsula.arena.gwt.client.beans.PropertyChangeEvent;
import org.openinsula.arena.gwt.client.beans.PropertyChangeListener;
import org.openinsula.arena.gwt.client.beans.converter.Converter;
import org.openinsula.arena.gwt.client.beans.converter.ToStringConverter;

import com.google.gwt.user.client.ui.HasText;

public class HasTextPropertyChangeListener<T> implements PropertyChangeListener {

	private final HasText component;

	private final Converter<T, String> converter;

	public HasTextPropertyChangeListener(final HasText component) {
		this(component, new ToStringConverter<T>());
	}

	public HasTextPropertyChangeListener(final HasText component, final Converter<T, String> converter) {
		assert component != null;
		assert converter != null;

		this.component = component;
		this.converter = converter;
	}

	@SuppressWarnings("unchecked")
	public void propertyChange(final PropertyChangeEvent evt) {
		component.setText(converter.convert((T) evt.newValue));
	}

}
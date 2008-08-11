package org.openinsula.arena.gwt.client.ui;

import java.util.Collection;

import org.openinsula.arena.gwt.client.beans.PropertyChangeEvent;
import org.openinsula.arena.gwt.client.beans.PropertyChangeListener;
import org.openinsula.arena.gwt.client.beans.converter.Converter;
import org.openinsula.arena.gwt.client.beans.converter.ToStringConverter;

import com.google.gwt.user.client.ui.ListBox;

public class ListBoxPropertyChangeListener<T> implements PropertyChangeListener {

	protected final ListBox component;

	protected final Converter<T, String> labelConverter;

	protected final Converter<T, String> valueConverter;

	protected boolean useDefaultSelection = true;

	protected String defaultSelectionLabel = "(choose)";

	public ListBoxPropertyChangeListener(final ListBox component) {
		this(component, new ToStringConverter<T>());
	}

	public ListBoxPropertyChangeListener(final ListBox component, final Converter<T, String> converter) {
		this(component, converter, converter);
	}

	public ListBoxPropertyChangeListener(final ListBox component, final Converter<T, String> labelConverter,
			final Converter<T, String> valueConverter) {
		assert component != null;
		assert labelConverter != null;
		assert valueConverter != null;

		this.component = component;
		this.labelConverter = labelConverter;
		this.valueConverter = valueConverter;

		init();
	}

	private void init() {
		if (useDefaultSelection) {
			component.clear();
			component.addItem(defaultSelectionLabel, "");
		}
	}

	public void setUseDefaultSelection(final boolean useDefaultSelection) {
		this.useDefaultSelection = useDefaultSelection;
	}

	public void setDefaultSelectionLabel(final String defaultSelectionLabel) {
		this.defaultSelectionLabel = defaultSelectionLabel;
	}

	@SuppressWarnings("unchecked")
	public void propertyChange(final PropertyChangeEvent evt) {
		component.clear();

		if (useDefaultSelection) {
			component.addItem(defaultSelectionLabel, "");
		}

		final Collection<T> collection = (Collection<T>) evt.newValue;

		filterCollection(collection);
		mapCollection(collection);
	}

	protected void filterCollection(final Collection<T> collection) {
	}

	protected void mapCollection(final Collection<T> values) {
		if (values != null && !values.isEmpty()) {
			for (final T item : values) {
				component.addItem(labelConverter.convert(item), valueConverter.convert(item));
			}
		}
	}

}

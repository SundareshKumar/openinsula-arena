package org.openinsula.arena.gwt.beans.client.converter;

import com.google.gwt.i18n.client.DateTimeFormat;

public abstract class DateConverter<S, T> implements Converter<S, T> {
	protected final DateTimeFormat dateTimeFormat;

	public DateConverter() {
		this(DateTimeFormat.getFormat("dd/MM/yyyy"));
	}

	public DateConverter(final DateTimeFormat dateTimeFormat) {
		assert dateTimeFormat != null;
		this.dateTimeFormat = dateTimeFormat;
	}

}

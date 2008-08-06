package org.openinsula.arena.gwt.client.beans.converter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class StringToDateConverter extends DateConverter<String, Date> {

	private boolean lenient = true;

	public StringToDateConverter() {
		super();
	}

	public StringToDateConverter(final DateTimeFormat dateTimeFormat) {
		super(dateTimeFormat);
	}

	public void setLenient(final boolean lenient) {
		this.lenient = lenient;
	}

	public Date convert(final String source) {
		return lenient ? dateTimeFormat.parse(source) : dateTimeFormat.parseStrict(source);
	}

}

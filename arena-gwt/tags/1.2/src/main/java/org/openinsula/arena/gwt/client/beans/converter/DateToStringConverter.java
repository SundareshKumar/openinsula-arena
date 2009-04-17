package org.openinsula.arena.gwt.client.beans.converter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateToStringConverter extends DateConverter<Date, String> {
	private boolean emptyStringOnError = true;

	public DateToStringConverter() {
		super();
	}

	public DateToStringConverter(final DateTimeFormat dateTimeFormat) {
		super(dateTimeFormat);
	}

	public void setEmptyStringOnError(final boolean emptyStringOnError) {
		this.emptyStringOnError = emptyStringOnError;
	}

	public String convert(final Date source) {
		try {
			return dateTimeFormat.format(source);

		} catch (Exception e) {
			if (emptyStringOnError) {
				return "";
			}
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}

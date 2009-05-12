package org.openinsula.arena.appengine.gwt.client.converter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateConverter implements Converter<String, Date> {

	private final DateTimeFormat format;

	private boolean strictMode;

	public DateConverter() {
		this(true);
	}

	public DateConverter(final boolean ignoreTimeFields) {
		this(ignoreTimeFields ? DateTimeFormat.getShortDateFormat() : DateTimeFormat.getShortDateTimeFormat());
	}

	public DateConverter(final DateTimeFormat format) {
		this.format = format;

	}

	public DateConverter strict(final boolean value) {
		this.strictMode = value;
		return this;
	}

	@Override
	public Date convertForward(final String value) {
		try {
			return format.parseStrict(value);
		}
		catch (RuntimeException e) {
			if (!strictMode) {
				return null;
			}
			throw e;
		}
	}

	@Override
	public String convertReverse(final Date value) {
		try {
			return format.format(value);
		}
		catch (RuntimeException e) {
			if (!strictMode) {
				return null;
			}
			throw e;
		}
	}

}

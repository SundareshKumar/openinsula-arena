package org.openinsula.arena.gwt.client.beans.converter;

public class ToStringConverter<T> implements Converter<T, String> {

	private boolean emptyStringWhenNull = true;

	/**
	 * Default is <b><code>true</code></b>
	 *
	 * @param emptyStringWhenNull
	 */
	public void setEmptyStringWhenNull(final boolean emptyStringWhenNull) {
		this.emptyStringWhenNull = emptyStringWhenNull;
	}

	public String convert(final T source) {
		if (source == null) {
			return emptyStringWhenNull ? "" : null;
		}

		return source.toString();
	}

}

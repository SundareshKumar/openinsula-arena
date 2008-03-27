package org.openinsula.arena.hibernate.types;

import java.util.Locale;
import java.util.Properties;

import org.hibernate.usertype.ParameterizedType;
import org.openinsula.arena.lang.numbers.Money;

public class MoneyUserType extends AbstractDecimalUserType<Money> implements ParameterizedType {
	public static final String PARAM_LANGUAGE = "_language";

	public static final String PARAM_COUNTRY = "_country";

	public static final String PARAM_VARIANT = "_variant";

	private Locale locale = Locale.getDefault();

	@Override
	public void setParameterValues(final Properties parameters) {

		if (parameters != null) {
			String language = parameters.getProperty(PARAM_LANGUAGE);
			String country = parameters.getProperty(PARAM_COUNTRY);
			String variant = parameters.getProperty(PARAM_VARIANT);

			if (language != null) {

				if (country != null) {

					if (variant != null) {
						locale = new Locale(language, country, variant);
					}
					else {
						locale = new Locale(language, country);
					}

				}
				else {
					locale = new Locale(language);
				}
			}

		}
	}

	@Override
	protected void prepareObject(final Money value) {
		value.from(locale);
	}

}

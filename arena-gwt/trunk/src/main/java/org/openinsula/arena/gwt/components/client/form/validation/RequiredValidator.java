package org.openinsula.arena.gwt.components.client.form.validation;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lucas K Mogari
 */
public class RequiredValidator implements Validator {

	public ValidationResult validate(Object value) {
		final ValidationResult result = new ValidationResult(this);
		boolean valid = true;

		if (value == null) {
			valid = false;
		}
		else if (String.class == value.getClass()) {
			valid = value.toString().trim().length() > 0;
		}
		else if (Boolean.class == value.getClass()) {
			valid = (Boolean) value;
		}
		else {
			int length = 1;

			if (value instanceof Collection) {
				length = ((Collection) value).size();
			}
			else if (value instanceof Map) {
				length = ((Map) value).size();
			}

			valid = length > 0;
		}

		if (!valid) {
			result.setValid(false);
			result.setMessage("Campo obrigatório");// TODO change message
		}
		return result;
	}

}

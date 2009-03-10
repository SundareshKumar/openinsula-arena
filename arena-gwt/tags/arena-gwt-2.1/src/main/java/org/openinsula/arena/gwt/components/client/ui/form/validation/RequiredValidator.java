package org.openinsula.arena.gwt.components.client.ui.form.validation;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lucas K Mogari
 */
public class RequiredValidator implements Validator {

	@SuppressWarnings("unchecked")
	public ValidationResult validate(Object value) {
		final ValidationResult result = new ValidationResult();
		boolean valid = true;

		if (value == null) {
			valid = false;
		}
		else if (value instanceof String) {
			valid = !"".equals(value);
		}
		else if (value instanceof Boolean) {
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

		result.setValid(valid);

		if (!valid) {
			result.setMessage("Campo obrigatório");
		}

		return result;
	}
}

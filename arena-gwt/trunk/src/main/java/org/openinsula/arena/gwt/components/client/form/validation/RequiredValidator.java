package org.openinsula.arena.gwt.components.client.form.validation;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lucas K Mogari
 */
public class RequiredValidator extends SynchronousValidator {

	@SuppressWarnings("unchecked")
	@Override
	protected boolean isValid(Object value) {
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
		else if (value instanceof Collection) {
			valid = !((Collection) value).isEmpty();
		}
		else if (value instanceof Map) {
			valid = !((Map) value).isEmpty();
		}

		if (!valid) {
			setMessage("Campo obrigatório");// TODO change message
		}
		return valid;
	}

}

package org.openinsula.arena.gwt.form.client.validator;

public interface AsyncValidator<T> {

	void validate(T object, ValidationCallback callback);

}

package org.openinsula.arena.appengine.gwt.client.forms.validator;

public interface AsyncValidator<T> {

	void validate(T object, ValidationCallback callback);

}

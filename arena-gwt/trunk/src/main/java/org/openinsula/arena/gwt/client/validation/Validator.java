package org.openinsula.arena.gwt.client.validation;

/**
 * @author Lucas K Mogari
 */
public interface Validator {

	public boolean validate(Object value);

	public String getMessage();

}

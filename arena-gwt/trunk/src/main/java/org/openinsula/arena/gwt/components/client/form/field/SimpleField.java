package org.openinsula.arena.gwt.components.client.form.field;

import org.openinsula.arena.gwt.client.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface SimpleField extends Field {

	public <T extends Widget> T getComponent();

	public void addValidator(Validator validator);

	public void removeValidator(Validator validator);

}

package org.openinsula.arena.gwt.components.client.form.field;

import org.openinsula.arena.gwt.client.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface CompositeField extends Field {

	public Widget[] getComponents();

	public void addValidator(Widget widget, Validator... validators);

	public void removeValidator(Widget widget, Validator validator);

	public void removeValidators(Widget widget);

}

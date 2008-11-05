package org.openinsula.arena.gwt.components.client.form.field;

import java.util.List;

import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface CompositeField extends Field {

	public List<Widget> getFieldWidgets();

	public void addValidator(Widget widget, Validator validator);

	public void removeValidator(Widget widget, Validator validator);

	public void removeValidators(Widget widget);

}

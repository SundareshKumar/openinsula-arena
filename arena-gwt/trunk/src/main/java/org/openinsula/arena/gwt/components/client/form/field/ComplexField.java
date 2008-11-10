package org.openinsula.arena.gwt.components.client.form.field;

import java.util.List;

import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface ComplexField extends Field {

	public List<Widget> getFieldWidgets();

	public Widget getFieldWidget(int index);

	public void addValidator(Widget fieldWidget, Validator validator);

	public void removeValidator(Widget fieldWidget, Validator validator);

	public void removeValidators(Widget fieldWidget);

	public void setValue(Widget fieldWidget, Object value);

	public <T> T getValue(Widget fieldWidget);

}

package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.ui.form.field.FieldValueHandler;

import com.google.gwt.user.client.ui.CheckBox;

/**
 * @author Lucas K Mogari
 */
public class CheckBoxValueHandler implements FieldValueHandler<CheckBox> {

	@SuppressWarnings("unchecked")
	public <V> V getValue(CheckBox widget) {
		return (V) Boolean.valueOf(widget.isChecked());
	}

	public void setValue(CheckBox widget, Object value) {
		widget.setChecked(value == null ? false : Boolean.valueOf(value.toString()));
	}

}

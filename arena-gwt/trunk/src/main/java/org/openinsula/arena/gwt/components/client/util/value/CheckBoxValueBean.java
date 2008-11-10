package org.openinsula.arena.gwt.components.client.util.value;

import com.google.gwt.user.client.ui.CheckBox;

/**
 * @author Lucas K Mogari
 */
public class CheckBoxValueBean implements ValueHandler<CheckBox> {

	@SuppressWarnings("unchecked")
	public <V> V getValue(CheckBox checkBox) {
		return (V) Boolean.valueOf(checkBox.isChecked());
	}

	public void setValue(CheckBox checkBox, Object value) {
		checkBox.setChecked(value == null ? false : Boolean.valueOf(value.toString()));
	}

}

package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.ui.form.field.FieldValueHandler;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public class BeanSuggestBoxValueHandler implements FieldValueHandler<BeanSuggestBox> {

	public <V> V getValue(BeanSuggestBox widget) {
		return (V) widget.getSelectedBean();
	}

	public void setValue(BeanSuggestBox widget, Object value) {
		widget.setText(value == null ? null : value.toString());
	}

}

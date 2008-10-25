package org.openinsula.arena.gwt.client.form;

import org.openinsula.arena.gwt.client.form.field.FieldValueHandler;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestBox;

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

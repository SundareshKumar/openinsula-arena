package org.openinsula.arena.gwt.components.client.ui.form.field.value;

import org.openinsula.arena.gwt.components.client.ui.suggest.bean.BeanSuggestBox;


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

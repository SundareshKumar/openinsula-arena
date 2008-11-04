package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.form.field.FieldValueHandler;

import com.google.gwt.user.client.ui.HasText;

/**
 * @author Lucas K Mogari
 */
public class HasTextValueHandler implements FieldValueHandler<HasText> {

	@SuppressWarnings("unchecked")
	public <V> V getValue(HasText hasText) {
		return (V) hasText.getText();
	}

	public void setValue(HasText hasText, Object value) {
		hasText.setText(value == null ? null : value.toString());
	}

}

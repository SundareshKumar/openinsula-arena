package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;

import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class NotNullFormItemValidator extends SyncFormItemValidator<Widget> {

	public String getInvalidValueMessage() {
		return "Campo obrigatório";
	}

	protected void evaluate(Widget widget, EvaluateCallback callback) {
		boolean valid = false;
		if (widget instanceof TextBoxBase) {
			valid = ((TextBoxBase) widget).getText().trim().length() > 0;
		} else if (widget instanceof BeanListBox) {
			valid = ((BeanListBox<?>) widget).getSelectedItem() != null;
		} else if (widget instanceof AbstractSearchFormTemplate) {
			valid = !((AbstractSearchFormTemplate<?>) widget).isEmpty();
		} else {
			throw new IllegalArgumentException("Não é possível verificar conteúdo de componentes do tipo: " + widget.getClass().getName());
		}

		if (valid) {
			callback.success();
		} else {
			callback.fail();
		}
	}

}

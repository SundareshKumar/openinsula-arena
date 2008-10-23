package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;

import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class NotNullFormItemValidator extends SyncFormItemValidator<Widget> {

	public String getInvalidValueMessage() {
		return "Campo obrigatório";
	}

	@Override
	protected boolean evaluate(Widget widget) {
		if (widget instanceof TextBoxBase) {
			return ((TextBoxBase) widget).getText().trim().length() > 0;
		} else if (widget instanceof BeanListBox) {
			return ((BeanListBox<?>) widget).getSelectedItem() != null;
		} else if (widget instanceof AbstractSearchFormTemplate) {
			return ((AbstractSearchFormTemplate<?>) widget).getEditInstance() != null;
		}

		throw new IllegalArgumentException("Não é possível verificar conteúdo de componentes do tipo: " + widget.getClass().getName());
	}

}

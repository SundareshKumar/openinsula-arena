package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.form.util.JSMaskedDateWidget;
import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestBox;

import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class NotNullFormItemValidator extends SyncFormItemValidator<Widget> {

	private final static String DEFAULT_ERROR_MESSAGE = "Campo Obrigatório";

	private String errorMessage;

	public NotNullFormItemValidator() {
		errorMessage = DEFAULT_ERROR_MESSAGE;
	}

	public NotNullFormItemValidator(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getInvalidValueMessage() {
		return errorMessage;
	}

	protected void evaluate(Widget widget, EvaluateCallback callback) {
		boolean valid = false;
		if (widget instanceof TextBoxBase) {
			valid = ((TextBoxBase) widget).getText().trim().length() > 0;
		} else if (widget instanceof BeanListBox) {
			valid = ((BeanListBox<?>) widget).getSelectedItem() != null;
		} else if (widget instanceof AbstractSearchFormTemplate) {
			valid = !((AbstractSearchFormTemplate<?>) widget).isEmpty();
		} else if (widget instanceof BeanSuggestBox) {
			valid = ((BeanSuggestBox<?>) widget).getText().trim().length() > 0;
		} else if (widget instanceof SimpleDateWidget) {
			valid = ((SimpleDateWidget) widget).getDate() != null;
		} else if (widget instanceof JSMaskedDateWidget) {
			valid = ((JSMaskedDateWidget) widget).getDate() != null;
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

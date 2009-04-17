package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.TextBoxBase;

public class LengthLowerValidator extends SyncFormItemValidator<TextBoxBase> {

	private Integer limit;

	public LengthLowerValidator(int limit) {
		this.limit = limit;
	}

	protected void evaluate(TextBoxBase widget, EvaluateCallback callback) {
		String valor = widget.getText();

		if (valor.length() < limit) {
			callback.fail();
		} else {
			callback.success();
		}
	}

	public String getInvalidValueMessage() {
		if (limit <= 1) {
			return "O limite deve conter no m\u00ednimo " + limit.toString() + " caracter.";
		} else {
			return "O limite deve conter no m\u00ednimo " + limit.toString() + " caracteres.";
		}
	}

}

package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.TextBoxBase;

public class LengthBetweenValidator extends SyncFormItemValidator<TextBoxBase> {

	private Integer min;

	private Integer max;

	public LengthBetweenValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	protected void evaluate(TextBoxBase widget, EvaluateCallback callback) {
		String valor = widget.getText();

		if (valor.length() > max && valor.length() < min) {
			callback.fail();
		}
		else {
			callback.success();
		}
	}

	public String getInvalidValueMessage() {
		String caracterMin = "";
		String caracterMax = "";

		if (min <= 1) {
			caracterMin = "caracter";
		}
		else {
			caracterMin = "caracteres";
		}

		if (max <= 1) {
			caracterMax = "caracter";
		}
		else {
			caracterMax = "caracteres";
		}

		return "O valor precisar ter um m\u00ednimo de " + min.toString() + " " + caracterMin + " e m\u00e1ximo de "
				+ max.toString() + " " + caracterMax;
	}

}

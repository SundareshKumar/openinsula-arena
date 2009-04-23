package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.util.JSMaskedDateWidget;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateFormItemValidator extends SyncFormItemValidator<JSMaskedDateWidget> {

	private String dateMask = "dd/MM/yyyy";

	public DateFormItemValidator() {
		super();
	}

	@Override
	protected void evaluate(JSMaskedDateWidget widget, EvaluateCallback callback) {
		String text = widget.getText();

		try {
			DateTimeFormat.getFormat(dateMask).parseStrict(text);
		}
		catch (Exception e) {
			callback.fail();
			return;
		}

		callback.success();
	}

	public String getInvalidValueMessage() {
		return "Data inv\u00e1lida";
	}

}

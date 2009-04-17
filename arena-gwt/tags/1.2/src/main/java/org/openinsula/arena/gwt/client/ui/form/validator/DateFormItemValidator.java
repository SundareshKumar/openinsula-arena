package org.openinsula.arena.gwt.client.ui.form.validator;

import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateFormItemValidator extends SyncFormItemValidator<SimpleDateWidget> {

	private String dateMask = "dd/MM/yyyy";

	public DateFormItemValidator() {
		super();
	}

	protected void evaluate(SimpleDateWidget widget, EvaluateCallback callback) {
		String text = widget.getText();

		try {
			DateTimeFormat.getFormat(dateMask).parse(text);
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

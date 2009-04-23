package org.openinsula.arena.gwt.client.ui.form.util;

import java.util.Date;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

public class DateFormItem extends FormItem<JSMaskedDateWidget> {

	public DateFormItem() {
		this("Data");
	}

	public DateFormItem(String label) {
		this(label, new JSMaskedDateWidget(), null, false);
	}

	public DateFormItem(String label, JSMaskedDateWidget widget, String hint, boolean optional) {
		super(label, widget, hint, optional);
	}

	public Date getDate() {
		return getWidget().getDate();
	}

	public void setDate(Date date) {
		getWidget().setDate(date);
	}

	@Override
	public void clear() {
		super.clear();
		getWidget().clear();
	}
}

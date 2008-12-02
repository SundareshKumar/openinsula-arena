package org.openinsula.arena.gwt.client.ui.form.util;

import java.util.Date;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

public class DateFormItem extends FormItem<SimpleDateWidget> {

	public DateFormItem() {
		this("Data");
	}

	public DateFormItem(String label) {
		this(label, new SimpleDateWidget(), null, false);
	}

	public DateFormItem(String label, SimpleDateWidget widget, String hint, boolean optional) {
		super(label, widget, hint, optional);
	}

	public Date getDate() {
		return getWidget().getDate();
	}

	public void setDate(Date date) {
		getWidget().setDate(date);
	}
}

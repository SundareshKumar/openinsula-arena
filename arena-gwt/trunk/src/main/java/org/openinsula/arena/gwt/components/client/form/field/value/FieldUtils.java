package org.openinsula.arena.gwt.components.client.form.field.value;


import org.openinsula.arena.gwt.components.client.form.field.CompositeField;
import org.openinsula.arena.gwt.components.client.form.field.Field;
import org.openinsula.arena.gwt.components.client.form.field.SingleField;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class FieldUtils {

	private static FieldUtilsBean bean = new FieldUtilsBean();

	public static void setValue(Field field, Object value) {
		bean.setValue(field, value);
	}

	public static void setValue(CompositeField field, Object value) {
		bean.setValue(field, value);
	}

	public static void setValue(SingleField field, Object value) {
		bean.setValue(field, value);
	}

	public static void setValue(Widget widget, Object value) {
		bean.setValue(widget, value);
	}

	public static <T> T getValue(Field field) {
		return bean.<T> getValue(field);
	}

	public static <T> T getValue(SingleField field) {
		return bean.<T> getValue(field);
	}

	public static <T> T getValue(Widget widget) {
		return bean.<T> getValue(widget);
	}

}

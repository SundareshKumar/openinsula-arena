package org.openinsula.arena.gwt.components.client.util;

import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.components.client.util.value.CheckBoxValueBean;
import org.openinsula.arena.gwt.components.client.util.value.HasTextValueHandler;
import org.openinsula.arena.gwt.components.client.util.value.ListBoxValueHandler;
import org.openinsula.arena.gwt.components.client.util.value.ValueHandler;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public abstract class WidgetUtils {

	private static final Map<Class<?>, ValueHandler<?>> VALUE_HANDLERS = new HashMap<Class<?>, ValueHandler<?>>();

	public WidgetUtils() {
		final HasTextValueHandler hasTextValueHandler = new HasTextValueHandler();

		registerValueHandler(Label.class, hasTextValueHandler);
		registerValueHandler(TextBoxBase.class, hasTextValueHandler);
		registerValueHandler(SuggestBox.class, hasTextValueHandler);
		registerValueHandler(CheckBox.class, new CheckBoxValueBean());
		registerValueHandler(ListBox.class, new ListBoxValueHandler());
	}

	public static <T> void registerValueHandler(Class<? extends T> klazz, ValueHandler<T> valueHandler) {
		VALUE_HANDLERS.put(klazz, valueHandler);
	}

	public static <T> ValueHandler<T> unregisterValueHandler(Class<? extends T> klazz) {
		return (ValueHandler<T>) VALUE_HANDLERS.remove(klazz);
	}

	public static void setValue(Widget widget, Object value) {
		final ValueHandler valueHandler = getFieldValueHandler(widget.getClass());

		if (valueHandler != null) {
			valueHandler.setValue(widget, value);
		}
	}

	public static <T> T getValue(Widget widget) {
		final ValueHandler valueHandler = getFieldValueHandler(widget.getClass());

		return valueHandler != null ? (T) valueHandler.getValue(widget) : null;
	}

	private static ValueHandler<?> getFieldValueHandler(Class<?> klazz) {
		ValueHandler<?> fieldValueHandler = null;
		Class<?> iterateClass = klazz;

		do {
			fieldValueHandler = VALUE_HANDLERS.get(iterateClass);

			if (fieldValueHandler != null) {
				break;
			}
			iterateClass = iterateClass.getSuperclass();
		} while (iterateClass != null);

		return fieldValueHandler;
	}

}

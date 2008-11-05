package org.openinsula.arena.gwt.components.client.form.field.value;

import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.components.client.form.field.CompositeField;
import org.openinsula.arena.gwt.components.client.form.field.Field;
import org.openinsula.arena.gwt.components.client.form.field.SingleField;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.table.client.SelectionGrid;

/**
 * @author Lucas K Mogari
 */
public class FieldUtilsBean {

	private final Map<Class<?>, FieldValueHandler<?>> map = new HashMap<Class<?>, FieldValueHandler<?>>();

	public FieldUtilsBean() {
		final HasTextValueHandler hasTextValueHandler = new HasTextValueHandler();

		map.put(Label.class, hasTextValueHandler);
		map.put(TextBoxBase.class, hasTextValueHandler);
		map.put(SuggestBox.class, hasTextValueHandler);
		map.put(CheckBox.class, new CheckBoxValueHandler());
		map.put(ListBox.class, new ListBoxValueHandler());
		map.put(BeanSuggestBox.class, new BeanSuggestBoxValueHandler());
		map.put(SelectionGrid.class, new BeanSuggestBoxValueHandler());
	}

	public void setValue(Field field, Object value) {
		if (field instanceof SingleField) {
			setValue((SingleField) field, value);
		}
		else if (field instanceof CompositeField) {
			setValue((CompositeField) field, value);
		}
	}

	public void setValue(CompositeField field, Object value) {
		for (final Widget widget : field.getFieldWidgets()) {
			setValue(widget, value);
		}
	}

	public void setValue(SingleField field, Object value) {
		final Widget component = field.getFieldWidget();

		setValue(component, value);
	}

	@SuppressWarnings("unchecked")
	public void setValue(Widget widget, Object value) {
		final FieldValueHandler fieldValueHandler = getFieldValueHandler(widget.getClass());

		if (fieldValueHandler != null) {
			fieldValueHandler.setValue(widget, value);
		}
	}

	public <T> T getValue(Field field) {
		if (field instanceof SingleField) {
			return this.<T> getValue((SingleField) field);
		}
		else if (field instanceof CompositeField) {
			throw new IllegalArgumentException("");
		}
		return null;
	}

	public <T> T getValue(SingleField field) {
		return this.<T> getValue(field.getFieldWidget());
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(Widget widget) {
		final FieldValueHandler fieldValueHandler = getFieldValueHandler(widget.getClass());

		if (fieldValueHandler != null) {
			return (T) fieldValueHandler.getValue(widget);
		}
		return null;
	}

	private FieldValueHandler<?> getFieldValueHandler(Class<?> klazz) {
		FieldValueHandler<?> fieldValueHandler = null;
		Class<?> iterateClass = klazz;

		do {
			fieldValueHandler = map.get(iterateClass);

			if (fieldValueHandler != null) {
				break;
			}

			iterateClass = iterateClass.getSuperclass();
		} while (iterateClass != null);

		return fieldValueHandler;
	}

}

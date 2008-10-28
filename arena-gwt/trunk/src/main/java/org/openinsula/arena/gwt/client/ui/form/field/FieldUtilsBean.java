package org.openinsula.arena.gwt.client.ui.form.field;

import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.client.ui.form.BeanSuggestBoxValueHandler;
import org.openinsula.arena.gwt.client.ui.form.CheckBoxValueHandler;
import org.openinsula.arena.gwt.client.ui.form.HasTextValueHandler;
import org.openinsula.arena.gwt.client.ui.form.ListBoxValueHandler;

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
		if (field instanceof SimpleField) {
			setValue((SimpleField) field, value);
		}
		else if (field instanceof CompositeField) {
			setValue((CompositeField) field, value);
		}
	}

	public void setValue(CompositeField field, Object value) {
		for (final Widget widget : field.getComponents()) {
			setValue(widget, value);
		}
	}

	public void setValue(SimpleField field, Object value) {
		final Widget component = field.getComponent();

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
		if (field instanceof SimpleField) {
			return this.<T> getValue((SimpleField) field);
		}
		else if (field instanceof CompositeField) {
			throw new IllegalArgumentException("");
		}
		return null;
	}

	public <T> T getValue(SimpleField field) {
		return this.<T> getValue(field.getComponent());
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

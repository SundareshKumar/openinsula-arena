package org.openinsula.arena.gwt.components.client.form;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.NamedPanel;
import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.form.field.ComplexField;
import org.openinsula.arena.gwt.components.client.form.field.DefaultComplexField;
import org.openinsula.arena.gwt.components.client.form.field.DefaultSimpleField;
import org.openinsula.arena.gwt.components.client.form.field.Field;
import org.openinsula.arena.gwt.components.client.form.field.SimpleField;
import org.openinsula.arena.gwt.components.client.form.validation.CompositeValidator;
import org.openinsula.arena.gwt.components.client.form.validation.ValidationCallback;
import org.openinsula.arena.gwt.components.client.form.validation.ValidationResult;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class SimpleForm extends NamedPanel {

	private Paragraph errorMessageParagraph;

	private CompositeValidator formValidator;

	private final List<Field> fields = new LinkedList<Field>();

	public SimpleForm() {
		this(null);
	}

	public SimpleForm(String name) {
		this(name, null);
	}

	public SimpleForm(String name, String description) {
		super(name, description);

		initComponents();
	}

	public void resetFields() {
		for (final Field field : fields) {
			if (field instanceof SimpleField) {
				((SimpleField) field).setValue(null);
			}
			else if (field instanceof ComplexField) {
				final ComplexField complexField = (ComplexField) field;

				for (final Widget widget : complexField.getFieldWidgets()) {
					complexField.setValue(widget, null);
				}
			}
		}
	}

	public void setErrorMessage(String errorMessage) {
		errorMessageParagraph.setText(errorMessage);
	}

	public void addValidator(Validator validator) {
		if (formValidator == null) {
			formValidator = new CompositeValidator();
		}
		formValidator.add(validator);
	}

	public void removeValidator(Validator validator) {
		if (formValidator != null) {
			formValidator.remove(validator);
		}
	}

	private void initComponents() {
		errorMessageParagraph = new Paragraph();

		add(errorMessageParagraph);
	}

	public void validate(ValidationCallback callback) {
		if (formValidator == null) {
			callback.onValueValidated(new ValidationResult(true));
		}
		else {
			formValidator.validate(this, callback);
		}
	}

	@Override
	public void add(Widget widget) {
		super.add(widget);

		if (widget instanceof Field) {
			fields.add((Field) widget);
		}
	}

	@Override
	public void insert(Widget widget, int beforeIndex) {
		super.insert(widget, beforeIndex);

		if (widget instanceof Field) {
			fields.set(beforeIndex, (Field) widget);
		}
	}

	@Override
	public boolean remove(Widget widget) {
		final boolean removed = super.remove(widget);

		if (removed && widget instanceof Field) {
			fields.remove(widget);
		}
		return removed;
	}

	@Override
	public boolean remove(int index) {
		final Widget widget = getWidget(++index);
		return remove(widget);
	}

	public SimpleField addField(String name, Widget widget) {
		final DefaultSimpleField field = new DefaultSimpleField(widget, name);
		add(field);
		return field;
	}

	public ComplexField addField(String name, Widget... widgets) {
		final DefaultComplexField field = new DefaultComplexField(name, widgets);
		add(field);
		return field;
	}

}

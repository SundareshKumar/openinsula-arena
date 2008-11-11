package org.openinsula.arena.gwt.components.client.form;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.SimpleTitle;
import org.openinsula.arena.gwt.components.client.form.field.ComplexField;
import org.openinsula.arena.gwt.components.client.form.field.DefaultComplexField;
import org.openinsula.arena.gwt.components.client.form.field.DefaultSimpleField;
import org.openinsula.arena.gwt.components.client.form.field.Field;
import org.openinsula.arena.gwt.components.client.form.field.SimpleField;
import org.openinsula.arena.gwt.components.client.form.validation.CompositeValidator;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class SimpleForm extends Composite {

	private Panel formPanel;

	private SimpleTitle title;

	private Paragraph errorMessageParagraph;

	private CompositeValidator formValidator;

	private final List<Field> fields = new LinkedList<Field>();

	public SimpleForm() {
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

	public void setName(String name) {
		title.setText(name);
	}

	public void setDescription(String description) {
		title.setDescription(description);
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
		title = new SimpleTitle();
		errorMessageParagraph = new Paragraph();
		formPanel = new FlowPanel();

		initWidget(formPanel);

		formPanel.add(title);
		formPanel.add(errorMessageParagraph);
	}

	public void add(Widget widget) {
		formPanel.add(widget);

		if (widget instanceof Field) {
			fields.add((Field) widget);
		}
	}

	public void remove(Widget widget) {
		formPanel.remove(widget);

		if (widget instanceof Field) {
			fields.remove(widget);
		}
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

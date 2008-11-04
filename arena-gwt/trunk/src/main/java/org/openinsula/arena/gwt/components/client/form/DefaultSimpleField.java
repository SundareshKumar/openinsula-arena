package org.openinsula.arena.gwt.components.client.form;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openinsula.arena.gwt.client.validation.ValidationResult;
import org.openinsula.arena.gwt.components.client.ListItem;
import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.form.field.Field;
import org.openinsula.arena.gwt.components.client.form.field.FieldUtils;
import org.openinsula.arena.gwt.components.client.form.field.SimpleField;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultSimpleField extends Composite implements SimpleField {

	private final ListItem listItem = new ListItem();

	private Widget widget;

	private Paragraph errorParagraph;

	private HTML instructionHtml;

	private final Map<String, Object> attributes = new HashMap<String, Object>();

	private final List<Validator> validators = new LinkedList<Validator>();

	public DefaultSimpleField() {
	}

	public DefaultSimpleField(Widget widget) {
		setComponent(widget);
	}

	{
		initWidget(listItem);

		setStyleName(FIELD_STYLE_NAME);

		sinkEvents(Event.ONMOUSEOVER | Event.ONMOUSEOUT | Event.ONMOUSEDOWN);
	}

	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);

		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEOVER:
			break;

		case Event.ONMOUSEOUT:
			break;

		case Event.ONMOUSEDOWN:
			break;
		}
	}

	public void addValidator(Validator validator) {
		validators.add(validator);
	}

	public void removeValidator(Validator validator) {
		validators.remove(validator);
	}

	public boolean isValid() {
		boolean valid = true;

		for (final Validator validator : validators) {
			final Object value = FieldUtils.getValue(widget);
			final ValidationResult result = validator.validate(value);

			if (result != null && !result.isValid()) {
				final String message = result.getMessage();
				valid = false;

				setAttribute(ERROR_MESSAGE_ATTRIBUTE, message);
				break;
			}
		}

		if (valid) {
			setAttribute(ERROR_MESSAGE_ATTRIBUTE, null);
		}

		return valid;
	}

	public Widget toWidget() {
		return this;
	}

	@SuppressWarnings("unchecked")
	public <A> A getAttribute(String name) {
		return (A) attributes.get(name);
	}

	public void setAttribute(String name, Object attribute) {
		final Object oldAttribute = getAttribute(name);

		if (EqualsUtils.isDifferent(oldAttribute, attribute)) {
			attributes.put(name, attribute);

			if (Field.INSTRUCTION_ATTRIBUTE.equals(name)) {
				setInstruction((String) attribute);
			}
			else if (Field.ERROR_MESSAGE_ATTRIBUTE.equals(name)) {
				setErrorMessage((String) attribute);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getComponent() {
		return (T) widget;
	}

	public void setComponent(Widget widget) {
		this.widget = widget;

		add(widget);
	}

	protected List<Validator> getValidators() {
		return validators;
	}

	protected void add(Widget widget) {
		if (widget instanceof HasFocus) {
			addFocusListener(widget);
		}
		else if (widget instanceof Suffix) {
			final Suffix suffix = (Suffix) widget;
			final Object component = suffix.getComponent();

			if (component instanceof HasFocus) {
				addFocusListener(component);
			}
		}
		listItem.add(widget);
	}

	protected void insert(int index, Widget widget) {
		if (widget instanceof HasFocus) {
			addFocusListener(widget);
		}
		else if (widget instanceof Suffix) {
			final Suffix suffix = (Suffix) widget;
			final Object component = suffix.getComponent();

			if (component instanceof HasFocus) {
				addFocusListener(component);
			}
		}
		listItem.insert(widget, index);
	}

	private final FocusListener focusListener = new FocusListener() {
		public void onLostFocus(Widget sender) {
			if (instructionHtml != null) {
				instructionHtml.setVisible(false);
			}

			removeStyleName(ON_FOCUS_STYLE_NAME);
		}

		public void onFocus(Widget sender) {
			if (instructionHtml != null) {
				instructionHtml.setVisible(true);
			}

			if (errorParagraph == null) {
				addStyleName(ON_FOCUS_STYLE_NAME);
			}
		}
	};

	private void addFocusListener(Object widget) {
		final HasFocus hasFocus = (HasFocus) widget;

		hasFocus.addFocusListener(focusListener);
	}

	private void setInstruction(String instruction) {
		if (instruction == null || instruction.length() == 0) {
			if (instructionHtml != null) {
				listItem.remove(instructionHtml);

				instructionHtml = null;
			}
			return;
		}

		if (instructionHtml == null) {
			instructionHtml = new HTML();

			instructionHtml.setStyleName(INSTRUCTION_STYLE_NAME);

			listItem.add(instructionHtml);
		}

		instructionHtml.setHTML(instruction);
	}

	private void setErrorMessage(String errorMessage) {
		if (errorMessage == null || errorMessage.length() == 0) {
			if (errorParagraph != null) {
				listItem.remove(errorParagraph);

				removeStyleName(ERROR_STYLE_NAME);

				errorParagraph = null;
			}
		}
		else {
			if (errorParagraph == null) {
				errorParagraph = new Paragraph(errorMessage);

				listItem.add(errorParagraph);
			}

			addStyleName(ERROR_STYLE_NAME);

			errorParagraph.setText(errorMessage);
		}
	}

}

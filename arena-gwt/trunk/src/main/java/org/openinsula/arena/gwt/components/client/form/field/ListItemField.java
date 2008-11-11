package org.openinsula.arena.gwt.components.client.form.field;

import org.openinsula.arena.gwt.components.client.ForLabel;
import org.openinsula.arena.gwt.components.client.ListItem;
import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.form.validation.CompositeValidator;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Field based on a {@link ListItem}.
 * 
 * @author Lucas K Mogari
 */
public abstract class ListItemField extends Composite implements Field {

	private final ListItem listItem = new ListItem();

	private final ForLabel forLabel;

	private CompositeValidator validators;

	private Paragraph erroMessageParagraph;

	private Widget instructionWidget;

	public ListItemField() {
		this(null);
	}

	public ListItemField(String label) {
		forLabel = new ForLabel();

		initWidget(listItem);
		setStyleName(StyleNames.FIELD);

		forLabel.setText(label);

		listItem.add(forLabel);
	}

	public void addValidator(Validator validator) {
		getValidators().add(validator);
	}

	public void removeValidator(Validator validator) {
		if (validators != null) {
			validators.remove(validator);
		}
	}

	protected CompositeValidator getValidators() {
		if (validators == null) {
			validators = new CompositeValidator();
		}
		return validators;
	}

	protected int getWidgetCount() {
		return listItem.getWidgetCount() - 1;
	}

	public void setErrorMessage(String text) {
		if (text == null || text.trim().isEmpty()) {
			if (erroMessageParagraph != null) {
				remove(erroMessageParagraph);
			}
		}
		else {
			if (erroMessageParagraph == null) {
				erroMessageParagraph = new Paragraph();

				add(erroMessageParagraph);
			}
			erroMessageParagraph.setText(text);
		}
	}

	public void setInstruction(String text) {
		if (text == null || text.trim().isEmpty()) {
			setInstruction((Widget) null);
		}
		else {
			Label label = null;

			if (instructionWidget instanceof Label) {
				label = (Label) instructionWidget;
			}
			else {
				label = new Label();
				instructionWidget = label;

				add(label);
			}
			label.setText(text);
		}
	}

	public void setInstruction(Widget instructionWidget) {
		if (instructionWidget == null) {
			if (this.instructionWidget != null) {
				remove(this.instructionWidget);
			}
		}
		else {
			final Widget oldInstructionWidget = this.instructionWidget;
			this.instructionWidget = instructionWidget;

			if (oldInstructionWidget != null) {
				remove(oldInstructionWidget);
			}
			add(instructionWidget);
		}
	}

	public void setLabel(String text) {
		forLabel.setText(text);
	}

	protected void add(Widget widget) {
		listItem.add(widget);
	}

	protected void remove(Widget widget) {
		listItem.remove(widget);
	}

	protected void remove(int index) {
		listItem.remove(++index);
	}

	protected void insert(Widget widget, int beforeIndex) {
		listItem.insert(widget, ++beforeIndex);
	}

}

package org.openinsula.arena.gwt.components.client.form.field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.Label;
import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractField extends Composite implements Field {

	private final List<Validator> validators = new ArrayList<Validator>();

	private final Label fieldLabel;

	private Paragraph errorMessageParagraph;

	private Widget instructionWidget;

	public AbstractField() {
		this(null);
	}

	public AbstractField(String label) {
		fieldLabel = new Label();

		initWidget(listItem);
		setStyleName(StyleNames.FIELD);

		listItem.add(fieldLabel);
	}

	public Widget asWidget() {
		return this;
	}

	public void addValidator(Validator validator) {
		validators.add(validator);
	}

	public void removeValidator(Validator validator) {
		validators.remove(validator);
	}

	protected List<Validator> getValidators() {
		return new LinkedList<Validator>(validators);
	}

	public void setErrorMessage(String errorMessage) {
		errorMessageParagraph.setText(errorMessage);
	}

	public void setInstruction(String text) {
		if (text == null || text.trim().length() == 0) {
			setInstruction((Widget) null);
		}
		else if (instructionWidget instanceof com.google.gwt.user.client.ui.Label) {
			((com.google.gwt.user.client.ui.Label) instructionWidget).setText(text);
		}
		else {
			setInstruction(new com.google.gwt.user.client.ui.Label(text));
		}
	}

	public void setInstruction(Widget instructionWidget) {
		this.instructionWidget = instructionWidget;

		if (instructionWidget == null) {
			listItem.remove(instructionWidget);
		}
		else {
			listItem.add(instructionWidget);
		}
	}

	public void setLabel(String text) {
		fieldLabel.setText(text);
	}

}

package org.openinsula.arena.gwt.components.client.form.field;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ForLabel;
import org.openinsula.arena.gwt.components.client.ListItem;
import org.openinsula.arena.gwt.components.client.Paragraph;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Field based on a {@link ListItem}.
 * 
 * @author Lucas K Mogari
 */
public abstract class ListItemField extends Composite implements Field {

	private final ListItem listItem = new ListItem();

	private final List<Validator> validators = new ArrayList<Validator>();

	private final ForLabel fieldForLabel;

	private Paragraph errorMessageParagraph;

	private Widget instructionWidget;

	public ListItemField() {
		this(null);
	}

	public ListItemField(String label) {
		fieldForLabel = new ForLabel();

		initWidget(listItem);
		setStyleName(StyleNames.FIELD);

		listItem.add(fieldForLabel);
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
		fieldForLabel.setText(text);
	}

}

package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.event.FormChangeListener;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormEvent;
import org.openinsula.arena.gwt.components.client.ui.form.validation.ValidationResult;
import org.openinsula.arena.gwt.components.client.ui.form.validation.Validator;
import org.openinsula.arena.gwt.components.client.ui.utils.EqualsUtils;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractFormModel implements FormModel {

	private String title;

	private String description;

	private String errorMessage;

	private Action primaryAction;

	private final List<FormChangeListener> listeners = new LinkedList<FormChangeListener>();

	private final List<Validator> validators = new LinkedList<Validator>();

	public AbstractFormModel() {
	}

	public AbstractFormModel(String title) {
		this.title = title;
	}

	public AbstractFormModel(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public void addFormListener(FormChangeListener listener) {
		listeners.add(listener);
	}

	public void removeFormListener(FormChangeListener listener) {
		listeners.remove(listener);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void resetFields() {
		setErrorMessage(null);
	}

	public void setErrorMessage(String warningMessage) {
		final String oldWarningMessage = errorMessage;
		errorMessage = warningMessage;

		if (EqualsUtils.isDifferent(oldWarningMessage, warningMessage)) {
			fireFormChanged(new FormEvent(this, FormEvent.ERROR_MESSAGE));
		}
	}

	public boolean isValid() {
		boolean valid = true;

		for (final Validator validator : validators) {
			final ValidationResult result = validator.validate(null);

			if (!result.isValid()) {
				valid = false;

				setErrorMessage(result.getMessage());
				break;
			}
		}

		return valid;
	}

	public void addValidator(Validator validator) {
		validators.add(validator);
	}

	public void removeValidator(Validator validator) {
		validators.remove(validator);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		final String oldDescription = this.description;
		this.description = description;

		if (EqualsUtils.isDifferent(oldDescription, description)) {
			fireFormChanged(new FormEvent(this, FormEvent.DESCRIPTION));
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		final String oldTitle = this.title;
		this.title = title;

		if (EqualsUtils.isDifferent(oldTitle, title)) {
			fireFormChanged(new FormEvent(this, FormEvent.TITLE));
		}
	}

	public void fireFormChanged() {
		fireFormChanged(new FormEvent(this, FormEvent.NEW_FORM));
	}

	public void fireFormItemsChanged() {
		fireFormChanged(new FormEvent(this, FormEvent.FORM_ITEMS_CHANGED));
	}

	protected void fireFormChanged(FormEvent e) {
		for (final FormChangeListener listener : listeners) {
			listener.formChanged(e);
		}
	}

	public Action getPrimaryAction() {
		return primaryAction;
	}

	public void setPrimaryAction(Action primaryAction) {
		this.primaryAction = primaryAction;
	}

}

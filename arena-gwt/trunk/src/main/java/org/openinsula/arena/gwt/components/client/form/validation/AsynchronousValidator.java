package org.openinsula.arena.gwt.components.client.form.validation;

/**
 * @author Lucas K Mogari
 */
public abstract class AsynchronousValidator implements Validator {

	private final ValidationResultNotifier notifier;

	public AsynchronousValidator(ValidationResultNotifier notifier) {
		this.notifier = notifier;
	}

	protected abstract void validateAsynchronously(Object value);

	protected void notifyValueValidated(ValidationResult validationResult) {
		notifier.onValueValidated(validationResult);
	}

	public final ValidationResult validate(Object value) {
		return null;
	}

}

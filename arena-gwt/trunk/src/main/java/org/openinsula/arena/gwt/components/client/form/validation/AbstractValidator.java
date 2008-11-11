package org.openinsula.arena.gwt.components.client.form.validation;

import com.google.gwt.user.client.Timer;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractValidator implements Validator {

	private int timeout;

	private Timer timer;

	public AbstractValidator() {
	}

	public AbstractValidator(int timeout) {
		this.timeout = timeout;
	}

	protected abstract void doValidation(Object value, ValidationCallback callback);

	public final void validate(Object value, ValidationCallback callback) {
		if (timeout > 0) {
			startTimer(callback);
		}
		doValidation(value, callback);
	}

	protected void stopValidation(ValidationCallback callback) {
		callback.onValueValidated(new ValidationTimedOut(this));
	}

	private void startTimer(final ValidationCallback callback) {
		timer = new Timer() {
			@Override
			public void run() {
				stopValidation(callback);
			}
		};
		timer.schedule(timeout);
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}

package org.openinsula.arena.gwt.client.ui.form;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
class FormListenerSupport {

	private final List<FormListener> formListeners = new LinkedList<FormListener>();

	private boolean formStarted;

	public void addFormListener(FormListener listener) {
		formListeners.add(listener);
	}

	public void removeFormListener(FormListener listener) {
		formListeners.remove(listener);
	}

	public void fireFormStarted() {
		if (formStarted) {
			return;
		}

		formStarted = true;

		for (final FormListener listener : formListeners) {
			listener.onFormStarted();
		}
	}

	public void fireFormFinished() {
		if (!formStarted) {
			return;
		}

		formStarted = false;

		for (final FormListener listener : formListeners) {
			listener.onFormFinished();
		}
	}

	public List<FormListener> getFormListeners() {
		return formListeners;
	}

}

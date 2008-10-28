package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.application.Application;
import org.openinsula.arena.gwt.client.application.history.HistoryChangeInterceptor;
import org.openinsula.arena.gwt.client.application.history.HistoryDispatcher;
import org.openinsula.arena.gwt.client.ui.ConfirmationRequiredHyperlink;

import com.google.gwt.user.client.Window;

/**
 * @author Lucas K Mogari
 */
public abstract class FormStateController extends FormListenerSupport implements FormListener,
		HistoryChangeInterceptor, HistoryDispatcher {

	private FormPanel formPanel;

	private FormModel currentFormModel;

	public FormStateController() {
	}

	public FormStateController(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	protected abstract FormModel getFormModel(String historyToken);

	public void forwardHistoryChanged(String historyToken) {
		addFormListener(this);

		showForm(historyToken);
	}

	public void onFormFinished() {
		final Application application = Application.getInstance();

		application.getHistoryController().removeHistoryChangeInterceptor(this);
		application.getContext().setAttribute(ConfirmationRequiredHyperlink.CONFIRMATION_MESSAGE_ATTRIBUTE, null);
	}

	public boolean preHistoryChange(String historyToken) {
		final String message = Application.getInstance().getContext().getAttribute(
				ConfirmationRequiredHyperlink.CONFIRMATION_MESSAGE_ATTRIBUTE);
		boolean confirm = true;

		if (message == null || message.trim().length() == 0) {
			fireFormFinished();
		}
		else {
			confirm = Window.confirm(message);

			if (confirm) {
				fireFormFinished();
			}
		}

		return confirm;
	}

	public void postHistoryChange(String historyToken, boolean success) {
		if (success) {
			removeFormListener(this);
		}
	}

	protected void showForm(String historyToken) {
		final Application application = Application.getInstance();
		application.getHistoryController().addHistoryChangeInterceptor(this);

		currentFormModel = getFormModel(historyToken);

		formPanel.setModel(currentFormModel);

		application.getNavigationController().show(formPanel.toWidget());

		fireFormStarted();
	}

	public FormModel getCurrentFormModel() {
		return currentFormModel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

}

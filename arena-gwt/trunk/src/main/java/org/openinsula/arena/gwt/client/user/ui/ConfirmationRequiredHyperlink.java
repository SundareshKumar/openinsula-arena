package org.openinsula.arena.gwt.client.user.ui;

import org.openinsula.arena.gwt.client.application.Application;
import org.openinsula.arena.gwt.client.application.ApplicationContext;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 * @author Lucas K Mogari
 */
public class ConfirmationRequiredHyperlink extends Hyperlink {

	public static final String CONFIRMATION_MESSAGE_ATTRIBUTE = "confirmationMessageAttribute";

	public ConfirmationRequiredHyperlink() {
	}

	public ConfirmationRequiredHyperlink(String text, boolean asHTML, String targetHistoryToken) {
		super(text, asHTML, targetHistoryToken);
	}

	public ConfirmationRequiredHyperlink(String text, String targetHistoryToken) {
		super(text, targetHistoryToken);
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (DOM.eventGetType(event) == Event.ONCLICK) {
			final ApplicationContext context = Application.getInstance().getContext();
			final String message = (String) context.getAttribute(CONFIRMATION_MESSAGE_ATTRIBUTE);

			if (message != null && message.trim().length() > 0 && !Window.confirm(message)) {
				event.preventDefault();
				return;
			}
			else {
				context.setAttribute(CONFIRMATION_MESSAGE_ATTRIBUTE, null);
			}
		}

		super.onBrowserEvent(event);
	}

}

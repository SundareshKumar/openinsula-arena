package org.openinsula.arena.echo2.component.dialog;

import java.util.Collection;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionListener;

public abstract class DialogApplicationInstance extends ApplicationInstance {

	private DialogUtils dialogUtils;

	public DialogApplicationInstance() {
	}

	public DialogApplicationInstance(DialogUtils dialogUtils) {
		this.dialogUtils = dialogUtils;
	}

	public void showMessageDialog(final String message) {
		dialogUtils.showMessageDialog(message);
	}

	public void showMessageDialog(final String message, final ActionListener okListener) {
		dialogUtils.showMessageDialog(message, okListener);
	}

	public void showConfirmDialog(final String message, final ActionListener confirmListener) {
		dialogUtils.showConfirmDialog(message, confirmListener);
	}

	public void showOptionDialog(final String message, final Component... components) {
		dialogUtils.showOptionDialog(message, components);
	}

	public void showOptionDialog(final String message, final Collection<Component> components) {
		dialogUtils.showOptionDialog(message, components.toArray(new Component[] {}));
	}

	public DialogUtils getDialogUtils() {
		return dialogUtils;
	}

	public void setDialogUtils(DialogUtils dialogUtils) {
		this.dialogUtils = dialogUtils;
	}

}

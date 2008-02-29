package org.openinsula.arena.echo2.component.dialog;

import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;

public final class EchoDialogs {

	private static DialogUtils dialogUtils;
	
    public static DialogUtils getDialogUtils() {
        return dialogUtils; 
    }

    public static void showMessageDialog(final String message) {
        getDialogUtils().showMessageDialog(message);
    }

    public static void showMessageDialog(final String message, final ActionListener okListener) {
        getDialogUtils().showMessageDialog(message, okListener);
    }

    public static void showConfirmDialog(final String message, final ActionListener confirmListener) {
        getDialogUtils().showConfirmDialog(message, confirmListener);
    }

    public static void showOptionDialog(final String message, final Component ... components) {
        getDialogUtils().showOptionDialog(message, components);
    }

    public static void showOptionDialog(final String message, final Collection<Component> components) {
        getDialogUtils().showOptionDialog(message, components.toArray(new Component[] {}));
    }

    @Autowired
	public static void setDialogUtils(DialogUtils dialogUtils) {
		EchoDialogs.dialogUtils = dialogUtils;
	}
}

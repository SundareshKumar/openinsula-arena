package org.openinsula.arena.gwt.util.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasFocus;

public abstract class FocusUtils {

	public static void setFocus(final HasFocus widget) {
		if (widget != null) {
			DeferredCommand.addCommand(new Command() {
				public void execute() {
					widget.setFocus(true);
				}
			});
		}
	}

}

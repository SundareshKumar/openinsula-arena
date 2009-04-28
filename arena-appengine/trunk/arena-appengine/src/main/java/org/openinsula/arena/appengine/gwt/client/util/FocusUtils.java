package org.openinsula.arena.appengine.gwt.client.util;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.TextBoxBase;

public abstract class FocusUtils {

	public static void setFocus(final Focusable widget) {
		if (widget != null) {
			DeferredCommand.addCommand(new Command() {
				public void execute() {
					widget.setFocus(true);
				}
			});
		}
	}

	public static void selectAllOnFocus(final TextBoxBase target) {
		target.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(final FocusEvent event) {
				target.setCursorPos(target.getText().length());
				target.selectAll();
			}
		});
	}

}

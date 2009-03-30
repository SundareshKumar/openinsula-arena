package org.openinsula.arena.gwt.util.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FocusListenerAdapter;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

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
	
	public static void selectAllOnFocus(final TextBoxBase target) {
		target.addFocusListener(new FocusListenerAdapter() {
			public void onFocus(final Widget sender) {
				target.setCursorPos(target.getText().length());
				target.selectAll();
			}
		});
	}

}

package org.openinsula.arena.gwt.components.client.ui.form.field.filter;

import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class KeyboardFilteringListener implements KeyboardFilter, KeyboardListener {

	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
	}

	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		if (!allow(sender, keyCode, modifiers) && sender instanceof TextBoxBase) {
			((TextBoxBase) sender).cancelKey();
		}
	}

	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
	}

}

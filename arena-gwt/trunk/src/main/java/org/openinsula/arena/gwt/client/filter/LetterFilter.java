package org.openinsula.arena.gwt.client.filter;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LetterFilter extends CommonAllowedKeysKeyboardListener implements EventListener {

	public void onBrowserEvent(Event event) {
	}

	@Override
	public boolean allow(Widget sender, char keyCode, int modifiers) {
		return Character.isLetter(keyCode) || super.allow(sender, keyCode, modifiers);
	}

}

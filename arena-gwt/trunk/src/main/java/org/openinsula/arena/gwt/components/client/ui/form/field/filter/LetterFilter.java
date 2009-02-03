package org.openinsula.arena.gwt.components.client.ui.form.field.filter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LetterFilter extends CommonAllowedKeysKeyboardListener {

	@Override
	public boolean allow(Widget sender, char keyCode, int modifiers) {
		return Character.isLetter(keyCode) || super.allow(sender, keyCode, modifiers);
	}

}

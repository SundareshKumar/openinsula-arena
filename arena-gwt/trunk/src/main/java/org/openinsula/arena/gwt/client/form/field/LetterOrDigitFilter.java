package org.openinsula.arena.gwt.client.form.field;

import org.openinsula.arena.gwt.client.filter.CommonAllowedKeysKeyboardListener;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LetterOrDigitFilter extends CommonAllowedKeysKeyboardListener {

	@Override
	public boolean allow(Widget sender, char keyCode, int modifiers) {
		return Character.isLetterOrDigit(keyCode) || super.allow(sender, keyCode, modifiers);
	}

}

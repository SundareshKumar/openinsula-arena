package org.openinsula.arena.gwt.client.filter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class CommonAllowedKeysKeyboardListener extends KeyboardFilteringListener {

	/**
	 * Common allowed keys like arrow keys, home, end and backspace.
	 */
	private static final char[] ALLOWED_KEYS = new char[] { 8, 9, 13, 35, 36, 37, 38, 39, 40, 46 };

	private static final char[] CTRL_ALLOWED_KEYS = new char[] { 8, 46, 97, 99, 118, 122 };

	public boolean allow(Widget sender, char keyCode, int modifiers) {
		switch (modifiers) {
		case 0:
			return containsKey(keyCode, ALLOWED_KEYS);

		case MODIFIER_CTRL:
			return containsKey(keyCode, CTRL_ALLOWED_KEYS);
		}
		return false;
	}

	private boolean containsKey(char key, char[] array) {
		for (final char key2 : array) {
			if (key2 == key) {
				return true;
			}
		}
		return false;
	}

}

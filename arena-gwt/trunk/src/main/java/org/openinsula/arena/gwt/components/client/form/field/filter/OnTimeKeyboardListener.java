package org.openinsula.arena.gwt.components.client.form.field.filter;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class OnTimeKeyboardListener extends Timer implements KeyboardListener {

	private final int delay;

	public OnTimeKeyboardListener() {
		this(500);
	}

	public OnTimeKeyboardListener(int delay) {
		this.delay = delay;
	}

	@Override
	public final void run() {
	}

	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
	}

	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		cancel();
		schedule(delay);
	}

	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
	}

}

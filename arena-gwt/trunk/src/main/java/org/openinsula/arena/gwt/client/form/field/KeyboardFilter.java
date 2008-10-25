package org.openinsula.arena.gwt.client.form.field;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface KeyboardFilter {

	public boolean allow(Widget sender, char keyCode, int modifiers);

}

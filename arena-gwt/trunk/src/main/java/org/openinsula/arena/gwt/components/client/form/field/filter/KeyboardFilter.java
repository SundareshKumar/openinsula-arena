package org.openinsula.arena.gwt.components.client.form.field.filter;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface KeyboardFilter {

	public boolean allow(Widget sender, char keyCode, int modifiers);

}

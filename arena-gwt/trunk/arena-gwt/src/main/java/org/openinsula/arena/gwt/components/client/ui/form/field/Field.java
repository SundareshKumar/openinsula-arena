package org.openinsula.arena.gwt.components.client.ui.form.field;

import org.openinsula.arena.gwt.components.client.ui.form.FormItem;

/**
 * @author Lucas K Mogari
 */
public interface Field extends FormItem {

	public static final String LABEL_ATTRIBUTE = "labelAttribute";

	public static final String INSTRUCTION_ATTRIBUTE = "instructionAttribute";

	public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessageAttribute";

	public static final String ENABLED_ATTRIBUTE = "enabledAttribute";

	public static final String VISIBLE_ATTRIBUTE = "visibleAttribute";

	public static final String FIELD_STYLE_NAME = "Field";

	public static final String LABEL_STYLE_NAME = "Description";

	public static final String INSTRUCTION_STYLE_NAME = "Instruction";

	public static final String ERROR_STYLE_NAME = "Error";

	public static final String ON_FOCUS_STYLE_NAME = "OnFocus";

	public void setAttribute(String name, Object attribute);

	public <T> T getAttribute(String name);

	public boolean isValid();

}

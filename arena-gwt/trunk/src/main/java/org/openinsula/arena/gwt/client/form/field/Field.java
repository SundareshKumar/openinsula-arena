package org.openinsula.arena.gwt.client.form.field;

import org.openinsula.arena.gwt.client.form.FormItem;

/**
 * @author Lucas K Mogari
 */
public interface Field extends FormItem {

	public static final String FIELD_STYLE_NAME = "Field";

	public static final String LABEL_STYLE_NAME = "Description";

	public static final String INSTRUCTION_STYLE_NAME = "Instruction";

	public static final String ERROR_STYLE_NAME = "Error";

	public static final String ON_FOCUS_STYLE_NAME = "OnFocus";

	public boolean isValid();

}

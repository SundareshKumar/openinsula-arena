package org.openinsula.arena.gwt.components.client.form.field;

import org.openinsula.arena.gwt.components.client.form.FormItem;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface Field extends FormItem {

	public static interface StyleNames {

		public static final String FIELD = "arena-Field";

		public static final String LABEL = "arena-Field-description";

		public static final String INSTRUCTION = "arena-Field-instruction";

		public static final String ERROR = "arena-Field-error";

		public static final String ERROR_MESSAGE = "arena-Field-error-message";

		public static final String ON_FOCUS = "arena-Field-on-focus";

	}

	public void setLabel(String text);

	public void setInstruction(String text);

	public void setInstruction(Widget instructionWidget);

	public void setErrorMessage(String text);

	public void addValidator(Validator validator);

	public void removeValidator(Validator validator);

	public boolean isValid();

}

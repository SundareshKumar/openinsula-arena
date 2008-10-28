package org.openinsula.arena.gwt.client.ui.form;

import java.util.EventObject;

/**
 * @author Lucas K Mogari
 */
public class FormEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public static final int NEW_FORM = 0;

	public static final int TITLE = 1;

	public static final int DESCRIPTION = 2;

	public static final int ERROR_MESSAGE = 3;

	public static final int FORM_ITEMS_CHANGED = 4;

	private int type;

	public FormEvent(FormModel source) {
		super(source);
	}

	public FormEvent(Object source, int type) {
		super(source);

		this.type = type;
	}

	@Override
	public FormModel getSource() {
		return (FormModel) super.getSource();
	}

	public int getType() {
		return type;
	}

}

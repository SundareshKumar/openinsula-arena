package org.openinsula.arena.gwt.components.client.form.field;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface SimpleField extends Field {

	public <T extends Widget> T getFieldWidget();

	public void setValue(Object value);

	public <T> T getValue();

}

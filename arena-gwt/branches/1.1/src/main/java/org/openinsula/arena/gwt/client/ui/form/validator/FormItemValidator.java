package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.Widget;


public interface FormItemValidator<T extends Widget> {

	boolean validate(T widget);

	String getInvalidValueMessage();

}

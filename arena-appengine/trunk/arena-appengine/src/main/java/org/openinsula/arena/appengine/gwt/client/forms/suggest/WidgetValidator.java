package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem;
import org.openinsula.arena.appengine.gwt.client.forms.validator.ValidatorAction;
import org.openinsula.arena.appengine.gwt.client.forms.validator.ValidatorChain;

import com.google.gwt.user.client.ui.Widget;

public interface WidgetValidator<W extends Widget> {

	void validate(W widget, ValidatorChain<W> chain, ValidatorAction action);

	void setFormItem(FormItem formItem);

	String getInvalidValueMessage();

}

package org.openinsula.arena.appengine.gwt.client.forms.validator;

import org.openinsula.arena.appengine.gwt.client.forms.suggest.WidgetValidator;

import com.google.gwt.user.client.ui.Widget;

public interface ValidatorChain<W extends Widget> {

	public void doChain(W widget, ValidatorAction action);

	public void validate(W widget, ValidatorAction action);

	public void addValidator(W widget, WidgetValidator<W> validator);

	public void clearValidator(W widget);

	public boolean isLastNode();

	public int size();

}

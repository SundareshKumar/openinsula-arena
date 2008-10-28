package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.Widget;

public interface ValidatorChain<W extends Widget> {

	public <T> void doChain(W widget, ValidatorAction action);

	public void validate(W widget, ValidatorAction action);

	public void addValidator(FormItemValidator<W> validator);

	public boolean isLastNode();

	public int size();

}

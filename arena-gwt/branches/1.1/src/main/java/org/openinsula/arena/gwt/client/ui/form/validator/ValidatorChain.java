package org.openinsula.arena.gwt.client.ui.form.validator;

import com.google.gwt.user.client.ui.Widget;

public interface ValidatorChain<W extends Widget> {

	public <T> void doChain(W widget);

	public void validate(W widget);

	public void addValidator(FormItemValidatorNew<W> validator);

	public int size();

}

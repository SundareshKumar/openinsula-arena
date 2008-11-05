package org.openinsula.arena.gwt.components.client.form;

import java.util.List;

import org.openinsula.arena.gwt.components.client.form.validation.Validator;

/**
 * @author Lucas K Mogari
 */
public interface NewFormProvider {

	public String getTitle();

	public String getDescription();

	public List<FormItem> getFormItems();

	public List<Validator> getValidators();

	public Action getPrimaryAction();

	public List<Action> getSecondaryActions();

}

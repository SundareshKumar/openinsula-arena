package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.form.validation.Validator;

/**
 * @author Lucas K Mogari
 */
public interface Form {

	public void setTitle(String title);

	public void setDescription(String description);

	public void setErrorMessage(String errorMessage);

	public void addValidator(Validator validator);

	public void removeValidator(Validator validator);

	public void addFormItem(FormItem formItem);

	public void addFormItem(int index, FormItem formItem);

	public void removeFormItem(FormItem formItem);

	public void setForm(NewFormProvider formProvider);

	public void clear();

	public boolean isValid();

}

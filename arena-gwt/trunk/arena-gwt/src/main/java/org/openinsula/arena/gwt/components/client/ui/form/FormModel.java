package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.Iterator;

import org.openinsula.arena.gwt.components.client.ui.form.event.FormChangeListener;
import org.openinsula.arena.gwt.components.client.ui.form.validation.Validator;

/**
 * @author Lucas K Mogari
 */
public interface FormModel {

	public String getTitle();

	public String getDescription();

	public String getErrorMessage();

	public Action getPrimaryAction();

	public Iterator<Action> getSecondaryActions();

	public Iterator<FormItem> getFormItems();

	public void addValidator(Validator validator);

	public void removeValidator(Validator validator);

	public void addFormListener(FormChangeListener listener);

	public void removeFormListener(FormChangeListener listener);

	public void resetFields();

	public boolean isValid();

}

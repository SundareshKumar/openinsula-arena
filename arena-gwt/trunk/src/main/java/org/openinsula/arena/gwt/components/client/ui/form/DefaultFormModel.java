package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.field.Field;
import org.openinsula.arena.gwt.components.client.ui.form.field.FieldUtils;
import org.openinsula.arena.gwt.components.client.ui.form.validation.ValidationResult;
import org.openinsula.arena.gwt.components.client.ui.form.validation.Validator;

/**
 * @author Lucas K Mogari
 */
public class DefaultFormModel extends AbstractFormModel {

	private final List<Action> secondaryActions = new LinkedList<Action>();

	private final List<FormItem> formItems = new LinkedList<FormItem>();

	public DefaultFormModel() {
	}

	public DefaultFormModel(String title) {
		super(title);
	}

	public DefaultFormModel(String title, String description) {
		super(title, description);
	}

	{
		addValidator(new FieldsValidator());
	}

	public Iterator<Action> getSecondaryActions() {
		return secondaryActions.iterator();
	}

	@Override
	public void resetFields() {
		super.resetFields();

		for (final FormItem formItem : formItems) {
			if (formItem instanceof Field) {
				FieldUtils.setValue((Field) formItem, null);
			}
		}
	}

	public void addSecondaryAction(Action secondaryAction) {
		secondaryActions.add(secondaryAction);
	}

	public void clearFormItems() {
		formItems.clear();
	}

	public void appendFormItem(FormItem formItem) {
		formItems.add(formItem);
	}

	public void appendFormItems(FormItemProvider formItemProvider) {
		formItems.addAll(formItemProvider.getFormItems());
	}

	public void removeFormItem(FormItem formItem) {
		formItems.remove(formItem);
	}

	public Iterator<FormItem> getFormItems() {
		return formItems.iterator();
	}

	private final class FieldsValidator implements Validator {

		public ValidationResult validate(Object value) {
			boolean valid = true;
			String message = null;

			for (final FormItem formItem : formItems) {
				if (formItem instanceof Field) {
					final Field field = (Field) formItem;

					if (!field.isValid() && valid) {
						valid = false;
					}
				}
			}

			if (!valid) {
				message = "Erro nos campos do formulário.";
			}

			return new ValidationResult(valid, message);
		}

	}

}

package org.openinsula.arena.gwt.client.components.test.search;

import org.openinsula.arena.gwt.client.ui.FocusUtils;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDetailsSearchFormTemplate<T> extends Composite {

	protected HasFocus nextFocusableComponent;

	private HasFocus[] focusSequence;

	private AbstractSearchFormTemplate<T> searchFormTemplate;

//	private FormItem<?>[] validationSequence;
//	private ValidatorAction validatorAction;

	public AbstractDetailsSearchFormTemplate(AbstractSearchFormTemplate<T> parent, HasFocus nextFocusableComponent) {
		super();
		this.nextFocusableComponent = nextFocusableComponent;
		this.searchFormTemplate = parent;
//		this.validationSequence = createValidationSequence();
//
//		for (FormItem<?> form : createValidationSequence()) {
//			form.validate(new ValidatorActionAdapter() {
//				public void onSuccess() {
//					validar o proximo -> validar o proximo -> no ultimo executar o action;
//				}
//			});
//		}

		initWidget(buildForm());
		initFocus();
		initValidators();
	}

//	private ValidatorAction createValidator(final FormItem<?> widget, final FormItem<?> nextWidget) {
//		widget.validate(new ValidatorActionAdapter() {
//			public void onSuccess() {
//				nextWidget.validate(action);
//			}
//		});
//	}

	protected abstract Widget buildForm();

	protected abstract void modelToView(T bean, boolean editionMode);

	protected abstract void viewToModel(T editInstance, ViewToModelCallback<T> callback);

	protected abstract void clear();

	protected abstract HasFocus[] createFocusSequence();

	protected abstract void initValidators();

	protected abstract void validateView(ValidatorAction action);

//	protected abstract FormItem<?>[] createValidationSequence();
//
//	protected void validateView(ValidatorAction action) {
//
//	}

	protected void focus(final HasFocus widget) {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				widget.setFocus(true);
			}
		});
	}

	private void initFocus() {
		focusSequence = createFocusSequence();
		if (focusSequence != null) {
			for (int i = 0; i < focusSequence.length - 1; i++) {
				FocusUtils.nextOnEnter(focusSequence[i], focusSequence[i + 1]);
			}

			if (nextFocusableComponent != null) {
				FocusUtils.nextOnEnter(focusSequence[focusSequence.length - 1], nextFocusableComponent);
			}
		}
	}

	public AbstractSearchFormTemplate<T> getSearchFormTemplate() {
		return searchFormTemplate;
	}

	HasFocus[] getFocusSequence() {
		return focusSequence;
	}

}

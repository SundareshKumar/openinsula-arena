package org.openinsula.arena.gwt.client.components.test.search;

import org.openinsula.arena.gwt.client.ui.FocusUtils;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDetailsSearchFormTemplate<T> extends Composite {

	protected HasFocus nextFocusableComponent;

	private HasFocus[] focusSequence;

	private AbstractSearchFormTemplate<T> searchFormTemplate;

	public AbstractDetailsSearchFormTemplate(AbstractSearchFormTemplate<T> parent, HasFocus nextFocusableComponent) {
		super();
		this.nextFocusableComponent = nextFocusableComponent;
		this.searchFormTemplate = parent;
		initWidget(buildForm());
		initFocus();
		initValidators();
	}

	protected abstract Widget buildForm();

	protected abstract void modelToView(T bean, boolean editionMode);

	protected abstract T viewToModel(T editInstance);

	protected abstract void clear();

	protected abstract HasFocus[] createFocusSequence();

	protected abstract void initValidators();

	protected abstract boolean validateView();

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

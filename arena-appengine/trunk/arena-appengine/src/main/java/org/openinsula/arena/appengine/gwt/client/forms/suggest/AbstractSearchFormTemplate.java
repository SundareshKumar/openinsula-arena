package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem;
import org.openinsula.arena.appengine.gwt.client.forms.validator.ValidatorAction;
import org.openinsula.arena.appengine.gwt.client.forms.validator.ValidatorChain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSearchFormTemplate<T> extends FocusComposite implements
		WidgetValidator<AbstractSearchFormTemplate<T>> {

	private DeckPanel forms;

	private T editInstance;

	private SearchForm searchForm;

	protected abstract String getSuggestBoxHint();

	private AbstractDetailsSearchFormTemplate<T> detailsForm;

	private List<SearchFormActionListener<T>> actionListeners;

	private List<SearchFormTransitionListener<T>> transitionListeners;

	protected abstract RemoteBeanSuggestOracle<T> getSuggestOracle();

	protected abstract String getSuggestBoxContent(T bean);

	protected abstract T createNewEditableInstance(String suggestBoxContent);

	protected abstract AbstractDetailsSearchFormTemplate<T> createDetailsSearchForm(HasFocus nextFocusableComponent);

	protected abstract String getErrorMessage();

	public AbstractSearchFormTemplate(HasFocus nextFocusableComponent) {
		forms = new DeckPanel();
		initWidget(forms);

		searchForm = new SearchForm();
		detailsForm = createDetailsSearchForm(nextFocusableComponent);
		// FocusUtils.nextOnEnter(searchForm, nextFocusableComponent);

		forms.add(searchForm);
		forms.add(detailsForm);

		searchForm.addEditListener(new ClickListener() {
			public void onClick(Widget sender) {
				if (editInstance != null) {
					getDetailsForm().modelToView(editInstance, true);
					showDetailForm(true);
				}
			}
		});

		showSearchForm();
	}

	/**
	 * @param editionMode
	 * @return
	 */
	protected boolean fireDetailFormShowed(boolean editionMode) {
		for (SearchFormTransitionListener<T> listener : transitionListeners()) {
			if (!listener.beforeDetailFormShow(detailsForm, editionMode)) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}

	protected boolean fireSearchFormShowed() {
		for (SearchFormTransitionListener<T> listener : transitionListeners()) {
			if (!listener.beforeSearchFormShow(this)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	public void addTransitionListener(SearchFormTransitionListener<T> transitionListener) {
		transitionListeners().add(transitionListener);
	}

	public void removeTransitionListener(SearchFormTransitionListener<T> transitionListener) {
		transitionListeners().remove(transitionListener);
	}

	private List<SearchFormTransitionListener<T>> transitionListeners() {
		if (transitionListeners == null) {
			transitionListeners = new ArrayList<SearchFormTransitionListener<T>>();
		}
		return transitionListeners;
	}

	public void addActionListener(SearchFormActionListener<T> actionListener) {
		actionListeners().add(actionListener);
	}

	public void removeActionListener(SearchFormActionListener<T> actionListener) {
		actionListeners().remove(actionListener);
	}

	public void setModel(final T model) {
		this.editInstance = model;

		searchForm.modelToView();
		showSearchForm();
	}

	public void showSearchForm() {
		if (fireSearchFormShowed()) {
			forms.showWidget(0);
		}
	}

	public void edit() {
		if (editInstance == null) {
			throw new IllegalStateException("Model is not ready!");
		}

		detailsForm.modelToView(editInstance, true);
		showDetailForm(true);
	}

	public void clear() {
		editInstance = null;
		searchForm.clear();
		detailsForm.clear();
		showSearchForm();
	}

	private List<SearchFormActionListener<T>> actionListeners() {
		if (actionListeners == null) {
			actionListeners = new ArrayList<SearchFormActionListener<T>>();
		}
		return actionListeners;
	}

	public void showDetailForm(final boolean editionMode) {
		if ((isEditionAllowed() || isInsertionAllowed()) && fireDetailFormShowed(editionMode)) {
			forms.showWidget(1);
		}
	}

	private void fireModelSelected(T bean) {
		for (SearchFormActionListener<T> listener : actionListeners()) {
			listener.onModelSelected(bean);
		}
	}

	private void fireNewEntry(T bean) {
		for (SearchFormActionListener<T> listener : actionListeners()) {
			listener.onNewEntry(bean);
		}
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		searchForm.addFocusListener(listener);
		HasFocus[] focusSequence = getDetailsForm().getFocusSequence();
		if (focusSequence != null) {
			focusSequence[focusSequence.length - 1].addFocusListener(listener);
		}
	}

	@Override
	public void removeFocusListener(FocusListener listener) {
		searchForm.removeFocusListener(listener);
	}

	public class SearchForm extends FocusComposite implements BeanSuggestBoxListener<T> {

		protected final BeanSuggestBox<T> suggestBox;

		private Hyperlink editLink;

		private boolean insertionAllowed = false;

		private boolean editionAllowed = false;

		public void setInsertionAllowed(boolean editable) {
			this.insertionAllowed = editable;
		}

		public void addEditListener(ClickListener listener) {
			editLink.addClickListener(listener);
		}

		public SearchForm() {
			suggestBox = new BeanSuggestBox<T>(getSuggestOracle(), this);
			editLink = new Hyperlink("Editar", "");
			editLink.setVisible(false);

			HorizontalPanel panel = new HorizontalPanel();
			panel.add(suggestBox);
			panel.add(editLink);

			initWidget(panel);
			setInsertionAllowed(false);

			modelToView();
		}

		public void clear() {
			suggestBox.setText("");
		}

		void modelToView() {
			if (editInstance == null) {
				suggestBox.getSuggestBox().setText(null);
				editLink.setVisible(false);
			}
			else {
				editLink.setVisible(isEditionAllowed());
				suggestBox.getSuggestBox().setText(getSuggestBoxContent(editInstance));
			}

			suggestBox.setFocus(true);
		}

		public void onBeanSelect(final T result) {
			editInstance = result;
			fireModelSelected(result);
			editLink.setVisible(isEditionAllowed());
		}

		public void onNewEntry(final String value) {
			if (isInsertionAllowed()) {
				editInstance = createNewEditableInstance(value);
				detailsForm.modelToView(editInstance, false);
				fireNewEntry(editInstance);
				showDetailForm(false);
			}
		}

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if (keyCode != KeyboardListener.KEY_ENTER) {
				editInstance = null;
				editLink.setVisible(false);
			}
		}

		@Override
		public void setFocus(boolean op) {
			suggestBox.setFocus(op);
		}

		@Override
		public void addFocusListener(FocusListener listener) {
			suggestBox.addFocusListener(listener);
		}

		@Override
		public void addKeyboardListener(KeyboardListener listener) {
			suggestBox.addKeyboardListener(listener);
		}

		@Override
		public void removeFocusListener(FocusListener listener) {
			suggestBox.removeFocusListener(listener);
		}

		@Override
		public void removeKeyboardListener(KeyboardListener listener) {
			suggestBox.removeKeyboardListener(listener);
		}

		public void setText(String value) {
			suggestBox.setText(value);
		}

		public void showSuggestions() {
			suggestBox.showSuggestions();
		}

		private boolean hasText() {
			return suggestBox.getText().trim().length() > 0;
		}

		public boolean isInsertionAllowed() {
			return insertionAllowed && hasText();
		}

		public boolean isEditionAllowed() {
			return editionAllowed;
		}

		public void setEditionAllowed(boolean editionAllowed) {
			this.editionAllowed = editionAllowed;
		}

		public boolean isEnabled() {
			return suggestBox.isEnabled();
		}

		public void setEnabled(boolean enabled) {
			suggestBox.setEnabled(enabled);
		}

	}

	public void setText(String value) {
		searchForm.setText(value);
	}

	public AbstractDetailsSearchFormTemplate<T> getDetailsForm() {
		return detailsForm;
	}

	@Override
	public void setFocus(boolean op) {
		searchForm.setFocus(op);
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setEnabled(boolean editable) {
		searchForm.setEnabled(editable);
	}

	public boolean isEnabled() {
		return searchForm.isEnabled();
	}

	public boolean isInsertionAllowed() {
		return searchForm.isInsertionAllowed();
	}

	public void setInsertionAllowed(boolean insertionAllowed) {
		searchForm.setInsertionAllowed(insertionAllowed);
	}

	public boolean isEditionAllowed() {
		return searchForm.isEditionAllowed();
	}

	public void setEditionAllowed(boolean editionAllowed) {
		searchForm.setEditionAllowed(editionAllowed);
	}

	public void validateView(ValidatorAction action) {
		switch (forms.getVisibleWidget()) {
		case 0:
			GWT.log("================validando suggest", null);
			if (editInstance != null) {
				GWT.log("executou o actoin no validateView() do abstractSearchForm, tipo do action: "
						+ action.getClass().getName(), null);
				action.onSuccess();
			}
			else {
				action.onFail();
			}
			break;
		case 1:
			GWT.log("================validando details", null);
			detailsForm.validateView(action);
			break;
		}
	}

	private FormItem formItem;

	public void validate(final AbstractSearchFormTemplate<T> widget,
			final ValidatorChain<AbstractSearchFormTemplate<T>> chain, final ValidatorAction action) {

		validateView(new ValidatorAction() {
			public void onFail() {
				formItem.parentSection().subtitle(getInvalidValueMessage());
				formItem.required(false);
			}

			public void onSuccess() {
				if (chain.isLastNode()) {
					GWT.log("executou o action dentro do validate() do SearchForm, tipo do action: "
							+ action.getClass().getName(), null);
					action.onSuccess();
				}
				else {
					chain.doChain(widget, action);
				}
				formItem.required(true);
			}
		});
	}

	public String getInvalidValueMessage() {
		return "Campo obrigat\u00f3rio";
	}

	public void setFormItem(FormItem formItem) {
		this.formItem = formItem;
	}

	public void getEditInstance(final GetValueAction<T> action) {
		switch (forms.getVisibleWidget()) {
		case 0:
			action.processValue(editInstance);
			break;
		case 1:
			detailsForm.viewToModel(editInstance, new ViewToModelCallback<T>() {
				public void processValue(T value) {
					action.processValue(value);
				}
			});
			break;
		}
	}

	public boolean isEmpty() {
		return editInstance == null;
	}

}

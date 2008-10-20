package org.openinsula.arena.gwt.client.ui.form;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.ui.FocusComposite;
import org.openinsula.arena.gwt.client.ui.form.validator.FormItemValidator;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestBox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 *
 * @see FormBuilder
 * @see FormItemWidgetWrapper
 * @see GroupFormItem
 * @param <T>
 */
public class FormItem<T extends Widget> extends FocusComposite {

	private FocusPanel mainPanel;

	protected Panel contentPanel;

	private Label label;

	private FormItemWidgetWrapper<T> widgetWrapper;

	private String hint;

	private boolean optional;

	private boolean valid = true;

	private Label errorLabel;

	private String suffixMessage;

	private Label suffixLabel;

	protected boolean isNew;

	protected FormItem() {
	}

	public FormItem(final String label, final T widget) {
		this(label, widget, null, false, null);
	}

	public FormItem(final String label, final T widget, final String hint) {
		this(label, widget, hint, false, null);
	}

	public FormItem(final String label, final T widget, boolean optional) {
		this(label, widget, null, optional, null);
	}

	public FormItem(final String label, final T widget, final String hint, boolean optional) {
		this(label, widget, hint, optional, null);
	}

	/**
	 *
	 * @param label the text
	 * @param widget the "user input component"
	 * @param hint any kind of hint/help that user may need
	 * @param optional if the field is optional
	 * @param suffixMessage the text that may appear after the widget
	 */
	public FormItem(final String label, final T widget, final String hint, boolean optional, final String suffixMessage) {
		createComponents(label, widget, hint, optional, suffixMessage);
		pack();
		initActions();
	}

	private void initActions() {
		addFocusListener(new FocusListener() {
			public void onFocus(Widget sender) {
				setErrorMessage("");
				setValid(true);
				refresh();
			}
			public void onLostFocus(Widget sender) {
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected void createComponents(String label, final T widget, String hint, boolean optional, String suffixMessage) {
		this.label = FormFactory.label(label);
		this.errorLabel = FormFactory.errorLabel("");
		this.optional = optional;
		this.contentPanel = new VerticalPanel();
		this.suffixMessage = suffixMessage;

		this.mainPanel = new FocusPanel();
		mainPanel.add(contentPanel);

		contentPanel.setTitle(label);

		this.hint = hint;

		if (widget != null) {
			if (widget instanceof FormItemValidator) {
				addFormItemValidator((FormItemValidator<T>) widget);
			}

			this.widgetWrapper = new FormItemWidgetWrapper<T>(widget, mainPanel, hint);
			setLabelClickListener(widget);
		}

		initWidget(mainPanel);
		setStyleName("salto-FormItem");

		isNew = true;
	}

	/**
	 *
	 */
	protected void pack() {
		setTabIndex(-1);

		label.setStyleName(FormFactory.getStyleFormLabel());

		if (optional) {
			Panel optionalPanel = new HorizontalPanel();
			Label optionalLabel = FormFactory.optionalLabel();
			optionalPanel.add(label);
			optionalPanel.add(optionalLabel);
			contentPanel.add(optionalPanel);
		}
		else {
			contentPanel.add(label);
		}

		contentPanel.add(getWidgetForPanel());

		if (suffixMessage != null) {
			suffixLabel = FormFactory.suffixLabel(suffixMessage);
			contentPanel.add(suffixLabel);
		}
	}

	/**
	 *
	 * @return
	 */
	protected Widget getWidgetForPanel() {
		return widgetWrapper.getWidget();
	}

	/**
	 *
	 */
	protected void refresh() {
		if (suffixMessage != null) {
			contentPanel.add(suffixLabel);
		}

		if (isNew) {
			contentPanel.remove(errorLabel);
			setStyleName(FormFactory.getStyleFormItem());
			setErrorMessage(null);
		}
		else {
			if (valid) {
				contentPanel.remove(errorLabel);
				removeStyleName(FormFactory.getStyleFormItemInvalid());
				addStyleName(FormFactory.getStyleFormItemValid());
				setErrorMessage(null);
			}
			else {
				removeStyleName(FormFactory.getStyleFormItemValid());
				addStyleName(FormFactory.getStyleFormItemInvalid());
				contentPanel.add(errorLabel);
			}
		}

	}

	public Label getLabel() {
		return this.label;
	}

	public T getWidget() {
		return this.widgetWrapper.getWidget();
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		isNew = false;
		this.valid = valid;
		refresh();
	}

	public void setErrorMessage(String errorMessage) {
		isNew = false;
		errorLabel.setText(errorMessage);
	}

	protected String getHint() {
		return hint;
	}

	protected FocusPanel getMainPanel() {
		return mainPanel;
	}

	public void clear() {
		isNew = true;
		clearWidget(getWidget());
		refresh();
	}

	protected void clearWidget(T widget) {
		if (widget instanceof TextBoxBase) {
			((TextBoxBase) widget).setText(null);
		}
		else if (widget instanceof CheckBox) {
			((CheckBox) widget).setChecked(false);
		}
		else if (widget instanceof BeanSuggestBox<?>) {
			((BeanSuggestBox<?>) widget).getSuggestBox().setText(null);
		}
	}

	/**
	 * This method looks for widget with "focus" properties and tries to add a
	 * clickListener to its label
	 *
	 * @param widget
	 */
	protected void setLabelClickListener(final T widget) {
		this.label.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				if (widget instanceof FocusWidget) {
					((FocusWidget) widget).setFocus(true);
				}
				else if (widget instanceof BeanSuggestBox<?>) {
					((BeanSuggestBox<?>) widget).getSuggestBox().setFocus(true);
				}
			}
		});
	}

	/*
	 * trecho abaixo é experimental
	 */
	private List<FormItemValidator<T>> formItemValidators;

	protected List<FormItemValidator<T>> formItemValidators() {
		if (formItemValidators == null) {
			formItemValidators = new ArrayList<FormItemValidator<T>>();
		}
		return formItemValidators;
	}

	public void addFormItemValidator(FormItemValidator<T> validator) {
		formItemValidators().add(validator);
	}

	public boolean isValidated() {
		for (FormItemValidator<T> validator : formItemValidators()) {
			if (!validator.validate(getWidget())) {
				setErrorMessage(validator.getInvalidValueMessage());
				setValid(false);
				refresh();
				return false;
			}
		}
		setErrorMessage("");
		setValid(true);
		refresh();
		return true;
	}

}

package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.ui.FocusComposite;
import org.openinsula.arena.gwt.client.ui.MouseEventPanel;
import org.openinsula.arena.gwt.client.ui.form.validator.DefaultValidatorChainImpl;
import org.openinsula.arena.gwt.client.ui.form.validator.FormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorChain;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
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
 * @param <W>
 */
public class FormItem<W extends Widget> extends FocusComposite {

	private MouseEventPanel mainPanel;

	protected Panel contentPanel;

	private Label label;

	private FormItemWidgetWrapper<W> widgetWrapper;

	private String hint;

	private boolean optional;

	protected boolean valid = true;

	private Label errorLabel;

	private String suffixMessage;

	private Label suffixLabel;

	protected boolean isNew;

	protected FormItem() {
	}

	public FormItem(final String label, final W widget) {
		this(label, widget, null, false, null);
	}

	public FormItem(final String label, final W widget, final String hint) {
		this(label, widget, hint, false, null);
	}

	public FormItem(final String label, final W widget, boolean optional) {
		this(label, widget, null, optional, null);
	}

	public FormItem(final String label, final W widget, final String hint, boolean optional) {
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
	public FormItem(final String label, final W widget, final String hint, boolean optional, final String suffixMessage) {
		createComponents(label, widget, hint, optional, suffixMessage);
		pack();
		initActions();
	}

	private void initActions() {
//		addFocusListener(new FocusListener() {
//			public void onFocus(Widget sender) {
//				setErrorMessage("");
//				setValid(true);
//				refresh();
//			}
//			public void onLostFocus(Widget sender) {
//				validate();
//			}
//		});
	}

	@SuppressWarnings("unchecked")
	protected void createComponents(String label, final W widget, String hint, boolean optional, String suffixMessage) {
		this.label = FormFactory.label(label);
		this.errorLabel = FormFactory.errorLabel("");
		this.optional = optional;
		this.contentPanel = new VerticalPanel();
		this.suffixMessage = suffixMessage;

		this.mainPanel = new MouseEventPanel();
		mainPanel.add(contentPanel);

		contentPanel.setTitle(label);

		this.hint = hint;

		if (widget != null) {
			if (widget instanceof FormItemValidator) {
				((FormItemValidator) widget).setFormItem(this);
				addFormItemValidator((FormItemValidator) widget);
			}

			this.widgetWrapper = new FormItemWidgetWrapper<W>(widget, mainPanel, hint);
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
	public void refresh() {
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

	public W getWidget() {
		return this.widgetWrapper.getWidget();
	}

	public void setValid(boolean valid) {
		isNew = false;
		this.valid = valid;
	}

	public void setErrorMessage(String errorMessage) {
		isNew = false;
		errorLabel.setText(errorMessage);
	}

	protected String getHint() {
		return hint;
	}

	protected MouseEventPanel getMainPanel() {
		return mainPanel;
	}

	public void clear() {
		isNew = true;
		clearWidget(getWidget());
		refresh();
	}

	protected void clearWidget(W widget) {
		if (widget instanceof TextBoxBase) {
			((TextBoxBase) widget).setText(null);
		}
		else if (widget instanceof CheckBox) {
			((CheckBox) widget).setChecked(false);
		}
		else if (widget instanceof BeanSuggestBox<?>) {
			((BeanSuggestBox<?>) widget).getSuggestBox().setText(null);
		}
		else if (widget instanceof BeanListBox<?>) {
			((BeanListBox<?>) widget).setSelectedItem(null);
		}
	}

	/**
	 * This method looks for widget with "focus" properties and tries to add a
	 * clickListener to its label
	 *
	 * @param widget
	 */
	protected void setLabelClickListener(final W widget) {
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

	private ValidatorChain<W> validatorChain;

	private ValidatorChain<W> validatorChain() {
		if (validatorChain == null) {
			validatorChain = new DefaultValidatorChainImpl<W>();
		}
		return validatorChain;
	}

	@SuppressWarnings("unchecked")
	public void addFormItemValidator(FormItemValidator validator) {
		validator.setFormItem(this);
		validatorChain().addValidator(validator);
	}

	public void validate(ValidatorAction action) {
		GWT.log("quantidade de validadores no momento do validate(): " + validatorChain.size(), null);
		validatorChain().validate(getWidget(), action);
	}

//	public ValidatorChain<W> getValidatorChain() {
//		return validatorChain();
//	}

}

package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestBox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
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
public class FormItem<T extends Widget> extends Composite {
	
	private FocusPanel mainPanel;

	private Panel contentPanel;

	private Label label;

	private FormItemWidgetWrapper<T> widgetWrapper;

	private String hint;

	private boolean optional;
	
	private boolean valid = true;
	
	private Label errorLabel;

	private String suffixMessage;
	
	private Label suffixLabel;
	
	protected boolean isNew;
	
	protected FormItem() {}
	
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
	}

	protected void createComponents(String label, T widget, String hint, boolean optional, String suffixMessage) {
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
			this.widgetWrapper = new FormItemWidgetWrapper<T>(widget, mainPanel, hint);
		}
		
		initWidget(mainPanel);
		setStyleName("salto-FormItem");
		
		isNew = true;
	}
	
	/**
	 * 
	 */
	protected void pack() {
		label.setStyleName(FormFactory.STYLE_FORM_LABEL);

		if (optional) {
			Panel optionalPanel = new HorizontalPanel();
			Label optionalLabel = FormFactory.optionalLabel(); 
			optionalPanel.add(label);
			optionalPanel.add(optionalLabel);
			contentPanel.add(optionalPanel);
		} else {
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
			removeStyleName("salto-FormItemInvalid");
			removeStyleName("salto-FormItemValid");
			addStyleName("salto-FormItem");
			setErrorMessage(null);
		} else {
			if (valid) {
				contentPanel.remove(errorLabel);
				removeStyleName("salto-FormItemInvalid");
				addStyleName("salto-FormItemValid");
				setErrorMessage(null);
			} else {
				removeStyleName("salto-FormItemValid");
				addStyleName("salto-FormItemInvalid");
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
		} else if(widget instanceof CheckBox) {
			((CheckBox) widget).setChecked(false);
		} else if(widget instanceof BeanSuggestBox<?>) {
			((BeanSuggestBox<?>) widget).getSuggestBox().setText(null);
		}
	}
}

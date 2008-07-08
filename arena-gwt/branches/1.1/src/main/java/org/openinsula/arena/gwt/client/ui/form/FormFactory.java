package org.openinsula.arena.gwt.client.ui.form;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 *
 */
public abstract class FormFactory {

	public static final Style style = null;

	public static final String STYLE_FORM_LABEL = "salto-FormItemLabel";
	
	public static final String STYLE_FORM_LABEL_OPTIONAL = "salto-FormItemLabelOptional";
	
	public static final String STYLE_FORM_LABEL_ERROR = "salto-FormItemLabelError";
	
	public static final String STYLE_FORM_PANEL_HINT = "salto-FormItemPopupPanelHint";
	
	public static final String STYLE_FORM_BUTTON = "salto-button";
	
	public static final String STYLE_FORM_LABEL_TITULO_H1 = "salto-FormItemLabelH1";
	
	public static final String STYLE_FORM_VERTICAL_PANEL = "salto-FormVerticalPanel";
	
	public static final String STYLE_FORM_ITEM = "salto-FormItem";
	
	public static final String STYLE_FORM_ITEM_FOCUSED = "salto-FormItemFocused";

	public static final String STYLE_FORM_LABEL_SUFFIX = "salto-FormItemLabelSuffix";
	
	public static final String STYLE_FORM_TEXT_BOX_DISABLED = "salto-FormItemTextBoxDisabled";
	
	public static Label label(String text) {
		Label label = new Label(text);
		label.setStyleName(STYLE_FORM_LABEL);
		return label;
	}
	
	public static Label labelH1(String text) {
		Label label = new Label(text);
		label.setStyleName(STYLE_FORM_LABEL_TITULO_H1);
		return label;
	}
	
	public static Label optionalLabel() {
		Label label = new Label("(opcional)");
		label.setStyleName(STYLE_FORM_LABEL_OPTIONAL);
		return label;
	}
	
	public static Label suffixLabel(String text) {
		Label label = new Label(text);
		label.setStyleName(STYLE_FORM_LABEL_SUFFIX);
		return label;
	}
	
	public static Label errorLabel(String text) {
		Label label = new Label(text);
		label.setStyleName(STYLE_FORM_LABEL_ERROR);
		return label;
	}
	
	public static TextBox textBox() {
		TextBox textBox = new TextBox();
		return textBox;
	}
	
	public static TextBox textBox(String width) {
		TextBox textBox = new TextBox();
		textBox.setWidth(width + "px");
		return textBox;
	}
	
	public static TextBox textBoxSmall() {
		return textBox("50");
	}
	
	public static TextBox textBoxBig() {
		return textBox("250");
	}
	
	public static Button button(String text) {
		Button button = new Button(text);
		return button;
	}
	
	public static Panel verticalPanel() {
		Panel panel = new VerticalPanel();
		panel.setStyleName(STYLE_FORM_VERTICAL_PANEL);
		return panel;
	}

	public static PasswordTextBox passwordTextBox() {
		PasswordTextBox textBox = new PasswordTextBox();
		return textBox;
	}
	
	public static PopupPanel hintPopupPanel() {
		PopupPanel panel = new PopupPanel();
		panel.setStyleName(STYLE_FORM_PANEL_HINT);
		return panel;
	}
	
	public static TextBox disabledTextBox() {
		TextBox textBox = new TextBox();
		textBox.setStyleName(STYLE_FORM_TEXT_BOX_DISABLED);
		textBox.setEnabled(false);
		return textBox;
	}
}

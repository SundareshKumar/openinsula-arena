package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.StyleBuilder;
import org.openinsula.arena.gwt.client.ui.form.util.DateFormItem;
import org.openinsula.arena.gwt.client.ui.form.util.JSMaskedDateWidget;
import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget;
import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget.LabelAlignment;
import org.openinsula.arena.gwt.client.ui.form.validator.DateFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.NotNullFormItemValidator;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 *
 */
public abstract class FormFactory {

	static final StyleBuilder styleBuilder;

	static {
		styleBuilder = new StyleBuilder();
	}

	public static void setModulePrefix(final String modulePrefix) {
		styleBuilder.setModulePrefix(modulePrefix);
	}

	static final String STYLE_FORM_LABEL = "FormItemLabel";

	public static final String getStyleFormLabel() {
		return styleBuilder.getRule(STYLE_FORM_LABEL);
	}

	static final String STYLE_FORM_LABEL_OPTIONAL = "FormItemLabelOptional";

	static final String STYLE_FORM_LABEL_ERROR = "FormItemLabelError";

	static final String STYLE_FORM_PANEL_HINT = "FormItemPopupPanelHint";

	static final String STYLE_FORM_BUTTON = "Button";

	static final String STYLE_FORM_LABEL_TITULO_H1 = "FormItemLabelH1";

	static final String STYLE_FORM_VERTICAL_PANEL = "FormVerticalPanel";

	static final String STYLE_FORM_ITEM = "FormItem";

	static final String STYLE_FORM_SIMPLE_DATE_WIDGET = "SimpleDateWidget";

	public static final String getStyleFormItem() {
		return styleBuilder.getRule(STYLE_FORM_ITEM);
	}

	static final String STYLE_FORM_ITEM_VALID = "FormItemValid";

	public static final String getStyleFormItemValid() {
		return styleBuilder.getRule(STYLE_FORM_ITEM_VALID);
	}

	static final String STYLE_FORM_ITEM_INVALID = "FormItemInvalid";

	public static final String getStyleFormItemInvalid() {
		return styleBuilder.getRule(STYLE_FORM_ITEM_INVALID);
	}

	static final String STYLE_FORM_ITEM_FOCUSED = "FormItemFocused";

	public static final String getStyleFormItemFocused() {
		return styleBuilder.getRule(STYLE_FORM_ITEM_FOCUSED);
	}

	static final String STYLE_FORM_LABEL_SUFFIX = "FormItemLabelSuffix";

	static final String STYLE_FORM_TEXT_BOX_DISABLED = "FormItemTextBoxDisabled";

	public static Label label(final String text) {
		final Label label = new Label(text);
		label.setStyleName(styleBuilder.getRule(STYLE_FORM_LABEL));
		return label;
	}

	public static Label labelH1(final String text) {
		final Label label = new Label(text);
		label.setStyleName(styleBuilder.getRule(STYLE_FORM_LABEL_TITULO_H1));
		return label;
	}

	public static Label optionalLabel() {
		final Label label = new Label("(opcional)");
		label.setStyleName(styleBuilder.getRule(STYLE_FORM_LABEL_OPTIONAL));
		return label;
	}

	public static Label suffixLabel(final String text) {
		final Label label = new Label(text);
		label.setStyleName(styleBuilder.getRule(STYLE_FORM_LABEL_SUFFIX));
		return label;
	}

	public static Label errorLabel(final String text) {
		final Label label = new Label(text);
		label.setStyleName(styleBuilder.getRule(STYLE_FORM_LABEL_ERROR));
		return label;
	}

	public static TextBox textBox() {
		final TextBox textBox = new TextBox();
		return textBox;
	}

	public static TextBox textBox(final String width) {
		final TextBox textBox = new TextBox();
		textBox.setWidth(width);
		return textBox;
	}

	public static TextBox textBoxSmall() {
		return textBox("50px");
	}

	public static TextBox textBoxBig() {
		return textBox("250px");
	}

	/**
	 * Usar {@link FormFactory#dateFormItem()}
	 *
	 * @return
	 */
	@Deprecated
	public static TextBox textBoxDate() {
		final TextBox box = textBox("80px");
		box.setMaxLength(10);
		return box;
	}

	public static Button button(final String text) {
		final Button button = new Button(text);
		return button;
	}

	public static VerticalPanel verticalPanel() {
		final VerticalPanel panel = new VerticalPanel();
		panel.setStyleName(styleBuilder.getRule(STYLE_FORM_VERTICAL_PANEL));
		return panel;
	}

	public static PasswordTextBox passwordTextBox() {
		final PasswordTextBox textBox = new PasswordTextBox();
		return textBox;
	}

	public static PopupPanel hintPopupPanel() {
		final PopupPanel panel = new PopupPanel(true);
		panel.setStyleName(styleBuilder.getRule(STYLE_FORM_PANEL_HINT));
		return panel;
	}

	public static TextBox disabledTextBox() {
		final TextBox textBox = new TextBox();
		textBox.setStyleName(styleBuilder.getRule(STYLE_FORM_TEXT_BOX_DISABLED));
		textBox.setEnabled(false);
		return textBox;
	}

	public static TextArea textArea(final String width, final String height) {
		final TextArea textArea = new TextArea();
		textArea.setSize(width, height);
		return textArea;
	}

	public static TextArea textArea() {
		return textArea("150px", "75px");
	}

	public static TextArea disabledTextArea() {
		final TextArea textArea = textArea();
		textArea.setEnabled(false);
		return textArea;
	}

	/**
	 * Cria um {@link Hyperlink} e gera um "token" automatico, baseado no label
	 * fornecido (concatenando com um numero aleatorio).
	 *
	 * @see #hyperlink(String, String)
	 * @param label
	 * @return
	 */
	public static Hyperlink hyperlink(final String label) {
		if (label == null || label.trim().equals("")) {
			return hyperlink("no label");
		}

		String noTokenAtAll = label.toLowerCase().replaceAll("\\s", "-").replaceAll("[\\W&&[^-]]", "").replaceAll("--",
		"-");

		noTokenAtAll += "-tk" + Math.random();

		return hyperlink(label, noTokenAtAll);
	}

	public static Hyperlink hyperlink(final String label, final String targetHistoryToken) {
		return new Hyperlink(label, targetHistoryToken);
	}

	public static HorizontalPanel horizontalPanel() {
		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		return horizontalPanel;
	}

	public static HorizontalPanel actionPanel() {
		final Widget[] ws = new Widget[] {};
		return actionPanel(ws);
	}

	public static HorizontalPanel actionPanel(final Widget... widgets) {
		final HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);

		for (final Widget widget : widgets) {
			panel.add(widget);
		}

		return panel;
	}

	public static Panel formBuilderToPanel(final FormItem<?>... formItems) {
		final FormBuilder formBuilder = new FormBuilder();

		for (final FormItem<?> formItem : formItems) {
			formBuilder.add(formItem);
		}

		return formBuilder.toPanel();
	}

	public static DateFormItem dateFormItem() {
		return dateFormItem("Data");
	}

	public static DateFormItem dateFormItem(final String label) {
		return dateFormItem(label, null, false);
	}

	public static DateFormItem dateFormItem(final String label, final String hint, final boolean optional) {
		JSMaskedDateWidget widget = new JSMaskedDateWidget();

		final DateFormItem formItem = new DateFormItem(label, widget, hint, optional);

		if (!optional) {
			formItem.addFormItemValidator(new DateFormItemValidator());
		}

		return formItem;
	}

	/**
	 * Usar {@link JSMaskedDateWidget}
	 *
	 * @deprecated
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return
	 */
	@Deprecated
	public static SimpleDateWidget simpleDateWidget(final String ano, final String mes, final String dia) {
		final SimpleDateWidget simpleDateWidget = new SimpleDateWidget(LabelAlignment.LEFT, "ano", "m\u00eas", "dia");
		simpleDateWidget.setStyleName(styleBuilder.getRule(STYLE_FORM_SIMPLE_DATE_WIDGET));
		return simpleDateWidget;
	}

	/**
	 * Usar {@link JSMaskedDateWidget}
	 *
	 * @return
	 */
	@Deprecated
	public static SimpleDateWidget simpleDateWidget() {
		return simpleDateWidget("ano", "m\u00eas", "dia");
	}

	public static FormItem<TextBox> formItemTextBox(final String label) {
		return formItemTextBox(label, false);
	}

	public static FormItem<TextBox> formItemTextBox(final String label, final boolean mandatory) {
		return formItemTextBox(label, FormFactory.textBox(), mandatory);
	}

	public static FormItem<TextBox> formItemTextBoxSmall(final String label) {
		return formItemTextBoxSmall(label, false);
	}

	public static FormItem<TextBox> formItemTextBoxSmall(final String label, final boolean mandatory) {
		return formItemTextBox(label, FormFactory.textBoxSmall(), mandatory);
	}

	public static FormItem<TextBox> formItemTextBoxBig(final String label) {
		return formItemTextBoxBig(label, false);
	}

	public static FormItem<TextBox> formItemTextBoxBig(final String label, final boolean mandatory) {
		return formItemTextBox(label, FormFactory.textBoxBig(), mandatory);
	}

	private static FormItem<TextBox> formItemTextBox(final String label, final TextBox widget, final boolean mandatory) {
		final FormItem<TextBox> formItem = new FormItem<TextBox>(label, widget);

		if (mandatory) {
			formItem.addFormItemValidator(new NotNullFormItemValidator());
		}

		return formItem;
	}

}

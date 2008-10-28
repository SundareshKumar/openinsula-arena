package org.openinsula.arena.gwt.client.ui.form.field;

import org.openinsula.arena.gwt.client.ui.ListItem;
import org.openinsula.arena.gwt.client.ui.form.DefaultSimpleField;
import org.openinsula.arena.gwt.client.ui.form.SectionBreak;
import org.openinsula.arena.gwt.client.ui.form.Suffix;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public final class FieldFactory {

	private FieldFactory() {
	}

	public static SimpleField field(String label, Widget widget) {
		return new LabeledSimpleField(label, widget);
	}

	public static SimpleField field(Widget widget) {
		return new DefaultSimpleField(widget);
	}

	public static SimpleField disabledField(String label, FocusWidget widget) {
		widget.setEnabled(false);
		return field(label, widget);
	}

	public static CompositeField field(String label, Widget... widgets) {
		return new LabeledCompositeField(label, widgets);
	}

	public static Suffix suffix(String suffix, Widget widget) {
		return new Suffix(suffix, widget);
	}

	public static ListItem listItem(Widget... widgets) {
		final ListItem listItem = new ListItem();

		for (final Widget widget : widgets) {
			listItem.add(widget);
		}

		return listItem;
	}

	public static SectionBreak sectionBreak(String text) {
		return sectionBreak(text, null);
	}

	public static SectionBreak sectionBreak(String text, String description) {
		final SectionBreak sectionBreak = new SectionBreak();

		sectionBreak.setText(text);
		sectionBreak.setDescription(description);

		return sectionBreak;
	}

	public static TextBox textBox(String width) {
		final TextBox textBox = new TextBox();

		textBox.setWidth(width);

		return textBox;
	}

	/**
	 * Creates a {@link TextBox} with style name equals to "size" + type.
	 */
	public static TextBox textBox(int type) {
		final TextBox textBox = new TextBox();
		textBox.setStyleName("Size" + type);
		return textBox;
	}

	public static TextBox dateTextBox() {
		return textBox(2);
	}

	public static SuggestBox suggestBox(SuggestOracle oracle) {
		final SuggestBox box = new SuggestBox(oracle);
		box.setWidth("400px");
		return box;
	}

	public static SuggestBox suggestBox(AbstractTimedSuggestOracle oracle) {
		final SuggestBox box = new SuggestBox(oracle);

		oracle.setTextWidget(box);

		return box;
	}

	public static ListBox listBox() {
		final ListBox box = new ListBox();
		return box;
	}

	public static ListBox diasDoMesListBox() {
		final ListBox box = listBox();
		for (int i = 1; i <= 31; i++) {
			box.addItem(Integer.toString(i));
		}
		return box;
	}

	public static ListBox mesesListBox() {
		final ListBox box = listBox();
		for (int i = 1; i <= 12; i++) {
			box.addItem(Integer.toString(i));
		}
		return box;
	}

	public static ListBox anosListBox(int inicio, int termino) {
		final ListBox box = listBox();

		if (inicio > termino) {
			final int inicio2 = inicio;
			inicio = termino;
			termino = inicio2;
		}

		for (int i = termino; i >= inicio; i--) {
			box.addItem(Integer.toString(i));
		}
		return box;
	}

	public static Button button(String text, ClickListener... clickListeners) {
		final Button button = new Button(text);

		for (final ClickListener clickListener : clickListeners) {
			button.addClickListener(clickListener);
		}

		return button;
	}

	public static Hyperlink hyperlink(String text, ClickListener... clickListeners) {
		return hyperlink(text, null, clickListeners);
	}

	public static Hyperlink hyperlink(String text, String historyToken, ClickListener... clickListeners) {
		final Hyperlink link = new Hyperlink();

		link.setText(text);

		if (historyToken != null) {
			link.setTargetHistoryToken(historyToken);
		}

		for (final ClickListener clickListener : clickListeners) {
			link.addClickListener(clickListener);
		}

		return link;
	}

	public static PasswordTextBox passwordTextBox() {
		return new PasswordTextBox();
	}

	public static TextArea textArea(String width, String height) {
		final TextArea textArea = new TextArea();
		textArea.setSize(width, height);
		return textArea;
	}

	public static TextArea textArea() {
		return textArea("150px", "75px");
	}

	public static CheckBox checkBox() {
		final CheckBox checkBox = new CheckBox();
		
		return checkBox;
	}
}

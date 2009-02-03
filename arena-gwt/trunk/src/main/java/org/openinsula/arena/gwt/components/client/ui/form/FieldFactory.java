package org.openinsula.arena.gwt.components.client.ui.form;

import org.openinsula.arena.gwt.components.client.ui.ListItem;
import org.openinsula.arena.gwt.components.client.ui.Suffix;
import org.openinsula.arena.gwt.components.client.ui.form.field.CompositeField;
import org.openinsula.arena.gwt.components.client.ui.form.field.DefaultSimpleField;
import org.openinsula.arena.gwt.components.client.ui.form.field.Field;
import org.openinsula.arena.gwt.components.client.ui.form.field.LabeledCompositeField;
import org.openinsula.arena.gwt.components.client.ui.form.field.LabeledSimpleField;
import org.openinsula.arena.gwt.components.client.ui.form.field.RequiredField;
import org.openinsula.arena.gwt.components.client.ui.form.field.SimpleField;
import org.openinsula.arena.gwt.components.client.ui.form.section.SectionBreak;
import org.openinsula.arena.gwt.components.client.ui.suggest.bean.AbstractTimedSuggestOracle;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
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

	@SuppressWarnings("unchecked")
	public static <T extends SimpleField> T field(final String label, final Widget widget) {
		return (T) new LabeledSimpleField(label, widget);
	}

	public static SimpleField field(final Widget widget) {
		return new DefaultSimpleField(widget);
	}

	public static SimpleField disabledField(final String label, final FocusWidget widget) {
		widget.setEnabled(false);
		return field(label, widget);
	}

	public static CompositeField field(final String label, final Widget... widgets) {
		return new LabeledCompositeField(label, widgets);
	}

	public static Suffix suffix(final String suffix, final Widget widget) {
		return new Suffix(suffix, widget);
	}

	public static ListItem listItem(final Widget... widgets) {
		final ListItem listItem = new ListItem();

		for (final Widget widget : widgets) {
			listItem.add(widget);
		}

		return listItem;
	}

	public static Break breakLine() {
		return new Break();
	}

	public static SectionBreak sectionBreak(final String text) {
		return sectionBreak(text, null);
	}

	public static SectionBreak sectionBreak(final String text, final String description) {
		final SectionBreak sectionBreak = new SectionBreak();

		sectionBreak.setText(text);
		sectionBreak.setDescription(description);

		return sectionBreak;
	}

	public static TextBox textBox(final String width) {
		final TextBox textBox = new TextBox();

		textBox.setWidth(width);

		return textBox;
	}

	/**
	 * Creates a {@link TextBox} with style name equals to "size" + type.
	 */
	public static TextBox textBox(final int type) {
		final TextBox textBox = new TextBox();
		textBox.setStyleName("Size" + type);
		return textBox;
	}

	public static TextBox dateTextBox() {
		return textBox(2);
	}

	public static SuggestBox suggestBox(final SuggestOracle oracle) {
		final SuggestBox box = new SuggestBox(oracle);
		box.setWidth("400px");
		return box;
	}

	public static SuggestBox suggestBox(final AbstractTimedSuggestOracle oracle) {
		final SuggestBox box = new SuggestBox(oracle);

		oracle.setTextWidget(box);

		return box;
	}

	public static ListBox listBox(final Enum<?>[] enumValues) {
		final ListBox box = new ListBox();

		if (enumValues != null && enumValues.length > 0) {
			for (final Enum<?> item : enumValues) {
				box.addItem(item.toString(), item.name());
			}
		}

		return box;
	}

	public static ListBox listBox(final Object... itemList) {
		final ListBox box = new ListBox();

		if (itemList != null && itemList.length > 0) {
			for (final Object item : itemList) {
				if (item != null) {
					box.addItem(String.valueOf(item));
				}
			}
		}

		return box;
	}

	public static ListBox diasDoMesListBox() {
		final ListBox box = listBox();
		box.addItem("");
		for (int i = 1; i <= 31; i++) {
			box.addItem(Integer.toString(i));
		}
		return box;
	}

	public static ListBox mesesListBox() {
		final ListBox box = listBox();
		box.addItem("");
		for (int i = 1; i <= 12; i++) {
			box.addItem(Integer.toString(i));
		}
		return box;
	}

	public static ListBox anosListBox(int inicio, int termino) {
		final ListBox box = listBox();
		box.addItem("");

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

	/**
	 * Usar {@link #field(String, Widget)} com o widget {@link DateChooser}.
	 * 
	 * <pre>
	 * // Widget
	 * DateChooser date = new DateChooser();
	 * 
	 * // FormItem
	 * appendFormItem(field(&quot;Data&quot;, date));
	 * </pre>
	 * 
	 * @param label
	 * @return
	 */
	@Deprecated
	public static DateCompositeField dateField(final String label) {
		return new DateCompositeField(label, 2000, 2008);
	}

	public static Button button(final String text, final ClickListener... clickListeners) {
		final Button button = new Button(text);

		for (final ClickListener clickListener : clickListeners) {
			button.addClickListener(clickListener);
		}

		return button;
	}

	public static Hyperlink hyperlink(final String text, final ClickListener... clickListeners) {
		return hyperlink(text, null, clickListeners);
	}

	public static Hyperlink hyperlink(final String text, final String historyToken,
			final ClickListener... clickListeners) {
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

	public static TextArea textArea(final String width, final String height) {
		final TextArea textArea = new TextArea();
		textArea.setSize(width, height);
		return textArea;
	}

	public static TextArea textArea() {
		return textArea("150px", "75px");
	}

	public static CheckBox checkBox() {
		return checkBox(null);
	}

	public static CheckBox checkBox(final Object label) {
		final CheckBox checkBox = label == null ? new CheckBox() : new CheckBox(String.valueOf(label));
		return checkBox;
	}

	public static RadioButton radioButton(final String groupName, final Object label) {
		final RadioButton radioButton = label == null ? new RadioButton(groupName) : new RadioButton(groupName, String
				.valueOf(label));

		return radioButton;
	}

	public static void markAsRequired(final Field field) {
		requiredImpl(field, true);
	}
	
	public static void markAsOptional(final Field field) {
		requiredImpl(field, false);
	}

	private static void requiredImpl(final Field field, final boolean required) {
		if (field instanceof RequiredField) {
			((RequiredField) field).setRequired(true);
		}
	}

}

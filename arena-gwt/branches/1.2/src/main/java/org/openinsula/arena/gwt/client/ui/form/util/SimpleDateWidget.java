/**
 *
 */
package org.openinsula.arena.gwt.client.ui.form.util;

import java.util.Date;

import org.openinsula.arena.gwt.client.ui.FocusComposite;
import org.openinsula.arena.gwt.client.ui.FocusUtils;
import org.openinsula.arena.gwt.client.ui.form.FormFactory;
import org.openinsula.arena.gwt.client.util.GwtDateUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SimpleDateWidget extends FocusComposite {

	public static enum LabelAlignment {
		LEFT, RIGHT
	}

	private String dateMask = "dd/MM/yyyy";

	private TextBox dayTextBox;

	private TextBox monthTextBox;

	private TextBox yearTextBox;

	private final HorizontalPanel contentPanel;

	/**
	 * @see SimpleDateWidget#SimpleDateWidget(org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget.LabelAlignment,
	 * String, String, String)
	 */
	public SimpleDateWidget() {
		this(null, null, null, null);
	}

	/**
	 *
	 * @param alignment
	 * @param yearLabel
	 * @param monthLabel
	 * @param dayLabel
	 */
	public SimpleDateWidget(LabelAlignment alignment, String yearLabel, String monthLabel, String dayLabel) {
		dayTextBox = FormFactory.textBox();
		monthTextBox = FormFactory.textBox();
		yearTextBox = FormFactory.textBox();

		configureFields();

		contentPanel = new HorizontalPanel();
		// contentPanel.addStyleName("dateWidget-Panel");
		contentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		if (LabelAlignment.LEFT.equals(alignment) && dayLabel != null) {
			contentPanel.add(createLabel(dayLabel));
		}

		contentPanel.add(dayTextBox);

		if (LabelAlignment.RIGHT.equals(alignment) && dayLabel != null) {
			contentPanel.add(createLabel(dayLabel));
		}

		if (LabelAlignment.LEFT.equals(alignment) && monthLabel != null) {
			contentPanel.add(createLabel(monthLabel));
		}

		contentPanel.add(monthTextBox);

		if (LabelAlignment.RIGHT.equals(alignment) && monthLabel != null) {
			contentPanel.add(createLabel(monthLabel));
		}

		if (LabelAlignment.LEFT.equals(alignment) && yearLabel != null) {
			contentPanel.add(createLabel(yearLabel));
		}

		contentPanel.add(yearTextBox);

		if (LabelAlignment.RIGHT.equals(alignment) && yearLabel != null) {
			contentPanel.add(createLabel(yearLabel));
		}

		initWidget(contentPanel);
	}

	private Label createLabel(String text) {
		Label label = new Label(text);
		// label.addStyleName("dateWidget-Label");
		return label;
	}

	public void setFocus(boolean op) {
		dayTextBox.setFocus(op);
	}

	public void setEnabled(boolean enabled) {
		dayTextBox.setEnabled(enabled);
		monthTextBox.setEnabled(enabled);
		yearTextBox.setEnabled(enabled);
	}

	public boolean isEnabled() {
		return dayTextBox.isEnabled();
	}

	public void addKeyboardListener(KeyboardListener listener) {
		yearTextBox.addKeyboardListener(listener);
	}

	private void configureFields() {
		dayTextBox.setWidth("20px");
		dayTextBox.setMaxLength(2);
		dayTextBox.addKeyboardListener(new MoveForwardKeyboardListener(monthTextBox));
		dayTextBox.addKeyboardListener(new OnlyDigitsKeyboardListener());

		monthTextBox.setWidth("20px");
		monthTextBox.setMaxLength(2);
		monthTextBox.addKeyboardListener(new MoveForwardKeyboardListener(yearTextBox));
		monthTextBox.addKeyboardListener(new MoveBackwardKeyboardListener(dayTextBox));
		monthTextBox.addKeyboardListener(new OnlyDigitsKeyboardListener());

		yearTextBox.setWidth("40px");
		yearTextBox.setMaxLength(4);
		yearTextBox.addKeyboardListener(new MoveBackwardKeyboardListener(monthTextBox));
		yearTextBox.addKeyboardListener(new OnlyDigitsKeyboardListener());
	}

	/**
	 *
	 * @return
	 */
	private String getDayText() {
		return dayTextBox.getText();
	}

	/**
	 *
	 * @return
	 */
	private String getMonthText() {
		return monthTextBox.getText();
	}

	/**
	 *
	 * @return
	 */
	private String getYearText() {
		return yearTextBox.getText();
	}

	/**
	 *
	 * @return
	 */
	public String getText() {
		return getDayText() + "/" + getMonthText() + "/" + getYearText();
	}

	/**
	 *
	 * @return
	 */
	public Date getDate() {
		Date date = null;

		try {
			String dd = getDayText();
			String mm = getMonthText();
			String yyyy = getYearText();

			date = DateTimeFormat.getFormat(dateMask).parse(dd + "/" + mm + "/" + yyyy);
		}
		catch (Exception e) {
			e.printStackTrace();
			GWT.log("Error parsing Date. Did you forget DateFormItemValidator ?", e);
		}

		return date;
	}

	/**
	 *
	 * @param date
	 * @throws IllegalArgumentException
	 */
	public void setDate(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("param date can not be null");
		}

		dayTextBox.setText(String.valueOf(GwtDateUtils.getDay(date)));
		monthTextBox.setText(String.valueOf(GwtDateUtils.getMonth(date)));
		yearTextBox.setText(String.valueOf(GwtDateUtils.getYear(date)));
	}

	private class OnlyDigitsKeyboardListener extends KeyboardListenerAdapter {

		public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			if ((!Character.isDigit(keyCode)) && (keyCode != (char) KEY_TAB) && (keyCode != (char) KEY_BACKSPACE)
					&& (keyCode != (char) KEY_DELETE) && (keyCode != (char) KEY_ENTER) && (keyCode != (char) KEY_HOME)
					&& (keyCode != (char) KEY_END) && (keyCode != (char) KEY_LEFT) && (keyCode != (char) KEY_UP)
					&& (keyCode != (char) KEY_RIGHT) && (keyCode != (char) KEY_DOWN)) {
				// TextBox.cancelKey() suppresses the current keyboard event.
				((TextBox) sender).cancelKey();
			}
		}

	}

	private class MoveForwardKeyboardListener extends KeyboardListenerAdapter {

		private TextBox nextWidget;

		private boolean isEndReached = false;

		public MoveForwardKeyboardListener(TextBox widget) {
			this.nextWidget = widget;
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
			TextBox textBox = ((TextBox) sender);
			int total = textBox.getText().length();

			if (total == textBox.getMaxLength() && !isABackwardKey(keyCode)) {
				go();
			}
			else if (textBox.getCursorPos() == textBox.getMaxLength()) {
				if (isEndReached) {
					go();
					isEndReached = false;
				}
				else {
					isEndReached = true;
				}
			}
		}

		private void go() {
			FocusUtils.selectAllOnFocus(nextWidget);
			FocusUtils.deferredFocus(nextWidget);
		}

		private boolean isABackwardKey(char keyCode) {
			return keyCode == KeyboardListener.KEY_DELETE || keyCode == KeyboardListener.KEY_HOME
					|| keyCode == KeyboardListener.KEY_LEFT;
		}
	}

	private class MoveBackwardKeyboardListener extends KeyboardListenerAdapter {

		private TextBox nextWidget;

		private boolean isEndReached = false;

		public MoveBackwardKeyboardListener(TextBox widget) {
			this.nextWidget = widget;
		}

		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
			TextBox textBox = ((TextBox) sender);
			int total = textBox.getText().length();

			if (total == 0 && isAForwardKey(keyCode)) {
				go();
			}
			else if (textBox.getCursorPos() == 0) {
				if (isEndReached) {
					go();
					isEndReached = false;
				}
				else {
					isEndReached = true;
				}
			}
		}

		private void go() {
			FocusUtils.selectAllOnFocus(nextWidget);
			FocusUtils.deferredFocus(nextWidget);
		}

		private boolean isAForwardKey(char keyCode) {
			return keyCode == KeyboardListener.KEY_BACKSPACE || keyCode == KeyboardListener.KEY_DELETE
					|| keyCode == KeyboardListener.KEY_HOME || keyCode == KeyboardListener.KEY_LEFT;
		}

	}
}
package org.openinsula.arena.gwt.client.ui.form.util;

import java.util.Date;

import org.openinsula.arena.gwt.client.ui.FocusComposite;
import org.openinsula.arena.gwt.client.ui.form.FormFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class JSMaskedDateWidget extends FocusComposite {

	private TextBox textBoxDate;

	private static final String dateMask = "dd/MM/yyyy";

	@SuppressWarnings("deprecation")
	public JSMaskedDateWidget() {
		textBoxDate = FormFactory.textBoxDate();
		initWidget(textBoxDate);

		textBoxDate.addKeyboardListener(new FormataDataKeyboardListener());
	}

	/**
	 *
	 * @return
	 */
	public Date getDate() {
		Date date = null;

		try {
			date = DateTimeFormat.getFormat(dateMask).parse(getText());
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

		setText(DateTimeFormat.getFormat(dateMask).format(date));
	}

	/**
	 * Format the date in "dd/MM/yyyy" pattern
	 * @param value
	 * @return
	 */
	public native static String formatDate(String value) /*-{
		function filtraCampo(value){
			var s = "";
			var cp = "";
			vr = value;
			tam = vr.length;
			for (i = 0; i < tam ; i++) {
				if (vr.substring(i,i + 1) != "/" && vr.substring(i,i + 1) != "-" && vr.substring(i,i + 1) != "."  && vr.substring(i,i + 1) != "," ){
				 	s = s + vr.substring(i,i + 1);}
			}
			return s;
		}

		value = filtraCampo(value);
		value = value.replace(/\D/gi, "");
		vr = value;
		tam = vr.length;

		if ( tam > 2 && tam < 5 )
			value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		if ( tam >= 5 && tam <= 10 )
			value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 4 );

		return value;

	}-*/;

	public void addClickListener(ClickListener listener) {
		textBoxDate.addClickListener(listener);
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		textBoxDate.addFocusListener(listener);
	}

	@Override
	public void addKeyboardListener(KeyboardListener listener) {
		textBoxDate.addKeyboardListener(listener);
	}

	public String getText() {
		return textBoxDate.getText();
	}

	@Override
	public boolean isEnabled() {
		return textBoxDate.isEnabled();
	}

	public boolean isReadOnly() {
		return textBoxDate.isReadOnly();
	}

	@Override
	public boolean isVisible() {
		return textBoxDate.isVisible();
	}

	@Override
	public void setEnabled(boolean enabled) {
		textBoxDate.setEnabled(enabled);
	}

	@Override
	public void setFocus(boolean focused) {
		textBoxDate.setFocus(focused);
	}

	private class FormataDataKeyboardListener extends KeyboardListenerAdapter {
		@Override
		public void onKeyUp(Widget sender, char keyCode, int modifiers) {
			textBoxDate.setText(formatDate(textBoxDate.getText()));
		}

		@Override
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

	public void setText(String text) {
		textBoxDate.setText(text);
	}

	public void clear() {
		textBoxDate.setText("");
	}
}

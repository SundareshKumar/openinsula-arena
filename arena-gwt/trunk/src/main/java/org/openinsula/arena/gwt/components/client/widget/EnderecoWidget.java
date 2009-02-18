package org.openinsula.arena.gwt.components.client.widget;

import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusListenerCollection;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EnderecoWidget extends Composite implements HasFocus, FocusListener {

	private FocusListenerCollection focusListeners;

	private final HTMLWidget<DivElement> mainElement;

	private TextBox cepTextBox;

	private TextBox logradouroTextBox;

	private TextBox numeroTextBox;

	private TextBox complementoTextBox;

	private TextBox bairroTextBox;

	private TextBox localidadeTextBox;

	private TextBox ufTextBox;

	public EnderecoWidget() {
		mainElement = HTMLWidgetFactory.div();
//		setElement(mainElement.getElement());
		initWidget(mainElement);
		setStyleName("column");
		initComponents();
	}

	private void initComponents() {
		cepTextBox = new TextBox();
		logradouroTextBox = new TextBox();
		numeroTextBox = new TextBox();
		complementoTextBox = new TextBox();
		bairroTextBox = new TextBox();
		localidadeTextBox = new TextBox();
		ufTextBox = new TextBox();

		mainElement.add(createTextBox("Cep", "left", cepTextBox, 0));
		mainElement.add(createTextBox("Logradouro", "right", logradouroTextBox, 0));
		mainElement.add(createTextBox("Numero", "left", numeroTextBox, 0));
		mainElement.add(createTextBox("Complemento", "right", complementoTextBox, 0));
		mainElement.add(createTextBox("Bairro", null, bairroTextBox, 5));
		mainElement.add(createTextBox("Localidade", null, localidadeTextBox, 5));
		mainElement.add(createTextBox("U.F.", null, ufTextBox, 2));
	}

	private Widget createTextBox(final String label, final String position, final TextBox textBox, final int size) {
		textBox.addFocusListener(this);

		if (size != 0) {
			textBox.getElement().setPropertyString("size", String.valueOf(size));
		}
		textBox.setStyleName("field text");

		final HTMLWidget<LabelElement> labelElement = HTMLWidgetFactory.label();
		labelElement.getHTMLElement().setInnerHTML(label);

		final HTMLWidget<SpanElement> span = HTMLWidgetFactory.span();

		if (position != null) {
			span.setStyleName(position);
		}

		span.add(textBox);
		span.add(labelElement);

		return span;
	}

	public void onFocus(final Widget sender) {
//		setFocus(true);
		if (focusListeners != null) {
			focusListeners.fireFocus(this);
		}
	}

	public void onLostFocus(final Widget sender) {
//		setFocus(false);
		if (focusListeners != null) {
			focusListeners.fireLostFocus(this);
		}
	}

	public int getTabIndex() {
		return 0;
	}

	public void setAccessKey(final char key) {
	}

	public void setFocus(final boolean focused) {
		cepTextBox.setFocus(focused);
	}

	public void setTabIndex(final int index) {
	}

	public void addFocusListener(final FocusListener listener) {
		if (focusListeners == null) {
			focusListeners = new FocusListenerCollection();
		}
		focusListeners.add(listener);
	}

	public void removeFocusListener(final FocusListener listener) {
		if (focusListeners != null) {
			focusListeners.remove(listener);
		}
	}

	public void addKeyboardListener(final KeyboardListener listener) {
	}

	public void removeKeyboardListener(final KeyboardListener listener) {
	}

}

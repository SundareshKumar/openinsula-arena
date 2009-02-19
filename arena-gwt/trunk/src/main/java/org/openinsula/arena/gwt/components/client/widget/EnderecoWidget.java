package org.openinsula.arena.gwt.components.client.widget;

import com.google.gwt.user.client.ui.TextBox;

public class EnderecoWidget extends AbstractWidget {

	private TextBox cepTextBox;
	private TextBox logradouroTextBox;
	private TextBox numeroTextBox;
	private TextBox complementoTextBox;
	private TextBox bairroTextBox;
	private TextBox localidadeTextBox;
	private TextBox ufTextBox;

	@Override
	protected void initComponents() {
		cepTextBox = new TextBox();
		logradouroTextBox = new TextBox();
		numeroTextBox = new TextBox();
		complementoTextBox = new TextBox();
		bairroTextBox = new TextBox();
		localidadeTextBox = new TextBox();
		ufTextBox = new TextBox();

		createTextBox("Cep", "left", cepTextBox, 0);
		createTextBox("Logradouro", "right", logradouroTextBox, 0);
		createTextBox("Numero", "left", numeroTextBox, 0);
		createTextBox("Complemento", "right", complementoTextBox, 0);
		createTextBox("Bairro", null, bairroTextBox, 5);
		createTextBox("Localidade", null, localidadeTextBox, 5);
		createTextBox("U.F.", null, ufTextBox, 2);
	}

	public void setFocus(final boolean focused) {
		cepTextBox.setFocus(focused);
	}

}

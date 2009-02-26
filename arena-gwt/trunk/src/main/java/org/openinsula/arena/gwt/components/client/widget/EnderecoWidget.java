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

	@Override
	public void setFocus(final boolean focused) {
		cepTextBox.setFocus(focused);
	}

	public EnderecoVO getEnderecoVO() {

		final EnderecoVO enderecoVO = new EnderecoVO();
		enderecoVO.setBairro(bairroTextBox.getText());
		enderecoVO.setCep(cepTextBox.getText());
		enderecoVO.setComplemento(complementoTextBox.getText());
		enderecoVO.setLocalidade(localidadeTextBox.getText());
		enderecoVO.setLogradouro(logradouroTextBox.getText());
		enderecoVO.setNumero(numeroTextBox.getText());
		enderecoVO.setUf(ufTextBox.getText());

		return enderecoVO;
	}

	public static class EnderecoVO {

		private String cep;
		private String logradouro;
		private String numero;
		private String complemento;
		private String bairro;
		private String localidade;
		private String uf;

		public String getCep() {
			return cep;
		}
		public void setCep(final String cep) {
			this.cep = cep;
		}

		public String getLogradouro() {
			return logradouro;
		}
		public void setLogradouro(final String logradouro) {
			this.logradouro = logradouro;
		}

		public String getNumero() {
			return numero;
		}
		public void setNumero(final String numero) {
			this.numero = numero;
		}

		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(final String complemento) {
			this.complemento = complemento;
		}

		public String getBairro() {
			return bairro;
		}
		public void setBairro(final String bairro) {
			this.bairro = bairro;
		}

		public String getLocalidade() {
			return localidade;
		}
		public void setLocalidade(final String localidade) {
			this.localidade = localidade;
		}

		public String getUf() {
			return uf;
		}
		public void setUf(final String uf) {
			this.uf = uf;
		}

	}

}

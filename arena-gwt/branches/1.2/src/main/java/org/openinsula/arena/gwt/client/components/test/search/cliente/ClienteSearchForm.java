package org.openinsula.arena.gwt.client.components.test.search.cliente;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.pessoa.Pessoa;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.ui.HasFocus;

public class ClienteSearchForm extends AbstractSearchFormTemplate<Cliente> {

	public ClienteSearchForm(HasFocus nextFocusableComponent) {
		super(nextFocusableComponent);
	}

	@Override
	protected AbstractDetailsSearchFormTemplate<Cliente> createDetailsSearchForm(HasFocus nextFocusableComponent) {
		return new ClienteDetailSearchForm(this, nextFocusableComponent);
	}

	@Override
	protected Cliente createNewEditableInstance(String suggestBoxContent) {
		Cliente cliente = new Cliente();
		cliente.setPessoa(new Pessoa());

		return cliente;
	}

	@Override
	protected String getSuggestBoxContent(Cliente bean) {
		return bean.getPessoa().getNome();
	}

	@Override
	protected String getSuggestBoxHint() {
		return "Digite o nome";
	}

	@Override
	protected String getSuggestBoxLabel() {
		return "Pessoa";
	}

	@Override
	protected RemoteBeanSuggestOracle<Cliente> getSuggestOracle() {
		return new ClienteSuggestOracle();
	}

	@Override
	protected String getErrorMessage() {
		return "Selecione o funcionario";
	}

}

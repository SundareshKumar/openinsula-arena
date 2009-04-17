package org.openinsula.arena.gwt.client.components.test.search.cliente;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.pessoa.PessoaTest;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.ui.HasFocus;

public class ClienteSearchFormTest extends AbstractSearchFormTemplate<ClienteTest> {

	public ClienteSearchFormTest(HasFocus nextFocusableComponent) {
		super(nextFocusableComponent);
	}

	@Override
	protected AbstractDetailsSearchFormTemplate<ClienteTest> createDetailsSearchForm(HasFocus nextFocusableComponent) {
		return new ClienteDetailSearchFormTest(this, nextFocusableComponent);
	}

	@Override
	protected ClienteTest createNewEditableInstance(String suggestBoxContent) {
		ClienteTest cliente = new ClienteTest();
		cliente.setPessoa(new PessoaTest());

		return cliente;
	}

	@Override
	protected String getSuggestBoxContent(ClienteTest bean) {
		return bean.getPessoa().getNome();
	}

	@Override
	protected String getSuggestBoxHint() {
		return "Digite o nome";
	}

	@Override
	protected RemoteBeanSuggestOracle<ClienteTest> getSuggestOracle() {
		return new ClienteSuggestOracleTest();
	}

	@Override
	protected String getErrorMessage() {
		return "Selecione o funcionario";
	}

}

package org.openinsula.arena.gwt.client.components.test.search.pessoa;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.ui.HasFocus;

public class PessoaSearchForm extends AbstractSearchFormTemplate<Pessoa> {

	public PessoaSearchForm(HasFocus nextFocusableComponent) {
		super(nextFocusableComponent);
	}

	@Override
	protected AbstractDetailsSearchFormTemplate<Pessoa> createDetailsSearchForm(HasFocus nextFocusableComponent) {
		return new PessoaDetailSearchForm(this, nextFocusableComponent);
	}

	@Override
	protected Pessoa createNewEditableInstance(String suggestBoxContent) {
		return new Pessoa();
	}

	@Override
	protected String getSuggestBoxContent(Pessoa bean) {
		return bean.getNome();
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
	protected RemoteBeanSuggestOracle<Pessoa> getSuggestOracle() {
		return new PessoaSuggestOracle();
	}

	@Override
	protected String getErrorMessage() {
		return "Selecione uma pessoa";
	}

}

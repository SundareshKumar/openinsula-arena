package org.openinsula.arena.gwt.client.components.test.search.pessoa;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.ui.HasFocus;

public class PessoaSearchFormTest extends AbstractSearchFormTemplate<PessoaTest> {

	public PessoaSearchFormTest(HasFocus nextFocusableComponent) {
		super(nextFocusableComponent);
	}

	@Override
	protected AbstractDetailsSearchFormTemplate<PessoaTest> createDetailsSearchForm(HasFocus nextFocusableComponent) {
		return new PessoaDetailSearchFormTest(this, nextFocusableComponent);
	}

	@Override
	protected PessoaTest createNewEditableInstance(String suggestBoxContent) {
		return new PessoaTest();
	}

	@Override
	protected String getSuggestBoxContent(PessoaTest bean) {
		return bean.getNome();
	}

	@Override
	protected String getSuggestBoxHint() {
		return "Digite o nome";
	}

	@Override
	protected RemoteBeanSuggestOracle<PessoaTest> getSuggestOracle() {
		return new PessoaSuggestOracleTest();
	}

	@Override
	protected String getErrorMessage() {
		return "Selecione uma pessoa";
	}

}

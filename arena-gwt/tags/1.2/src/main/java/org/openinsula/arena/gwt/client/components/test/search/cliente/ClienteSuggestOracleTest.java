package org.openinsula.arena.gwt.client.components.test.search.cliente;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.components.test.search.pessoa.PessoaTest;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestion;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ClienteSuggestOracleTest extends RemoteBeanSuggestOracle<ClienteTest> {

	private List<ClienteTest> model;


	@Override
	protected void getRemoteCandidates(Request request, AsyncCallback<List<ClienteTest>> callback) {
		if (model == null) {
			PessoaTest p1 = new PessoaTest();
			p1.setNome("fabiano");
			ClienteTest cli1 = new ClienteTest();
			cli1.setPessoa(p1);

			PessoaTest p2 = new PessoaTest();
			p2.setNome("maeda");
			ClienteTest cli2 = new ClienteTest();
			cli2.setPessoa(p2);

			PessoaTest p3 = new PessoaTest();
			p3.setNome("marcelo");
			ClienteTest cli3 = new ClienteTest();
			cli3.setPessoa(p3);

			model = new ArrayList<ClienteTest>();
			model.add(cli1);
			model.add(cli2);
			model.add(cli3);
		}

		callback.onSuccess(model);
	}

	public boolean isDisplayStringHTML() {
		return true;
	}

	protected BeanSuggestion<ClienteTest> getBeanSuggestionFor(final ClienteTest bean, final Request request) {
		return new ClienteSuggestion(bean, request.getQuery());
	}

	private class ClienteSuggestion extends BeanSuggestion<ClienteTest> {
		public ClienteSuggestion(final ClienteTest bean, final String query) {
			super(bean, query);
		}

		protected String getBeanDisplayString(final ClienteTest bean) {
			return bean.getPessoa().getNome();
		}

		protected String getBeanReplacementString(final ClienteTest bean) {
			return bean.getPessoa().getNome();
		}
	}

}

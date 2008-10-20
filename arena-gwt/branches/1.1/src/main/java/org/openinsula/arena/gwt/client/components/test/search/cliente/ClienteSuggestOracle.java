package org.openinsula.arena.gwt.client.components.test.search.cliente;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.components.test.search.pessoa.Pessoa;
import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestion;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ClienteSuggestOracle extends RemoteBeanSuggestOracle<Cliente> {

	private List<Cliente> model;


	@Override
	protected void getRemoteCandidates(Request request, AsyncCallback<List<Cliente>> callback) {
		if (model == null) {
			Pessoa p1 = new Pessoa();
			p1.setNome("fabiano");
			Cliente cli1 = new Cliente();
			cli1.setPessoa(p1);

			Pessoa p2 = new Pessoa();
			p2.setNome("maeda");
			Cliente cli2 = new Cliente();
			cli2.setPessoa(p2);

			Pessoa p3 = new Pessoa();
			p3.setNome("marcelo");
			Cliente cli3 = new Cliente();
			cli3.setPessoa(p3);

			model = new ArrayList<Cliente>();
			model.add(cli1);
			model.add(cli2);
			model.add(cli3);
		}

		callback.onSuccess(model);
	}

	public boolean isDisplayStringHTML() {
		return true;
	}

	protected BeanSuggestion<Cliente> getBeanSuggestionFor(final Cliente bean, final Request request) {
		return new ClienteSuggestion(bean, request.getQuery());
	}

	private class ClienteSuggestion extends BeanSuggestion<Cliente> {
		public ClienteSuggestion(final Cliente bean, final String query) {
			super(bean, query);
		}

		protected String getBeanDisplayString(final Cliente bean) {
			return bean.getPessoa().getNome();
		}

		protected String getBeanReplacementString(final Cliente bean) {
			return bean.getPessoa().getNome();
		}
	}

}

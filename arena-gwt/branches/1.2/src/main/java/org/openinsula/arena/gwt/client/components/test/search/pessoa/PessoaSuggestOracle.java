package org.openinsula.arena.gwt.client.components.test.search.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestion;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class PessoaSuggestOracle extends RemoteBeanSuggestOracle<Pessoa> {

	private List<Pessoa> model;


	@Override
	protected void getRemoteCandidates(Request request, AsyncCallback<List<Pessoa>> callback) {
		if (model == null) {
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("fabiano");
			pessoa.setCpf("1");

			Pessoa pessoa2 = new Pessoa();
			pessoa2.setNome("maeda");
			pessoa2.setCpf("2");

			Pessoa pessoa3 = new Pessoa();
			pessoa3.setNome("marcelo");
			pessoa3.setCpf("3");

			model = new ArrayList<Pessoa>();
			model.add(pessoa);
			model.add(pessoa2);
			model.add(pessoa3);
		}

		callback.onSuccess(model);
	}

	public boolean isDisplayStringHTML() {
		return true;
	}

	protected BeanSuggestion<Pessoa> getBeanSuggestionFor(final Pessoa bean, final Request request) {
		return new PessoaSuggestion(bean, request.getQuery());
	}

	private class PessoaSuggestion extends BeanSuggestion<Pessoa> {
		public PessoaSuggestion(final Pessoa bean, final String query) {
			super(bean, query);
		}

		protected String getBeanDisplayString(final Pessoa bean) {
			return bean.getNome();
		}

		protected String getBeanReplacementString(final Pessoa bean) {
			return bean.getNome();
		}
	}

}

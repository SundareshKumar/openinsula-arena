package org.openinsula.arena.gwt.client.components.test.search.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.ui.suggest.BeanSuggestion;
import org.openinsula.arena.gwt.client.ui.suggest.RemoteBeanSuggestOracle;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class PessoaSuggestOracleTest extends RemoteBeanSuggestOracle<PessoaTest> {

	private List<PessoaTest> model;


	@Override
	protected void getRemoteCandidates(Request request, AsyncCallback<List<PessoaTest>> callback) {
		if (model == null) {
			PessoaTest pessoa = new PessoaTest();
			pessoa.setNome("fabiano");
			pessoa.setCpf("1");

			PessoaTest pessoa2 = new PessoaTest();
			pessoa2.setNome("maeda");
			pessoa2.setCpf("2");

			PessoaTest pessoa3 = new PessoaTest();
			pessoa3.setNome("marcelo");
			pessoa3.setCpf("3");

			model = new ArrayList<PessoaTest>();
			model.add(pessoa);
			model.add(pessoa2);
			model.add(pessoa3);
		}

		callback.onSuccess(model);
	}

	public boolean isDisplayStringHTML() {
		return true;
	}

	protected BeanSuggestion<PessoaTest> getBeanSuggestionFor(final PessoaTest bean, final Request request) {
		return new PessoaSuggestion(bean, request.getQuery());
	}

	private class PessoaSuggestion extends BeanSuggestion<PessoaTest> {
		public PessoaSuggestion(final PessoaTest bean, final String query) {
			super(bean, query);
		}

		protected String getBeanDisplayString(final PessoaTest bean) {
			return bean.getNome();
		}

		protected String getBeanReplacementString(final PessoaTest bean) {
			return bean.getNome();
		}
	}

}

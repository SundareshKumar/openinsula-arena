package org.openinsula.arena.tiss.builder;

import br.gov.ans.padroes.tiss.schemas.CtTabela;

public class CtTabelaBuilder {
	private CtTabela tabela = new CtTabela();
	
	public CtTabelaBuilder(String codigoTabela, TipoTabela tipoTabela) {
		tabela.setCodigo(codigoTabela);
		tabela.setTipoTabela(tipoTabela.toString());
	}
	
	public CtTabelaBuilder descricao(String descricao) {
		tabela.setDescricao(descricao);
		
		return this;
	}
	
	public CtTabela build() {
		return tabela;
	}
	
}
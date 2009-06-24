package org.openinsula.arena.tiss.builder;


import java.math.BigDecimal;

import br.gov.ans.padroes.tiss.schemas.CtItemSolicitacao;
import br.gov.ans.padroes.tiss.schemas.CtTabela;

public class CtItemSolicitacaoBuilder {
	
	private CtItemSolicitacao itemSolicitacao = new CtItemSolicitacao();

	public CtItemSolicitacaoBuilder(CtTabela identificacaoProcedimento, int quantidadeSolicitada, StatusSolicitacao statusSolicitacao) {
		itemSolicitacao.setIdentificacaoProcedimentos(identificacaoProcedimento);
		itemSolicitacao.setQuantidadeSolicitada(new BigDecimal(quantidadeSolicitada));
		itemSolicitacao.setStatusSolicitacaoProcedimento(statusSolicitacao.toString());
	}
	
	public CtItemSolicitacaoBuilder quantidadeAutorizada(int quantidadeAutorizada) {
		itemSolicitacao.setQuantidadeAutorizada(new BigDecimal(quantidadeAutorizada));
		
		return this;
	}
	
	public CtItemSolicitacaoBuilder observacao(String observacao) {
		itemSolicitacao.setObservacao(observacao);
		
		return this;
	}
	
	public CtItemSolicitacao build() {
		return itemSolicitacao;
	}
	
}

package org.openinsula.arena.tiss.builder;

import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Destino;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.IdentificacaoTransacao;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Origem;

public class CabecalhoTransacaoBuilder {
	
	private CabecalhoTransacao cabecalhoTransacao = new CabecalhoTransacao();
	
	public static final String VERSAO_TISS = "2.02.01";
	
	public CabecalhoTransacaoBuilder(IdentificacaoTransacao identificacaoTransacao, Origem origem, Destino destino) {
		cabecalhoTransacao.setIdentificacaoTransacao(identificacaoTransacao);
		cabecalhoTransacao.setOrigem(origem);
		cabecalhoTransacao.setDestino(destino);
		cabecalhoTransacao.setVersaoPadrao(VERSAO_TISS);
	}
	
	public CabecalhoTransacao build() {
		return cabecalhoTransacao;
	}

}

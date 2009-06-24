package org.openinsula.arena.tiss.builder;

import br.gov.ans.padroes.tiss.schemas.CtIdentificacaoPrestadorExecutante;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Destino;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.Origem;


public class OrigemDestinoBuilder {

	private CtIdentificacaoPrestadorExecutante identificacaoPrestadorExecutante;

	private String registroANS;

	private String cnpjPagador;

	public OrigemDestinoBuilder registroANS(String registroANS) {
		this.registroANS = registroANS;
		
		return this;
	}
	
	public OrigemDestinoBuilder codigoPrestadorNaOperadora(String codigoPrestadorNaOperadora) {
		identificacaoPrestadorExecutante = new CtIdentificacaoPrestadorExecutante();
		identificacaoPrestadorExecutante.setCodigoPrestadorNaOperadora(codigoPrestadorNaOperadora);
		
		return this;
	}
	
	public OrigemDestinoBuilder cnpjPagador(String cnpjPagador) {
		this.cnpjPagador = cnpjPagador;
		
		return this;
	}
	
	public Origem buildOrigem() {
		Origem origem = new Origem();
		
		origem.setCodigoPrestadorNaOperadora(identificacaoPrestadorExecutante);
		origem.setRegistroANS(registroANS);
		origem.setCnpjPagador(cnpjPagador);
		
		return origem;
	}
	
	public Destino buildDestino() {
		Destino destino = new Destino();
		
		destino.setCodigoPrestadorNaOperadora(identificacaoPrestadorExecutante);
		destino.setRegistroANS(registroANS);
		destino.setCnpjPagador(cnpjPagador);
		
		return destino;
	}
	
}

package org.openinsula.arena.tiss.builder;

import br.gov.ans.padroes.tiss.schemas.CtContratado;
import br.gov.ans.padroes.tiss.schemas.CtEndereco;
import br.gov.ans.padroes.tiss.schemas.CtIdentificacaoPrestadorExecutante;

public class CtContratadoBuilder {
	private CtContratado contratado = new CtContratado();

	public CtContratadoBuilder(String codigoPrestadorNaOperadora, String nomeContratado) {
		CtIdentificacaoPrestadorExecutante identificacaoPrestadorExecutante = new CtIdentificacaoPrestadorExecutante();
		identificacaoPrestadorExecutante.setCodigoPrestadorNaOperadora(codigoPrestadorNaOperadora);

		contratado.setIdentificacao(identificacaoPrestadorExecutante);
		contratado.setNomeContratado(nomeContratado);
	}
	
	public CtContratadoBuilder enderecoContratado(CtEndereco endereco) {
		contratado.setEnderecoContratado(endereco);
		
		return this;
	}
	
	public CtContratadoBuilder numeroCNES(String numeroCNES) {
		contratado.setNumeroCNES(numeroCNES);
		
		return this;
	}
	
	public CtContratado build() {
		return contratado;
	}

}

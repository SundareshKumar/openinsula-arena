package org.openinsula.arena.tiss.builder;

import java.util.Date;

import br.gov.ans.padroes.tiss.schemas.CtCabecalhoGuia;
import br.gov.ans.padroes.tiss.schemas.CtCabecalhoGuia.IdentificacaoFontePagadora;

public class CtCabecalhoGuiaBuilder {
	private CtCabecalhoGuia cabecalhoGuia = new CtCabecalhoGuia();

	public CtCabecalhoGuiaBuilder(String registroANS, Date dataEmissaoGuia, String numeroGuiaPrestador) {
		IdentificacaoFontePagadora identificacaoFontePagadora = new IdentificacaoFontePagadora();
		identificacaoFontePagadora.setRegistroANS(registroANS);

		cabecalhoGuia.setIdentificacaoFontePagadora(identificacaoFontePagadora);
		cabecalhoGuia.setDataEmissaoGuia(TissTypeConverterUtils.getAsData(dataEmissaoGuia));
		cabecalhoGuia.setNumeroGuiaPrestador(numeroGuiaPrestador);
	}
	
	public CtCabecalhoGuiaBuilder numeroGuiaOperadora(String numeroGuiaOperadora) {
		cabecalhoGuia.setNumeroGuiaOperadora(numeroGuiaOperadora);
		
		return this;
	}
	
	public CtCabecalhoGuia build() {
		return cabecalhoGuia;
	}
}

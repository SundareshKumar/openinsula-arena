package org.openinsula.arena.tiss.builder;

import java.math.BigInteger;
import java.util.Date;

import br.gov.ans.padroes.tiss.schemas.StTipoTransacao;
import br.gov.ans.padroes.tiss.schemas.CabecalhoTransacao.IdentificacaoTransacao;

public class IdentificacaoTransacaoBuilder {
	private IdentificacaoTransacao identificacaoTransacao = new IdentificacaoTransacao();
	
	public IdentificacaoTransacaoBuilder(StTipoTransacao tipoTransacao, BigInteger sequencialTransacao, Date date) {
		identificacaoTransacao.setTipoTransacao(tipoTransacao);
		identificacaoTransacao.setSequencialTransacao(sequencialTransacao);
		identificacaoTransacao.setDataRegistroTransacao(TissTypeConverterUtils.getAsData(date));
		identificacaoTransacao.setHoraRegistroTransacao(TissTypeConverterUtils.getAsTime(date));
	}
	
	public IdentificacaoTransacao build() {
		return identificacaoTransacao;
	}
	
}

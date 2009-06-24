package org.openinsula.arena.tiss.builder;

import java.util.Date;

import br.gov.ans.padroes.tiss.schemas.CtBeneficiario;

public class CtBeneficiarioBuilder {
	private CtBeneficiario beneficiario = new CtBeneficiario();
	
	public CtBeneficiarioBuilder(String numeroCarteira, String nomeBeneficiario, String nomePlano) {
		beneficiario.setNumeroCarteira(numeroCarteira);
		beneficiario.setNomeBeneficiario(nomeBeneficiario);
		beneficiario.setNomePlano(nomePlano);
	}
	
	public CtBeneficiarioBuilder validadeCarteira(Date validadeCarteira) {
		beneficiario.setValidadeCarteira(TissTypeConverterUtils.getAsData(validadeCarteira));
		return this;
	}
	
	public CtBeneficiario build() {
		return beneficiario;
	}
	
}
package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoTransacaoRateioDeCreditoLineFactory extends AbstractLineFactory {
	private static final String FILLER = "FILLER";

	public RetornoTransacaoRateioDeCreditoLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new StringField(16, "IDENTIFICACAO DA EMPRESA NO BANCO"));
		addField(new StringField(12, "IDENTIFICACAO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DE CALCULO DO RATEIO"));
		addField(new NumericField(1, "TIPO DE VALOR INFORMADO"));
		addField(new StringField(12, FILLER));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 1.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 2.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 2.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 2.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 3.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 3.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 3.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 0));
	}
}

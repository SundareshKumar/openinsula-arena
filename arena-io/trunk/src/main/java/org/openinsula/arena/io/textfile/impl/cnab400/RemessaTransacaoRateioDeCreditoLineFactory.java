package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoRateioDeCreditoLineFactory extends AbstractLineFactory {
	private static final String FILLER = "FILLER";

	public RemessaTransacaoRateioDeCreditoLineFactory() {
		addField(new NumericField(1, "TIPO REGISTRO"));
		addField(new StringField(16, "IDENTIFICACAO DA EMPRESA NO BANCO"));
		addField(new StringField(12, "IDENTIFICACAO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DE CALCULO DO RATEIO"));
		addField(new NumericField(1, "TIPO DE VALOR INFORMADO"));
		addField(new StringField(12, FILLER));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 1.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 1.o BENEFICIARIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 2.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 3.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 3.o BENEFICIARIO"));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 0));
	}
}

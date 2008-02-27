package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoTransacaoDetalheLineFactory extends AbstractLineFactory {
	public RetornoTransacaoDetalheLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(2, "TIPO DE INSCRICAO EMPRESA"));
		addField(new NumericField(14, "N INSCRICAO DA EMPRESA"));
		addField(new NumericField(3, "ZEROS"));
		addField(new StringField(17, "IDENTIFICACAO DA EMPRESA CEDENTE NO BANCO"));
		addField(new StringField(25, "N CONTROLE DO PARTICIPANTE"));
		addField(new NumericField(8, "ZEROS"));
		addField(new StringField(12, "IDENTIFICACAO DO TITULO NO BANCO"));
		addField(new StringField(10, "USO DO BANCO"));
		addField(new NumericField(12, "USO DO BANCO"));
		addField(new StringField(1, "IDENTIFICACAO DO RATEIO CREDITO"));
		addField(new NumericField(2, "ZEROS"));
		addField(new NumericField(1, "CARTEIRA"));
		addField(new NumericField(2, "IDENTIFICACAO DA OCORRENCIA"));
		addField(new DateField(6, "ddMMyy", "DATA OCORRENCIA NO BANCO"));
		addField(new StringField(10, "NUMERO DO DOCUMENTO"));
		addField(new StringField(20, "IDENTIFICACAO DO TITULO NO BANCO"));
		addField(new DateField(6, "ddMMyy", "DATA VENCIMENTO DO TITULO"));
		addField(new NumericField(13, "VALOR TITULO"));
		addField(new NumericField(3, "BANCO COBRADOR"));
		addField(new NumericField(5, "AGENCIA COBRADORA"));
		addField(new StringField(2, "ESPECIE DO TITULO"));
		addField(new NumericField(13, "DESPESAS DE COBRANCA"));
		addField(new NumericField(13, "OUTRAS DESPESAS"));
		addField(new NumericField(13, "JUROS OPERACAO EM ATRASAO"));
		addField(new NumericField(13, "IOF DEVIDO"));
		addField(new NumericField(13, "ABATIMENTO CONCEDIDO SOBRE O TITULO"));
		addField(new NumericField(13, "DESCONTO CONCEDIDO"));
		addField(new NumericField(13, "VALOR PAGO"));
		addField(new NumericField(13, "JUROS DE MORA"));
		addField(new NumericField(13, "OUTROS CREDITOS"));
		addField(new StringField(2, "BRANCOS"));
		addField(new StringField(1, "MOTIVO DO CODIGO DE OCORRENCIA 19"));
		addField(new DateField(6, "ddMMyy", "DATA DO CREDITO"));
		addField(new StringField(17, "BRANCOS"));
		addField(new StringField(10, "MOTIVOS DAS REJEICOES"));
		addField(new StringField(66, "BRANCOS"));
		addField(new NumericField(6, "N SEQUENCIAL DE REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "1".equals(getFieldString(s, 0));
	}
}

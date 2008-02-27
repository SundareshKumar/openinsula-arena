package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoTraillerLineFactory extends AbstractLineFactory {
	private static final String BRANCOS = "BRANCOS";

	public RetornoTraillerLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(1, "IDENTIFICACAO DO RETORNO"));
		addField(new NumericField(2, "IDENTIFICACAO TIPO DE REGISTRO"));
		addField(new NumericField(3, "CODIGO DO BANCO"));
		addField(new StringField(10, BRANCOS));
		addField(new NumericField(8, "QUANTIDADE DE TITULOS EM COBRANCA"));
		addField(new NumericField(14, "VALOR TOTAL EM COBRANCA"));
		addField(new NumericField(8, "N DO AVISO BANCARIO"));
		addField(new StringField(10, BRANCOS));
		addField(new NumericField(5, "QUANTIDADE DE REGISTROS - OCORRENCIA 02"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 02"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 06"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 06"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 06/09/10"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 09/10"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 09/10"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 13"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 13"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 14"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 14"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 12"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 12"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 19"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 19"));
		addField(new StringField(174, BRANCOS));
		addField(new NumericField(15, "VALOR TOTAL DOS RATEIOS EFETUADOS"));
		addField(new NumericField(8, "QUANTIDADE TOTAL DOS RATEIOS EFETUADOS"));
		addField(new StringField(9, BRANCOS));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "9".equals(getFieldString(s, 0));
	}
}

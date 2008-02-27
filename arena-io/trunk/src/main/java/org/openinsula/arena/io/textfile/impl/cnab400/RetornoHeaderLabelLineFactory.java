package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoHeaderLabelLineFactory extends AbstractLineFactory {
	public RetornoHeaderLabelLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(1, "IDENTIFICACAO DO ARQUIVO RETORNO"));
		addField(new StringField(7, "LITERAL RETORNO"));
		addField(new NumericField(2, "CODIGO DO SERVICO"));
		addField(new StringField(15, "LITERAL SERVICO"));
		addField(new NumericField(20, "CODIGO DA EMPRESA"));
		addField(new StringField(30, "NOME DA EMPRESA POR EXTENSO"));
		addField(new NumericField(3, "N DO BANCO NA CAMARA COMPENSACAO"));
		addField(new StringField(15, "NOME DO BANCO POR EXTENSO"));
		addField(new DateField(6, "ddMMyy", "DATA DA GRAVACAO DO ARQUIVO"));
		addField(new NumericField(8, "ZEROS"));
		addField(new NumericField(5, "N AVISO BANCARIO"));
		addField(new StringField(266, "BRANCO"));
		addField(new DateField(6, "ddMMyy", "DATA DO CREDITO"));
		addField(new StringField(9, "BRANCO"));
		addField(new NumericField(6, "N SEQUENCIAL DE REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "0".equals(getFieldString(s, 0)) && "2".equals(getFieldString(s, 1));
	}
}

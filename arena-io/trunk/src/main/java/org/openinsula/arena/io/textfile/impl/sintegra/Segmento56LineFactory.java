package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento56LineFactory extends AbstractLineFactory {

	public Segmento56LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ OU CPF DO ADQUIRENTE"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new NumericField(3, "CODIGO DA SITUACAO TRIBUTARIA"));
		addField(new NumericField(3, "NUMERO DE ORDEM DO ITEM NA NOTA FISCAL"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO INFORMANTE"));
		addField(new NumericField(1, "TIPO DE OPERACAO"));
		addField(new NumericField(14, "CNPJ da Concessionaria"));
		addField(new NumericField(4, "ALIQUOTA do IPI"));
		addField(new StringField(17, "CODIGO DO CHASSI DO VEICULO"));
		addField(new StringField(39, "BRANCOS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "56".equals(s.substring(0, 2));
	}

}

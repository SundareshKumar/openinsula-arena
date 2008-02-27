package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento88ECFLineFactory extends AbstractLineFactory {

	public Segmento88ECFLineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new StringField(3, "SUBTIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new NumericField(3, "CODIGO DA SITUACAO TRIBUTARIA (CST)"));
		addField(new NumericField(3, "NUMERO DE ORDEM DO ITEM NA NOTA FISCAL"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO DO INFORMANTE"));
		addField(new StringField(20, "NUMERO DE SERIE DE FABRICACAO DO EQUIPAMENTO ECF"));
		addField(new StringField(52, "BRANCOS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "88ECF".equals(s.substring(0, 5));
	}

}

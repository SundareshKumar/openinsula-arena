package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento88ALineFactory extends AbstractLineFactory {

	public Segmento88ALineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new StringField(1, "SUBTIPO"));
		addField(new NumericField(2, "MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(10, "CODIFICACAO DA NOMENCLATURA BRASILEIRA DE MERCADORIAS (NBM)"));
		addField(new StringField(14, "CNPJ OU CPF DO TRANSPORTADOR"));
		addField(new StringField(34, "NOME DO TRANSPORTADOR"));
		addField(new StringField(2, "UNIDADE DA FEDERACAO"));
		addField(new NumericField(1, "IDENTIFICACAO DO MEIO DE TRANSPORTE"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (1)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (1)"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (2)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (2)"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (3)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (3)"));
		addField(new StringField(16, "NUMERO DA MATRICULA"));
		addField(new DateField(8, "yyyyMMdd", "DATA DA SAIDA DA MERCADORIA"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "88A".equals(s.substring(0, 3));
	}

}

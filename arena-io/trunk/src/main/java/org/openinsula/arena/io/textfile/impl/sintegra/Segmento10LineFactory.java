package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento10LineFactory extends AbstractLineFactory {

	public Segmento10LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(14, "INSCRICAO ESTADUAL DO ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(35, "NOME COMERCIAL (RAZAO SOCIAL/DENOMINACAO) DO CONTRIBUINTE"));
		addField(new StringField(30, "MUNICIPIO ONDE ESTA DOMICILIADO O ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(2, "UNIDADE DA FEDERACAO REFERENTE AO MUNICIPIO"));
		addField(new NumericField(10, "NUMERO DO FAX DO ESTABELECIMENTO INFORMANTE"));
		addField(new DateField(8, "yyyyMMdd", "DATA DO INICIO DO PERIODO REFERENTE AS INFORMACOES PRESTADAS"));
		addField(new DateField(8, "yyyyMMdd", "DATA DO FIM DO PERIODO REFERENTE AS INFORMACOES PRESTADAS"));
		addField(new StringField(1, "CODIGO DA IDENTIFICACAO DO CONVENIO UTILIZADO NO ARQUIVO MAGNETICO"));
		addField(new StringField(1, "CODIGO DA IDENTIFICACAO DA NATUREZA DAS OPERACOES INFORMADAS"));
		addField(new StringField(1, "CODIGO DA FINALIDADE DO ARQUIVO MAGNETICO"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "10".equals(s.substring(0, 2));
	}

}

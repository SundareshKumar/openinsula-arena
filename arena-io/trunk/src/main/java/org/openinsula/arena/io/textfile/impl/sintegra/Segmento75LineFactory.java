package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento75LineFactory extends AbstractLineFactory {

	public Segmento75LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new DateField(8, "yyyyMMdd", "DATA INICIAL DO PERIODO DE VALIDADE DAS INFORMACOES"));
		addField(new DateField(8, "yyyyMMdd", "DATA FINAL DO PERIODO DE VALIDADE DAS INFORMACOES"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO UTILIZADO PELO CONTRIBUINTE"));
		addField(new StringField(8, "CODIFICACAO DA NOMENCLATURA COMUM DO MERCOSUL"));
		addField(new StringField(53, "DESCRICAO DO PRODUTO OU SERVICO"));
		addField(new StringField(6,
				"UNIDADE DE MEDIDA DE COMERCIALIZACAO DO PRODUTO(UN, KG, MT, M3, SC, FRD, KWH, ETC."));
		addField(new NumericField(5, "ALIQUOTA DO IPI DO PRODUTO"));
		addField(new NumericField(
				4,
				"ALIQUOTA DO ICMS APLICAVEL A MERCADORIA OU SERVICO NAS OPERACOES OU PRESTACOES INTERNAS OU NAQUELAS QUE TIVEREM SIDO INICIADAS NO EXTERIOR"));
		addField(new NumericField(5, "PERCENTUAL DE REDUCAO DA BASE DE CALCULO DO ICMS, NAS OPERACOES INTERNAS"));
		addField(new NumericField(13, "BASE DE CALCULO DO ICMS DE SUBSTITUICAO TRIBUTARIA"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "75".equals(s.substring(0, 2));
	}

}

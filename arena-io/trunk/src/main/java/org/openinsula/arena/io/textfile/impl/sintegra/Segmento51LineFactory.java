package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento51LineFactory extends AbstractLineFactory {

	public Segmento51LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new StringField(14, "INSCRICAO ESTADUAL DO REMENTENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new DateField(8, "yyyyMMdd", "DATA DE EMISSAO NA SAIDA OU DE RECEBIMENTO NA ENTRADA"));
		addField(new StringField(2, "SIGLA DA FEDERACAO DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new NumericField(13, "VALOR TOTAL DA NOTA FISCAL"));
		addField(new NumericField(13, "MONTANTE DO IPI"));
		addField(new NumericField(13, "VALOR AMPARADO POR ISENCAO OU NAO INCIDENCIA DO IPI"));
		addField(new NumericField(13, "VALOR QUEN NAO CONFIRA DEBITO OU CREDITO DO IPI"));
		addField(new StringField(20, "BRANCOS"));
		addField(new StringField(1, "SITUACAO DA NOTA FISCAL"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "51".equals(s.substring(0, 2));
	}

}

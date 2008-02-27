package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class Segmento53LineFactory extends AbstractSegmento50And53LineFactory {

	public Segmento53LineFactory() {
		super();
		addField(new NumericField(13, "BASE DE CALCULO DE RETENCAO DO ICMS"));
		addField(new NumericField(13, "ICMS RETIDO PELO SUBSTITUTO"));
		addField(new StringField(13, "SOMA DAS DESPESAS ACESSORIAS (FRETE, SEGURO e OUTRAS)"));
		addField(new StringField(1, "SITUACAO DA NOTA FISCAL"));
		addField(new StringField(1, "CODIGO QUE IDENTIFICA O TIPO DA ANTECIPACAO TRIBUTARIA"));
		addField(new StringField(29, "BRANCOS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "53".equals(s.substring(0, 2));
	}

}

package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class Segmento50LineFactory extends AbstractSegmento50And53LineFactory {

	public Segmento50LineFactory() {
		super();
		addField(new NumericField(13, "VALOR TOTAL DA NOTA FISCAL"));
		addField(new NumericField(13, "BASE DE CALCULO DO ICMS"));
		addField(new NumericField(13, "VALOR DO ICMS. MONTANTE DO IMPOSTO"));
		addField(new NumericField(13, "VALOR AMPARADO POR ISENCAO OU NAO INCIDENCIA"));
		addField(new NumericField(13, "VALOR QUE NAO CONFIRA DEBITO OU CREDITO DO ICMS"));
		addField(new NumericField(4, "ALIQUOTA DO ICMS"));
		addField(new StringField(1, "SITUACAO DA NOTA FISCAL"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "50".equals(s.substring(0, 2));
	}

}

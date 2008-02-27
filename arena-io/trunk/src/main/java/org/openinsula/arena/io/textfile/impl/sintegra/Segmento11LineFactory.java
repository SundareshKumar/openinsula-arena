package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento11LineFactory extends AbstractLineFactory {

	public Segmento11LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new StringField(34, "LIGRADOURO"));
		addField(new NumericField(5, "NUMERO"));
		addField(new StringField(22, "COMPLEMENTO"));
		addField(new StringField(15, "BAIRRO"));
		addField(new NumericField(8, "CEP"));
		addField(new StringField(28, "PESSOA RESPONSAVEL PARAA CONTATOS"));
		addField(new NumericField(12, "NUMERO DO TELEFONE PARA CONTATO"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "11".equals(s.substring(0, 2));
	}

}

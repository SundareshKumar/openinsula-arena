package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTraillerLineFactory extends AbstractLineFactory {
	public RemessaTraillerLineFactory() {
		addField(new NumericField(1, "Identificacao do registro"));
		addField(new StringField(393, "Branco"));
		addField(new NumericField(6, "Numero sequencial de registro"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "9".equals(getFieldString(s, 0));
	}
}

package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoMensagemLineFactory extends AbstractLineFactory {
	public RemessaTransacaoMensagemLineFactory() {
		addField(new NumericField(1, "TIPO REGISTRO"));
		addField(new StringField(80, "MENSAGEM 1"));
		addField(new StringField(80, "MENSAGEM 2"));
		addField(new StringField(80, "MENSAGEM 3"));
		addField(new StringField(80, "MENSAGEM 4"));
		addField(new StringField(45, "RESERVA"));
		addField(new NumericField(3, "CARTEIRA"));
		addField(new NumericField(5, "AGENCIA"));
		addField(new NumericField(7, "CONTA CORRENTE"));
		addField(new StringField(1, "DIGITO C/C"));
		addField(new NumericField(11, "NOSSO NUMERO"));
		addField(new StringField(1, "DAC NOSSO NUMERO"));
		addField(new NumericField(6, "N.o SEQUENCIAL DE REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "2".equals(getFieldString(s, 0));
	}
}

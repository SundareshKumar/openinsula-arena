/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
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

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

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaHeaderLabelLineFactory extends AbstractLineFactory {
	public RemessaHeaderLabelLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(1, "IDENTIFICACAO DO ARQUIVO REMESSA"));
		addField(new StringField(7, "LITERAL REMESSA"));
		addField(new NumericField(2, "CODIGO DE SERVICO"));
		addField(new StringField(15, "LITERAL SERVICO"));
		addField(new NumericField(20, "CODIGO DA EMPRESA"));
		addField(new StringField(30, "NOME DA EMPRESA"));
		addField(new NumericField(3, "NUMERO DO BANCO NA CAMARA DE COMPENSACAO"));
		addField(new StringField(15, "NOME DO BANCO POR EXTENSO"));
		addField(new DateField(6, "ddMMyy", "DATA DA GRAVACAO DO ARQUIVO"));
		addField(new StringField(8, "BRANCO"));
		addField(new StringField(2, "IDENTIFICACAO DO SISTEMA"));
		addField(new NumericField(7, "N SEQUENCIAL DO ARQUIVO"));
		addField(new StringField(277, "BRANCO"));
		addField(new NumericField(6, "N SEQUENCIAL DO REGISTRO DE UM EM UM"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "0".equals(getFieldString(s, 0)) && "1".equals(getFieldString(s, 1));
	}
}

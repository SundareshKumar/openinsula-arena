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
package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento88ECFLineFactory extends AbstractLineFactory {

	public Segmento88ECFLineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new StringField(3, "SUBTIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new NumericField(3, "CODIGO DA SITUACAO TRIBUTARIA (CST)"));
		addField(new NumericField(3, "NUMERO DE ORDEM DO ITEM NA NOTA FISCAL"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO DO INFORMANTE"));
		addField(new StringField(20, "NUMERO DE SERIE DE FABRICACAO DO EQUIPAMENTO ECF"));
		addField(new StringField(52, "BRANCOS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "88ECF".equals(s.substring(0, 5));
	}

}

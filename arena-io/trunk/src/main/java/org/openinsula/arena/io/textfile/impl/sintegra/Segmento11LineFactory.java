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

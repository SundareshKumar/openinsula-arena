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
package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class TrailerDeArquivoLineFactory extends AbstractLineFactory {
	public TrailerDeArquivoLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA COMPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO TRAILER DE ARQUIVO"));
		addField(new StringField(9, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(6, "QUANTIDADE DE LOTES DO ARQUIVO"));
		addField(new NumericField(6, "QUANTIDADE DE REGISTROS DO ARQUIVO"));
		addField(new NumericField(6, "QTDADE DE CONTAS P/CONC. - LOTES"));
		addField(new StringField(205, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "9".equals(getFieldString(s, 2));
	}
}

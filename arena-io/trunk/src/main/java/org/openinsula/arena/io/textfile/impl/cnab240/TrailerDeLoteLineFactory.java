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

public class TrailerDeLoteLineFactory extends AbstractLineFactory {
	private static final String QUANTIDADE_DE_TIT_EM_COBRANCA = "QUANTIDADE DE TIT. EM COBRANCA";

	public TrailerDeLoteLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO TRAILER DO LOTE"));
		addField(new StringField(9, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(6, "QUANTIDADE DE REGISTROS DO LOTE"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRA"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(8, "NUMERO DO AVISO DE LANCAMENTO"));
		addField(new StringField(117, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "5".equals(getFieldString(s, 2));
	}
}

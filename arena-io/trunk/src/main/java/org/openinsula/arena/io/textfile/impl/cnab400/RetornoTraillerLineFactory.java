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

public class RetornoTraillerLineFactory extends AbstractLineFactory {
	private static final String BRANCOS = "BRANCOS";

	public RetornoTraillerLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(1, "IDENTIFICACAO DO RETORNO"));
		addField(new NumericField(2, "IDENTIFICACAO TIPO DE REGISTRO"));
		addField(new NumericField(3, "CODIGO DO BANCO"));
		addField(new StringField(10, BRANCOS));
		addField(new NumericField(8, "QUANTIDADE DE TITULOS EM COBRANCA"));
		addField(new NumericField(14, "VALOR TOTAL EM COBRANCA"));
		addField(new NumericField(8, "N DO AVISO BANCARIO"));
		addField(new StringField(10, BRANCOS));
		addField(new NumericField(5, "QUANTIDADE DE REGISTROS - OCORRENCIA 02"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 02"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 06"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 06"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 06/09/10"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 09/10"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 09/10"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 13"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 13"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 14"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 14"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 12"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 12"));
		addField(new NumericField(5, "QUANTIDADE DOS REGISTROS - OCORRENCIA 19"));
		addField(new NumericField(12, "VALOR DOS REGISTROS - OCORRENCIA 19"));
		addField(new StringField(174, BRANCOS));
		addField(new NumericField(15, "VALOR TOTAL DOS RATEIOS EFETUADOS"));
		addField(new NumericField(8, "QUANTIDADE TOTAL DOS RATEIOS EFETUADOS"));
		addField(new StringField(9, BRANCOS));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "9".equals(getFieldString(s, 0));
	}
}

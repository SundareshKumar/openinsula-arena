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

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento88ALineFactory extends AbstractLineFactory {

	public Segmento88ALineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new StringField(1, "SUBTIPO"));
		addField(new NumericField(2, "MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(10, "CODIFICACAO DA NOMENCLATURA BRASILEIRA DE MERCADORIAS (NBM)"));
		addField(new StringField(14, "CNPJ OU CPF DO TRANSPORTADOR"));
		addField(new StringField(34, "NOME DO TRANSPORTADOR"));
		addField(new StringField(2, "UNIDADE DA FEDERACAO"));
		addField(new NumericField(1, "IDENTIFICACAO DO MEIO DE TRANSPORTE"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (1)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (1)"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (2)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (2)"));
		addField(new StringField(7, "IDENTIFICACAO DA PLACA DO VEICULO OU DO CAVALO (3)"));
		addField(new StringField(2, "UF DE LICENCIMENTO DO VEICULO (3)"));
		addField(new StringField(16, "NUMERO DA MATRICULA"));
		addField(new DateField(8, "yyyyMMdd", "DATA DA SAIDA DA MERCADORIA"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "88A".equals(s.substring(0, 3));
	}

}

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

public class Segmento10LineFactory extends AbstractLineFactory {

	public Segmento10LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(14, "INSCRICAO ESTADUAL DO ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(35, "NOME COMERCIAL (RAZAO SOCIAL/DENOMINACAO) DO CONTRIBUINTE"));
		addField(new StringField(30, "MUNICIPIO ONDE ESTA DOMICILIADO O ESTABELECIMENTO INFORMANTE"));
		addField(new StringField(2, "UNIDADE DA FEDERACAO REFERENTE AO MUNICIPIO"));
		addField(new NumericField(10, "NUMERO DO FAX DO ESTABELECIMENTO INFORMANTE"));
		addField(new DateField(8, "yyyyMMdd", "DATA DO INICIO DO PERIODO REFERENTE AS INFORMACOES PRESTADAS"));
		addField(new DateField(8, "yyyyMMdd", "DATA DO FIM DO PERIODO REFERENTE AS INFORMACOES PRESTADAS"));
		addField(new StringField(1, "CODIGO DA IDENTIFICACAO DO CONVENIO UTILIZADO NO ARQUIVO MAGNETICO"));
		addField(new StringField(1, "CODIGO DA IDENTIFICACAO DA NATUREZA DAS OPERACOES INFORMADAS"));
		addField(new StringField(1, "CODIGO DA FINALIDADE DO ARQUIVO MAGNETICO"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "10".equals(s.substring(0, 2));
	}

}

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

public class Segmento75LineFactory extends AbstractLineFactory {

	public Segmento75LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new DateField(8, "yyyyMMdd", "DATA INICIAL DO PERIODO DE VALIDADE DAS INFORMACOES"));
		addField(new DateField(8, "yyyyMMdd", "DATA FINAL DO PERIODO DE VALIDADE DAS INFORMACOES"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO UTILIZADO PELO CONTRIBUINTE"));
		addField(new StringField(8, "CODIFICACAO DA NOMENCLATURA COMUM DO MERCOSUL"));
		addField(new StringField(53, "DESCRICAO DO PRODUTO OU SERVICO"));
		addField(new StringField(6,
				"UNIDADE DE MEDIDA DE COMERCIALIZACAO DO PRODUTO(UN, KG, MT, M3, SC, FRD, KWH, ETC."));
		addField(new NumericField(5, "ALIQUOTA DO IPI DO PRODUTO"));
		addField(new NumericField(
				4,
				"ALIQUOTA DO ICMS APLICAVEL A MERCADORIA OU SERVICO NAS OPERACOES OU PRESTACOES INTERNAS OU NAQUELAS QUE TIVEREM SIDO INICIADAS NO EXTERIOR"));
		addField(new NumericField(5, "PERCENTUAL DE REDUCAO DA BASE DE CALCULO DO ICMS, NAS OPERACOES INTERNAS"));
		addField(new NumericField(13, "BASE DE CALCULO DO ICMS DE SUBSTITUICAO TRIBUTARIA"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "75".equals(s.substring(0, 2));
	}

}

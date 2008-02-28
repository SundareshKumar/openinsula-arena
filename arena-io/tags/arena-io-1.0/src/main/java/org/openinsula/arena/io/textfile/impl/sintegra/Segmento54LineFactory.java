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

public class Segmento54LineFactory extends AbstractLineFactory {

	public Segmento54LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new StringField(3, "CODIGO DA SITUACAO TRIBUTARIA"));
		addField(new NumericField(3, "NUMERO DE ORDEM DO ITEM DA NOTA"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO DO INFORMANTE"));
		addField(new NumericField(11, "QUANTIDADE DO PRODUTO"));
		addField(new NumericField(12, "VALOR BRUTO DO PRODUTO (VALOR UNITARIO MULTIPLICADO PELA QUANTIDADE)"));
		addField(new NumericField(12, "VALOR DO DESCONTO CONCEDITO NO ITEM"));
		addField(new NumericField(12, "BASE DE CALCULO DO ICMS"));
		addField(new NumericField(12, "BASE DE CALCULO DO ICMS DE RETENCAO NA SUBSTITUICAO TRIBUTARIA"));
		addField(new NumericField(12, "VALOR DO IPI"));
		addField(new NumericField(4, "ALIQUOTA UTILIZADA NO CALCULO DO ICMS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "54".equals(s.substring(0, 2));
	}

}

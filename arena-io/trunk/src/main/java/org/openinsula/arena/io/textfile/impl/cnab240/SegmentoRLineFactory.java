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

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class SegmentoRLineFactory extends AbstractLineFactory {
	public SegmentoRLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
		addField(new NumericField(1, "CODIGO DO DESCONTO 2"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO DESCONTO 2"));
		addField(new NumericField(15, "VALOR/PERCENTUAL A SER CONCEDIDO"));
		addField(new NumericField(1, "CODIGO DO DESCONTO 3"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO DESCONTO 3"));
		addField(new NumericField(15, "VALOR/PRECENTUAL A SER CONCEDIDO"));
		addField(new NumericField(1, "CODIGO DA MULTA"));
		addField(new DateField(8, "ddMMyyyy", "DATA DA MULTA"));
		addField(new NumericField(15, "VALOR PERCENTUAL A SER APLICADO"));
		addField(new StringField(10, "INFORMACAO DO BANCO AO SACADO"));
		addField(new StringField(40, "MENSAGEM 3"));
		addField(new StringField(40, "MENSAGEM 4"));
		addField(new NumericField(3, "COD. DO BANCO DA CONTA DO DEBITO"));
		addField(new NumericField(4, "CODIGO DA AGENCIA DO DEBITO"));
		addField(new NumericField(13, "CONTA CORRENTE/DV DO DEBITO"));
		addField(new NumericField(8, "CODIGO DE OCORRENCIA DO SACADO"));
		addField(new NumericField(33, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "R".equals(getFieldString(s, 4));
	}
}

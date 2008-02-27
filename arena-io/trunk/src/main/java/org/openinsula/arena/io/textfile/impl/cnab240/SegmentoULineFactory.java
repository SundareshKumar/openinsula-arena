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

public class SegmentoULineFactory extends AbstractLineFactory {
	public SegmentoULineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
		addField(new NumericField(15, "JUROS/MULTA/ENCARGOS"));
		addField(new NumericField(15, "VALOR DO DESCONTO CONCEDIDO"));
		addField(new NumericField(15, "VALOR DO ABAT. CONCEDIDO/CANCEL"));
		addField(new NumericField(15, "VALOR DO IOF RECOLHIDO"));
		addField(new NumericField(15, "VALOR PAGO PELO SACADO"));
		addField(new NumericField(15, "VALOR LIQUIDO A SER CREDITADO"));
		addField(new NumericField(15, "VALOR DE OUTRAS DESPESAS"));
		addField(new NumericField(15, "VALOR DE OUTROS CREDITOS"));
		addField(new DateField(8, "ddMMyyyy", "DATA DA OCORRENCIA"));
		addField(new DateField(8, "ddMMyyyy", "DATA DA EFETIVACAO DO CREDITO"));
		addField(new StringField(4, "CODIGO DA OCORRENCIA DO SACADO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DA OCORRENCIA DO SACADO"));
		addField(new NumericField(15, "VALOR DA OCORRENCIA DO SACADO"));
		addField(new StringField(30, "COMPLEM. DA OCORRENCIA DO SACADO"));
		addField(new NumericField(3, "CODIGO DO bco CORRESP. COMPENS."));
		addField(new StringField(20, "NOSSO NUM. BCO CORRESPONDENTE"));
		addField(new StringField(7, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "U".equals(getFieldString(s, 4));
	}
}

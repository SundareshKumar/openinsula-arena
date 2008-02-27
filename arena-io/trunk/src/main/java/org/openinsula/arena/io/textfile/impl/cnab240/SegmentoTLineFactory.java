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

public class SegmentoTLineFactory extends AbstractLineFactory {
	public SegmentoTLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
		addField(new NumericField(5, "AGENCIA MANTENEDORA DA CONTA"));
		addField(new NumericField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE"));
		addField(new NumericField(1, "DIGITO VERIFICADOR DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AG/CONTA"));
		addField(new StringField(20, "IDENTIFICACAO DO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DA CARTEIRA"));
		addField(new StringField(15, "NUMERO DO DOCUMENTO DE COBRANCA"));
		addField(new DateField(8, "ddMMyyyy", "DATA DE VENCIMENTO DO TITULO"));
		addField(new NumericField(15, "VALOR NOMINAL DO TITULO"));
		addField(new NumericField(3, "NUMERO DO BANCO"));
		addField(new NumericField(5, "AGENCIA COBRADORA/RECEBEDORA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new StringField(25, "IDENTIF. DO TITULO NA EMPRESA"));
		addField(new NumericField(2, "CODIGO DA MOEDA"));
		addField(new NumericField(1, "TIPO DE INSCRICAO"));
		addField(new NumericField(15, "NUMERO DE INSCRICAO"));
		addField(new StringField(40, "NOME"));
		addField(new NumericField(10, "N. DO CONTR. DA OPERACAO D CRED"));
		addField(new NumericField(15, "VALOR DA TARIFA/CUSTAS"));
		addField(new NumericField(10, "IDENTIFICACAO PARA REJEICOES, TARIFAS, CUSTAS, LIQUID. BAIXAS"));
		addField(new StringField(17, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "T".equals(getFieldString(s, 4));
	}
}

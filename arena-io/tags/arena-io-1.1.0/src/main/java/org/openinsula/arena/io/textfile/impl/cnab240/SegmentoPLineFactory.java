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

public class SegmentoPLineFactory extends AbstractLineFactory {
	private static final String DD_MM_YYYYY = "ddMMyyyy";

	public SegmentoPLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
		addField(new NumericField(5, "AGENCIA MANTENEDORA DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AG/CONTA"));
		addField(new StringField(20, "IDENTIFICACAO DO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DA CARTEIRA"));
		addField(new NumericField(1, "FORMA DE CADASTRAMENTO DO TITULO NO BANCO"));
		addField(new NumericField(1, "TIPO DE DOCUMENTO"));
		addField(new NumericField(1, "IDENTIFICACAO DA EMISSAO DO BLOQUETO"));
		addField(new NumericField(1, "IDENTIFICACAO DA DISTRIBUICAO"));
		addField(new StringField(15, "NUMERO DO DOCUMENTO DE COBRANCA"));
		addField(new DateField(8, DD_MM_YYYYY, "DATA DE VENCIMENTO DO TITULO"));
		addField(new NumericField(15, "VALOR NOMINAL DO TITULO"));
		addField(new NumericField(5, "AGENCIA ENCARREGADA DA COBRANCA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new NumericField(2, "ESPECIE DO TITULO"));
		addField(new StringField(1, "IDENTIFICACAO DE TITULO ACEITO/NAO ACEITO"));
		addField(new DateField(8, DD_MM_YYYYY, "DATA DE EMISSAO DO TITULO"));
		addField(new NumericField(1, "CODIGO DO JUROS DE MORA"));
		addField(new DateField(8, DD_MM_YYYYY, "DATA DO JUROS DE MORA"));
		addField(new NumericField(15, "JUROS DE MORA POR DIA/TAXA"));
		addField(new NumericField(1, "CODIGO DO DESCONTO 1"));
		addField(new DateField(8, DD_MM_YYYYY, "DATA DO DESCONTO 1"));
		addField(new NumericField(15, "VALOR/PERCENTUAL A SER CONCEDIDO"));
		addField(new NumericField(15, "VALOR DO IOF A SER RECOLHIDO"));
		addField(new NumericField(15, "VALOR DO ABATIMENTO"));
		addField(new StringField(25, "IDENTIFICACAO DO TIT. NA EMPRESA"));
		addField(new NumericField(1, "CODIGO PARA PROTESTO"));
		addField(new NumericField(2, "NUMERO DE DIAS PARA PROTESTO"));
		addField(new NumericField(1, "CODIGO PARA BAIXA/DEVOLUCAO"));
		addField(new NumericField(3, "NUMERO DE DIAS PARA BAIXA/DEVOLUCAO"));
		addField(new NumericField(2, "CODIGO DA MOEDA"));
		addField(new NumericField(10, "N. DO CONTR. DA OPERACAO D CRED"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "P".equals(getFieldString(s, 4));
	}
}

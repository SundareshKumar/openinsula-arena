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

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoTransacaoRateioDeCreditoLineFactory extends AbstractLineFactory {
	private static final String FILLER = "FILLER";

	public RetornoTransacaoRateioDeCreditoLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new StringField(16, "IDENTIFICACAO DA EMPRESA NO BANCO"));
		addField(new StringField(12, "IDENTIFICACAO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DE CALCULO DO RATEIO"));
		addField(new NumericField(1, "TIPO DE VALOR INFORMADO"));
		addField(new StringField(12, FILLER));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 1.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 2.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 2.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 2.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEIFICARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR EFETIVO DO RATEIO QUANDO DO PAGAMENTO"));
		addField(new StringField(40, "NOME DO 3.o BENEFICIARIO"));
		addField(new StringField(21, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA 3.o BENEFICIARIO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO PARA O 3.o BENEFICIARIO"));
		addField(new NumericField(2, "STATUS/MOTIVO DA OCORRENCIA DE RATEIO"));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 0));
	}
}

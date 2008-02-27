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

public class RemessaTransacaoRateioDeCreditoLineFactory extends AbstractLineFactory {
	private static final String FILLER = "FILLER";

	public RemessaTransacaoRateioDeCreditoLineFactory() {
		addField(new NumericField(1, "TIPO REGISTRO"));
		addField(new StringField(16, "IDENTIFICACAO DA EMPRESA NO BANCO"));
		addField(new StringField(12, "IDENTIFICACAO TITULO NO BANCO"));
		addField(new NumericField(1, "CODIGO DE CALCULO DO RATEIO"));
		addField(new NumericField(1, "TIPO DE VALOR INFORMADO"));
		addField(new StringField(12, FILLER));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 1.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 1.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 1.o BENEFICIARIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 2.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 2.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 2.o BENEFICIARIO"));
		addField(new NumericField(3, "CODIGO DO BANCO PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(5, "CODIGO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA AGENCIA PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE PARA CREDITO DO 3.o BENEFICIARIO"));
		addField(new NumericField(15, "VALOR OU % PARA RATEIO"));
		addField(new StringField(40, "NOME DO 3.o BENEFICIARIO"));
		addField(new StringField(31, FILLER));
		addField(new StringField(6, "PARCELA"));
		addField(new NumericField(3, "FLOATING PARA O 3.o BENEFICIARIO"));
		addField(new NumericField(6, "NUMERO SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 0));
	}
}

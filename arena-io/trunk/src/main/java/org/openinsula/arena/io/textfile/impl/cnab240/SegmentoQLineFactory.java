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

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class SegmentoQLineFactory extends AbstractLineFactory {
	public SegmentoQLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
		addField(new NumericField(1, "TIPO DE INSCRICAO"));
		addField(new NumericField(15, "NUMERO DE INSCRICAO"));
		addField(new StringField(40, "NOME"));
		addField(new StringField(40, "ENDERECO"));
		addField(new StringField(15, "BAIRRO"));
		addField(new NumericField(5, "CEP"));
		addField(new NumericField(3, "SUFIXO DO CEP"));
		addField(new StringField(15, "CIDADE"));
		addField(new StringField(2, "UNIDADE DA FEDERACAO"));
		addField(new NumericField(1, "TIPO DE INSCRICAO"));
		addField(new NumericField(15, "NUMERO DE INSCRICAO"));
		addField(new StringField(40, "NOME DO SACADOR/AVALISTA"));
		addField(new NumericField(3, "COD. BCO CORRESP. NA COMPENSACAO"));
		addField(new NumericField(20, "NOSSO NUM. NO BCO CORRESPONDENTE"));
		addField(new StringField(8, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "Q".equals(getFieldString(s, 4));
	}
}

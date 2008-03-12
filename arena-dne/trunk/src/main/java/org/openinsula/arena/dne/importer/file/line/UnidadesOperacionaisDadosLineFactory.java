/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.importer.file.line;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class UnidadesOperacionaisDadosLineFactory extends AbstractLineFactory {
	public UnidadesOperacionaisDadosLineFactory() {
		addField(new StringField(1, "Tipo de Registro"));
		addField(new StringField(2, "Sigla da UF"));
		addField(new NumericField(6, "Chave da Localidade no Sistema CEP"));
		addField(new NumericField(8, "Chave da Localidade no DNE"));
		addField(new StringField(72, "Nome oficial da Localidade"));
		addField(new NumericField(5, "Chave do Bairro no Sistema CEP"));
		addField(new NumericField(8, "Chave do Bairro no DNE"));
		addField(new StringField(72, "Nome Oficial do Bairro"));
		addField(new StringField(72, "Tipo Da Unidade Operacional"));
		addField(new NumericField(8, "CEP da Unidade Operacional"));
		addField(new NumericField(6, "Chave da Unidade Operacional no Sistema CEP"));
		addField(new NumericField(8, "Chave da Unidade Operacional no DNE"));
		addField(new StringField(72, "Nome Oficial da Unidade Operacional"));
		addField(new StringField(36, "Abreviatura da Unidade Operacional recomendada pela ECT"));
		addField(new StringField(1, "Tipo da Numeração de Caixa Postal (primeira faixa)"));
		addField(new NumericField(7, "Numeração Inicial de Caixa Postal (primeira faixa)"));
		addField(new NumericField(7, "Numeração Final de Caixa Postal (primeira faixa)"));
		addField(new StringField(1, "Tipo da Numeração de Caixa Postal (segunda faixa)"));
		addField(new NumericField(7, "Numeração Inicial de Caixa Postal (segunda faixa)"));
		addField(new NumericField(7, "Numeração Final de Caixa Postal (segunda faixa)"));
		addField(new StringField(1, "Tipo da Numeração de Caixa Postal (terceira faixa)"));
		addField(new NumericField(7, "Numeração Inicial de Caixa Postal (terceira faixa)"));
		addField(new NumericField(7, "Numeração Final de Caixa Postal (terceira faixa)"));
		addField(new StringField(5, "Separador"));
	}

	@Override
	public boolean doMatches(String s) {
		return "D".equals(getFieldString(s, 0));
	}
}

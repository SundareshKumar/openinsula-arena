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

public class CaixasPostaisComunitariasDadosLineFactory extends AbstractLineFactory {
	public CaixasPostaisComunitariasDadosLineFactory() {
		addField(new StringField(1, "Tipo de Registro"));
		addField(new StringField(2, "Sigla da UF"));
		addField(new NumericField(6, "Chave da Localidade no Sistema CEP"));
		addField(new NumericField(8, "Chave da Localidade no DNE"));
		addField(new StringField(72, "Nome da Localidade"));
		addField(new NumericField(8, "CEP do Ponto de Caixa Postal Comunitária"));
		addField(new StringField(72, "Nome do Ponto de Caixa Postal Comunitária"));
		addField(new NumericField(8, "Chave da Caixa Postal Comunitária "));
		addField(new StringField(72, "Endereço do Ponto de Caixa Postal Comunitária "));
		addField(new NumericField(6, "Número Inicial da Caixa Postal Comunitária"));
		addField(new NumericField(6, "Número Final da Caixa Postal Comunitária"));
		addField(new StringField(72, "Área de Abrangência da Caixa Postal Comunitária"));
		addField(new StringField(1, "Separador"));
	}

	@Override
	public boolean doMatches(String s) {
		return "D".equals(getFieldString(s, 0));
	}
}

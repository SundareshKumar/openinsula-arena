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

import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class UnidadesFederacaoDadosLineFactory extends AbstractLineFactory {
	public UnidadesFederacaoDadosLineFactory() {
		addField(new StringField(1, "Tipo de Registro"));
		addField(new StringField(2, "Sigla do Pa�s"));
		addField(new StringField(2, "Sigla da Unidade da Federa��o"));
		addField(new StringField(2, "Chave da UF no Sistema CEP"));
		addField(new StringField(2, "Chave da UF no DNE"));
		addField(new StringField(72, "Nome Oficial da UF"));
		addField(new StringField(36, "Abreviatura da UF recomendada pela ECT"));
		addField(new StringField(1, "Separador"));
	}

	@Override
	public boolean doMatches(String s) {
		return "D".equals(getFieldString(s, 0));
	}
}

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

import org.openinsula.arena.dne.importer.file.field.NumeroTrechoField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class GrandesUsuariosEnderecoLineFactory extends AbstractLineFactory {
	public GrandesUsuariosEnderecoLineFactory() {
		addField(new StringField(1, "Tipo de Registro"));
		addField(new NumericField(6, "Chave do Grande Usuário no Sistema CEP"));
		addField(new NumericField(8, "Chave do Grande Usuário no DNE"));
		addField(new StringField(72, "Tipo Oficial do Logradouro"));
		addField(new StringField(3, "Preposição"));
		addField(new StringField(72, "Título ou Patente Oficial do Logradouro"));
		addField(new NumericField(6, "Chave do Logradouro no Sistema CEP"));
		addField(new NumericField(8, "Chave do Logradouro no DNE"));
		addField(new StringField(72, "Nome Oficial do Logradouro"));
		addField(new NumeroTrechoField(11, "Número do Lote"));
		addField(new StringField(36, "Nome do Complemento_1"));
		addField(new StringField(11, "Número ou Letra do Complemento_1"));
		addField(new StringField(36, "Nome do Complemento_2"));
		addField(new StringField(11, "Número ou Letra do Complemento_2"));
		addField(new StringField(36, "Tipo Oficial da Unidade de Ocupação"));
		addField(new StringField(36, "Número ou Letra da Unidade de Ocupação"));
		addField(new StringField(1, "Separador"));
	}

	@Override
	public boolean doMatches(String s) {
		return "E".equals(getFieldString(s, 0));
	}
}

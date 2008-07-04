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

public class LogradourosComplemento1LineFactory extends AbstractLineFactory {
	public LogradourosComplemento1LineFactory() {
		addField(new StringField(1, "Tipo de Registro"));
		addField(new StringField(2, "Sigla da UF"));
		addField(new NumericField(6, "Chave da Localidade no Sistema CEP"));
		addField(new NumericField(8, "Chave da Localidade no DNE"));
		addField(new StringField(72, "Nome oficial da Localidade"));
		addField(new NumericField(5, "Chave do Bairro Inicial no Sistema CEP"));
		addField(new NumericField(8, "Chave do Bairro Inicial no DNE"));
		addField(new StringField(72, "Bairro inicial do Logradouro"));
		addField(new NumericField(5, "Chave do Bairro Final no Sistema CEP"));
		addField(new NumericField(8, "Chave do Bairro Final no DNE"));
		addField(new StringField(72, "Bairro Final do Logradouro"));
		addField(new StringField(26, "Tipo Oficial do Logradouro"));
		addField(new StringField(3, "Preposição"));
		addField(new StringField(72, "Título ou Patente Oficial do Logradouro"));
		addField(new StringField(6, "Chave do Logradouro no Sistema CEP"));
		addField(new NumericField(8, "Chave do Logradouro no DNE"));
		addField(new StringField(72, "Nome Oficial do Logradouro"));
		addField(new StringField(36, "Abreviatura do Logradouro recomendada pela ECT"));
		addField(new StringField(36, "Informação adicional"));
		addField(new NumericField(8, "CEP do Logradouro"));
		addField(new StringField(1, "Indicador de existência de Grande Usuário no Logradouro"));
		addField(new NumeroTrechoField(11, "Número do Lote no DNE"));
		addField(new StringField(36, "Nome do Complemento_1"));
		addField(new StringField(11, "Número ou Letra do Complemento_1"));
		addField(new NumericField(8, "Chave do Lote no DNE"));
		addField(new NumericField(8, "Chave do Complemento 1 no DNE"));
		addField(new StringField(31, "Separador"));
	}

	@Override
	public boolean doMatches(String s) {
		return "K".equals(getFieldString(s, 0));
	}
}

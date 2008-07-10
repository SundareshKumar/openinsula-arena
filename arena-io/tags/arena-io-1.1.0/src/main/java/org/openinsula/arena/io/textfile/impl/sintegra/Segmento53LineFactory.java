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
package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class Segmento53LineFactory extends AbstractSegmento50And53LineFactory {

	public Segmento53LineFactory() {
		super();
		addField(new NumericField(13, "BASE DE CALCULO DE RETENCAO DO ICMS"));
		addField(new NumericField(13, "ICMS RETIDO PELO SUBSTITUTO"));
		addField(new StringField(13, "SOMA DAS DESPESAS ACESSORIAS (FRETE, SEGURO e OUTRAS)"));
		addField(new StringField(1, "SITUACAO DA NOTA FISCAL"));
		addField(new StringField(1, "CODIGO QUE IDENTIFICA O TIPO DA ANTECIPACAO TRIBUTARIA"));
		addField(new StringField(29, "BRANCOS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "53".equals(s.substring(0, 2));
	}

}

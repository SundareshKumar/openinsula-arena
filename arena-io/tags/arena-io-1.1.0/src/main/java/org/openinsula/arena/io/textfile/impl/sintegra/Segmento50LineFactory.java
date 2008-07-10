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

public class Segmento50LineFactory extends AbstractSegmento50And53LineFactory {

	public Segmento50LineFactory() {
		super();
		addField(new NumericField(13, "VALOR TOTAL DA NOTA FISCAL"));
		addField(new NumericField(13, "BASE DE CALCULO DO ICMS"));
		addField(new NumericField(13, "VALOR DO ICMS. MONTANTE DO IMPOSTO"));
		addField(new NumericField(13, "VALOR AMPARADO POR ISENCAO OU NAO INCIDENCIA"));
		addField(new NumericField(13, "VALOR QUE NAO CONFIRA DEBITO OU CREDITO DO ICMS"));
		addField(new NumericField(4, "ALIQUOTA DO ICMS"));
		addField(new StringField(1, "SITUACAO DA NOTA FISCAL"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "50".equals(s.substring(0, 2));
	}

}

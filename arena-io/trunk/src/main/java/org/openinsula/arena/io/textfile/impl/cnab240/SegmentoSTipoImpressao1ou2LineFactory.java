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

public class SegmentoSTipoImpressao1ou2LineFactory extends SegmentoSLineFactory {
	public SegmentoSTipoImpressao1ou2LineFactory() {
		super();
		addField(new NumericField(1, "IDENTIFICACAO PARA IMPRESSAO"));
		addField(new NumericField(2, "NUMERO DA LINHA A SER IMPRESSA"));
		addField(new StringField(140, "MENSAGEM A SER IMPRESSA"));
		addField(new NumericField(2, "TIPO DO CARACTER A SER IMPRESSO"));
		addField(new StringField(78, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}
}

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

public class SegmentoSTipoImpressao3LineFactory extends SegmentoSLineFactory {
	public SegmentoSTipoImpressao3LineFactory() {
		super();
		addField(new NumericField(1, "IDENTIFICACAO DA IMPRESSAO"));
		addField(new StringField(40, "MENSAGEM 5"));
		addField(new StringField(40, "MENSAGEM 6"));
		addField(new StringField(40, "MENSAGEM 7"));
		addField(new StringField(40, "MENSAGEM 8"));
		addField(new StringField(40, "MENSAGEM 9"));
		addField(new StringField(22, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}
}

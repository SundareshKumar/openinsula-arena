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
package org.openinsula.arena.io.textfile;

import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RetornoExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RetornoExpression;
import org.openinsula.arena.io.textfile.impl.sintegra.SintegraExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class FileParserFactory {
	public static FileParser newFileParser(final Expression expression) {
		return new DefaultFileParser(expression);
	}

	public static FileParser newCnab240RemessaFileParser() {
		return new DefaultFileParser(new Cnab240RemessaExpression());
	}

	public static FileParser newCnab240RetornoFileParser() {
		return new DefaultFileParser(new Cnab240RetornoExpression());
	}

	public static FileParser newCnab400RemessaFileParser() {
		return new DefaultFileParser(new Cnab400RemessaExpression());
	}

	public static FileParser newCnab400RetornoFileParser() {
		return new DefaultFileParser(new Cnab400RetornoExpression());
	}

	public static FileParser newSintegraFileParser() {
		return new DefaultFileParser(new SintegraExpression());
	}
}

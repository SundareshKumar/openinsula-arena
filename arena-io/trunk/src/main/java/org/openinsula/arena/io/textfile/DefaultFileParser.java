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

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.ParseContextFactory;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class DefaultFileParser implements FileParser {
	protected final Log logger = LogFactory.getLog(getClass());

	private Expression expression;

	protected DefaultFileParser(Expression expression) {
		this.expression = expression;
	}

	public void parse(InputStream in, Object handler) throws IOException {
		ParseContext parseContext = ParseContextFactory.newParseContext(in, handler);

		if (!expression.evaluate(parseContext)) {
			throw new IllegalStateException("File did not match file expression at line: "
					+ parseContext.getCurrentLineCount());
		}
	}

	public void addLine(Line line) throws IOException {
		ParseContext parseContext = null;

		if (!expression.evaluate(parseContext)) {
			throw new IllegalStateException("Line added [" + line.getLineFactory().getClass()
					+ "] did not match expression at index: " + parseContext.getCurrentLineCount());
		}
	}

}
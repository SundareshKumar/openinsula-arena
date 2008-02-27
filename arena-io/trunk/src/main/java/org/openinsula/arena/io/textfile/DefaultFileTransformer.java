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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class DefaultFileTransformer implements FileTransformer {
	protected final Log logger = LogFactory.getLog(getClass());

	private Expression expression;

	private PrintWriter pw;

	protected DefaultFileTransformer(Expression expression) {
		this.expression = expression;
	}

	public void addLine(Line line) throws IOException {
		if (pw == null) {
			throw new IllegalStateException("Output not set for this FileTransformer.");
		}

		if (expression == null) {
			logger.warn("Expression is null. Validation is disabled.");
		}
		pw.print(line.toString());
		pw.print('\r');
		pw.print('\n');
	}

	public void close() {
		if (pw != null) {
			if (pw.checkError()) {
				throw new IllegalStateException("Error writing file do output.");
			}
			pw.close();
		}
		else {
			throw new IllegalStateException("Output not set.");
		}
	}

	public void setOutput(OutputStream out) {
		if (pw != null) {
			throw new IllegalStateException("Output already set.");
		}
		this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, Charset.forName("ISO-8859-1"))));
	}

	public void setOutput(Writer writer) {
		if (pw != null) {
			throw new IllegalStateException("Output already set.");
		}
		this.pw = new PrintWriter(writer);
	}

}

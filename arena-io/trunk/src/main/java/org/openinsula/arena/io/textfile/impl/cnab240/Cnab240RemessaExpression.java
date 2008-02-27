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

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class Cnab240RemessaExpression implements Expression {
	private Expression expression;

	public Cnab240RemessaExpression() {
		expression = new AndExpression(new LineExpression(new HeaderArquivoLineFactory()), new LineExpression(
				new HeaderLoteLineFactory()), new AndExpression(new LineExpression(new SegmentoPLineFactory()),
				new LineExpression(new SegmentoQLineFactory()), new LineExpression(new SegmentoRLineFactory())
						.zeroOrOneTimes()).zeroOrMoreTimes(), new LineExpression(new TrailerDeLoteLineFactory()),
				new LineExpression(new TrailerDeArquivoLineFactory()));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		return expression.evaluate(context);
	}

}

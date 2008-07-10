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
package org.openinsula.arena.io.textfile.parser.expression;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.io.textfile.parser.ParseContext;

public class OrExpression extends AbstractExpression {
	private List<Expression> expressionList = new LinkedList<Expression>();

	public OrExpression(Expression... expressions) {
		expressionList.addAll(Arrays.asList(expressions));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		for (Expression expression : expressionList) {
			if (expression.evaluate(context)) {
				return true;
			}
		}

		return false;
	}
}

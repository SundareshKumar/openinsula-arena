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

import org.openinsula.arena.io.textfile.parser.ParseContext;

public abstract class AbstractExpression implements Expression {
	public Expression zeroOrOneTimes() {
		return new Expression() {
			public boolean evaluate(ParseContext context) throws IOException {
				AbstractExpression.this.evaluate(context);
				return true;
			}
		};
	}

	public Expression oneOrMoreTimes() {
		return new Expression() {
			public boolean evaluate(ParseContext context) throws IOException {
				if (AbstractExpression.this.evaluate(context)) {
					while (AbstractExpression.this.evaluate(context)) {
						;
					}
					return true;
				}
				return false;
			}
		};
	}

	public Expression zeroOrMoreTimes() {
		return new Expression() {
			public boolean evaluate(ParseContext context) throws IOException {
				while (AbstractExpression.this.evaluate(context)) {
					;
				}
				return true;
			}
		};
	}

}

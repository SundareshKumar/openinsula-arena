/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.importer.file.parser.expression;

import java.io.IOException;

import org.openinsula.arena.dne.importer.file.line.CabecalhoLineFactory;
import org.openinsula.arena.dne.importer.file.line.TerminadorLineFactory;
import org.openinsula.arena.dne.importer.file.line.TitulosPatentesDadosLineFactory;
import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class DneGuTitulosPatentesExpression implements Expression {
	private Expression expression;

	public DneGuTitulosPatentesExpression() {
		expression = new AndExpression(new LineExpression(new CabecalhoLineFactory()), new LineExpression(
				new TitulosPatentesDadosLineFactory()).oneOrMoreTimes(),
				new LineExpression(new TerminadorLineFactory()));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		return expression.evaluate(context);
	}
}

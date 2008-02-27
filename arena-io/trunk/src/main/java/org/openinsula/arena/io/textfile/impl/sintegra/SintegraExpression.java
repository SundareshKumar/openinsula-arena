package org.openinsula.arena.io.textfile.impl.sintegra;

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class SintegraExpression implements Expression {

	private Expression expression;

	public SintegraExpression() {
		expression = new AndExpression(new LineExpression(new Segmento10LineFactory()), new LineExpression(
				new Segmento11LineFactory()), new LineExpression(new Segmento50LineFactory()).zeroOrMoreTimes(),
				new LineExpression(new Segmento51LineFactory()).zeroOrMoreTimes(), new LineExpression(
						new Segmento53LineFactory()).zeroOrMoreTimes(), new LineExpression(new Segmento54LineFactory())
						.zeroOrMoreTimes(), new LineExpression(new Segmento56LineFactory()).zeroOrMoreTimes(),
				new LineExpression(new Segmento75LineFactory()).zeroOrMoreTimes(), new LineExpression(
						new Segmento88ECFLineFactory()).zeroOrMoreTimes(), new LineExpression(
						new Segmento88ALineFactory()).zeroOrMoreTimes(),
				new LineExpression(new Segmento90LineFactory()).oneOrMoreTimes());
	}

	@Override
	public boolean evaluate(final ParseContext context) throws IOException {
		return expression.evaluate(context);
	}

}

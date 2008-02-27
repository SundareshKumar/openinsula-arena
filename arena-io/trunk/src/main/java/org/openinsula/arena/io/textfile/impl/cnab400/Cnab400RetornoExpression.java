package org.openinsula.arena.io.textfile.impl.cnab400;

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class Cnab400RetornoExpression implements Expression {
	private Expression expression;

	public Cnab400RetornoExpression() {
		expression = new AndExpression(new LineExpression(new RetornoHeaderLabelLineFactory()), new AndExpression(
				new LineExpression(new RetornoTransacaoDetalheLineFactory()), new LineExpression(
						new RetornoTransacaoRateioDeCreditoLineFactory()).zeroOrOneTimes()).zeroOrMoreTimes(),
				new LineExpression(new RetornoTraillerLineFactory()));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		return expression.evaluate(context);
	}

}

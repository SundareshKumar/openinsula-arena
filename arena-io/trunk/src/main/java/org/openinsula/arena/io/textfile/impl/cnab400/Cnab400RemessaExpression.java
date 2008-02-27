package org.openinsula.arena.io.textfile.impl.cnab400;

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class Cnab400RemessaExpression implements Expression {
	private Expression expression;

	public Cnab400RemessaExpression() {
		expression = new AndExpression(new LineExpression(new RemessaHeaderLabelLineFactory()), new AndExpression(
				new LineExpression(new RemessaTransacaoDetalheLineFactory()), new LineExpression(
						new RemessaTransacaoMensagemLineFactory()), new LineExpression(
						new RemessaTransacaoRateioDeCreditoLineFactory()).zeroOrOneTimes()).zeroOrMoreTimes(),
				new LineExpression(new RemessaTraillerLineFactory()));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		return expression.evaluate(context);
	}

}

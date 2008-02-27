package org.openinsula.arena.io.textfile.impl.cnab240;

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.expression.AndExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;
import org.openinsula.arena.io.textfile.parser.expression.LineExpression;

public class Cnab240RetornoExpression implements Expression {
	private Expression expression;

	public Cnab240RetornoExpression() {
		expression = new AndExpression(new LineExpression(new HeaderArquivoLineFactory()), new LineExpression(
				new HeaderLoteLineFactory()), new AndExpression(new LineExpression(new SegmentoTLineFactory()),
				new LineExpression(new SegmentoULineFactory())).zeroOrMoreTimes(), new LineExpression(
				new TrailerDeLoteLineFactory()), new LineExpression(new TrailerDeArquivoLineFactory()));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		return expression.evaluate(context);
	}

}

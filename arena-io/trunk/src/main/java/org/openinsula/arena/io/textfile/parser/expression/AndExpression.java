package org.openinsula.arena.io.textfile.parser.expression;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.io.textfile.parser.ParseContext;

public class AndExpression extends AbstractExpression {
	private List<Expression> expressionList = new LinkedList<Expression>();

	public AndExpression(Expression... expressions) {
		expressionList.addAll(Arrays.asList(expressions));
	}

	public boolean evaluate(ParseContext context) throws IOException {
		for (Expression expression : expressionList) {
			if (!expression.evaluate(context)) {
				return false;
			}
		}

		return true;
	}
}

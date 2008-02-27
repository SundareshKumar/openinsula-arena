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

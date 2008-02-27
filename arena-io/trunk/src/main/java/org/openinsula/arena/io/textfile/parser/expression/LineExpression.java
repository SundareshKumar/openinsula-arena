package org.openinsula.arena.io.textfile.parser.expression;

import java.io.IOException;

import org.openinsula.arena.io.textfile.line.LineFactory;
import org.openinsula.arena.io.textfile.parser.ParseContext;

public class LineExpression extends AbstractExpression {
	private LineFactory lineFactory;

	public LineExpression(LineFactory lineFactory) {
		this.lineFactory = lineFactory;
	}

	public boolean evaluate(ParseContext context) throws IOException {
		String s = context.lookAhead();
		if (lineFactory.matches(s)) {
			try {
				context.process(lineFactory.parseLine(context.getLine()));
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception ex) {
				throw new IOException("[Line " + context.getCurrentLineCount() + "] " + ex.getMessage());
			}
			return true;
		}
		else {
			return false;
		}
	}

}

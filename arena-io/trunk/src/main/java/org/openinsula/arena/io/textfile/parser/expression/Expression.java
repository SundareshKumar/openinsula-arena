package org.openinsula.arena.io.textfile.parser.expression;

import java.io.IOException;

import org.openinsula.arena.io.textfile.parser.ParseContext;

public interface Expression {
	public boolean evaluate(ParseContext context) throws IOException;
}

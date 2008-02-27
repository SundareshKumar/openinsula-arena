package org.openinsula.arena.io.textfile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.ParseContext;
import org.openinsula.arena.io.textfile.parser.ParseContextFactory;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class DefaultFileParser implements FileParser {
	protected final Log logger = LogFactory.getLog(getClass());

	private Expression expression;

	protected DefaultFileParser(Expression expression) {
		this.expression = expression;
	}

	public void parse(InputStream in, Object handler) throws IOException {
		ParseContext parseContext = ParseContextFactory.newParseContext(in, handler);

		if (!expression.evaluate(parseContext)) {
			throw new IllegalStateException("File did not match file expression at line: "
					+ parseContext.getCurrentLineCount());
		}
	}

	public void addLine(Line line) throws IOException {
		ParseContext parseContext = null;

		if (!expression.evaluate(parseContext)) {
			throw new IllegalStateException("Line added [" + line.getLineFactory().getClass()
					+ "] did not match expression at index: " + parseContext.getCurrentLineCount());
		}
	}

}
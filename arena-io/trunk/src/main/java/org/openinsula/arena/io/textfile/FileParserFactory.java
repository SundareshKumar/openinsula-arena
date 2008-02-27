package org.openinsula.arena.io.textfile;

import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RetornoExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RetornoExpression;
import org.openinsula.arena.io.textfile.impl.sintegra.SintegraExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class FileParserFactory {
	public static FileParser newFileParser(final Expression expression) {
		return new DefaultFileParser(expression);
	}

	public static FileParser newCnab240RemessaFileParser() {
		return new DefaultFileParser(new Cnab240RemessaExpression());
	}

	public static FileParser newCnab240RetornoFileParser() {
		return new DefaultFileParser(new Cnab240RetornoExpression());
	}

	public static FileParser newCnab400RemessaFileParser() {
		return new DefaultFileParser(new Cnab400RemessaExpression());
	}

	public static FileParser newCnab400RetornoFileParser() {
		return new DefaultFileParser(new Cnab400RetornoExpression());
	}

	public static FileParser newSintegraFileParser() {
		return new DefaultFileParser(new SintegraExpression());
	}
}

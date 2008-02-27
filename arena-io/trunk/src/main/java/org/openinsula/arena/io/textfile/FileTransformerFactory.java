package org.openinsula.arena.io.textfile;

import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab240.Cnab240RetornoExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RemessaExpression;
import org.openinsula.arena.io.textfile.impl.cnab400.Cnab400RetornoExpression;
import org.openinsula.arena.io.textfile.impl.sintegra.SintegraExpression;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class FileTransformerFactory {
	public static FileTransformer newFileTransfomer(final Expression expression) {
		return new DefaultFileTransformer(expression);
	}

	public static FileTransformer newCnab240RemessaFileTransformer() {
		return new DefaultFileTransformer(new Cnab240RemessaExpression());
	}

	public static FileTransformer newCnab240RetornoFileTransformer() {
		return new DefaultFileTransformer(new Cnab240RetornoExpression());
	}

	public static FileTransformer newCnab400RemessaFileTransformer() {
		return new DefaultFileTransformer(new Cnab400RemessaExpression());
	}

	public static FileTransformer newCnab400RetornoFileTransformer() {
		return new DefaultFileTransformer(new Cnab400RetornoExpression());
	}

	public static FileTransformer newSintegraFileTransformer() {
		return new DefaultFileTransformer(new SintegraExpression());
	}
}

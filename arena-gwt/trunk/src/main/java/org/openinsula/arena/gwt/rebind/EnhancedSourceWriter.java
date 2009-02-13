package org.openinsula.arena.gwt.rebind;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.user.rebind.SourceWriter;

public class EnhancedSourceWriter implements SourceWriter {

	private final SourceWriter delegate;

	public EnhancedSourceWriter(final SourceWriter delegate) {
		this.delegate = delegate;
	}
	
	public EnhancedSourceWriter declareDefaultConstructor(final String className) {
		return printf("public %s()", className);
	}
	
	public EnhancedSourceWriter writeEmptyDefaultConstructor(final String className) {
		return declareDefaultConstructor(className).beginBlock().endBlock();
	}
	
	public EnhancedSourceWriter beginBlock() {
		println("{");
		indent();
		return this;
	}
	
	public EnhancedSourceWriter endBlock() {
		println();
		outdent();
		println("}");
		return this;
	}
	
	/**
	 * Avoid using %n (causes a new line without proper indentation). Use {@link #newLine()} instead.
	 */
	public EnhancedSourceWriter printf(final String format, final Object...args) {
		print(String.format(format, args));
		return this;
	}
	
	public EnhancedSourceWriter newLine() {
		println();
		return this;
	}
	
	// Delegate Methods
	
	public void beginJavaDocComment() {
		delegate.beginJavaDocComment();
	}

	public void commit(final TreeLogger logger) {
		delegate.commit(logger);
	}

	public void endJavaDocComment() {
		delegate.endJavaDocComment();
	}

	public void indent() {
		delegate.indent();
	}

	public void indentln(final String s) {
		delegate.indentln(s);
	}

	public void outdent() {
		delegate.outdent();
	}

	public void print(final String s) {
		delegate.print(s);
	}

	public void println() {
		delegate.println();
	}

	public void println(final String s) {
		delegate.println(s);
	}

}

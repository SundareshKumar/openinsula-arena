package org.openinsula.arena.io.textfile.parser;

import java.io.IOException;

import org.openinsula.arena.io.textfile.line.Line;

public interface ParseContext {
	public String getLine() throws IOException;

	public String lookAhead() throws IOException;

	public int getCurrentLineCount();

	public void process(Line line);
}
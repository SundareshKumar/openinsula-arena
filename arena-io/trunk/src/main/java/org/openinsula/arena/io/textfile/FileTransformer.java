package org.openinsula.arena.io.textfile;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.openinsula.arena.io.textfile.line.Line;

public interface FileTransformer {

	public void addLine(Line line) throws IOException;

	public void setOutput(OutputStream out);

	public void setOutput(Writer writer);

	public void close();

}

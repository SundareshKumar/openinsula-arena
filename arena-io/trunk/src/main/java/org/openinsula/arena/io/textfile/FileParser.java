package org.openinsula.arena.io.textfile;

import java.io.IOException;
import java.io.InputStream;

public interface FileParser {

	public void parse(InputStream in, Object handler) throws IOException;

}
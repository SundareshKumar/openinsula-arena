package org.openinsula.blackberry.textdatabase.serialization;

import java.io.IOException;
import java.io.OutputStream;

public interface Builder {

	public void build(Object obj, OutputStream out) throws IOException;
	
}

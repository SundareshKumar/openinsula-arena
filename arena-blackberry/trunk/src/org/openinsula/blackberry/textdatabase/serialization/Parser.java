package org.openinsula.blackberry.textdatabase.serialization;

import java.io.IOException;
import java.io.InputStream;

public interface Parser {

	public Object parse(InputStream is) throws IOException;
	
}

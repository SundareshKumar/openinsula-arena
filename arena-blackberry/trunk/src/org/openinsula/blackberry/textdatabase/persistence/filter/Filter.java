package org.openinsula.blackberry.textdatabase.persistence.filter;

import org.openinsula.blackberry.textdatabase.persistence.map.Map;

public interface Filter {

	public String getFilePath();
	
	public Map getMap();

	public int getField();
	
	public String getPattern();

	public boolean matches(byte[] bs);

	public int getLimit();
	
}

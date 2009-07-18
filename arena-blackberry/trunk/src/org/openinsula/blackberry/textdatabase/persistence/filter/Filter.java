package org.openinsula.blackberry.textdatabase.persistence.filter;

import org.openinsula.blackberry.textdatabase.persistence.map.Map;

public interface Filter {

	public String getFilePath();
	
	public Map getMap();

	public int[] getFields();
	
	public String[] getPatterns();

	public boolean matches(int index, byte[] bs);

	public int getLimit();
	
}

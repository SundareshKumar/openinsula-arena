package org.openinsula.blackberry.textdatabase.persistence.map.impl;

import org.openinsula.blackberry.textdatabase.persistence.map.Map;

public class SimpleMap implements Map {

	private int[] fields;
	
	public SimpleMap() {
	}
	
	public SimpleMap(int[] fields) {
		super();
		this.fields = fields;
	}

	public int[] getFields() {
		return fields;
	}

	public void setFields(int[] fields) {
		this.fields = fields;
	}

}

package org.openinsula.blackberry.textdatabase.persistence.filter.impl;

import org.openinsula.blackberry.textdatabase.persistence.filter.Filter;
import org.openinsula.blackberry.textdatabase.persistence.map.Map;

public abstract class FileFilter implements Filter {

	private int field;

	private String filePath;

	private int limit;

	private Map map;

	private String pattern;

	protected FileFilter() {
	}

	protected FileFilter(int field, String pattern) {
		this.field = field;
		this.pattern = pattern;
	}

	protected FileFilter(int field, String pattern, int limit) {
		this.field = field;
		this.pattern = pattern;
		this.limit = limit;
	}

	protected FileFilter(int field, String pattern, int limit, String filePath, Map map) {
		this.field = field;
		this.filePath = filePath;
		this.limit = limit;
		this.map = map;
		this.pattern = pattern;
	}

	public int getField() {
		return field;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getLimit() {
		return limit;
	}

	public Map getMap() {
		return map;
	}

	public String getPattern() {
		return pattern;
	}

	public void setField(int field) {
		this.field = field;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}

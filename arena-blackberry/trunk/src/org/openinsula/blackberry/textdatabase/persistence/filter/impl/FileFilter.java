package org.openinsula.blackberry.textdatabase.persistence.filter.impl;

import org.openinsula.blackberry.textdatabase.persistence.filter.Filter;
import org.openinsula.blackberry.textdatabase.persistence.map.Map;

public abstract class FileFilter implements Filter {

	private int[] fields;
	
	private String[] patterns;

	private String filePath;

	private int limit;

	private Map map;

	protected FileFilter() {
	}

	protected FileFilter(int[] fields, String[] patterns) {
		super();
		this.fields = fields;
		this.patterns = patterns;
	}
	
	protected FileFilter(int[] fields, String[] patterns, int limit) {
		super();
		this.fields = fields;
		this.patterns = patterns;
		this.limit = limit;
	}

	protected FileFilter(int[] fields, String[] patterns, int limit, String filePath, Map map) {
		super();
		this.fields = fields;
		this.patterns = patterns;
		this.limit = limit;
		this.filePath = filePath;
		this.map = map;
	}

	public int getLimit() {
		return limit;
	}

	public Map getMap() {
		return map;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int[] getFields() {
		return fields;
	}

	public String getFilePath() {
		return filePath;
	}

	public String[] getPatterns() {
		return patterns;
	}

	public void setFields(int[] fields) {
		this.fields = fields;
	}

	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

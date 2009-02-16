package org.openinsula.arena.gwt.components.client.ui;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

// TODO tests
public class CssBuilder {
	private final Set<String> ruleList = new LinkedHashSet<String>();
	
	public CssBuilder clear() {
		ruleList.clear();
		return this;
	}
	
	public CssBuilder addClassname(final String classname) {
		if (classname != null) {
			ruleList.add(classname);
		}
		return this;
	}
	
	public CssBuilder removeClassname(final String classname) {
		if (classname != null) {
			ruleList.remove(classname);
		}
		return this;	
	}
	
	public String buildClassname() {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<String> iterator = ruleList.iterator(); iterator.hasNext();) {
			sb.append(iterator.next());
			
			if (iterator.hasNext()) {
				sb.append(' ');
			}
		}
		
		return sb.toString();
	}

}

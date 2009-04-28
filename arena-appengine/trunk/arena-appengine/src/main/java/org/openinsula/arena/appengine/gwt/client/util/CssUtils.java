package org.openinsula.arena.appengine.gwt.client.util;

import java.util.LinkedHashSet;
import java.util.Set;

public final class CssUtils {
	private Set<String> rules;

	private boolean dirty;

	private String styleString;

	public CssUtils() {
		this(null);
	}

	public CssUtils(final String style) {
		setStyle(style);
	}

	public void setStyle(final String style) {
		clear();

		if (StringUtils.hasText(style)) {
			String[] styleRules = style.split("\\s");

			for (String rule : styleRules) {
				addRule(rule);
			}
		}
		else {
			clear();
		}
	}

	public void addRule(final String rule) {
		if (StringUtils.hasText(rule)) {
			createRulesIfNull();
			
			if (rules.add(rule.trim())) {
				dirty = true;				
			}
		}
	}

	public void dropRule(final String rule) {
		if (rules != null && StringUtils.hasText(rule) && rules.remove(rule.trim())) {
			if (rules.isEmpty()) {
				rules = null;
			}
			dirty = true;
		}
	}

	public boolean hasRule(final String rule) {
		if (rules == null || !StringUtils.hasText(rule)) {
			return false;
		}
		
		return rules.contains(rule.trim());
	}

	private void createRulesIfNull() {
		if (rules == null) {
			rules = new LinkedHashSet<String>();
			dirty = true;
			styleString = null;
		}
	}

	private void clear() {
		rules = null;
		dirty = true;
		styleString = null;
	}

	@Override
	public String toString() {
		if (!dirty) {
			return styleString;
		}

		styleString = StringUtils.collectionToDelimitedString(rules, " ");
		dirty = false;

		return styleString;
	}
}

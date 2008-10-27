package org.openinsula.arena.gwt.client;

import java.util.LinkedList;

public class StyleBuilder {
	private final LinkedList<String> rules = new LinkedList<String>();

	private String modulePrefix;

	private String separator = "-";

	private String rootComponent;

	private String rootCache = "";

	private String rulesCache = "";

	private String fullCache = "";

	private boolean rootDirty;

	private boolean rulesDirty;

	public StyleBuilder() {
		this(null, null);
	}

	public StyleBuilder(final String modulePrefix) {
		this(modulePrefix, null);
	}

	public StyleBuilder(final String modulePrefix, final String rootComponent) {
		setModulePrefix(modulePrefix);
		setRootComponent(rootComponent);
	}

	public void setModulePrefix(final String modulePrefix) {
		String oldValue = this.modulePrefix;
		this.modulePrefix = nullSafeTrim(modulePrefix);
		rootDirty = rootDirty || !nullSafeEquals(this.modulePrefix, oldValue);
	}

	public void setRootComponent(final String rootComponent) {
		String oldValue = this.rootComponent;
		this.rootComponent = nullSafeTrim(rootComponent);
		rootDirty = rootDirty || !nullSafeEquals(this.rootComponent, oldValue);
	}

	public void setSeparator(final String separator) {
		String oldValue = this.separator;
		this.separator = separator;
		rootDirty = rootDirty || !nullSafeEquals(this.separator, oldValue);
	}

	public String addRule(final String rule) {
		String _rule = nullSafeTrim(rule);

		if (_rule != null) {
			rules.add(rule);
			rulesDirty = true;
		}

		return toString();
	}

	public void dropRule() {
		if (!rules.isEmpty()) {
			rules.removeLast();
			rulesDirty = true;
		}
	}

	public void dropAllRules() {
		if (!rules.isEmpty()) {
			rules.clear();
			rulesDirty = true;
		}
	}

	public String getRule(final String rule) {
		String _rule = nullSafeTrim(rule);

		return _rule == null ? toString() : toString() + _rule;
	}

	public String getRuleFromModule(final String rule) {
		String _rule = nullSafeTrim(rule);

		return _rule == null ? modulePrefix : new StringBuffer().append(modulePrefix).append(separator).append(_rule)
				.toString();
	}

	public String toString() {
		if (!rootDirty && !rulesDirty) {
			return fullCache;
		}

		updateRootCache();
		updateRulesCache();

		fullCache = rootCache + rulesCache;

		return fullCache;
	}

	private String nullSafeTrim(final String value) {
		if (value == null) {
			return null;
		}
		String trimString = value.trim();
		return trimString.length() == 0 ? null : trimString;
	}

	private boolean nullSafeEquals(final String o1, final String o2) {
		if (o1 == o2) {
			return true;
		}

		if (o1 == null || o2 == null) {
			return false;
		}

		return o1.equals(o2);
	}

	private void updateRootCache() {
		if (!rootDirty) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		if (modulePrefix != null) {
			nullSafeAppend(sb, modulePrefix);
			nullSafeAppend(sb, separator);
		}

		nullSafeAppend(sb, rootComponent);

		rootCache = sb.toString();
		rootDirty = false;
	}

	private void updateRulesCache() {
		if (!rulesDirty) {
			return;
		}

		StringBuilder sb = new StringBuilder(rules.size() * 5);

		for (String rule : rules) {
			sb.append(rule);
		}

		rulesCache = sb.toString();
		rulesDirty = false;
	}

	private void nullSafeAppend(final StringBuilder sb, final String value) {
		if (value != null) {
			sb.append(value);
		}
	}

}

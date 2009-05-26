package org.openinsula.arena.gwt.components.client.ui.form;

/**
 * Default {@link LabelFunction} implementation that simply retrieves the
 * label UI property invoking <code>toString()</code> on the specified bean.
 * <p>By default, <code>asHTML()</code> returns <b><code>false</code></b>.
 * 
 * @author Eduardo Rebola
 */
public class ToStringLabelFunction implements LabelFunction {
	
	public String getLabel(Object obj) {
		return String.valueOf(obj);
	}

	public boolean asHTML() {
		return false;
	}

}

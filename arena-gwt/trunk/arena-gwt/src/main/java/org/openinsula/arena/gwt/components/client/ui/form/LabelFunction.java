package org.openinsula.arena.gwt.components.client.ui.form;


/**
 * Defines operations to extract the label UI property of a binded business bean.
 * 
 * @author Eduardo Rebola
 */
public interface LabelFunction {
	
	/**
	 * Is the specified label a HTML string?
	 * @return <code>true</code> to treat the specified label as html
	 */
	boolean asHTML();
	
	/**
	 * Extract the label value from the speficied object
	 * @param obj Binded bean
	 * @return Bean label to be displayed 
	 */
	String getLabel(Object obj);

}

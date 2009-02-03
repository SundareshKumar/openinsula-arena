package org.openinsula.arena.gwt.components.client.ui.form;

import org.openinsula.arena.gwt.components.client.ui.ListItem;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates a {@link Widget} in this format:
 * 
 * <pre>
 * &lt;li class=&quot;Break&quot;&gt;
 * 	&lt;p&gt;&nbsp;&lt;/p&gt;
 * &lt;/li&gt;
 * 
 * .Break {
 *    clear: both;
 *    display: block;
 *    margin: 0;
 *    padding: 0;
 *    width: 100%;
 * }
 * </pre>
 * @author Eduardo Rebola
 */
public class Break extends Composite implements FormItem {

	public Break() {
		ListItem listItem = new ListItem();
		listItem.add(new Label(" "));
		initWidget(listItem);
		setStyleName("Break");
	}

	public Widget toWidget() {
		return this;
	}

}

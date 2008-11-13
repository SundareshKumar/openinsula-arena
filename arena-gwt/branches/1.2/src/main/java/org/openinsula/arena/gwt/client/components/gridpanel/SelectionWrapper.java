package org.openinsula.arena.gwt.client.components.gridpanel;

import com.google.gwt.user.client.ui.FocusWidget;

public interface SelectionWrapper <W extends FocusWidget> {

	boolean isSelected(W widget);
	void setSelected(W widget, boolean selected);

}

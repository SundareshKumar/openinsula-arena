package org.openinsula.arena.gwt.client.components.gridpanel;

import com.google.gwt.user.client.ui.FocusWidget;


public interface DataGridCellRender<R, C> {

	FocusWidget render(R rowValue, C columnValue);

}

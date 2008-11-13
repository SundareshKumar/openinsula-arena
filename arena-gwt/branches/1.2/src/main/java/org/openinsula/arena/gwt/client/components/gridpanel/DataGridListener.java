package org.openinsula.arena.gwt.client.components.gridpanel;

import com.google.gwt.user.client.ui.FocusWidget;

public interface DataGridListener<R, C> {

	void onCellClicked(FocusWidget sender, R rowValue, C columnValue, int row, int col);

	void onInvalidSelection(FocusWidget sender, int row, int col);

}

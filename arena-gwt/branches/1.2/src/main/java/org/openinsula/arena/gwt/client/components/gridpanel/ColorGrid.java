package org.openinsula.arena.gwt.client.components.gridpanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Grid;

class ColorGrid extends Grid {

	private RowFormatter rowFormatter = new RowFormatter();

	private ColumnFormatter columnFormatter = new ColumnFormatter();

	public ColorGrid(int rowCount, int colCount) {
		super(rowCount, colCount);
		sinkEvents(Event.MOUSEEVENTS);
		setCellPadding(5);

		setRowFormatter(rowFormatter);
		setColumnFormatter(columnFormatter);
	}

	@Override
	public void onBrowserEvent(Event event) {
		Element td = getEventTargetCell(event);
		if (td == null) {
			return;
		}
		Element tr = DOM.getParent(td);
		Element body = DOM.getParent(tr);
		int row = DOM.getChildIndex(body, tr);
		int column = DOM.getChildIndex(tr, td);

		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEOVER:
			rowFormatter.addStyleName(row, "cellbackground-hover");
			columnFormatter.addStyleName(column, "cellbackground-hover");
			break;
		case Event.ONMOUSEOUT:
			rowFormatter.removeStyleName(row, "cellbackground-hover");
			columnFormatter.removeStyleName(column, "cellbackground-hover");
			break;
		default:
		}
	}

}

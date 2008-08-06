/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.openinsula.arena.gwt.client.components.datatable;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite that displays a list of emails that can be selected.
 */
public class DataTable<T> extends Composite implements TableListener,
		ClickListener, DataTableModelListener {

	private int visibleRows = 10;

	private final HTML countLabel = new HTML();
	private final HTML newerButton = new HTML(
			"<a href='javascript:;'>&lt;&lt;</a>", true);
	private final HTML olderButton = new HTML(
			"<a href='javascript:;'>&gt;&gt;</a>", true);
	private int startIndex, selectedRow = -1;
	private final FlexTable table = new FlexTable();
	private final HorizontalPanel navBar = new HorizontalPanel();
	
	private DataTableModel<T> model;

	public DataTable() {
		// Setup the table.
		table.setCellSpacing(0);
		table.setCellPadding(0);
		table.setWidth("100%");

		// Hook up events.
		table.addTableListener(this);
		newerButton.addClickListener(this);
		olderButton.addClickListener(this);

		// Create the 'navigation' bar at the upper-right.
		HorizontalPanel innerNavBar = new HorizontalPanel();
		navBar.setStyleName("dataTable-NavigationBar");
		innerNavBar.add(newerButton);
		innerNavBar.add(countLabel);
		innerNavBar.add(olderButton);

		navBar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		navBar.add(innerNavBar);
		navBar.setWidth("100%");

		initWidget(table);
		setStyleName("dataTable");

		initTable();
		update();
	}

	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		// Select the row that was clicked (-1 to account for header row).
		if (row > 0) {
			setSelectedRow(row - 1);
		}
	}

	public void onClick(Widget sender) {
		if (sender == olderButton) {
			// Move forward a page.
			startIndex += visibleRows;
			if (startIndex >= model.getRowCount()) {
				startIndex -= visibleRows;
			} else {
				styleRow(selectedRow, false);
				selectedRow = -1;
				update();
			}
		} else if (sender == newerButton) {
			// Move back a page.
			startIndex -= visibleRows;
			if (startIndex < 0) {
				startIndex = 0;
			} else {
				styleRow(selectedRow, false);
				selectedRow = -1;
				update();
			}
		}
	}

	/**
	 * Initializes the table so that it contains enough rows for a full page of
	 * emails. Also creates the images that will be used as 'read' flags.
	 */
	private void initTable() {
		table.getRowFormatter().setStyleName(0, "dataTable-header");

		// Initialize the rest of the rows.
		for (int i = 0; i < visibleRows; ++i) {
			table.setText(i + 1, 0, "");
			table.setText(i + 1, 1, "");
			table.setText(i + 1, 2, "");
			table.getCellFormatter().setWordWrap(i + 1, 0, false);
			table.getCellFormatter().setWordWrap(i + 1, 1, false);
			table.getCellFormatter().setWordWrap(i + 1, 2, false);
			table.getFlexCellFormatter().setColSpan(i + 1, 2, 2);
		}
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			if (selected) {
				table.getRowFormatter().addStyleName(row + 1,
						"dataTable-SelectedRow");
			} else {
				table.getRowFormatter().removeStyleName(row + 1,
						"dataTable-SelectedRow");
			}
		}
	}

	private void update() {
		// Update the older/newer buttons & label.
		if (model == null) {
			return;
		}
		
		int count = model.getRowCount();
		int max = startIndex + visibleRows;
		if (max > count) {
			max = count;
		}

		newerButton.setVisible(startIndex != 0);
		olderButton.setVisible(startIndex + visibleRows < count);
		countLabel
				.setText("" + (startIndex + 1) + " - " + max + " of " + count);

		// Show the selected emails.
		int i = 0;
		for (; i < visibleRows; ++i) {
			// Don't read past the end.
			if (startIndex + i >= model.getRowCount()) {
				break;
			}

			// Add a new row to the table, then set each of its columns to the
			// email's sender and subject values.
			int row = i + 1;
			for (int col = 0; col < model.getColumnCount(); col++) {
				Object cell = model.getValueAt(startIndex + i, col);
				if (cell == null) {
					continue;
				}
				if (cell instanceof Widget) {
					table.setWidget(row, col, (Widget)cell);
				} else {
					table.setWidget(row, col, new Label(cell.toString()));
				}
			}
			table.getFlexCellFormatter().setColSpan(row, model.getColumnCount() -1, 2);
		}

		// Clear any remaining slots.
		if (visibleRows <= table.getRowCount() -1) {
			for (; i < visibleRows; ++i) {
				table.removeRow(table.getRowCount() -1);
			}
		}

		// Select the first row if none is selected.
		if (selectedRow == -1) {
			setSelectedRow(0);
		}
	}

	public void onDataModelChange() {
		update();
		renderHeader();
	}
	
	private void renderHeader() {
		for (int col = 0; col < model.getColumnCount(); col++) {
			table.setText(0, col, model.getColumnName(col));
		}
		table.getFlexCellFormatter().setColSpan(0, model.getColumnCount(), 1);
		if (model.getRowCount() > visibleRows) {
			table.setWidget(0, model.getColumnCount(), navBar);
		} else {
			table.setText(0, model.getColumnCount(), "");
		}
	}
	
	public void setModel(DataTableModel<T> model) {
		this.model = model;
		model.removeAllDataTableModelListeners();
		model.addDataTableModelListener(this);
		
		onDataModelChange();
	}

	public int getVisibleRows() {
		return visibleRows;
	}

	public void setVisibleRows(int visibleRows) {
		this.visibleRows = visibleRows;
	}

	public int getSelectedRow() {
		return selectedRow + startIndex;
	}

	public void setSelectedRow(int row) {
		styleRow(selectedRow, false);
		styleRow(row, true);

		selectedRow = row;
	}

	public DataTableModel<T> getModel() {
		return model;
	}
	
}
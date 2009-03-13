package org.openinsula.arena.gwt.components.client.ui.table;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Jo�o Galli
 * 
 */
public abstract class SelectableBeanTableModel extends BeanTableModel {

	private SelectionGrid selectionGrid;

	public SelectableBeanTableModel() {
	}

	public SelectableBeanTableModel(SelectionGrid selectionGrid) {
		this.selectionGrid = selectionGrid;
	}

	public Widget buildSelectionWidget(final Integer row, final String groupName) {
		switch (selectionGrid.getSelectionPolicy()) {
		case ONE_ROW:
			final RadioButton radioButton = new RadioButton(groupName);

			radioButton.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					if (radioButton.isChecked()) {
						selectionGrid.selectRow(row);
					}
					else {
						selectionGrid.deselectRow(row);
					}

				}
			});
			return radioButton;

		case MULTI_ROW:
			final CheckBox checkBox = new CheckBox();

			checkBox.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					if (checkBox.isChecked()) {
						selectionGrid.selectRow(row);
					}
					else {
						selectionGrid.deselectRow(row);
					}

				}
			});
			return checkBox;
		}
		return null;
	}

	public SelectionGrid getSelectionGrid() {
		return selectionGrid;
	}

	public void setSelectionGrid(SelectionGrid selectionGrid) {
		this.selectionGrid = selectionGrid;
	}

}

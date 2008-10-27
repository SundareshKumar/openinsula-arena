package org.openinsula.arena.gwt.client.ui.table;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.widgetideas.table.client.overrides.HTMLTable;

/**
 * @author Lucas K Mogari
 */
public abstract class Table<T extends HTMLTable> extends Composite implements TableModelListener {

	private T table;

	private TableModel model;

	public Table() {
	}

	public Table(TableModel tableModel) {
		setModel(tableModel);
	}

	{
		table = createNewTable();

		initWidget(table);
	}

	protected abstract T createNewTable();

	@SuppressWarnings("unchecked")
	@Override
	public T getWidget() {
		return (T) super.getWidget();
	}

	public TableModel getModel() {
		return model;
	}

	public void setModel(TableModel tableModel) {
		if (tableModel == null) {
			throw new IllegalArgumentException("'tableModel' must not be null.");
		}

		final TableModel oldTableModel = this.model;

		if (EqualsUtils.isDifferent(oldTableModel, tableModel)) {
			this.model = tableModel;

			if (oldTableModel != null) {
				oldTableModel.removeTableModelListener(this);
			}

			tableModel.addTableModelListener(this);

			tableChanged(new TableModelEvent(tableModel));
		}
	}

}

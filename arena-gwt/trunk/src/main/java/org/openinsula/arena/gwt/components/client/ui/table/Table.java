package org.openinsula.arena.gwt.components.client.ui.table;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLTable;

/**
 * @author Lucas K Mogari
 */
public abstract class Table<T extends HTMLTable> extends Composite implements TableModelListener {

	private T table;

	private TableModel model;

	private boolean useTheme = true;

	public Table() {
	}

	public Table(final TableModel tableModel) {
		setModel(tableModel);
	}

	{
		table = createNewTable();
		initWidget(table);
	}

	protected abstract T createNewTable();

	public T getTable() {
		return table;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getWidget() {
		return (T) super.getWidget();
	}

	public TableModel getModel() {
		return model;
	}

	public void setModel(final TableModel tableModel) {
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

			if (useTheme) {
				tableModel.addTableModelListener(new StyleTableModelListener(this));
			}

			tableChanged(new TableModelEvent(tableModel));
		}
	}

	public void useTheme(final boolean value) {
		if (this.useTheme != value) {
			if (model != null && value) {
				model.addTableModelListener(new StyleTableModelListener(this));
			}
		}
		this.useTheme = value;
	}

}

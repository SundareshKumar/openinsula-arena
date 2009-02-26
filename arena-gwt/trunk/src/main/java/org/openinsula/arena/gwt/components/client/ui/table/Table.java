package org.openinsula.arena.gwt.components.client.ui.table;

import org.openinsula.arena.gwt.components.client.util.Assert;
import org.openinsula.arena.gwt.components.client.util.ObjectUtils;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLTable;

/**
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public abstract class Table<T extends HTMLTable> extends Composite implements TableModelListener {

	private T table;

	private TableModel model;

	private boolean useTheme = true;

	public Table() {
		table = createNewTable();
		initWidget(table);
	}

	public Table(final TableModel tableModel) {
		this();
		setModel(tableModel);
	}

	protected abstract T createNewTable();

	public final T getTable() {
		return table;
	}

	/** 
	 * @deprecated In favor of {@link #getTable()}.
	 */
	@Override
	public T getWidget() {
		return getTable();
	}

	public final TableModel getModel() {
		return model;
	}

	public void setModel(final TableModel tableModel) {
		Assert.notNull(tableModel, "TableModel is required!");

		final TableModel oldTableModel = this.model;

		if (ObjectUtils.nullSafeEquals(oldTableModel, tableModel)) {
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

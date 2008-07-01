package org.openinsula.arena.echo2.component.model.cellrenderer;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.table.TableCellRenderer;

import org.apache.log4j.Logger;
import org.openinsula.arena.echo2.component.model.SortableTableModel;
import org.openinsula.arena.echo2.component.util.FormFactory;

public class SortableHeaderCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	protected final Logger logger = Logger.getLogger(getClass());

	private SortableHeaderCellRendererStyles styles;

	private SortableTableModel sortableTableModel;

	private int lastSortedColumn = -1;

	private boolean lastOrder;

	public SortableHeaderCellRenderer() {
		this(null);
		logger.warn("This class must have a SortableTableModel to work like it was planned.");
	}

	public SortableHeaderCellRenderer(SortableTableModel sortableTableModel) {
		this(sortableTableModel, null);
	}

	public SortableHeaderCellRenderer(SortableTableModel sortableTableModel, SortableHeaderCellRendererStyles styles) {
		this.sortableTableModel = sortableTableModel;
		this.styles = styles;
	}

	public Component getTableCellRendererComponent(Table table, Object value, final int column, int row) {
		Component component = null;

		if (sortableTableModel != null && sortableTableModel.isSortable(column)) {
			if (value == null) {
				component = new Button("-");
			}
			else {
				component = new Button(value.toString());

				if (lastSortedColumn == column) {
					if (lastOrder) {
						if (styles != null) {
							((Button) component).setIcon(styles.getOrderAscIcon());
						}
					}
					else {
						if (styles != null) {
							((Button) component).setIcon(styles.getOrderDescIcon());
						}
					}
				}

				((Button) component).setLineWrap(false);
			}

			((Button) component).addActionListener(new ActionListener() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent event) {
					if (lastSortedColumn == column) {
						lastOrder = !lastOrder;
					}
					else {
						lastOrder = true;
					}
					sortableTableModel.sortTable(column, lastOrder);
					lastSortedColumn = column;
				}
			});
		}
		else {
			if (value == null) {
				component = FormFactory.label("");
			}
			else {
				component = FormFactory.label(value.toString());
				((Label) component).setLineWrap(false);
			}
		}

		if (styles != null) {
			component.setStyle(styles.getHeaderComponent());
		}

		return component;
	}

	public SortableTableModel getSortableTableModel() {
		return sortableTableModel;
	}

	public void setSortableTableModel(SortableTableModel sortableTableModel) {
		this.sortableTableModel = sortableTableModel;
	}

	public SortableHeaderCellRendererStyles getStyles() {
		return styles;
	}

	public void setStyles(SortableHeaderCellRendererStyles styles) {
		this.styles = styles;
	}

}

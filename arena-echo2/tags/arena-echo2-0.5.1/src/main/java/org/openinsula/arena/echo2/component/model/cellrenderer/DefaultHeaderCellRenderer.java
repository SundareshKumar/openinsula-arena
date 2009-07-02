package org.openinsula.arena.echo2.component.model.cellrenderer;

import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.model.container.impl.BeanReflectionContainerTableModel;
import org.openinsula.arena.echo2.component.util.FormFactory;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.table.TableCellRenderer;
import nextapp.echo2.app.table.TableModel;

public class DefaultHeaderCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(Table table, Object value, final int column, int row) {
		
		Component component = null;
		
		if (value == null) {
			component = FormFactory.label("");
		} else if (value instanceof Component) {
			component = (Component) value;
		} else {
			component = FormFactory.label(value.toString());
			((Label)component).setLineWrap(false);
		}
		
		TableModel model = table.getModel();
		
		if (model != null && (model instanceof BeanReflectionContainerTableModel)) {
			int width = ((BeanReflectionContainerTableModel)model).getColumnWidth(column);

			if (width > -1) {
				Div div = new Div();
				div.setWidth(new Extent(width));

				div.add(component);

				return div;
			}
		}
		
		return component;
	}

}

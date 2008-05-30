package org.openinsula.arena.echo2.component.model.cellrenderer;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.TableLayoutData;
import nextapp.echo2.app.table.TableCellRenderer;

import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.model.container.impl.BeanReflectionContainerTableModel;
import org.springframework.beans.factory.annotation.Autowired;

public class DataTableCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Autowired
	private DataTableCellRendererStyles styles;

	public Component getTableCellRendererComponent(Table table, Object value, int columnIndex, int rowIndex) {
		Component component;
		TableLayoutData layoutData = new TableLayoutData();
		layoutData.setInsets(new Insets(2));

		if (value == null) {
			component = new Label("(Nenhum)");
			component.setFont(new Font(Font.ARIAL, Font.PLAIN, new Extent(10, Extent.PX)));
		}
		else if (value instanceof AbstractButton) {
			component = (AbstractButton) value;
			layoutData.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
		}
		else if (value instanceof Div) {
			component = (Div) value;
		}
		else if (value instanceof Label) {
			component = (Label) value;
			component.setFont(new Font(Font.ARIAL, Font.PLAIN, new Extent(10, Extent.PX)));
			((Label) component).setLineWrap(false);
		}
		else {
			component = new Label(value.toString());
			component.setFont(new Font(Font.ARIAL, Font.PLAIN, new Extent(10, Extent.PX)));
			((Label) component).setLineWrap(false);
		}
		component.setFont(new Font(Font.ARIAL, Font.PLAIN, new Extent(10, Extent.PX)));

		if (styles != null) {
			layoutData.setBackground(styles.getCellColor(columnIndex, rowIndex));
		}
		else {
			layoutData.setBackground(Color.WHITE);
		}
		component.setLayoutData(layoutData);
		
		try {
			if (table.getModel() instanceof BeanReflectionContainerTableModel) {
				BeanReflectionContainerTableModel model = (BeanReflectionContainerTableModel) table.getModel();

				int width = model.getColumnWidth(columnIndex);

				if (width > -1) {
					Div div = new Div();
					div.setWidth(new Extent(width));

					div.add(component);

					return div;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return component;
	}

	public DataTableCellRendererStyles getStyles() {
		return styles;
	}

	public void setStyles(DataTableCellRendererStyles styles) {
		this.styles = styles;
	}

}

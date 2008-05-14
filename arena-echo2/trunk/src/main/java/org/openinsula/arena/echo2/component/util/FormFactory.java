package org.openinsula.arena.echo2.component.util;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.PasswordField;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import nextapp.echo2.app.list.ListModel;
import nextapp.echo2.app.text.TextComponent;

import org.openinsula.arena.echo2.component.field.NumberMaskField;
import org.openinsula.arena.echo2.component.model.SortableTableModel;
import org.openinsula.arena.echo2.component.model.cellrenderer.DataTableCellRenderer;
import org.openinsula.arena.echo2.component.model.cellrenderer.DataTableCellRendererStyles;
import org.openinsula.arena.echo2.component.model.cellrenderer.SortableHeaderCellRenderer;
import org.openinsula.arena.echo2.component.model.cellrenderer.SortableHeaderCellRendererStyles;

/**
 * Classe que monta componentes dos formulários
 * 
 * @author realm
 * 
 */
public class FormFactory {

	private static Styles styles;

	/**
	 * Insere uma nova linha no formulário.
	 * 
	 * @return a linha inserida.
	 */
	public static Row newLine() {
		RowLayoutData layout = new RowLayoutData();
		layout.setWidth(new Extent(100, Extent.PERCENT));

		Row row = new Row();
		row.setLayoutData(layout);

		return row;
	}

	public static Column widthSpace(final int widthCell) {
		Column columnComponent = new Column();

		Row rowLD = new Row();
		columnComponent.add(rowLD);

		Row rowComponent = new Row();
		rowLD.add(rowComponent);
		RowLayoutData rowLayoutData = new RowLayoutData();
		rowLayoutData.setWidth(new Extent(widthCell, Extent.PX));
		rowComponent.setLayoutData(rowLayoutData);

		return columnComponent;
	}

	public static Column heightSpace(final int heightCell) {
		Column columnComponent = new Column();

		ColumnLayoutData columnLayoutData = new ColumnLayoutData();
		columnLayoutData.setHeight(new Extent(heightCell, Extent.PX));
		columnComponent.setLayoutData(columnLayoutData);

		return columnComponent;
	}

	public static Label label(final String text) {
		Label label = new Label(text);
		if (styles != null) {
			label.setStyle(styles.getDefaultLabel());
		}
		return label;
	}

	public static Label label(final String text, final Style style) {
		Label label = label(text);
		label.setStyle(style);
		return label;
	}

	public static Label h1(final String text) {
		Label label = new Label(text);
		if (styles != null) {
			label.setStyle(styles.getH1());
		}
		return label;
	}

	public static Label h2(final String text) {
		Label label = new Label(text);
		if (styles != null) {
			label.setStyle(styles.getH2());
		}
		return label;
	}

	public static Label h3(final String text) {
		Label label = new Label(text);
		if (styles != null) {
			label.setStyle(styles.getH3());
		}
		return label;
	}

	public static Label h4(final String text) {
		Label label = new Label(text);
		if (styles != null) {
			label.setStyle(styles.getH4());
		}
		return label;
	}

	public static TextField textField(final int size) {
		if (styles != null) {
			return textField(size, styles.getTextField());
		}
		return textField(size, null);
	}

	public static TextField textField(final int size, final Style style) {
		TextField textField = new TextField();
		textField.setWidth(new Extent(size, Extent.PX));
		if (style != null) {
			textField.setStyle(style);
		}
		return textField;
	}

	public static TextField textField(final ComponentSize size) {
		return textField(size.getSize());
	}

	public static TextField textField(final ComponentSize size, final Style style) {
		return textField(size.getSize(), style);
	}

	public static NumberMaskField numberMaskField(final ComponentSize size) {
		NumberMaskField field = new NumberMaskField();
		field.setWidth(new Extent(size.getSize(), Extent.PX));
		return field;
	}
	
	public static PasswordField passwordField(final ComponentSize size) {
		PasswordField passwordField = new PasswordField();
		passwordField.setWidth(new Extent(size.getSize(), Extent.PX));
		passwordField.setStyle(styles.getTextField());
		return passwordField;
	}

	public static TextArea textArea(final int width, final int height) {
		if (styles != null) {
			return textArea(width, height, styles.getTextArea());
		}
		return textArea(width, height, null);
	}

	public static TextArea textArea(final int width, final int height, final Style style) {
		TextArea textArea = new TextArea();
		textArea.setWidth(new Extent(width));
		textArea.setHeight(new Extent(height));
		if (style != null) {
			textArea.setStyle(style);
		}
		return textArea;
	}

	public static CheckBox checkBox(final String label) {
		if (styles != null) {
			return checkBox(label, styles.getCheckBox());
		}
		return checkBox(label, null);
	}

	public static CheckBox checkBox(final String label, final Style style) {
		CheckBox checkBox = new CheckBox(label);
		if (style != null) {
			checkBox.setStyle(style);
		}
		return checkBox;
	}

	public static Button button(final String label) {
		if (styles != null) {
			return button(label, styles.getButton());
		}
		return button(label, null, null);
	}

	public static Button button(final String label, final ImageReference icon) {
		if (styles != null) {
			return button(label, icon, styles.getButton());
		}
		return button(label, icon, null);
	}

	public static Button button(final String label, final Style style) {
		if (styles != null) {
			return button(label, null, styles.getButton());
		}
		return button(label, null, null);
	}

	public static Button button(final String label, final ImageReference icon, final Style style) {
		Button button = new Button(label, icon);
		button.setStyle(style);
		return button;
	}

	public static Button iconButton(final String toolTip, final ImageReference icon) {
		if (styles != null) {
			return iconButton(toolTip, icon, styles.getIconButton());
		}
		return iconButton(toolTip, icon, null);
	}

	public static Button iconButton(final String toolTip, final ImageReference icon, final Style style) {
		Button button = new Button();

		if (icon != null) {
			button.setIcon(icon);
		}
		if (style != null) {
			button.setStyle(style);
		}
		return button;
	}

	public static SelectField select(final ListModel listModel) {
		if (styles != null) {
			return select(listModel, styles.getSelect());
		}
		return select(listModel, null);
	}

	public static SelectField select(final ListModel listModel, final Style style) {
		SelectField selectField = new SelectField(listModel);
		if (style != null) {
			selectField.setStyle(style);
		}
		return selectField;
	}

	public static void disableField(final TextComponent component, boolean disabled) {
		component.setEnabled(!disabled);
		component.setDisabledBackground(new Color(225, 225, 225));
	}

	public static Table table() {
		if (styles != null) {
			return table(styles.getTable());
		}
		return table(null);
	}

	public static Table table(SortableTableModel sortableTableModel, SortableHeaderCellRendererStyles styles) {
		Table table = table();
		table.setModel(sortableTableModel);
		table.setDefaultHeaderRenderer(new SortableHeaderCellRenderer(sortableTableModel, styles));
		return table;
	}

	public static Table table(final Style style) {
		Table table = new Table();
		table.setStyle(style);
		DataTableCellRenderer dataTableCellRenderer = new DataTableCellRenderer();
		if (getStyles() != null && getStyles() instanceof DataTableCellRendererStyles) {
			dataTableCellRenderer.setStyles((DataTableCellRendererStyles) styles);
		}
		table.setDefaultRenderer(Object.class, dataTableCellRenderer);
		return table;
	}

	public static Styles getStyles() {
		return styles;
	}

	public static void setStyles(Styles styles) {
		FormFactory.styles = styles;
	}
}

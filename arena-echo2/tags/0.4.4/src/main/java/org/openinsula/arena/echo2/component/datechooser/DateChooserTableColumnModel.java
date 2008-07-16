package org.openinsula.arena.echo2.component.datechooser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.TableLayoutData;
import nextapp.echo2.app.table.DefaultTableColumnModel;
import nextapp.echo2.app.table.TableCellRenderer;
import nextapp.echo2.app.table.TableColumn;

public class DateChooserTableColumnModel extends DefaultTableColumnModel {
	private static final long serialVersionUID = 1L;

	private Calendar date;

	private Style headerStyle;

	private Style currentMonthStyle;

	private Style currentDateStyle;

	private Style otherMonthStyle;

	private List<ActionListener> dayButtonActionListeners = new ArrayList<ActionListener>();

	@Override
	public TableColumn getColumn(int c) {

		TableColumn column = new TableColumn(c);
		column.setHeaderRenderer(new LabelTableCellRenderer(getHeaderStyle()));
		column.setCellRenderer(new DayButtonTableCellRenderer(getCurrentMonthStyle()));

		return column;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	protected class LabelTableCellRenderer implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		
		private Style style;

		public LabelTableCellRenderer() {
			this(new MutableStyle());
		}

		public LabelTableCellRenderer(Style style) {
			this.style = style;
		}

		public Component getTableCellRendererComponent(Table table, Object value, int arg2, int arg3) {
			Label label = null;
			if (value instanceof Label)
				label = (Label) value;
			else if (value != null) {
				label = new Label(value.toString());
			}
			else {
				label = new Label("-");
				if (style.getProperty(Table.PROPERTY_LAYOUT_DATA) instanceof TableLayoutData) {
					TableLayoutData data = (TableLayoutData) style.getProperty(Table.PROPERTY_LAYOUT_DATA);
					label.setForeground(data.getBackground());
				}
			}

			label.setStyle(style);
			return label;
		}
	}

	protected class DayButtonTableCellRenderer implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		
		private Style buttonStyle;

		public DayButtonTableCellRenderer() {
			this(new MutableStyle());
		}

		public DayButtonTableCellRenderer(Style buttonStyle) {
			this.buttonStyle = buttonStyle;
		}

		public Component getTableCellRendererComponent(Table arg0, Object value, int arg2, int arg3) {

			Calendar buttonDate = new GregorianCalendar();
			try {
				buttonDate.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(value.toString()));
			}
			catch (ParseException e) {
				e.printStackTrace();
			}

			String buttonText = "";
			if (value != null && value.toString().length() >= 2)
				buttonText = Integer.toString(Integer.parseInt(value.toString().substring(0, 2)));

			Button button = new Button(buttonText);
			button.setActionCommand(value.toString());
			button.setStyle(buttonStyle);

			if (buttonDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
				if (buttonDate.get(Calendar.DATE) == date.get(Calendar.DATE))
					button.setStyle(getCurrentDateStyle());
				else
					button.setStyle(getCurrentMonthStyle());
			}
			else {
				button.setStyle(getOtherMonthStyle());
			}

			for (ActionListener a : dayButtonActionListeners) {
				button.addActionListener(a);
			}

			return button;
		}
	}

	public void addDayButtonActionListener(ActionListener actionListener) {
		dayButtonActionListeners.add(actionListener);
	}

	public void removeDayButtonActionListener(ActionListener actionListener) {
		dayButtonActionListeners.remove(actionListener);
	}

	public Style getCurrentMonthStyle() {
		return currentMonthStyle;
	}

	public void setCurrentMonthStyle(Style dayButtonStyle) {
		this.currentMonthStyle = dayButtonStyle;
	}

	public List<ActionListener> getDayButtonActionListeners() {
		return dayButtonActionListeners;
	}

	public void setDayButtonActionListeners(List<ActionListener> dayButtonActionListeners) {
		this.dayButtonActionListeners = dayButtonActionListeners;
	}

	public Style getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(Style headerStyle) {
		this.headerStyle = headerStyle;
	}

	public Style getCurrentDateStyle() {
		return currentDateStyle;
	}

	public void setCurrentDateStyle(Style currentDayButtonStyle) {
		this.currentDateStyle = currentDayButtonStyle;
	}

	public Style getOtherMonthStyle() {
		return otherMonthStyle;
	}

	public void setOtherMonthStyle(Style otherMonthDayButtonStyle) {
		this.otherMonthStyle = otherMonthDayButtonStyle;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		this.date = calendar;
	}

}

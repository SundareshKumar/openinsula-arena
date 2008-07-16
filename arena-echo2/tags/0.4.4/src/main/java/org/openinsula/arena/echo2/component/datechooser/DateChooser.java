package org.openinsula.arena.echo2.component.datechooser;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.list.DefaultListModel;

public class DateChooser extends Column {
	private static final long serialVersionUID = 1L;

	private Date date;

	private SelectField monthField;

	private TextField yearField;

	private Button minusYearButton;

	private Button plusYearButton;

	private Table daysTable;

	private Button daysVisibilityButton;

	private DateChooserTableColumnModel tableColumnModel = new DateChooserTableColumnModel();

	private Row headerRow;

	private Style monthSelectFieldStyle;

	private Style minusYearButtonStyle;

	private Style plusYearButtonStyle;

	private Style yearFieldStyle;

	private Style daysTableStyle;

	private Style weekStyle;

	private Style currentMonthDayButtonStyle;

	private Style currentDayButtonStyle;

	private Style otherMonthDayButtonStyle;

	private Style daysVisibilityButtonStyle;

	private Style headerRowStyle;

	public DateChooser() {
		this(new Date());
	}

	public DateChooser(Date date) {
		this.date = date;

		initComponents();
	}

	@SuppressWarnings("serial")
	private void initComponents() {
		final Calendar current = new GregorianCalendar();

		if (getDate() == null)
			this.date = new Date();

		current.setTime(date);

		headerRow = new Row();

		monthField = new SelectField(new MonthListModel());
		monthField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				current.setTime(getDate());
				current.set(Calendar.MONTH, monthField.getSelectedIndex());

				setDate(current.getTime());
			}
		});
		headerRow.add(monthField);

		minusYearButton = new Button("<");
		minusYearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				current.setTime(getDate());
				current.add(Calendar.YEAR, -1);
				setDate(current.getTime());
			}
		});
		headerRow.add(minusYearButton);

		yearField = new TextField();
		yearField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextField tf = (TextField) e.getSource();
				String year = tf.getText();
				current.setTime(getDate());
				try {
					current.set(Calendar.YEAR, Integer.parseInt(year));
					setDate(current.getTime());
				}
				catch (Exception ex) {
					tf.setText(Integer.toString(current.get(Calendar.YEAR)));
				}
			}
		});
		headerRow.add(yearField);

		plusYearButton = new Button(">");
		plusYearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				current.setTime(getDate());
				current.add(Calendar.YEAR, 1);
				setDate(current.getTime());
			}
		});

		headerRow.add(plusYearButton);

		daysVisibilityButton = new Button("Mostrar");
		daysVisibilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setDaysVisible(!daysTable.isVisible());
			}
		});

		headerRow.add(daysVisibilityButton);

		this.add(headerRow);

		daysTable = new Table();

		DateChooserTableModel dateChooserTableModel = new DateChooserTableModel();
		dateChooserTableModel.setDate(getDate());

		if (tableColumnModel == null) {
			tableColumnModel = new DateChooserTableColumnModel();
		}
		tableColumnModel.addDayButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Date buttonDate = new SimpleDateFormat("dd/MM/yyyy").parse(event.getActionCommand());
					setDate(buttonDate);
				}
				catch (Exception e) {
					throw new IllegalArgumentException("Erro na transformação da data!\n" + e.getMessage());
				}
			}
		});

		daysTable.setModel(dateChooserTableModel);

		this.add(daysTable);
	}

	@Override
	public void init() {
		super.init();

		customizeComponents();
	}

	public void setDaysVisible(boolean visible) {
		daysTable.setVisible(visible);
		if (daysTable.isVisible())
			daysVisibilityButton.setText("Esconder");
		else
			daysVisibilityButton.setText("Mostrar");
	}

	private void customizeComponents() {
		final Calendar current = new GregorianCalendar();

		if (getDate() == null)
			this.date = new Date();

		current.setTime(date);

		headerRow.setStyle(getHeaderRowStyle());

		monthField.setSelectedIndex(current.get(Calendar.MONTH));
		monthField.setStyle(monthSelectFieldStyle);

		minusYearButton.setStyle(getMinusYearButtonStyle());

		yearField.setStyle(getYearFieldStyle());
		yearField.setText(new SimpleDateFormat("yyyy").format(getDate()));

		plusYearButton.setStyle(getPlusYearButtonStyle());

		daysVisibilityButton.setStyle(getDaysVisibilityButtonStyle());

		daysTable.setStyle(getDaysTableStyle());

		tableColumnModel.setDate(getDate());
		tableColumnModel.setHeaderStyle(getWeekStyle());
		tableColumnModel.setCurrentMonthStyle(getCurrentMonthDayButtonStyle());

		if (getCurrentDayButtonStyle() != null)
			tableColumnModel.setCurrentDateStyle(getCurrentDayButtonStyle());
		else
			tableColumnModel.setCurrentDateStyle(getCurrentMonthDayButtonStyle());

		if (getOtherMonthDayButtonStyle() != null)
			tableColumnModel.setOtherMonthStyle(getOtherMonthDayButtonStyle());
		else
			tableColumnModel.setOtherMonthStyle(getCurrentMonthDayButtonStyle());

		daysTable.setColumnModel(tableColumnModel);
	}

	@SuppressWarnings("serial")
	private class MonthListModel extends DefaultListModel {
		String[] months;

		public MonthListModel() {
			months = new DateFormatSymbols(Locale.getDefault()).getMonths();
		}

		@Override
		public Object get(int m) {

			try {
				if (m < months.length && months != null)
					return months[m].substring(0, 3);
			}
			catch (Exception e) {
				return months[m];
			}
			return "Erro";
		}

		@Override
		public int size() {
			return 12;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;

		Calendar current = new GregorianCalendar();
		current.setTime(date);

		String actionCommand = new SimpleDateFormat("dd/MM/yyyy").format(getDate());

		monthField.setActionCommand(actionCommand);
		minusYearButton.setActionCommand(actionCommand);
		plusYearButton.setActionCommand(actionCommand);

		monthField.setSelectedIndex(current.get(Calendar.MONTH));
		yearField.setText(new SimpleDateFormat("yyyy").format(date));

		tableColumnModel.setDate(current);

		DateChooserTableModel model = (DateChooserTableModel) daysTable.getModel();
		model.setDate(date);
		model.fireTableDataChanged();
	}

	public void addActionListener(ActionListener actionListener) {
		monthField.addActionListener(actionListener);
		minusYearButton.addActionListener(actionListener);
		plusYearButton.addActionListener(actionListener);
		tableColumnModel.addDayButtonActionListener(actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		monthField.removeActionListener(actionListener);
		minusYearButton.removeActionListener(actionListener);
		plusYearButton.removeActionListener(actionListener);
		tableColumnModel.removeDayButtonActionListener(actionListener);
	}

	public Style getMonthSelectFieldStyle() {
		return monthSelectFieldStyle;
	}

	public void setMonthSelectFieldStyle(Style monthSelectFieldStyle) {
		this.monthSelectFieldStyle = monthSelectFieldStyle;
	}

	public Style getMinusYearButtonStyle() {
		return minusYearButtonStyle;
	}

	public void setMinusYearButtonStyle(Style minusYearButtonStyle) {
		this.minusYearButtonStyle = minusYearButtonStyle;
	}

	public Style getPlusYearButtonStyle() {
		return plusYearButtonStyle;
	}

	public void setPlusYearButtonStyle(Style plusYearButtonStyle) {
		this.plusYearButtonStyle = plusYearButtonStyle;
	}

	public Style getYearFieldStyle() {
		return yearFieldStyle;
	}

	public void setYearFieldStyle(Style yearFieldStyle) {
		this.yearFieldStyle = yearFieldStyle;
	}

	public Style getCurrentMonthDayButtonStyle() {
		return currentMonthDayButtonStyle;
	}

	public void setCurrentMonthDayButtonStyle(Style dayButtonStyle) {
		this.currentMonthDayButtonStyle = dayButtonStyle;
	}

	public DateChooserTableColumnModel getTableColumnModel() {
		return tableColumnModel;
	}

	public void setTableColumnModel(DateChooserTableColumnModel dateChooserTableColumnModel) {
		this.tableColumnModel = dateChooserTableColumnModel;
	}

	public Style getWeekStyle() {
		return weekStyle;
	}

	public void setWeekStyle(Style weekStyle) {
		this.weekStyle = weekStyle;
	}

	public Style getDaysTableStyle() {
		return daysTableStyle;
	}

	public void setDaysTableStyle(Style daysTableStyle) {
		this.daysTableStyle = daysTableStyle;
	}

	public Style getCurrentDayButtonStyle() {
		return currentDayButtonStyle;
	}

	public void setCurrentDayButtonStyle(Style currentDayButtonStyle) {
		this.currentDayButtonStyle = currentDayButtonStyle;
	}

	public Style getOtherMonthDayButtonStyle() {
		return otherMonthDayButtonStyle;
	}

	public void setOtherMonthDayButtonStyle(Style otherMonthDayButtonStyle) {
		this.otherMonthDayButtonStyle = otherMonthDayButtonStyle;
	}

	public Style getDaysVisibilityButtonStyle() {
		return daysVisibilityButtonStyle;
	}

	public void setDaysVisibilityButtonStyle(Style daysVisibilityButtonStyle) {
		this.daysVisibilityButtonStyle = daysVisibilityButtonStyle;
	}

	public Style getHeaderRowStyle() {
		return headerRowStyle;
	}

	public void setHeaderRowStyle(Style headerRowStyle) {
		this.headerRowStyle = headerRowStyle;
	}

	public Button getDaysVisibilityButton() {
		return daysVisibilityButton;
	}

}

package org.openinsula.arena.echo2.component.datechooser;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import nextapp.echo2.app.table.AbstractTableModel;

public class DateChooserTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private Date date;

	private Calendar[] values;

	private String[] weekDays = null;

	@Override
	public Class getColumnClass(int x) {
		return String.class;
	}

	public DateChooserTableModel() {
		this(new Date());
	}

	public DateChooserTableModel(Date date) {
		this.date = date;
		setDate(date);

		weekDays = new DateFormatSymbols(Locale.getDefault()).getShortWeekdays();
	}

	public int getColumnCount() {
		return 7;
	}

	public int getRowCount() {
		return 6;
	}

	public Object getValueAt(int col, int row) {
		int x = (row * 7) + col;
		return new SimpleDateFormat("dd/MM/yyyy").format(values[x].getTime());
	}

	@Override
	public String getColumnName(int col) {
		return weekDays[col + 1].substring(0, 1);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		Calendar currentCalendar = new GregorianCalendar();
		currentCalendar.setTime(getDate());

		Calendar parameterCalendar = new GregorianCalendar();
		parameterCalendar.setTime(date);

		this.date = date;

		if ((currentCalendar.get(Calendar.MONTH) != parameterCalendar.get(Calendar.MONTH) || currentCalendar
				.get(Calendar.YEAR) != parameterCalendar.get(Calendar.YEAR))
				|| (values == null)) {

			values = new Calendar[6 * 7];

			// descobre o dia da semana do dia 1
			Calendar primeiroDiaCalendar = new GregorianCalendar();
			primeiroDiaCalendar.setTime(getDate());
			primeiroDiaCalendar.set(Calendar.DAY_OF_MONTH, 1);
			int diaSemanaPrimeiroDia = primeiroDiaCalendar.get(Calendar.DAY_OF_WEEK);

			primeiroDiaCalendar.add(Calendar.DATE, -(diaSemanaPrimeiroDia - 1));

			for (int i = 0; i < values.length; i++) {
				values[i] = new GregorianCalendar();
				values[i].setTime(primeiroDiaCalendar.getTime());
				primeiroDiaCalendar.add(Calendar.DATE, 1);
			}
		}

	}

}

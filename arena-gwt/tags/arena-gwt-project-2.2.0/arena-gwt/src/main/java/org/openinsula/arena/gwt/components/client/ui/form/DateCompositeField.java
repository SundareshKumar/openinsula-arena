package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.Date;

import org.openinsula.arena.gwt.components.client.ui.Suffix;
import org.openinsula.arena.gwt.components.client.ui.form.field.LabeledCompositeField;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import static org.openinsula.arena.gwt.components.client.ui.form.FieldFactory.anosListBox;
import static org.openinsula.arena.gwt.components.client.ui.form.FieldFactory.diasDoMesListBox;
import static org.openinsula.arena.gwt.components.client.ui.form.FieldFactory.mesesListBox;
import static org.openinsula.arena.gwt.components.client.ui.form.FieldFactory.suffix;

/**
 * @author João Galli
 * 
 */
@Deprecated
public class DateCompositeField implements FormItem {

	private Suffix diaField;

	private Suffix mesField;

	private Suffix anoField;

	private final LabeledCompositeField labeledCompositeField;

	public DateCompositeField(final String label, final int inicio, final int termino) {
		this(label, inicio, termino, false);
	}

	public DateCompositeField(final String label, final int inicio, final int termino, final boolean startToday) {
		labeledCompositeField = new LabeledCompositeField(label, getWidgets(inicio, termino, startToday));
	}

	private Widget[] getWidgets(final int inicio, final int termino, final boolean startToday) {
		diaField = suffix("Dia", diasDoMesListBox());
		mesField = suffix("Mês", mesesListBox());
		anoField = suffix("Ano", anosListBox(inicio, termino));

		return new Widget[] { diaField, mesField, anoField };
	}

	public int getDia() {
		final ListBox diaListBox = diaField.getComponent();
		return new Integer(diaListBox.getItemText(diaListBox.getSelectedIndex())).intValue();
	}

	public int getMes() {
		final ListBox mesListBox = mesField.getComponent();
		return (new Integer(mesListBox.getItemText(mesListBox.getSelectedIndex())).intValue());
	}

	public int getAno() {
		final ListBox anoListBox = anoField.getComponent();
		return new Integer(anoListBox.getItemText(anoListBox.getSelectedIndex())).intValue();
	}

	public Widget toWidget() {
		return labeledCompositeField;
	}

	public Date getSelectedDate() {
		Date date = new Date();
		date.setYear(getAno() - 1900);
		date.setMonth(getMes());
		date.setDate(getDia());

		return date;
	}

	public void setEnabled(final boolean b) {
		diaField.<ListBox> getComponent().setEnabled(b);
		mesField.<ListBox> getComponent().setEnabled(b);
		anoField.<ListBox> getComponent().setEnabled(b);
	}

}

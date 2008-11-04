package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.form.field.FieldValueHandler;

import com.google.gwt.user.client.ui.ListBox;

/**
 * @author Lucas K Mogari
 */
public class ListBoxValueHandler implements FieldValueHandler<ListBox> {

	@SuppressWarnings("unchecked")
	public <V> V getValue(ListBox listbox) {
		final int selectedIndex = listbox.getSelectedIndex();

		if (selectedIndex > -1) {
			return (V) listbox.getItemText(selectedIndex);
		}

		return null;
	}

	public void setValue(ListBox listbox, Object value) {
		listbox.setSelectedIndex(value == null ? 0 : Integer.valueOf(value.toString()));
	}

}

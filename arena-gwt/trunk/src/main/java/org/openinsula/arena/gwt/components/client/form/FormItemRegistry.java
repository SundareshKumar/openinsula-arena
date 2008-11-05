package org.openinsula.arena.gwt.components.client.form;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class FormItemRegistry {

	public List<FormItem> formItems = new LinkedList<FormItem>();

	public void append(FormItem... formItems) {
		for (final FormItem formItem : formItems) {
			this.formItems.add(formItem);
		}
	}

	public void appendField(String label, Widget widget) {
		append(field(label, widget));
	}

	public void appendField(String label, Suffix... widgets) {
		append(field(label, widgets));
	}

	public void appendDisabledField(String label, FocusWidget widget) {
		append(disabledField(label, widget));
	}

	public void insert(int index, FormItem formItem) {
		formItems.add(index, formItem);
	}

	public void insertField(int index, String label, Suffix... widgets) {
		insert(index, field(label, widgets));
	}

	public void insertField(int index, String label, Widget widget) {
		insert(index, field(label, widget));
	}

	public int getSize() {
		return formItems.size();
	}

	public List<FormItem> toList() {
		return formItems;
	}

}

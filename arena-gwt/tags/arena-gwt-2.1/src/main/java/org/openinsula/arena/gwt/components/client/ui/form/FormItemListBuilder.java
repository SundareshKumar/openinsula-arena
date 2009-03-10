package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.Suffix;

import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class FormItemListBuilder {

	public List<FormItem> formItems = new LinkedList<FormItem>();

	public FormItemListBuilder append(FormItem... formItems) {
		for (final FormItem formItem : formItems) {
			this.formItems.add(formItem);
		}
		return this;
	}

	public FormItemListBuilder appendField(String label, Widget widget) {
		return append(FieldFactory.field(label, widget));
	}

	public FormItemListBuilder appendField(String label, Suffix... widgets) {
		return append(FieldFactory.field(label, widgets));
	}

	public FormItemListBuilder appendDisabledField(String label, FocusWidget widget) {
		return append(FieldFactory.disabledField(label, widget));
	}

	public FormItemListBuilder insert(int index, FormItem formItem) {
		formItems.add(index, formItem);
		return this;
	}

	public FormItemListBuilder insertField(int index, String label, Suffix... widgets) {
		return insert(index, FieldFactory.field(label, widgets));
	}

	public FormItemListBuilder insertField(int index, String label, Widget widget) {
		return insert(index, FieldFactory.field(label, widget));
	}

	public int getSize() {
		return formItems.size();
	}

	public List<FormItem> toList() {
		return formItems;
	}

}

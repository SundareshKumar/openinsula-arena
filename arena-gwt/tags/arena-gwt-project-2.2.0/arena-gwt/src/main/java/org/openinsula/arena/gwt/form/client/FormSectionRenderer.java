package org.openinsula.arena.gwt.form.client;

import java.util.List;

import org.openinsula.arena.gwt.form.client.FormSection.Position;

public interface FormSectionRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onSubtitleChange(String oldValue, String newValue);

	void onActionAdded(Action action, int position);
	
	void onFormItemAdded(List<FormItem> formItemList, final FormItem formItem, final Position position, int index);

	void onFormItemRemoved(List<FormItem> formItemList, final FormItem formItem);

}

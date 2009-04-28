package org.openinsula.arena.appengine.gwt.client.forms;

import java.util.List;

import org.openinsula.arena.appengine.gwt.client.forms.FormSection.Position;

public interface FormSectionRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onSubtitleChange(String oldValue, String newValue);

	void onActionAdded(Action action, int position);

	void onFormItemAdded(List<FormItem> formItemList, final FormItem formItem, final Position position, int index);

	void onFormItemRemoved(List<FormItem> formItemList, final FormItem formItem);

}

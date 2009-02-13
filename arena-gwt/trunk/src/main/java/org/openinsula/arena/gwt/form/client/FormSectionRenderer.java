package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.form.client.FormSection.Position;

public interface FormSectionRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onSubtitleChange(String oldValue, String newValue);

	void onFormItemAdded(final FormItem formItem, final int idx, final Position position);

	void onFormItemRemoved(final FormItem formItem, final int idx);

}

package org.openinsula.arena.gwt.form.client;

public interface FormRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onSubtitleChange(String oldValue, String newValue);

	void onPrimaryActionChange(Action oldValue, Action newValue);

	void onSecondaryActionAdded(Action action, int position);

	void onFormSectionAdded(FormSection formSection, int position);

	void onFormSectionRemoved(FormSection formSection, int position);

}

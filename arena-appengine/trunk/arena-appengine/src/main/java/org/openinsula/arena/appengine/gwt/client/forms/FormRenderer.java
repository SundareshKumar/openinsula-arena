package org.openinsula.arena.appengine.gwt.client.forms;

import java.util.List;

public interface FormRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onSubtitleChange(String oldValue, String newValue);

	void onPrimaryActionChange(Action oldValue, Action newValue);

	void onSecondaryActionAdded(Action action, int position);

	void onFormSectionAdded(List<FormSection> sectionList, FormSection formSection);

	void onFormSectionRemoved(List<FormSection> sectionList, FormSection formSection);

}

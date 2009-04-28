package org.openinsula.arena.appengine.gwt.client.forms;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem.Size;

import com.google.gwt.user.client.ui.Widget;

public interface FormItemRenderer extends UIModelRenderer {

	void onLabelChange(String oldValue, String newValue);

	void onRequiredChange(Boolean oldValue, Boolean newValue);

	void onTipChange(String oldValue, String newValue);

	void onWidgetChange(Widget oldValue, Widget newValue);

	void onSizeChange(Size oldValue, Size newValue);

	void onValidChange(Boolean oldValue, Boolean newValue);

	void onValidationMessageChange(String oldValue, String newValue);

}

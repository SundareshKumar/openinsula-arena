package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.form.client.FormItem.Size;

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

package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.form.client.FormItem.Size;

import com.google.gwt.user.client.ui.Widget;

public interface FormItemRenderer extends UIModelRenderer {

	void onLabelChange(String oldValue, String newValue);

	void onRequiredChange(boolean oldValue, boolean newValue);

	void onTipChange(String oldValue, String newValue);

	void onWidgetChange(Widget oldValue, Widget newValue);

	void onSizeChange(Size oldValue, Size newValue);

	void onValidChange(boolean oldValue, boolean newValue);

	void onValidationMessageChange(String oldValue, String newValue);

}

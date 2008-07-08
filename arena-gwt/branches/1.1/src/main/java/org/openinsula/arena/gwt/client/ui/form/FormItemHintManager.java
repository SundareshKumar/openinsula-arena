package org.openinsula.arena.gwt.client.ui.form;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 * @see FormItem
 * @see GroupFormItem
 * @see FormBuilder
 * @param <T>
 */
public class FormItemHintManager<T extends Widget> {
	
	private PopupPanel hintPopupPanel;

	private final T widget;
	
	private String hint;
	
	public FormItemHintManager(T widget, String hint) {
		hintPopupPanel = FormFactory.hintPopupPanel();
		
		this.widget = widget;
		this.hint = hint;
		
		createComponents();
	}
	
	private void createComponents() {
		hintPopupPanel.add(new Label(hint));
	}

	public void showHint() {
		if (hint == null) {
			return;
		}
		int left = widget.getAbsoluteLeft() + widget.getOffsetWidth();
		int top = widget.getAbsoluteTop();
		hintPopupPanel.setPopupPosition(left, top);
		hintPopupPanel.show();
		
	}
	
	public void hideHint() {
		if (hint == null) {
			return;
		}
		hintPopupPanel.hide();
	}

}

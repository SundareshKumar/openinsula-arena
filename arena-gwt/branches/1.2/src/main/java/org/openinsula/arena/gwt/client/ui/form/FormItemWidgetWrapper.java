package org.openinsula.arena.gwt.client.ui.form;

import org.openinsula.arena.gwt.client.ui.MouseEventPanel;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.Widget;

/**
 * Adds a listener to its parent (FocusPanel) and itself.
 *
 * @param <T>
 */
public class FormItemWidgetWrapper<T extends Widget> {

	private final T widget;

	private FormItemHintManager<Widget> hintManager;

	private MouseEventPanel parent;


	private boolean hasMouse;

	public FormItemWidgetWrapper(T w, MouseEventPanel parentFocusPanel, String hint) {
		widget = w;
		parent = parentFocusPanel;
		hintManager = new FormItemHintManager<Widget>(parentFocusPanel, hint);
		hasMouse = false;

		if (widget instanceof SourcesFocusEvents) {
			((SourcesFocusEvents) widget).addFocusListener(new FormItemFocusedListener());
		}
	}

	public T getWidget() {
		return widget;
	}

	/**
	 *
	 *
	 */
	public class FormItemFocusedListener implements FocusListener {
		public void onFocus(Widget sender) {
			parent.setStyleName(FormFactory.getStyleFormItemFocused());
			hintManager.showHint();
		}

		public void onLostFocus(Widget sender) {
			parent.setStyleName(FormFactory.getStyleFormItem());
			hintManager.hideHint();

			if (!hasMouse) {
			}
		}
	}

	protected FormItemHintManager<Widget> getHintManager() {
		return hintManager;
	}
}
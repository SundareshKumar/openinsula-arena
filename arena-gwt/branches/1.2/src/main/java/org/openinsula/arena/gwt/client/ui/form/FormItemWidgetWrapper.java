package org.openinsula.arena.gwt.client.ui.form;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
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

	private FocusPanel parent;

	private boolean hasFocus;

	private boolean hasMouse;

	public FormItemWidgetWrapper(T w, FocusPanel parentFocusPanel, String hint) {
		widget = w;
		parent = parentFocusPanel;
		hintManager = new FormItemHintManager<Widget>(parentFocusPanel, hint);
		hasFocus = false;
		hasMouse = false;

		if (widget instanceof SourcesFocusEvents) {
			((SourcesFocusEvents) widget).addFocusListener(new FormItemFocusedListener());
		}
		parent.addMouseListener(new FormItemParentMouseListener());
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
			hasFocus = true;
		}

		public void onLostFocus(Widget sender) {
			parent.setStyleName(FormFactory.getStyleFormItem());
			hasFocus = false;
			if (!hasMouse) {
				hintManager.hideHint();
			}
		}
	}

	/**
	 * 
	 * 
	 */
	public class FormItemParentMouseListener extends MouseListenerAdapter {
		public void onMouseEnter(Widget sender) {
			hintManager.showHint();
			hasMouse = true;
		}

		public void onMouseLeave(Widget sender) {
			hasMouse = false;
			if (!hasFocus) {
				hintManager.hideHint();
			}
		}
	}

	protected FormItemHintManager<Widget> getHintManager() {
		return hintManager;
	}
}
package org.openinsula.arena.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.SourcesKeyboardEvents;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.FocusImpl;

public class FocusComposite extends Composite implements HasFocus, SourcesFocusEvents, SourcesKeyboardEvents {

	private static final FocusImpl impl = FocusImpl.getFocusImplForWidget();

//	private FocusListenerCollection focusListeners;
//
//	private KeyboardListenerCollection keyboardListeners;

//	@Override
//	public void onBrowserEvent(Event event) {
//		switch (DOM.eventGetType(event)) {
//		case Event.ONCLICK:
//			break;
//		case Event.ONBLUR:
//		case Event.ONFOCUS:
//			GWT.log("onFocus: " + DOM.eventGetType(event), null);
//			focusListeners().fireFocusEvent(this, event);
//			break;
//		case Event.ONKEYDOWN:
//		case Event.ONKEYUP:
//		case Event.ONKEYPRESS:
//			keyboardListeners().fireKeyboardEvent(this, event);
//			break;
//		case Event.ONCHANGE:
//			break;
////		default:
////			if (this instanceof GroupFormItem<?>) {
//////TODO rever esse problema com os eventos do GROUP				for (Widget widget : ((GroupFormItem<?>) this).getWidgets()) {
//////					widget.onBrowserEvent(event);
//////				}
////			} else {
////				getWidget().onBrowserEvent(event);
////			}
//		}
//	}

//	private FocusListenerCollection focusListeners() {
//		if (focusListeners == null) {
//			focusListeners = new FocusListenerCollection();
//		}
//		return focusListeners;
//	}
//
//	private KeyboardListenerCollection keyboardListeners() {
//		if (keyboardListeners == null) {
//			keyboardListeners = new KeyboardListenerCollection();
//			sinkEvents(Event.KEYEVENTS);
//		}
//		return keyboardListeners;
//	}

	public int getTabIndex() {
		return impl.getTabIndex(getWidget().getElement());
	}

	public void setAccessKey(char key) {
		DOM.setElementProperty(getElement(), "accessKey", "" + key);
	}

	public void setFocus(boolean op) {
		Widget widget = getWidget();
		if (widget instanceof HasFocus) {
			((HasFocus) widget).setFocus(op);
		}
	}

	public void setTabIndex(int index) {
		impl.setTabIndex(getWidget().getElement(), index);
	}

	public void addFocusListener(FocusListener listener) {
		if (getWidget() instanceof SourcesFocusEvents) {
			((SourcesFocusEvents) getWidget()).addFocusListener(listener);
		}
	}

	public void removeFocusListener(FocusListener listener) {
		if (getWidget() instanceof SourcesFocusEvents) {
			((SourcesFocusEvents) getWidget()).removeFocusListener(listener);
		}
	}

	public void addKeyboardListener(KeyboardListener listener) {
		if (getWidget() instanceof SourcesKeyboardEvents) {
			((SourcesKeyboardEvents) getWidget()).addKeyboardListener(listener);
		}
	}

	public void removeKeyboardListener(KeyboardListener listener) {
		if (getWidget() instanceof SourcesKeyboardEvents) {
			((SourcesKeyboardEvents) getWidget()).removeKeyboardListener(listener);
		}
	}

	public void setEnabled(boolean enabled) {
		Widget component = getWidget();
		if (component instanceof FocusWidget) {
			((FocusWidget) component).setEnabled(enabled);
		} else if (component instanceof FocusComposite){
			((FocusComposite) component).setEnabled(enabled);
		}
	}

	public boolean isEnabled() {
		Widget component = getWidget();
		if (component instanceof FocusWidget) {
			return ((FocusWidget) component).isEnabled();
		} else if (component instanceof FocusComposite){
			return ((FocusComposite) component).isEnabled();
		}

		return false;
	}

}

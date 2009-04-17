package org.openinsula.arena.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseListenerCollection;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesMouseEvents;

public class MouseEventPanel extends SimplePanel implements SourcesMouseEvents {

	private MouseListenerCollection mouseListeners;

	public MouseEventPanel() {
		sinkEvents(Event.MOUSEEVENTS);
	}

	@Override
	  public void onBrowserEvent(Event event) {
	    switch (DOM.eventGetType(event)) {
	      case Event.ONMOUSEDOWN:
	      case Event.ONMOUSEUP:
	      case Event.ONMOUSEMOVE:
	      case Event.ONMOUSEOVER:
	      case Event.ONMOUSEOUT:
	        if (mouseListeners != null) {
	          mouseListeners.fireMouseEvent(this, event);
	        }
	        break;
	    }
	  }

	public void addMouseListener(MouseListener listener) {
		if (mouseListeners == null) {
			mouseListeners = new MouseListenerCollection();
		}
		mouseListeners.add(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		if (mouseListeners != null) {
			mouseListeners.remove(listener);
		}
	}

}

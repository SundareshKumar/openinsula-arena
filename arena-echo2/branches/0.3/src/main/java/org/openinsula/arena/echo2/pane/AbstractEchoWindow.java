package org.openinsula.arena.echo2.pane;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.list.AbstractListComponent;
import nextapp.echo2.app.text.TextComponent;

public abstract class AbstractEchoWindow extends WindowPane {

	public void hideValidatorMessage(Object component) {
		final Component c = ((Component) component);
		if (c.isEnabled()) {
			c.setBackground(Color.WHITE);
		}
		else {
			c.setBackground(new Color(250, 240, 235));
		}
		setComponentToolTip(component, "");
	}

	public void showValidatorMessage(Object component, String message) {
		((Component) component).setBackground(Color.RED);
		setComponentToolTip(component, message);
	}

	private void setComponentToolTip(Object component, String message) {
		if (component instanceof TextComponent) {
			((TextComponent) component).setToolTipText(message);
		}
		else if (component instanceof AbstractButton) {
			((AbstractButton) component).setToolTipText(message);
		}
		else if (component instanceof AbstractListComponent) {
			((AbstractListComponent) component).setToolTipText(message);
		}
	}

	public void alert() {
	}

}

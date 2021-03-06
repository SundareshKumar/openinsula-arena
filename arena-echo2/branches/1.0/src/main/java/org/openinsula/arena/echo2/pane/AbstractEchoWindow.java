package org.openinsula.arena.echo2.pane;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.list.AbstractListComponent;
import nextapp.echo2.app.text.TextComponent;

import org.openinsula.arena.echo2.component.util.FormFactory;
import org.openinsula.arena.echo2.component.util.Styles;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public abstract class AbstractEchoWindow extends WindowPane implements InitializingBean {

	@Autowired
	private Styles styles;
	
	public AbstractEchoWindow() {
		if (FormFactory.getStyles() != null) {
			FormFactory.setStyles(styles);
		}
		initComponents();
	}

	public void afterPropertiesSet() throws Exception {
		customizeComponents();
		initActions();
		verifyComponents();
	}

	public abstract void initComponents();
	public abstract void customizeComponents();
	public abstract void initActions();
	public abstract void verifyComponents();
	public abstract void clearComponents();

	public boolean validateComponents() {
		return false;
	}
	
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

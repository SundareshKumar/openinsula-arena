package org.openinsula.arena.echo2.component.model.container.impl;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.event.ActionListener;

import org.openinsula.arena.echo2.component.model.container.ComponentBuilder;
import org.openinsula.arena.echo2.component.util.FormFactory;

public class GenericButtonBuilder implements ComponentBuilder<Button> {

	private String caption;

	private ActionListener actionListener;

	private ImageReference icon;

	private String actionCommand;

	private Style[] styles;

	public GenericButtonBuilder(String caption, ActionListener actionListener, ImageReference icon, String actionCommand) {
		super();
		this.caption = caption;
		this.actionListener = actionListener;
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	public GenericButtonBuilder(String caption, ActionListener actionListener, ImageReference icon,
			String actionCommand, Style[] styles) {
		this(caption, actionListener, icon, actionCommand);

		this.styles = styles;
	}

	public Button buildComponent() {
		Button button = FormFactory.button(caption);

		if (icon != null) {
			button.setIcon(icon);
		}
		else if (caption != null) {
			button.setText(caption);
		}

		if (actionListener != null) {
			button.addActionListener(actionListener);
		}

		button.setActionCommand(actionCommand);

		for (int i = 0; i < styles.length; i++) {
			Style style = styles[i];
			button.setStyle(style);
		}

		return button;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	public ImageReference getIcon() {
		return icon;
	}

	public void setIcon(ImageReference icon) {
		this.icon = icon;
	}

	public String getActionCommand() {
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

}

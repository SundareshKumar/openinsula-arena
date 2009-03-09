package org.openinsula.arena.gwt.form.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;

public class Action implements Command {

	private String label;

	private String tip;

	private Command command;
	
	private boolean enabled = true;

	public Action label(final String label) {
		this.label = label;
		return this;
	}

	public String label() {
		return label;
	}

	public Action tip(final String tip) {
		this.tip = tip;
		return this;
	}

	public String tip() {
		return tip;
	}
	
	public Action enabled(final boolean value) {
		this.enabled = value;
		return this;
	}
	
	public boolean enabled() {
		return enabled;
	}

	public Action command(final Command command) {
		this.command = command;
		return this;
	}

	public Command command() {
		return command;
	}

	public void execute() {
		if (command == null && !GWT.isScript()) {
			GWT.log("Command not ready!", null);
		}
		else {
			command.execute();
		}
	}
	
	public Action clone() {
		Action action = new Action();
		action.label = label;
		action.tip = tip;
		action.command = command;
		
		return action;
	}

}

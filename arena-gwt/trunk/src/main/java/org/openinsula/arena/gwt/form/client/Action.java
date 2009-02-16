package org.openinsula.arena.gwt.form.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;

public class Action implements Command {

	private String label;

	private String tip;

	private Command command;

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

}

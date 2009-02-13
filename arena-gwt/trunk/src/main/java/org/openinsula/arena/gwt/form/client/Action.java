package org.openinsula.arena.gwt.form.client;

import com.google.gwt.user.client.Command;

public interface Action extends Command {
	
	String label();
	
	String tip();

}

package org.openinsula.blackberry.textdatabase.connection.configuration;

import javax.microedition.io.Connector;

import org.openinsula.blackberry.textdatabase.connection.Configuration;

public abstract class ConnectorConfiguration implements Configuration {
	
	private int mode = Connector.READ_WRITE;
	
	private boolean timeouts = false;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean getTimeouts() {
		return timeouts;
	}

	public void setTimeouts(boolean timeouts) {
		this.timeouts = timeouts;
	}

}

package org.openinsula.arena.echo2.component.provider;

import java.io.Serializable;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Command;
import nextapp.echo2.app.RenderIdSupport;

/**
 * A command that causes the client to download a file. Executing this
 * <code>Command</code> via ApplicationInstance.executeCommand() will direct
 * the client browser to download a file, as defined by the given
 * <code>DownloadProvider</code>
 */
public class DownloadCommand implements Command, RenderIdSupport, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private boolean active;

	private DownloadProvider provider;

	/**
	 * Creates a new inactive <code>Download</code> command with no download
	 * provider.
	 */
	public DownloadCommand() {
		this(null, false);
	}

	/**
	 * Creates a new <code>Download</code> command with the specified prodcuer
	 * and active state.
	 *
	 * @param provider The <code>DownloadProvider</code> that will provide the
	 *            file download.
	 * @param active True if the file should be immediately downloaded by the
	 *            client.
	 */
	public DownloadCommand(DownloadProvider provider, boolean active) {
		super();
		this.provider = provider;
		this.active = active;
	}

	/**
	 * Returns the <code>DownloadProvider</code> that will provide the file
	 * download.
	 *
	 * @return The <code>DownloadProvider</code> that will provide the file
	 *         download.
	 */
	public DownloadProvider getProvider() {
		return provider;
	}

	/**
	 * Returns whether the download component is &quot;active&quot;. If the
	 * component is active, it will cause the client to download the file.
	 *
	 * @return True if the file should be immediately downloaded by the client.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets whether the download component is &quot;active&quot;. If the
	 * component is active, it will cause the client to download the file.
	 *
	 * @param newValue True if the file should be immediately downloaded by the
	 *            client.
	 */
	public void setActive(boolean newValue) {
		active = newValue;
	}

	/**
	 * Sets the <code>DownloadProvider</code> that will provide the file
	 * download.
	 *
	 * @param newValue A <code>DownloadProvider</code> that will provide the
	 *            file download.
	 */
	public void setProvider(DownloadProvider newValue) {
		provider = newValue;
	}

	/**
	 * @see nextapp.echo2.app.RenderIdSupport#getRenderId()
	 */
	public String getRenderId() {
		if (id == null) {
			id = ApplicationInstance.generateSystemId();
		}
		return id;
	}
}

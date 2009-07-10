package org.openinsula.blackberry.textdatabase.connection.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import org.openinsula.blackberry.textdatabase.connection.Configuration;
import org.openinsula.blackberry.textdatabase.connection.ConnectionProvider;
import org.openinsula.blackberry.textdatabase.connection.configuration.FileConfiguration;

public class FileConnectionProvider implements ConnectionProvider {

	private FileConnection conn;

	private FileConfiguration configuration;

	public FileConnection getConnection() throws Throwable {
		if (conn == null) {
			conn = (FileConnection) Connector.open(configuration.getFilePath(), Connector.READ_WRITE);
			// , configuration.getMode(), configuration.getTimeouts()

			if (conn.exists() && conn.isDirectory()) {
				Enumeration list = conn.list();
				StringBuffer sb = new StringBuffer();

				while (list.hasMoreElements()) {
					Object object = (Object) list.nextElement();
					sb.append(object).append(", ");
				}

				throw new IllegalArgumentException("Arquivos: " + sb.toString());
			}

			if (conn.exists() && configuration.isDeleteExistentFile()) {
				conn.delete();
			}

			if (!conn.exists() && configuration.isCreateNotExistentFile()) {
				conn.create();
			}

			if (!conn.exists()) {
				throw new RuntimeException("Arquivo não encontrado.");
			}
		}
		return conn;
	}

	public InputStream getInputStream() throws Throwable {
		return getConnection().openInputStream();
	}

	public OutputStream getOutputStream() throws Throwable {
		return getConnection().openOutputStream();
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = (FileConfiguration) configuration;
	}

	public void finish() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				conn = null;
			}
		}
	}

}

package org.openinsula.blackberry.textdatabase.connection;

import java.util.Hashtable;

import org.openinsula.blackberry.textdatabase.connection.configuration.FileConfiguration;
import org.openinsula.blackberry.textdatabase.connection.configuration.HttpConfiguration;
import org.openinsula.blackberry.textdatabase.connection.configuration.SystemOutConfiguration;
import org.openinsula.blackberry.textdatabase.connection.provider.FileConnectionProvider;
import org.openinsula.blackberry.textdatabase.connection.provider.HttpConnectionProvider;
import org.openinsula.blackberry.textdatabase.connection.provider.SystemOutConnectionProvider;

public abstract class ConnectionProviderFactory {

	public static Hashtable providers = new Hashtable();

	static {
		providers.put(HttpConfiguration.class, new HttpConnectionProvider());
		providers.put(FileConfiguration.class, new FileConnectionProvider());
		providers.put(SystemOutConfiguration.class, new SystemOutConnectionProvider());
	}

	public static ConnectionProvider get(Configuration configuration) {
		ConnectionProvider provider = (ConnectionProvider) providers.get(configuration.getClass());
		if (provider == null) {
			throw new IllegalArgumentException("The provider for the following configuration could not be found: "
					+ configuration.getClass());
		}
		else {
			provider.setConfiguration(configuration);
			return provider;
		}
	}

}

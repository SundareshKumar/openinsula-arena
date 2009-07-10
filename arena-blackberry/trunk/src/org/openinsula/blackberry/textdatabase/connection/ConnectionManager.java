package org.openinsula.blackberry.textdatabase.connection;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.openinsula.blackberry.textdatabase.connection.configuration.FileConfiguration;
import org.openinsula.blackberry.textdatabase.serialization.Builder;
import org.openinsula.blackberry.textdatabase.serialization.Parser;

public abstract class ConnectionManager {

	public static void persist(final Object obj, final Builder builder, final Configuration configuration,
			final AsyncCallback callback) {
//		new Thread() {
//			public void run() {
				try {
					ConnectionProvider provider = ConnectionProviderFactory.get(configuration);

					OutputStream out = provider.getOutputStream();
					
					builder.build(obj, out);

					out.flush();
					out.close();

					provider.finish();
					
					callback.success(null);
				}
				catch (Throwable e) {
					callback.failure(null, e);
				}
//			}
//		}.start();
	}

	public static void getAll(final Parser parser, final FileConfiguration configuration, final AsyncCallback callback) {
		// new Thread() {
		// public void run() {
		try {
			ConnectionProvider connectionProvider = ConnectionProviderFactory.get(configuration);

			InputStream in = connectionProvider.getInputStream();

			Vector vector = (Vector) parser.parse(in);

			in.close();

			connectionProvider.finish();

			callback.success(vector);
		}
		catch (Throwable t) {
			callback.failure(null, t);
		}
		// }
		// }.start();
	}

}

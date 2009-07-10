package org.openinsula.blackberry.textdatabase.connection.iterator;

import java.io.IOException;
import java.io.InputStream;

import org.openinsula.blackberry.textdatabase.connection.ConnectionProvider;
import org.openinsula.blackberry.textdatabase.connection.ConnectionProviderFactory;
import org.openinsula.blackberry.textdatabase.connection.configuration.FileConfiguration;

/**
 * @author João Galli
 *
 */
public class FileIteratorReader {

	private InputStream inputStream;

	private final int[] fields;

	private ConnectionProvider connectionProvider;

	public FileIteratorReader(String fileName, int[] fields) throws Throwable {
		this.fields = fields;
		inputStream = getInputStream(fileName);
	}

	private InputStream getInputStream(String fileName) throws Throwable {
		FileConfiguration configuration = new FileConfiguration(fileName);
		configuration.setDeleteExistentFile(false);
		configuration.setCreateNotExistentFile(false);

		connectionProvider = ConnectionProviderFactory.get(configuration);

		InputStream in = connectionProvider.getInputStream();

		return in;
	}

	public byte[][] next() throws IOException {
		byte[][] bean = new byte[fields.length][];

		for (int findex = 0; findex < fields.length; findex++) {
			byte[] field = new byte[fields[findex]];

			if (inputStream.read(field) > -1) {
				bean[findex] = field;
			}
			else {
				return null;
			}
		}

		return bean;
	}

	public byte[][][] getValuesFromFile(int numRegistros) throws Throwable {
		byte[][][] values = new byte[numRegistros][fields.length][];

		try {
			for (int i = 0; i < values.length; i++) {
				values[i] = next();
			}

			return values;
		}
		catch (Throwable t) {
			throw t;
		}
	}

	public void finish() throws IOException {
		inputStream.close();
		connectionProvider.finish();
	}

}

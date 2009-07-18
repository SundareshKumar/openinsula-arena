package org.openinsula.blackberry.textdatabase.persistence;

import java.util.Vector;

import org.openinsula.blackberry.textdatabase.connection.iterator.FileIteratorReader;
import org.openinsula.blackberry.textdatabase.persistence.filter.Filter;

public abstract class PersistenceManager {

	private static FileIteratorReader fileIteratorReader;

	public static Vector search(Filter filter, boolean newSearch) throws Throwable {
		if (newSearch) {
			if (fileIteratorReader != null) {
				fileIteratorReader.finish();
			}
			fileIteratorReader = null;
		}

		if (fileIteratorReader == null) {
			fileIteratorReader = new FileIteratorReader(filter.getFilePath(), filter.getMap().getFields());
		}

		Vector result = new Vector();

		if (filter.getLimit() <= -1) {
			byte[][] next = null;

			while ((next = fileIteratorReader.next()) != null) {
				if (match(filter, next)) {
					result.addElement(next);
				}
			}
		}
		else {
			int i = 0;
			byte[][] next = null;
			while ((i < filter.getLimit()) && ((next = fileIteratorReader.next()) != null)) {
				if (match(filter, next)) {
					i++;
					result.addElement(next);
				}
			}
		}

		return result;
	}

	private static boolean match(Filter filter, byte[][] next) {
		int[] fields = filter.getFields();

		for (int k = 0; k < fields.length; k++) {
			int j = fields[k];

			if (!filter.matches(k, next[j])) {
				return false;
			}
		}

		return true;
	}

}

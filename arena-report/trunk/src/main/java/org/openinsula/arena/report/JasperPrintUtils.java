package org.openinsula.arena.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Utility class to load and cache {@link JasperReport} resources to generate
 * {@link JasperPrint} instances.
 * 
 * @author Lucas K Mogari
 * @author yanaga
 * @since 1.1
 * @see JasperFillManager
 * @see JasperPrint
 * @see JasperReport
 */
public class JasperPrintUtils {

	/**
	 * The {@link Map} representing the {@link JasperReport} cache.
	 */
	protected static final Map<String, JasperReport> jasperReportCache = new ConcurrentHashMap<String, JasperReport>();

	/**
	 * Static method that loads, caches and fills the {@link JasperReport} with
	 * the corresponding values, returning the completed {@link JasperPrint}
	 * instance.
	 * 
	 * @param resourceName The classpath resource representing the
	 * {@link JasperReport}.
	 * @param values The report values.
	 * @return The {@link JasperPrint} instance.
	 * @throws JRException
	 * @throws IOException
	 */
	public static JasperPrint generate(String resourceName, List<Map<String, Object>> values) throws JRException,
			IOException {
		return generate(resourceName, new HashMap<Object, Object>(), values);
	}

	/**
	 * Static method that loads, caches and fills the {@link JasperReport} with
	 * the corresponding values, returning the completed {@link JasperPrint}
	 * instance.
	 * 
	 * @param resourceName The classpath resource representing the
	 * {@link JasperReport}.
	 * @param parameters The report parameters.
	 * @param values The report values.
	 * @return The {@link JasperPrint} instance.
	 * @throws JRException
	 * @throws IOException
	 */
	public static JasperPrint generate(String resourceName, Map<?, ?> parameters, List<Map<String, Object>> values)
			throws JRException, IOException {
		JasperReport jasperReport = jasperReportCache.get(resourceName);

		if (jasperReport == null) {
			InputStream is = null;

			try {
				is = JasperPrintUtils.class.getResourceAsStream(resourceName);

				if (is != null) {
					jasperReport = (JasperReport) JRLoader.loadObject(is);

					jasperReportCache.put(resourceName, jasperReport);
				}
				else {
					throw new IllegalArgumentException(String.format("Unable to load JasperReport resource: %s",
							resourceName));
				}
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}

		return JasperFillManager.fillReport(jasperReport, parameters, new JRMapCollectionDataSource(values));
	}

}

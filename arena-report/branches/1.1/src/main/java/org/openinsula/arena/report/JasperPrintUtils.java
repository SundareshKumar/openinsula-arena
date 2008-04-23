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
 * Utility class to generate a new {@link JasperPrint} filled with the values
 * and parameters passed.
 *
 * @author Lucas K Mogari
 */
public class JasperPrintUtils {

	private static final Map<String, JasperReport> jasperReportCache = new ConcurrentHashMap<String, JasperReport>();

	@SuppressWarnings("unchecked")
	public static JasperPrint generate(String resourceName, List<Map<String, Object>> values) throws JRException,
			IOException {
		return generate(resourceName, new HashMap(), values);
	}

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

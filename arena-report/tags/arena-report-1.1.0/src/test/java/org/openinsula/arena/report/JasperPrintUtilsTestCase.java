package org.openinsula.arena.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.junit.Test;

public class JasperPrintUtilsTestCase {

	@Test
	public void testLoadReport() throws JRException, IOException {
		JasperPrint jasperPrint = JasperPrintUtils.generate("/SampleReport.jasper",
				new LinkedList<Map<String, Object>>());

		assertNotNull(jasperPrint);
		assertEquals(1, JasperPrintUtils.jasperReportCache.size());

		jasperPrint = JasperPrintUtils.generate("/SampleReport.jasper", new HashMap<Object, Object>(),
				new LinkedList<Map<String, Object>>());

		assertNotNull(jasperPrint);
		assertEquals(1, JasperPrintUtils.jasperReportCache.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonExistingResource() throws JRException, IOException {
		JasperPrintUtils.generate("/NonExistingReport.jasper", new LinkedList<Map<String, Object>>());
	}
}

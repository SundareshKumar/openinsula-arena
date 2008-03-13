package org.openinsula.arena.echo2.component.provider;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;


public class PdfDownloadProvider extends AbstractDownloadProvider {
	private static final long serialVersionUID = 1L;

	/**
	 * @param jasperPrint
	 * @throws JRException
	 */
	public PdfDownloadProvider(JasperPrint jasperPrint) throws JRException {
		initDownloadProvider(JasperExportManager.exportReportToPdf(jasperPrint));
	}

	public PdfDownloadProvider(byte[] byteArray) {
		initDownloadProvider(byteArray);
	}

	private void initDownloadProvider(byte[] byteArray) {
		setByteArray(byteArray);
		setContentType("application/pdf");
		setFileName("relatorio.pdf");
	}
}
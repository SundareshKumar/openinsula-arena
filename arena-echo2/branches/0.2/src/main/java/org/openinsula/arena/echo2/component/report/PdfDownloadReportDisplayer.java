package org.openinsula.arena.echo2.component.report;

import net.sf.jasperreports.engine.JasperPrint;

import org.openinsula.arena.echo2.component.provider.DownloadProvider;
import org.openinsula.arena.echo2.component.provider.PdfDownloadProvider;

/**
 * @author Lucas K Mogari
 */
public class PdfDownloadReportDisplayer extends AbstractDownloadReportDisplayer {

	@Override
	protected DownloadProvider getDownloadProvider(JasperPrint jasperPrint) throws Exception {
		return new PdfDownloadProvider(jasperPrint);
	}

}

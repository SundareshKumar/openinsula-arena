package org.openinsula.arena.echo2.component.report;

import net.sf.jasperreports.engine.JasperPrint;
import nextapp.echo2.app.ApplicationInstance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.echo2.component.provider.Download;
import org.openinsula.arena.echo2.component.provider.DownloadProvider;
import org.openinsula.arena.report.ReportDisplayer;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractDownloadReportDisplayer implements ReportDisplayer {

	protected final Log logger = LogFactory.getLog(getClass());

	protected abstract DownloadProvider getDownloadProvider(JasperPrint jasperPrint) throws Exception;

	public boolean display(JasperPrint jasperPrint) {
		DownloadProvider downloadProvider = null;

		try {
			downloadProvider = getDownloadProvider(jasperPrint);

			final Download download = new Download(downloadProvider, true);

			ApplicationInstance.getActive().enqueueCommand(download);

			return true;
		}
		catch (final Exception e) {
			logger.error("Error when displaying the report.", e);
		}

		return false;
	}

}

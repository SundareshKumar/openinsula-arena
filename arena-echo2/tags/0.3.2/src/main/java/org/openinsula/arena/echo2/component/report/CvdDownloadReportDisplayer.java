package org.openinsula.arena.echo2.component.report;

import net.sf.jasperreports.engine.JasperPrint;

import org.openinsula.arena.echo2.component.provider.CsvDownloadProvider;
import org.openinsula.arena.echo2.component.provider.DownloadProvider;
import org.openinsula.arena.echo2.component.provider.type.FieldDelimiter;
import org.openinsula.arena.echo2.component.provider.type.RecordDelimiter;

/**
 * @author Lucas K Mogari
 */
public class CvdDownloadReportDisplayer extends AbstractDownloadReportDisplayer {

	private FieldDelimiter fieldDelimiter = FieldDelimiter.VIRGULA;

	private RecordDelimiter recordDelimiter = RecordDelimiter.BARRA_N;

	public CvdDownloadReportDisplayer() {
	}

	public CvdDownloadReportDisplayer(FieldDelimiter fieldDelimiter, RecordDelimiter recordDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
		this.recordDelimiter = recordDelimiter;
	}

	@Override
	protected DownloadProvider getDownloadProvider(JasperPrint jasperPrint) throws Exception {
		return new CsvDownloadProvider(jasperPrint, fieldDelimiter, recordDelimiter);
	}

}

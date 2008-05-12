package org.openinsula.arena.echo2.component.provider;

import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import org.openinsula.arena.echo2.component.provider.type.FieldDelimiter;
import org.openinsula.arena.echo2.component.provider.type.RecordDelimiter;

/**
 * @author lucas
 */
public class CsvDownloadProvider extends AbstractDownloadProvider {
	private static final long serialVersionUID = 1L;

	/**
	 * @param jasperPrint
	 * @throws JRException
	 */
	public CsvDownloadProvider(JasperPrint jasperPrint, FieldDelimiter fieldDelimiter, RecordDelimiter recordDelimiter)
			throws JRException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final JRCsvExporter csvExporter = new JRCsvExporter();

		if (fieldDelimiter != null) {
			csvExporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, fieldDelimiter.getDelimiter());
		}
		else {
			csvExporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, FieldDelimiter.VIRGULA.getDelimiter());
		}
		if (recordDelimiter != null) {
			csvExporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, recordDelimiter.getDelimiter());
		}
		else {
			csvExporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, RecordDelimiter.BARRA_N.getDelimiter());
		}

		csvExporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
		csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
		csvExporter.exportReport();

		initDownloadProvider(baos.toByteArray());
	}

	public CsvDownloadProvider(byte[] byteArray) {
		initDownloadProvider(byteArray);
	}

	private void initDownloadProvider(byte[] byteArray) {
		setByteArray(byteArray);
		setContentType("application/octet-stream");
		setFileName("relatorio.csv");
	}
}
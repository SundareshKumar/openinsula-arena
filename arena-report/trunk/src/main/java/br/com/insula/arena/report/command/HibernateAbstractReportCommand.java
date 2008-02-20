package br.com.insula.arena.report.command;

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

import org.openinsula.vulcano.core.command.SingleObjectCommand;


public abstract class HibernateAbstractReportCommand extends SingleObjectCommand<Object, JasperPrint> {
	private static Map<String, JasperReport> jasperReportCache = new ConcurrentHashMap<String, JasperReport>();
	
	public HibernateAbstractReportCommand(Object object) {
		super(object);
	}

	/**
	 * Retorna o JasperPrint criado pelo método execute(). Deve ser usado para
	 * os reports que não tem necessidade de usar um Dao.
	 * 
	 * @return o JasperPrint
	 */
	public JasperPrint createJasperPrint() {
		return execute();
	}

	protected JasperPrint createReport(String resourceName, List<Map<String, Object>> values) {
		return createReport(resourceName, new HashMap(), values);
	}

	protected JasperPrint createReport(String resourceName, Map parameters, List<Map<String, Object>> values) {
		JasperReport jasperReport = jasperReportCache.get(resourceName);

		if (jasperReport == null) {
			InputStream is = getClass().getResourceAsStream(resourceName);

			if (is == null) {
				throw new IllegalArgumentException("Nao foi possivel localizar o relatorio " + resourceName);
			} else {
				try {
					jasperReport = (JasperReport) JRLoader.loadObject(is);
				} catch (JRException e) {
					throw new IllegalArgumentException("Erro ao carregar relatorio " + resourceName);
				}
				jasperReportCache.put(resourceName, jasperReport);
			}
		}

		try {
			return JasperFillManager.fillReport(jasperReport, parameters, new JRMapCollectionDataSource(values));
		} catch (JRException e) {
			throw new IllegalArgumentException("Erro ao preencher o relatorio " + resourceName + " com os valores.", e);
		}
	}

}

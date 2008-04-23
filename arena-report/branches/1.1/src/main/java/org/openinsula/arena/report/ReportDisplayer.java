package org.openinsula.arena.report;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author Lucas K Mogari
 */
public interface ReportDisplayer {

	public boolean display(JasperPrint jasperPrint);

}
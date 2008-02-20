package org.openinsula.arena.report.controller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.report.command.AbstractReportCommand;
import org.openinsula.vulcano.orm.command.transaction.DaoCommandTransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author utiumi
 *
 */
@Component
public abstract class ReportController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	protected DaoCommandTransactionFacade transactionFacade;
	
	public boolean visualizarPdf(AbstractReportCommand reportCommand) {
		try {
			JasperPrint jasperPrint = transactionFacade.invoke(reportCommand);
			
			if(jasperPrint == null) {
				return false;
			}
			
			downloadPdf(jasperPrint);
			
			return true;
		} catch (JRException e) {
			logger.error("(JRException) Erro ao gerar o relatorio.", e);
		} catch (Exception e) {
			logger.error("(Exception) Erro ao gerar o relatorio.", e);
		}
		
		return false;
	}
	
	public boolean visualizarCsv(AbstractReportCommand reportCommand) {
		try {
			JasperPrint jasperPrint = transactionFacade.invoke(reportCommand);
			
			if(jasperPrint == null) {
				return false;
			}
			
			downloadCsv(jasperPrint);
			
			return true;
		} catch (JRException e) {
			logger.error("(JRException) Erro ao gerar o relatorio.", e);
		} catch (Exception e) {
			logger.error("(Exception) Erro ao gerar o relatorio.", e);
		}
		
		return false;
	}
	
	public abstract void downloadPdf(JasperPrint jasperPrint) throws JRException;
	
	public abstract void downloadCsv(JasperPrint jasperPrint) throws JRException;
}
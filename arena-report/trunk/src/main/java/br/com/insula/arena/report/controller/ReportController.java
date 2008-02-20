package br.com.insula.arena.report.controller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.vulcano.core.command.CommandInvoker;
import br.com.insula.arena.report.command.AbstractReportCommand;

/**
 * 
 * @author utiumi
 *
 */
public abstract class ReportController {
	protected final Log logger = LogFactory.getLog(getClass());

	protected CommandInvoker commandInvoker;
	
	public boolean visualizarPdf(AbstractReportCommand reportCommand) {
		try {
			JasperPrint jasperPrint = commandInvoker.invoke(reportCommand);
			
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
			JasperPrint jasperPrint = commandInvoker.invoke(reportCommand);
			
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
	
	public void setCommandInvoker(CommandInvoker commandInvoker) {
		this.commandInvoker = commandInvoker;
	}
}
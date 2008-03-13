package org.openinsula.arena.echo2.component.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfDownloadProvider implements DownloadProvider {
    private byte[] pdfByteArray;
    
    private String fileName;
    
    public PdfDownloadProvider() {
	}
    
    public PdfDownloadProvider(String fileName) {
		super();
		this.fileName = fileName;
	}

	public PdfDownloadProvider(byte[] pdfByteArray) {
        this.pdfByteArray = pdfByteArray;
    }
    
    public String getContentType() {
        return "application/pdf";
    }

    public String getFileName() {
    	if(fileName == null) {
    		String time = new SimpleDateFormat("_hh:mm:ss_dd-MM-yy").format(new Date());
    		fileName = "relatorio-"+time+".pdf";
    	}
    	return fileName;
    }

    public int getSize() {
        return pdfByteArray.length;
    }

    public void writeFile(OutputStream stream) throws IOException {
        stream.write(pdfByteArray);
    }

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

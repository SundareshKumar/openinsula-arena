package org.openinsula.arena.echo2.component.provider;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An interface for download-providing objects.  This interface specifies
 * methods for obtaining the content type, filename, size, and data of a
 * downloadable file.
 */
public interface DownloadProvider {

    /**
     * Returns the content type of the file.
     *
     * @return The content type of the file.
     */
    public String getContentType();

    /** 
     * Returns the file's name.  Returning null is allowed.
     *
     * @return The file's name.
     */
    public String getFileName();
    
    /**
     * Returns the size of the file.  If the size is unknown, -1 may be 
     * returned.
     *
     * @return The size of the file.
     */
    public int getSize();

    /** 
     * Writes the file to the specified output stream.
     *
     * @param out The output stream to which the file should be written.
     * @throws IOException If the provider is unable to perform this operation.
     */
    public void writeFile(OutputStream out)
    throws IOException;
}

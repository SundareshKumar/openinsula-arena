/*
 * This file is part of the Echo File Transfer Library (hereinafter "EFTL").
 * Copyright (C) 2002-2005 NextApp, Inc. Version: MPL 1.1/GPL 2.0/LGPL 2.1 The
 * contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 */

package org.openinsula.arena.echo2.component.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * An abstract implementation of a <code>DownloadProvider</code>.
 */
public abstract class AbstractDownloadProvider implements DownloadProvider, Serializable {

	private String fileName;

	private String contentType;

	private byte[] byteArray;

	/**
	 *
	 */
	public AbstractDownloadProvider() {
	}

	/**
	 * @param byteArray
	 */
	public AbstractDownloadProvider(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	/**
	 * Returns null by default.
	 *
	 * @see br.com.insula.echo2.component.filetransfer.DownloadProvider#getFileName()
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Returns -1 by default, indicating an unknown file size.
	 *
	 * @see br.com.insula.echo2.component.filetransfer.DownloadProvider#getSize()
	 */
	public int getSize() {
		return byteArray.length;
	}

	public void writeFile(OutputStream stream) throws IOException {
		stream.write(byteArray);
	}

	public String getContentType() {
		return contentType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
}

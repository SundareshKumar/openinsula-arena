/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.io.textfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class DefaultFileTranformerTestCase {
	@Test
	public void testSetOutput() {
		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		transformerImpl.setOutput(new ByteArrayOutputStream());

		try {
			transformerImpl.setOutput(new ByteArrayOutputStream());
			fail();
		}
		catch (Exception ex) {
		}

		try {
			transformerImpl.setOutput(new PrintWriter(new ByteArrayOutputStream()));
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testClose() {
		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		try {
			transformerImpl.close();
			fail();
		}
		catch (Exception ex) {
		}

		transformerImpl.setOutput(new ByteArrayOutputStream());
		transformerImpl.close();
	}

	@Test
	public void testAddLine() throws Exception {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			protected boolean doMatches(String s) {
				return true;
			}
		};

		lineFactory.addField(new StringField(10));
		Line line = lineFactory.createLine();
		line.setValue(0, "abcd");

		DefaultFileTransformer transformerImpl = new DefaultFileTransformer(null);
		try {
			transformerImpl.addLine(line);
			fail();
		}
		catch (Exception ex) {
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		transformerImpl.setOutput(baos);
		transformerImpl.addLine(line);
		transformerImpl.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));
		String s = br.readLine();
		assertNotNull(s);
		assertEquals("abcd      ", s);
	}
}

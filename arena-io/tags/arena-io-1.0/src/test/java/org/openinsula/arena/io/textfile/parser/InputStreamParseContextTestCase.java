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
package org.openinsula.arena.io.textfile.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import org.junit.Test;

public class InputStreamParseContextTestCase {
	@Test
	public void testParseWithLookAheadFirst() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos));
		pw.println("A");
		pw.println("B");
		pw.println("C");
		pw.println("D");
		pw.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		ParseContext parseContext = new InputStreamParseContext(bais, Charset.defaultCharset(), null, null);
		assertEquals("A", parseContext.lookAhead());
		assertEquals(0, parseContext.getCurrentLineCount());
		assertEquals("A", parseContext.getLine());
		assertEquals(1, parseContext.getCurrentLineCount());
		assertEquals("B", parseContext.getLine());
		assertEquals(2, parseContext.getCurrentLineCount());
		assertEquals("C", parseContext.getLine());
		assertEquals(3, parseContext.getCurrentLineCount());
		assertEquals("D", parseContext.lookAhead());
		assertEquals(3, parseContext.getCurrentLineCount());
		assertEquals("D", parseContext.getLine());
		assertEquals(4, parseContext.getCurrentLineCount());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.getLine());
	}

	@Test
	public void testParseWithGetLineFirst() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos));
		pw.println("A");
		pw.println("B");
		pw.println("C");
		pw.println("D");
		pw.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		ParseContext parseContext = new InputStreamParseContext(bais, Charset.defaultCharset(), null, null);
		assertEquals("A", parseContext.getLine());
		assertEquals("B", parseContext.lookAhead());
		assertEquals("B", parseContext.getLine());
		assertEquals("C", parseContext.getLine());
		assertEquals("D", parseContext.lookAhead());
		assertEquals("D", parseContext.getLine());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.getLine());

		bais = new ByteArrayInputStream(baos.toByteArray());

		parseContext = new InputStreamParseContext(bais, Charset.defaultCharset(), null, null);
		assertEquals("A", parseContext.getLine());
		assertEquals("B", parseContext.getLine());
		assertEquals("C", parseContext.getLine());
		assertEquals("D", parseContext.lookAhead());
		assertEquals("D", parseContext.lookAhead());
		assertEquals("D", parseContext.getLine());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.lookAhead());
		assertNull(parseContext.getLine());
		assertNull(parseContext.getLine());
	}

}

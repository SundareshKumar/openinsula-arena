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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.HeaderArquivoLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoPLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoQLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class ParseContextFactoryTestCase {

	@Test
	public void testGetParseMap() {
		assertEquals(3, ParseContextFactory.buildParseMap(Teste.class).size());
	}

	@Test
	public void testPopulateMapWithLineParserMethods() {
		assertEquals(2, ParseContextFactory.buildParseMap(ParserInterface.class).size());
	}

	@Test
	public void testParseContext() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write("TESTE\n".getBytes());

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		InputStreamParseContext parseContext = (InputStreamParseContext) ParseContextFactory.newParseContext(bais,
				new Teste());

		assertEquals(3, parseContext.parseMap.size());
	}

	private class Teste implements ParserInterface {
		public void testeHeader(Line line) {
		}

		public void testeSegmentoP(Line line) {
		}

		@LineParser(SegmentoQLineFactory.class)
		public void testSegmentoQ(Line line) {
		}
	}

	private interface ParserInterface {
		@LineParser(HeaderArquivoLineFactory.class)
		public void testeHeader(Line line);

		@LineParser(SegmentoPLineFactory.class)
		public void testeSegmentoP(Line line);
	}

}

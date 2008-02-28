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
package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openinsula.arena.io.textfile.FileParser;
import org.openinsula.arena.io.textfile.FileParserFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.LineParser;

public class Cnab240FileParserFactoryTestCase {
	private int headerArquivoCount = 0;

	private int headerLoteCount = 0;

	private int segmentoPCount = 0;

	private int segmentoQCount = 0;

	private int segmentoRCount = 0;

	private int segmentoTCount = 0;

	private int segmentoUCount = 0;

	private int trailerLoteCount = 0;

	private int trailerArquivoCount = 0;

	@Before
	public void initCounter() {
		headerArquivoCount = 0;
		headerLoteCount = 0;
		segmentoPCount = 0;
		segmentoQCount = 0;
		segmentoRCount = 0;
		segmentoTCount = 0;
		segmentoUCount = 0;
		trailerLoteCount = 0;
		trailerArquivoCount = 0;
	}

	@Test
	@SuppressWarnings("unused")
	public void testNewCnab240RemessaFileParser() throws IOException {
		initCounter();
		FileParser fileParser = FileParserFactory.newCnab240RemessaFileParser();
		fileParser.parse(getClass().getResourceAsStream("/cnab240/remessa1.txt"), new Cnab240Handler());

		assertEquals(1, headerArquivoCount);
		assertEquals(1, headerLoteCount);
		assertEquals(1, segmentoPCount);
		assertEquals(1, segmentoQCount);
		assertEquals(1, segmentoRCount);
		assertEquals(1, trailerLoteCount);
		assertEquals(1, trailerLoteCount);
	}

	@Test
	public void testNewCnab240RetornoFileParser() throws IOException {
		initCounter();
		FileParser fileParser = FileParserFactory.newCnab240RetornoFileParser();
		fileParser.parse(getClass().getResourceAsStream("/cnab240/retorno1.txt"), new Cnab240Handler());

		assertEquals(1, headerArquivoCount);
		assertEquals(1, headerLoteCount);
		assertEquals(18, segmentoTCount);
		assertEquals(18, segmentoUCount);
		assertEquals(1, trailerLoteCount);
		assertEquals(1, trailerLoteCount);

	}

	public class Cnab240Handler {
		@LineParser(HeaderArquivoLineFactory.class)
		public void headerArquivo(Line line) {
			headerArquivoCount++;
		}

		@LineParser(HeaderLoteLineFactory.class)
		public void headerLote(Line line) {
			headerLoteCount++;
		}

		@LineParser(SegmentoPLineFactory.class)
		public void segmentoP(Line line) {
			segmentoPCount++;
		}

		@LineParser(SegmentoQLineFactory.class)
		public void segmentoQ(Line line) {
			segmentoQCount++;
		}

		@LineParser(SegmentoRLineFactory.class)
		public void segmentoR(Line line) {
			segmentoRCount++;
		}

		@LineParser(SegmentoTLineFactory.class)
		public void segmentoT(Line line) {
			segmentoTCount++;
		}

		@LineParser(SegmentoULineFactory.class)
		public void segmentoU(Line line) {
			segmentoUCount++;
		}

		@LineParser(TrailerDeLoteLineFactory.class)
		public void trailerDeLote(Line line) {
			trailerLoteCount++;
		}

		@LineParser(TrailerDeArquivoLineFactory.class)
		public void trailerDeArquivo(Line line) {
			trailerArquivoCount++;
		}
	}
}

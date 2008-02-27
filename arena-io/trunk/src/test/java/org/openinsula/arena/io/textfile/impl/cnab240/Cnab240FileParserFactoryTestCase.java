package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openinsula.arena.io.textfile.FileParser;
import org.openinsula.arena.io.textfile.FileParserFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.HeaderArquivoLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.HeaderLoteLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoPLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoQLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoRLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoTLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoULineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.TrailerDeArquivoLineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.TrailerDeLoteLineFactory;
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

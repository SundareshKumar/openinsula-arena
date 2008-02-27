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
import org.openinsula.arena.io.textfile.parser.InputStreamParseContext;
import org.openinsula.arena.io.textfile.parser.ParseContextFactory;

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

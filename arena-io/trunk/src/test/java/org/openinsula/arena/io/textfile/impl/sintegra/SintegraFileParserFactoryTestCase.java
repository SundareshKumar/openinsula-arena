package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openinsula.arena.io.textfile.FileParser;
import org.openinsula.arena.io.textfile.FileParserFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento10LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento11LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento50LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento51LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento53LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento54LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento56LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento75LineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento88ALineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento88ECFLineFactory;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento90LineFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.LineParser;

public class SintegraFileParserFactoryTestCase {

	private int segmento10Count = 0;

	private int segmento11Count = 0;

	private int segmento50Count = 0;

	private int segmento51Count = 0;

	private int segmento53Count = 0;

	private int segmento54Count = 0;

	private int segmento56Count = 0;

	private int segmento75Count = 0;

	private int segmento88ECFCount = 0;

	private int segmento88ACount = 0;

	private int segmento90Count = 0;

	@Test
	@SuppressWarnings("unused")
	public void testNewSintegraFileParser() throws IOException {
		FileParser fileParser = FileParserFactory.newSintegraFileParser();
		fileParser.parse(getClass().getResourceAsStream("/sintegra/sintegra1.txt"), new SintegraHandler());

		assertEquals(1, segmento10Count);
		assertEquals(1, segmento11Count);
		assertEquals(1, segmento50Count);
		assertEquals(0, segmento51Count);
		assertEquals(0, segmento53Count);
		assertEquals(4, segmento54Count);
		assertEquals(0, segmento56Count);
		assertEquals(1, segmento75Count);
		assertEquals(0, segmento88ACount);
		assertEquals(0, segmento88ECFCount);
		assertEquals(4, segmento90Count);
	}

	public class SintegraHandler {
		@LineParser(Segmento10LineFactory.class)
		public void segmento10(final Line line) {
			segmento10Count++;
		}

		@LineParser(Segmento11LineFactory.class)
		public void segmento11(final Line line) {
			segmento11Count++;
		}

		@LineParser(Segmento50LineFactory.class)
		public void segmento50(final Line line) {
			segmento50Count++;
		}

		@LineParser(Segmento51LineFactory.class)
		public void segmento51(final Line line) {
			segmento51Count++;
		}

		@LineParser(Segmento53LineFactory.class)
		public void segmento53(final Line line) {
			segmento53Count++;
		}

		@LineParser(Segmento54LineFactory.class)
		public void segmento54(final Line line) {
			segmento54Count++;
		}

		@LineParser(Segmento56LineFactory.class)
		public void segmento56(final Line line) {
			segmento56Count++;
		}

		@LineParser(Segmento75LineFactory.class)
		public void segmento75(final Line line) {
			segmento75Count++;
		}

		@LineParser(Segmento88ECFLineFactory.class)
		public void segmento88ECF(final Line line) {
			segmento88ECFCount++;
		}

		@LineParser(Segmento88ALineFactory.class)
		public void segmento88A(final Line line) {
			segmento88ACount++;
		}

		@LineParser(Segmento90LineFactory.class)
		public void segmento90(final Line line) {
			segmento90Count++;
		}

	}

}

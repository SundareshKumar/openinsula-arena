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
package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openinsula.arena.io.textfile.FileParser;
import org.openinsula.arena.io.textfile.FileParserFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.LineParser;

public class Cnab400FileParserFactoryTestCase {
	private int headerArquivoCount = 0;

	private int detalheCount = 0;

	private int mensagemCount = 0;

	private int rateioCount = 0;

	private int trailerArquivoCount = 0;

	@Before
	public void initCounter() {
		headerArquivoCount = 0;
		detalheCount = 0;
		mensagemCount = 0;
		rateioCount = 0;
		trailerArquivoCount = 0;
	}

	@Test
	@SuppressWarnings("unused")
	public void testNewCnab400RemessaFileParser() throws IOException {
		initCounter();
		FileParser fileParser = FileParserFactory.newCnab400RemessaFileParser();
		fileParser.parse(getClass().getResourceAsStream("/cnab400/remessa1.txt"), new RemessaCnab400Handler());

		assertEquals(1, headerArquivoCount);
		assertEquals(218, detalheCount);
		assertEquals(218, mensagemCount);
		assertEquals(0, rateioCount);
		assertEquals(1, trailerArquivoCount);
	}

	@Test
	public void testNewCnab400RetornoFileParser() throws IOException {
		initCounter();
		FileParser fileParser = FileParserFactory.newCnab400RetornoFileParser();
		fileParser.parse(getClass().getResourceAsStream("/cnab400/retorno1.txt"), new RetornoCnab400Handler());

		assertEquals(1, headerArquivoCount);
		assertEquals(238, detalheCount);
		assertEquals(0, mensagemCount);
		assertEquals(0, rateioCount);
		assertEquals(1, trailerArquivoCount);
	}

	public class RemessaCnab400Handler {
		@LineParser(RemessaHeaderLabelLineFactory.class)
		public void headerArquivo(Line line) {
			headerArquivoCount++;
		}

		@LineParser(RemessaTransacaoDetalheLineFactory.class)
		public void detalhe(Line line) {
			detalheCount++;
		}

		@LineParser(RemessaTransacaoMensagemLineFactory.class)
		public void mensagem(Line line) {
			mensagemCount++;
		}

		@LineParser(RemessaTransacaoRateioDeCreditoLineFactory.class)
		public void rateio(Line line) {
			rateioCount++;
		}

		@LineParser(RemessaTraillerLineFactory.class)
		public void trailerDeArquivo(Line line) {
			trailerArquivoCount++;
		}
	}

	public class RetornoCnab400Handler {
		@LineParser(RetornoHeaderLabelLineFactory.class)
		public void headerArquivo(Line line) {
			headerArquivoCount++;
		}

		@LineParser(RetornoTransacaoDetalheLineFactory.class)
		public void detalhe(Line line) {
			detalheCount++;
		}

		@LineParser(RetornoTransacaoRateioDeCreditoLineFactory.class)
		public void rateio(Line line) {
			rateioCount++;
		}

		@LineParser(RetornoTraillerLineFactory.class)
		public void trailerDeArquivo(Line line) {
			trailerArquivoCount++;
		}
	}

}

package org.openinsula.arena.tiss.util;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import br.gov.ans.padroes.tiss.schemas.MensagemTISS;

public class MensagemTISSUtilsTestCase {

	@Test
	public void testCalculateHashWithDocument() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("/response.xml");

		JAXBContext context = JAXBContext.newInstance("br.gov.ans.padroes.tiss.schemas");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		MensagemTISS mensagemTISS = (MensagemTISS) unmarshaller.unmarshal(inputStream);

		Document document = MensagemTISSUtils.calculateHash(mensagemTISS);
		assertEquals("32a9beaf594993c1ef59ae362c9e9b9b", document.getElementsByTagName("hash").item(0).getTextContent());
	}

	@Test
	public void testCalculateHash() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("/response.xml");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);

		String hash = MensagemTISSUtils.calculateHash(document.getDocumentElement());
		assertEquals("32a9beaf594993c1ef59ae362c9e9b9b", hash);
	}

	@Test
	public void testAcumularAtributosConcatenados() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("/response.xml");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);

		StringBuilder sb = new StringBuilder();
		Node cabecalhoNode = MensagemTISSUtils.getChildNode(document.getDocumentElement(), "cabecalho");
		Node operadoraParaPrestadorNode = MensagemTISSUtils.getChildNode(document.getDocumentElement(),
				"operadoraParaPrestador");
		MensagemTISSUtils.acumularAtributosConcatenados(cabecalhoNode, sb);
		MensagemTISSUtils.acumularAtributosConcatenados(operadoraParaPrestadorNode, sb);

		String original = "SOLICITACAO_PROCEDIMENTOS162009-03-2315:50:363712547522.02.013712542009-03-231100970000009585146Helvio NunesSem Plano752PAULO AFONSO DE ALMEIDA MACHADO000001001400Consulta Ambulatorial - Ps111";

		assertEquals(original, sb.toString());
	}
}

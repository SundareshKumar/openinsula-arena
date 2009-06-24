package org.openinsula.arena.tiss.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openinsula.arena.lang.util.HashUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.gov.ans.padroes.tiss.schemas.MensagemTISS;

public class MensagemTISSUtils {

	public boolean verifyHash(Document document) throws Exception {
		return false;
	}
	
	public static Document calculateHash(MensagemTISS mensagemTISS) throws Exception {
		JAXBContext context = JAXBContext.newInstance("br.gov.ans.padroes.tiss.schemas");
		Marshaller marshaller = context.createMarshaller();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		marshaller.marshal(mensagemTISS, document);
		
		Node cabecalhoTransacaoNode = document.getElementsByTagName("cabecalho").item(0);
		Node operadoraParaPrestadorNode = document.getElementsByTagName("operadoraParaPrestador").item(0);
		Node prestadorParaOperadoraNode = document.getElementsByTagName("prestadorParaOperadora").item(0);
		
		StringBuilder sb = new StringBuilder();
		if (cabecalhoTransacaoNode != null) {
			acumularAtributosConcatenados(cabecalhoTransacaoNode, sb);
		}
		if (operadoraParaPrestadorNode != null) {
			acumularAtributosConcatenados(operadoraParaPrestadorNode, sb);
		}
		if (prestadorParaOperadoraNode != null) {
			acumularAtributosConcatenados(prestadorParaOperadoraNode, sb);
		}
		
		String md5 = HashUtils.createMD5(sb.toString());
		
		Node hashNode = document.getElementsByTagName("hash").item(0);
		hashNode.setTextContent(md5);
		
		return document;
	}
	
	public static String calculateHash(Node node) {
		Node cabecalhoTransacaoNode = getChildNode(node, "cabecalho");
		Node operadoraParaPrestadorNode = getChildNode(node, "operadoraParaPrestador");
		Node prestadorParaOperadoraNode = getChildNode(node, "prestadorParaOperadora");
		
		StringBuilder sb = new StringBuilder();
		if (cabecalhoTransacaoNode != null) {
			acumularAtributosConcatenados(cabecalhoTransacaoNode, sb);
		}
		if (operadoraParaPrestadorNode != null) {
			acumularAtributosConcatenados(operadoraParaPrestadorNode, sb);
		}
		if (prestadorParaOperadoraNode != null) {
			acumularAtributosConcatenados(prestadorParaOperadoraNode, sb);
		}
		
		return HashUtils.createMD5(sb.toString());
	}

	protected static Node getChildNode(Node node, String nodeName) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			String localName = child.getLocalName();
			if (localName == null) {
				String[] split = child.getNodeName().split(":");
				if (split.length == 2) {
					localName = split[1];
				}
			}
			if (nodeName.equals(localName)) {
				return child;
			}
		}
		return null;
	}

	protected static void acumularAtributosConcatenados(Node node, StringBuilder sb) {
		if (node.hasChildNodes()) {
			NodeList childNodes = node.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				acumularAtributosConcatenados(childNode, sb);
			}
		}
		else {
			String content = node.getTextContent().replace("\n", "");
			if (content.trim().length() > 0) {
				sb.append(content);
			}
		}
	}
}

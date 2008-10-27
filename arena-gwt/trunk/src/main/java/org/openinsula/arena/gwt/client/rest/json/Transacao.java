package org.openinsula.arena.gwt.client.rest.json;

import org.openinsula.arena.gwt.client.rest.xml.atom.Entry;
import org.openinsula.arena.gwt.client.xml.AttributeDependentCompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.MultipleNodeFactory;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class Transacao extends Entry {

	private String numero;

	private String estado;

	public Transacao() {
		final CompositeNodeParser contentNodeParser = getContentNodeParser();

		contentNodeParser.addParser("nome", new NodeParser() {
			public T parse(Node node) {
				numero = XmlParserUtils.getText(node);
			}
		});
		contentNodeParser.addParser("senha", new NodeParser() {
			public T parse(Node node) {
				estado = XmlParserUtils.getText(node);
			}
		});
		final AttributeDependentCompositeNodeParser parser = new AttributeDependentCompositeNodeParser("tipo");

		parser.addParser("consulta", new Consulta().getContentNodeParser());

		contentNodeParser.addParser("guias", parser);

		getContentNodeFactory().addNodeFactory(new MultipleNodeFactory() {
			@Override
			public Node[] createNodes() {
				return new Node[] { createTextElement("nome", numero), createTextElement("senha", estado) };
			}
		});
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String nome) {
		numero = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String senha) {
		estado = senha;
	}

}

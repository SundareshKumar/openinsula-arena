package org.openinsula.arena.gwt.client.rest.json;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.rest.xml.atom.BaseEntry;
import org.openinsula.arena.gwt.client.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.xml.ValueNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.AttributeDependentNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.NodeParser;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class Transacao extends BaseEntry<Transacao> {

	private String numero;

	private String estado;

	private List<Guia> guias = new ArrayList<Guia>();

	public Transacao() {
		final AttributeDependentNodeParser<Guia> parser = new AttributeDependentNodeParser<Guia>(
				"tipo");

		addNodeFactory(new CompositeNodeFactory() {
			@Override
			protected Node createBaseNode() {
				final Element guiasElement = createElement("guias");
				return guiasElement;
			}
		});

		addNodeParser("guias", parser);
		addNodeParser("numero", new ValueNodeParser() {
			public void onNodeParsed(String value) {
				numero = value;
			}
		});
		addNodeParser("estado", new ValueNodeParser() {
			public void onNodeParsed(String value) {
				estado = value;
			}
		});

		parser.addNodeParser("consulta", new NodeParser<Consulta>() {
			public Consulta parse(Node node) {
				final Consulta consulta = new Consulta();

				guias.add(consulta);

				return consulta.parse(node);
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

	public List<Guia> getGuias() {
		return guias;
	}

	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}

}

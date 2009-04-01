package org.openinsula.arena.dne.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.openinsula.arena.dne.entity.Uf;

@XmlRootElement(name = "uf")
@XmlAccessorType(XmlAccessType.FIELD)
class UfVO {
	
	String sigla;
	
	String nome;
	
	UfVO() {
	}
	
	UfVO(final Uf uf) {
		sigla = uf.name();
		nome = uf.getNome();
	}

}

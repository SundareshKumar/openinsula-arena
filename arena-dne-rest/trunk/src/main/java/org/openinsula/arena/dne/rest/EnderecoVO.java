package org.openinsula.arena.dne.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.openinsula.arena.dne.entity.Endereco;

@XmlRootElement(name = "endereco")
@XmlAccessorType(XmlAccessType.FIELD)
class EnderecoVO {

	String logradouro;

	String numero;

	String complemento;

	String bairro;

	String localidade;

	UfVO uf;

	String cep;

	EnderecoVO() {
	}

	EnderecoVO(final Endereco endereco) {
		if (endereco != null) {
			logradouro = endereco.getLogradouro();
			numero = endereco.getNumero();
			complemento = endereco.getComplemento();
			bairro = endereco.getBairro();
			localidade = endereco.getLocalidade();
			
			if (endereco.getUf() != null) {
				uf = new UfVO(endereco.getUf());
			}
			
			cep = endereco.getCep();
		}
	}

}

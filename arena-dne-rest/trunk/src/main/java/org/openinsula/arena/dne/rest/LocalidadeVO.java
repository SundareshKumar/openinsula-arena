package org.openinsula.arena.dne.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.openinsula.arena.dne.entity.Localidade;

@XmlRootElement(name = "localidade")
@XmlAccessorType(XmlAccessType.FIELD)
class LocalidadeVO {

	Long chaveDne;

	UfVO uf;

	String nomeOficial;
	
	LocalidadeVO() {
	}
	
	LocalidadeVO(final Localidade localidade) {
		if (localidade != null) {
			chaveDne = localidade.getChaveDne();
			
			if (localidade.getUf() != null) {
				uf = new UfVO(localidade.getUf());
			}
			
			nomeOficial = localidade.getNomeOficial();
		}
	}
	
	static List<LocalidadeVO> toVOList(final List<Localidade> localidades) {
		if (localidades == null) {
			return null;
		}
		
		List<LocalidadeVO> voList = new ArrayList<LocalidadeVO>(localidades.size());
		
		for (Localidade localidade : localidades) {
			voList.add(new LocalidadeVO(localidade));
		}
		
		return voList;
	}

}

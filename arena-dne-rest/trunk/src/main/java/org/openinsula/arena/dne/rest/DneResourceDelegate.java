package org.openinsula.arena.dne.rest;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.dne.dao.DneDao;
import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Localidade;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DneResourceDelegate {

	private DneDao dneDao;

	public LocalidadeVO findLocalidade(final Long chaveLocalidadeDne) {
		Localidade localidade = dneDao.findLocalidade(chaveLocalidadeDne);
		return new LocalidadeVO(localidade);

	}

	public List<LocalidadeVO> findLocalidade(final String nome, final String siglaUf) {
		List<Localidade> localidades = dneDao.findLocalidade(nome, Uf.valueOf(siglaUf));
		return LocalidadeVO.toVOList(localidades);
	}

	public EnderecoVO findEnderecoByCep(final String cep) {
		Endereco endereco = dneDao.findEnderecoByCep(cep);
		return new EnderecoVO(endereco);
	}
	
	public List<EnderecoVO> findEnderecoByParameter(final String logradouro, final String numero, final String localidade, final String uf) {
		String _logradouro = logradouro == null ? "" : logradouro;
		String _numero= numero == null ? "" : numero;
		String _localidade = localidade == null ? "" : localidade;
		String _uf = uf == null ? "" : uf;
		
		List<BuscaEndereco> buscaList = dneDao.findEnderecoByParameter(_logradouro, _numero, _localidade, Uf.valueOf(_uf));
		
		List<EnderecoVO> result = new ArrayList<EnderecoVO>(buscaList.size());
		
		for (BuscaEndereco buscaEndereco : buscaList) {
			result.add(new EnderecoVO(buscaEndereco.toEndereco()));
		}
		
		return result;
	}

	public List<UfVO> listarEstados() {
		List<UfVO> result = new ArrayList<UfVO>();

		for (Uf uf : Uf.values()) {
			result.add(new UfVO(uf));
		}

		return result;
	}

	@Autowired
	public void setDneDao(final DneDao dneDao) {
		this.dneDao = dneDao;
	}

}

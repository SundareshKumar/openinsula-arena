package org.openinsula.arena.dne.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class DneResource {

	private DneResourceDelegate delegate;

	@GET
	@Path("localidade/{chave}")
	public LocalidadeVO findLocalidade(@PathParam("chave") final Long chaveLocalidadeDne) {
		return delegate.findLocalidade(chaveLocalidadeDne);
	}

	@GET
	@Path("localidade/{nome}/{uf}")
	public List<LocalidadeVO> findLocalidade(@PathParam("nome") final String nome, @PathParam("uf") final String siglaUf) {
		return delegate.findLocalidade(nome, siglaUf.toUpperCase());
	}

	@GET
	@Path("cep/{cep}")
	public EnderecoVO findEnderecoByCep(@PathParam("cep") final String cep) {
		return delegate.findEnderecoByCep(cep);
	}
	
	
	@GET
	@Path("endereco")
	public List<EnderecoVO> findEnderecoByParameter(@QueryParam("logradouro") final String logradouro, @QueryParam("numero") final String numero, @QueryParam("localidade") final String localidade, @QueryParam("uf") final String uf) {
		return delegate.findEnderecoByParameter(logradouro, numero, localidade, uf);
	}

	@GET
	@Path("uf")
	public List<UfVO> listarEstados() {
		return delegate.listarEstados();
	}

	@Autowired
	public void setDelegate(final DneResourceDelegate delegate) {
		this.delegate = delegate;
	}
	
}

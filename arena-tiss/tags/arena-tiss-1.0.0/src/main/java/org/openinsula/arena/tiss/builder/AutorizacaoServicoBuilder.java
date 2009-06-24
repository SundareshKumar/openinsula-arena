package org.openinsula.arena.tiss.builder;

import br.gov.ans.padroes.tiss.schemas.CtBeneficiario;
import br.gov.ans.padroes.tiss.schemas.CtCabecalhoGuia;
import br.gov.ans.padroes.tiss.schemas.CtContratado;
import br.gov.ans.padroes.tiss.schemas.CtItemSolicitacao;
import br.gov.ans.padroes.tiss.schemas.CtAutorizacaoProcedimento.Procedimentos;
import br.gov.ans.padroes.tiss.schemas.MensagemTISS.OperadoraParaPrestador.AutorizacaoServico;

public class AutorizacaoServicoBuilder {

	private AutorizacaoServico autorizacaoServico = new AutorizacaoServico();
	
	public AutorizacaoServicoBuilder(CtCabecalhoGuia identificacaoAutorizacao, CtBeneficiario beneficiario) {
		autorizacaoServico.setIdentificacaoAutorizacao(identificacaoAutorizacao);
		autorizacaoServico.setBeneficiario(beneficiario);
	}

	public AutorizacaoServicoBuilder statusSolicitacao(StatusSolicitacao statusSolicitacao) {
		autorizacaoServico.setStatusSolicitacao(statusSolicitacao.toString());
		
		return this;
	}
	
	public AutorizacaoServicoBuilder prestadorAutorizado(CtContratado prestadorAutorizado) {
		autorizacaoServico.setPrestadorAutorizado(prestadorAutorizado);
		
		return this;
	}
	
	public AutorizacaoServicoBuilder procedimento(CtItemSolicitacao itemSolicitacao) {
		Procedimentos procedimentos = autorizacaoServico.getProcedimentos();
		if (procedimentos == null) {
			procedimentos = new Procedimentos();
			autorizacaoServico.setProcedimentos(procedimentos);
		}
		
		procedimentos.getProcedimento().add(itemSolicitacao);
		
		return this;
	}
	
	public AutorizacaoServico build() {
		return autorizacaoServico;
	}
	
}

package org.openinsula.arena.tiss.builder;

public enum StatusSolicitacao {
	AUTORIZADO(1),
	EM_ANALISE(2),
	NEGADO(3);
	
	private int codigo;
	
	private StatusSolicitacao(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return Integer.toString(codigo);
	}
	
}
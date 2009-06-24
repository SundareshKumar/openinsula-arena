package org.openinsula.arena.tiss.builder;

import java.text.DecimalFormat;

public enum TipoTabela {
	AMB_90(1),
	AMB_92(2),
	AMB_96(3),
	AMB_99(4),
	BRASINDICE(5),
	CLASSIFICACAO_BRASILEIRA_HIERARQUIZADA_DE_PROCEDIMENTOS_MEDICOS(6),
	CEIFAS_93(7),
	CIEFAS_2000(8),
	ROL_DE_PROCEDIMENTOS_ANS(9),
	PROCEDIMENTOS_AMBULATORIAIS_SUS(10),
	PROCEDIMENTOS_HOSPITALARES_SUS(11),
	SIMPRO(12),
	TUNEP(13),
	VRPO(14),
	INTERCAMBIO_SISTEMA_UNIODONTO(15),
	TUSS(16),
	PROPRIA_PROCEDIMENTO(94),
	PROPRIA_MATERIAIS(95),
	PROPRIA_MEDICAMENTOS(96),
	PROPRIA_TAXAS_HOSPITALARES(97),
	PROPRIA_PACOTES(98),
	PROPRIA_GASES_MEDICINAIS(99),
	OUTRAS(0);

	private int codigo;
	
	private TipoTabela(int codigo) {
		this.codigo = codigo;
	}
	
	public static TipoTabela toEnum(int codigo) {
		for (TipoTabela tipoTabela: values()) {
			if (tipoTabela.codigo == codigo) {
				return tipoTabela;
			}
		}
		return null;
	}
	
	public static TipoTabela toEnum(String codigo) {
		return toEnum(Integer.parseInt(codigo));
	}
	
	@Override
	public String toString() {
		return new DecimalFormat("00").format(codigo);
	}
	
}
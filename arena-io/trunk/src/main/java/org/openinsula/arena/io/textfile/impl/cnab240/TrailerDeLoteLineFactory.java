package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class TrailerDeLoteLineFactory extends AbstractLineFactory {
	private static final String QUANTIDADE_DE_TIT_EM_COBRANCA = "QUANTIDADE DE TIT. EM COBRANCA";

	public TrailerDeLoteLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO TRAILER DO LOTE"));
		addField(new StringField(9, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(6, "QUANTIDADE DE REGISTROS DO LOTE"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRA"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(6, QUANTIDADE_DE_TIT_EM_COBRANCA));
		addField(new NumericField(17, "VALOR TOT. DOS TIT. EM CARTEIRAS"));
		addField(new NumericField(8, "NUMERO DO AVISO DE LANCAMENTO"));
		addField(new StringField(117, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "5".equals(getFieldString(s, 2));
	}
}

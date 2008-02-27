package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class TrailerDeArquivoLineFactory extends AbstractLineFactory {
	public TrailerDeArquivoLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA COMPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO TRAILER DE ARQUIVO"));
		addField(new StringField(9, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(6, "QUANTIDADE DE LOTES DO ARQUIVO"));
		addField(new NumericField(6, "QUANTIDADE DE REGISTROS DO ARQUIVO"));
		addField(new NumericField(6, "QTDADE DE CONTAS P/CONC. - LOTES"));
		addField(new StringField(205, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "9".equals(getFieldString(s, 2));
	}
}

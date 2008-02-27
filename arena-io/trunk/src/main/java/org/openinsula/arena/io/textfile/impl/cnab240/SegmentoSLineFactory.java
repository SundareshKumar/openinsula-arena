package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public abstract class SegmentoSLineFactory extends AbstractLineFactory {
	public SegmentoSLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO DETALHE"));
		addField(new NumericField(5, "N SEQUENCIAL DO REG. NO LOTE"));
		addField(new StringField(1, "COD. SEGMENTO DO REG. DETALHE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(2, "CODIGO DE MOVIMENTO"));
	}

	@Override
	public boolean doMatches(String s) {
		return "3".equals(getFieldString(s, 2)) && "S".equals(getFieldString(s, 4));
	}
}

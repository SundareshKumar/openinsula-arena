package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class SegmentoSTipoImpressao1ou2LineFactory extends SegmentoSLineFactory {
	public SegmentoSTipoImpressao1ou2LineFactory() {
		super();
		addField(new NumericField(1, "IDENTIFICACAO PARA IMPRESSAO"));
		addField(new NumericField(2, "NUMERO DA LINHA A SER IMPRESSA"));
		addField(new StringField(140, "MENSAGEM A SER IMPRESSA"));
		addField(new NumericField(2, "TIPO DO CARACTER A SER IMPRESSO"));
		addField(new StringField(78, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}
}

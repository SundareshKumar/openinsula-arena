package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class SegmentoSTipoImpressao3LineFactory extends SegmentoSLineFactory {
	public SegmentoSTipoImpressao3LineFactory() {
		super();
		addField(new NumericField(1, "IDENTIFICACAO DA IMPRESSAO"));
		addField(new StringField(40, "MENSAGEM 5"));
		addField(new StringField(40, "MENSAGEM 6"));
		addField(new StringField(40, "MENSAGEM 7"));
		addField(new StringField(40, "MENSAGEM 8"));
		addField(new StringField(40, "MENSAGEM 9"));
		addField(new StringField(22, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}
}

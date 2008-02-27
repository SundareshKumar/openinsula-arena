package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public abstract class AbstractSegmento50And53LineFactory extends AbstractLineFactory {

	public AbstractSegmento50And53LineFactory() {
		super();
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new StringField(14, "INSCRICAO ESTADUAL DO REMENTENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new DateField(8, "yyyyMMdd", "DATA DE EMISSAO NA SAIDA OU DE RECEBIMENTO NA ENTRADA"));
		addField(new StringField(2, "SIGLA DA FEDERACAO DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new StringField(1, "EMITENTE DA NOTA FISCAL"));
	}

}
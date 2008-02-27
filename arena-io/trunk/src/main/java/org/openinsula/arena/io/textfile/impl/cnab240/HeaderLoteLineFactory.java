package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class HeaderLoteLineFactory extends AbstractLineFactory {
	public HeaderLoteLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA CONPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO HEADER DE LOTE"));
		addField(new StringField(1, "TIPO DE OPERACAO"));
		addField(new NumericField(2, "TIPO DE SERVICO"));
		addField(new NumericField(2, "FORMA DE LANCAMENTO"));
		addField(new NumericField(3, "N DA VERSAO DO LAYOUT DO LOTE"));
		addField(new StringField(1, "USO EXCLUSIVO FEBRANBAN/CNAB"));
		addField(new NumericField(1, "TIPO DE INCRICAO DA EMPRESA"));
		addField(new NumericField(15, "N DE INSCRICAO DA EMPRESA"));
		addField(new StringField(20, "CODIGO DE CONVENIO NO BANCO"));
		addField(new NumericField(5, "AGENCIA MANTENEDORA DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AG/CONTA"));
		addField(new StringField(30, "NOME DA EMPRESA"));
		addField(new StringField(40, "MENSAGEM 1"));
		addField(new StringField(40, "MENSAGEM 2"));
		addField(new NumericField(8, "NUMERO REMESSA/RETORNO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DE GRAVACAO REMESSA/RETORNO"));
		addField(new DateField(8, "ddMMyyyy", "DATA DO CREDITO"));
		addField(new StringField(33, "USO EXCLUSIVO FEBRABAN/CNAB"));
	}

	@Override
	public boolean doMatches(String s) {
		return "1".equals(getFieldString(s, 2));
	}
}

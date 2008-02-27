package org.openinsula.arena.io.textfile.impl.cnab240;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class HeaderArquivoLineFactory extends AbstractLineFactory {
	public HeaderArquivoLineFactory() {
		addField(new NumericField(3, "CODIGO DO BANCO NA COMPENSACAO"));
		addField(new NumericField(4, "LOTE DE SERVICO"));
		addField(new NumericField(1, "REGISTRO HEADER DE ARQUIVO"));
		addField(new StringField(9, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(1, "TIPO DE INSCRICAO DA EMPRESA"));
		addField(new NumericField(14, "N DE INSCRICAO DA EMPRESA"));
		addField(new StringField(20, "CODIGO DO CONVENIO NO BANCO"));
		addField(new NumericField(5, "AGENCIA MANTENEDORA DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AGENCIA"));
		addField(new NumericField(12, "NUMERO DA CONTA CORRENTE"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA CONTA"));
		addField(new StringField(1, "DIGITO VERIFICADOR DA AG/CONTA"));
		addField(new StringField(30, "NOME DA EMPRESA"));
		addField(new StringField(30, "NOME DO BANCO"));
		addField(new StringField(10, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new NumericField(1, "CODIGO REMESSA/RETORNO"));
		addField(new DateField(14, "ddMMyyyyHHmmss", "DATA E HORA DE GERACAO DO ARQUIVO"));
		addField(new NumericField(6, "N SEQUENCIAL DO ARQUIVO"));
		addField(new NumericField(3, "N DA VERSAO DO LAYOUT DO ARQUIVO"));
		addField(new NumericField(5, "DENSIDADE DE GRAVACAO DO ARQUIVO"));
		addField(new StringField(20, "PARA USO RESERVADO DO BANCO"));
		addField(new StringField(20, "PARA USO RESERVADO DA EMPRESA"));
		addField(new StringField(11, "USO EXCLUSIVO FEBRABAN/CNAB"));
		addField(new StringField(3, "IDENTIFICACAO COBRANCA S/ PAPEL"));
		addField(new NumericField(3, "USO EXCLUSIVO DAS VANS"));
		addField(new StringField(2, "TIPO DE SERVICO"));
		addField(new StringField(10, "CODIGOS DAS OCORRENCIAS"));
	}

	@Override
	public boolean doMatches(String s) {
		return "0".equals(getFieldString(s, 2));
	}
}

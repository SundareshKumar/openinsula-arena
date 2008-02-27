package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento54LineFactory extends AbstractLineFactory {

	public Segmento54LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO REMETENTE NAS ENTRADAS E DO DESTINATARIO NAS SAIDAS"));
		addField(new NumericField(2, "CODIGO DO MODELO DA NOTA FISCAL"));
		addField(new StringField(3, "SERIE DA NOTA FISCAL"));
		addField(new NumericField(6, "NUMERO DA NOTA FISCAL"));
		addField(new NumericField(4, "CODIGO FISCAL DE OPERACAO E PRESTACAO (CFOP)"));
		addField(new StringField(3, "CODIGO DA SITUACAO TRIBUTARIA"));
		addField(new NumericField(3, "NUMERO DE ORDEM DO ITEM DA NOTA"));
		addField(new StringField(14, "CODIGO DO PRODUTO OU SERVICO DO INFORMANTE"));
		addField(new NumericField(11, "QUANTIDADE DO PRODUTO"));
		addField(new NumericField(12, "VALOR BRUTO DO PRODUTO (VALOR UNITARIO MULTIPLICADO PELA QUANTIDADE)"));
		addField(new NumericField(12, "VALOR DO DESCONTO CONCEDITO NO ITEM"));
		addField(new NumericField(12, "BASE DE CALCULO DO ICMS"));
		addField(new NumericField(12, "BASE DE CALCULO DO ICMS DE RETENCAO NA SUBSTITUICAO TRIBUTARIA"));
		addField(new NumericField(12, "VALOR DO IPI"));
		addField(new NumericField(4, "ALIQUOTA UTILIZADA NO CALCULO DO ICMS"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "54".equals(s.substring(0, 2));
	}

}

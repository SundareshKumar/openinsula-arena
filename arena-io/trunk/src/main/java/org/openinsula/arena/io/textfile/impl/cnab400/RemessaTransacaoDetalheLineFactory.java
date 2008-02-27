package org.openinsula.arena.io.textfile.impl.cnab400;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoDetalheLineFactory extends AbstractLineFactory {
	public RemessaTransacaoDetalheLineFactory() {
		addField(new NumericField(1, "IDENTIFICACAO DO REGISTRO"));
		addField(new NumericField(5, "AGENCIA DE DEBITO"));
		addField(new StringField(1, "DIGITO DA AGENCIA DE DEBITO"));
		addField(new NumericField(5, "RAZAO DA CONTA CORRENTE"));
		addField(new NumericField(7, "CONTA CORRENTE"));
		addField(new StringField(1, "DIGITO DA CONTA CORRENTE"));
		addField(new StringField(17, "IDENTIFICACAO DA EMPRESA CEDENTE NO BANCO"));
		addField(new StringField(25, "N.o CONTROLE DO PARTICIPANTE"));
		addField(new NumericField(3, "CODIGO DO BANCO NA CAMARA DE COMPENSACAO"));
		addField(new NumericField(5, "ZEROS"));
		addField(new StringField(12, "IDENTIFICACAO DO TITULO NO BANCO"));
		addField(new NumericField(10, "DESCONTO BONIFICACAO POR DIA"));
		addField(new NumericField(1, "CONDICAO PARA EMISSAO DE PAPELETA DE COBRANCA"));
		addField(new StringField(1, "IDENT. SE EMITE PAPELETA PARA DEBITO AUTOMATICA"));
		addField(new StringField(10, "IDENTIFICACAO DA OPERACAO DO BANCO"));
		addField(new StringField(1, "INDICADOR RATEIO CREDITO"));
		addField(new NumericField(1, "ENDERECAMENTO PARA AVISO DO DEBITO AUTOMATICO"));
		addField(new StringField(2, "BRANCO"));
		addField(new NumericField(2, "IDENTIFICACAO OCORRENCIA"));
		addField(new StringField(10, "N.o DO DOCUMENTO"));
		addField(new DateField(6, "ddMMyy", "DATA DO VENCIMENTO DO TITULO"));
		addField(new NumericField(13, "VALOR DO TITULO"));
		addField(new NumericField(3, "BANCO ENCARREGADO DA COBRANCA"));
		addField(new NumericField(5, "AGENCIA DEPOSITARIA"));
		addField(new NumericField(2, "ESPECIE DE TITULO"));
		addField(new StringField(1, "IDENTIFICACAO"));
		addField(new DateField(6, "ddMMyy", "DATA DE EMISSAO DO TITULO"));
		addField(new NumericField(2, "1.a INSTRUCAO"));
		addField(new NumericField(2, "2.a INSTRUCAO"));
		addField(new NumericField(13, "VALOR A SER COBRADO POR DIA DE ATRASO"));
		addField(new DateField(6, "ddMMyy", "DATA LIMITE P/ CONCESSAO DE DESCONTO"));
		addField(new NumericField(13, "VALOR DO DESCONTO"));
		addField(new NumericField(13, "VALOR DO IOF"));
		addField(new NumericField(13, "VALOR DO ABATIMENTO A SER CONCEDIDO OU CANCELADO"));
		addField(new NumericField(2, "IDENTIFICACAO DO TIPO DE INSCRICAO DO SACADO"));
		addField(new NumericField(14, "N.o INSCRICAO DO SACADO"));
		addField(new StringField(40, "NOME DO SACADO"));
		addField(new StringField(40, "ENDERECO COMPLETO"));
		addField(new StringField(12, "1.a MENSAGEM"));
		addField(new NumericField(5, "CEP"));
		addField(new NumericField(3, "SUFIXO DO CEP"));
		addField(new StringField(60, "SACADOR/AVALISTA OU 2.a MENSAGEM"));
		addField(new NumericField(6, "N.o SEQUENCIAL DO REGISTRO"));
	}

	@Override
	protected boolean doMatches(String s) {
		return "1".equals(getFieldString(s, 0));
	}

}

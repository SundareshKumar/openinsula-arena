package org.openinsula.arena.io.textfile.impl.sintegra;

import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento90LineFactory extends AbstractLineFactory {

	private static final String TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO = "TOTAL DE REGISTRO DO TIPO INFORMADO";

	private static final String TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO = "TIPO DE REGISTRO QUE SERA TOTALIZADO PELO PROXIMO CAMPO";

	public Segmento90LineFactory() {
		addField(new NumericField(2, "TIPO"));
		addField(new NumericField(14, "CNPJ DO INFORMANTE"));
		addField(new StringField(14, "INSCRICAO ESTADUAL DO INFORMANTE"));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new NumericField(2, TIPO_DE_REGISTRO_QUE_SERA_TOTALIZADO_PELO_PROXIMO_CAMPO));
		addField(new NumericField(8, TOTAL_DE_REGISTRO_DO_TIPO_INFORMADO));
		addField(new StringField(5, "BRANCOS"));
		addField(new NumericField(1, "NUMERO DE REGISTROS TIPO 90"));
	}

	@Override
	protected boolean doMatches(final String s) {
		return "90".equals(s.substring(0, 2));
	}

}

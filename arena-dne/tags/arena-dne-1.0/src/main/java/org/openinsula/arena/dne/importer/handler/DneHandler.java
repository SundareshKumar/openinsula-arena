/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.importer.handler;

import org.openinsula.arena.dne.importer.file.line.BairrosDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.CabecalhoLineFactory;
import org.openinsula.arena.dne.importer.file.line.CaixasPostaisComunitariasDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.GrandesUsuariosDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.GrandesUsuariosEnderecoLineFactory;
import org.openinsula.arena.dne.importer.file.line.LocalidadesDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.LogradourosComplemento1LineFactory;
import org.openinsula.arena.dne.importer.file.line.LogradourosDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.LogradourosNumeracaoLoteLineFactory;
import org.openinsula.arena.dne.importer.file.line.LogradourosSeccionamentoLineFactory;
import org.openinsula.arena.dne.importer.file.line.PaisesDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.TerminadorLineFactory;
import org.openinsula.arena.dne.importer.file.line.UnidadesFederacaoDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.UnidadesOperacionaisDadosLineFactory;
import org.openinsula.arena.dne.importer.file.line.UnidadesOperacionaisEnderecoLineFactory;
import org.openinsula.arena.dne.importer.jdbc.DneGuBairrosSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuCaixasPostaisComunitariasSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuGrandesUsuariosSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuLocalidadesSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuLogradourosCompl1SqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuLogradourosNumLoteSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuLogradourosSecSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuLogradourosSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuPaisesSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuUnidadesFederacaoSqlUpdate;
import org.openinsula.arena.dne.importer.jdbc.DneGuUnidadesOperacionaisSqlUpdate;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.parser.LineParser;
import org.springframework.jdbc.core.JdbcTemplate;

public class DneHandler {
	private DneGuPaisesSqlUpdate dneGuPaisesSqlUpdate;

	private DneGuUnidadesFederacaoSqlUpdate dneGuUnidadesFederacaoSqlUpdate;

	private DneGuLocalidadesSqlUpdate dneGuLocalidadesSqlUpdate;

	private DneGuUnidadesOperacionaisSqlUpdate dneGuUnidadesOperacionaisSqlUpdate;

	private DneGuCaixasPostaisComunitariasSqlUpdate dneGuCaixasPostaisComunitariasSqlUpdate;

	private DneGuBairrosSqlUpdate dneGuBairrosSqlUpdate;

	private DneGuLogradourosSqlUpdate dneGuLogradourosSqlUpdate;

	private DneGuLogradourosSecSqlUpdate dneGuLogradourosSecSqlUpdate;

	private DneGuLogradourosNumLoteSqlUpdate dneGuLogradourosNumLoteSqlUpdate;

	private DneGuLogradourosCompl1SqlUpdate dneGuLogradourosCompl1SqlUpdate;

	private DneGuGrandesUsuariosSqlUpdate dneGuGrandesUsuariosSqlUpdate;

	@LineParser(CabecalhoLineFactory.class)
	public void cabecalho(Line line) {
	}

	@LineParser(TerminadorLineFactory.class)
	public void terminador(Line line) {
	}

	@LineParser(PaisesDadosLineFactory.class)
	public void paises(Line line) {
		dneGuPaisesSqlUpdate.update(new Object[] { line.getValue(1), line.getValue(2), line.getValue(3),
				line.getValue(4), line.getValue(5), line.getValue(6), });
	}

	@LineParser(UnidadesFederacaoDadosLineFactory.class)
	public void unidadesFederacao(Line line) {
		dneGuUnidadesFederacaoSqlUpdate.update(new Object[] { line.getValue(2), line.getValue(4), line.getValue(1),
				line.getValue(5), line.getValue(6), });
	}

	@LineParser(LocalidadesDadosLineFactory.class)
	public void localidades(Line line) {
		dneGuLocalidadesSqlUpdate.update(new Object[] { line.getValue(4), line.getValue(2), line.getValue(5),
				line.getValue(6), line.getValue(7), line.getValue(8), line.getValue(9), line.getValue(10),
				line.getValue(12), });
	}

	private Object[] unidadesParams = new Object[28];

	@LineParser(UnidadesOperacionaisDadosLineFactory.class)
	public void unidadesOperacionaisDados(Line line) {
		Object[] dados = new Object[] { line.getValue(11), line.getValue(1), line.getValue(3), line.getValue(6),
				line.getValue(8), line.getValue(9), line.getValue(12), line.getValue(13), line.getValue(14),
				line.getValue(15), line.getValue(16), line.getValue(17), line.getValue(18), line.getValue(19),
				line.getValue(20), line.getValue(21), line.getValue(22), };
		System.arraycopy(dados, 0, unidadesParams, 0, dados.length);
	}

	@LineParser(UnidadesOperacionaisEnderecoLineFactory.class)
	public void unidadesOperacionaisEndereco(Line line) {
		Object[] endereco = new Object[] { line.getValue(3), line.getValue(4), line.getValue(5), line.getValue(7),
				line.getValue(9), line.getValue(10), line.getValue(11), line.getValue(12), line.getValue(13),
				line.getValue(14), line.getValue(15), };
		System.arraycopy(endereco, 0, unidadesParams, 17, endereco.length);

		dneGuUnidadesOperacionaisSqlUpdate.update(unidadesParams);
	}

	@LineParser(CaixasPostaisComunitariasDadosLineFactory.class)
	public void caixasPostaisComunitarias(Line line) {
		dneGuCaixasPostaisComunitariasSqlUpdate.update(new Object[] { line.getValue(7), line.getValue(1),
				line.getValue(3), line.getValue(5), line.getValue(6), line.getValue(8), line.getValue(9),
				line.getValue(10), line.getValue(11), });
	}

	@LineParser(BairrosDadosLineFactory.class)
	public void bairros(Line line) {
		dneGuBairrosSqlUpdate.update(new Object[] { line.getValue(6), line.getValue(3), line.getValue(1),
				line.getValue(7), line.getValue(8), });
	}

	@LineParser(LogradourosDadosLineFactory.class)
	public void logradouros(Line line) {
		Number bairroInicial = (Number) line.getValue(6);
		if (bairroInicial.intValue() == 0) {
			bairroInicial = null;
		}

		Number bairroFinal = (Number) line.getValue(9);
		if (bairroFinal.intValue() == 0) {
			bairroFinal = null;
		}
		dneGuLogradourosSqlUpdate.update(new Object[] { line.getValue(15), line.getValue(3), line.getValue(1),
				bairroInicial, bairroFinal, line.getValue(11), line.getValue(12), line.getValue(13), line.getValue(16),
				line.getValue(17), line.getValue(18), line.getValue(19), line.getValue(20), });
	}

	@LineParser(LogradourosSeccionamentoLineFactory.class)
	public void logradourosSec(Line line) {
		JdbcTemplate jdbcTemplate = dneGuLogradourosSqlUpdate.getJdbcTemplate();
		int count = jdbcTemplate.queryForInt("select count(*) from DNE_GU_LOGRADOUROS where chave_logradouro_dne = ?",
				new Object[] { line.getValue(15) });
		if (count == 0) {
			logradouros(line);
		}
		dneGuLogradourosSecSqlUpdate.update(new Object[] { line.getValue(24), line.getValue(15), line.getValue(6),
				line.getValue(21), line.getValue(22), line.getValue(23), line.getValue(19), });
	}

	@LineParser(LogradourosNumeracaoLoteLineFactory.class)
	public void logradourosNumLote(Line line) {
		JdbcTemplate jdbcTemplate = dneGuLogradourosSqlUpdate.getJdbcTemplate();
		int count = jdbcTemplate.queryForInt("select count(*) from DNE_GU_LOGRADOUROS where chave_logradouro_dne = ?",
				new Object[] { line.getValue(15) });
		if (count == 0) {
			logradouros(line);
		}

		dneGuLogradourosNumLoteSqlUpdate.update(new Object[] { line.getValue(22), line.getValue(15), line.getValue(21),
				line.getValue(19), });
	}

	@LineParser(LogradourosComplemento1LineFactory.class)
	public void logradourosCompl1(Line line) {
		JdbcTemplate jdbcTemplate = dneGuLogradourosNumLoteSqlUpdate.getJdbcTemplate();
		int count = jdbcTemplate.queryForInt(
				"select count(*) from DNE_GU_LOGRADOUROS_NUM_LOTE where chave_lot_DNE = ?", new Object[] { line
						.getValue(24) });
		if (count == 0) {
			count = jdbcTemplate.queryForInt("select count(*) from DNE_GU_LOGRADOUROS where chave_logradouro_dne = ?",
					new Object[] { line.getValue(15) });
			if (count == 0) {
				logradouros(line);
			}

			dneGuLogradourosNumLoteSqlUpdate.update(new Object[] { line.getValue(24), line.getValue(15),
					line.getValue(21), line.getValue(19), });
		}

		dneGuLogradourosCompl1SqlUpdate.update(new Object[] { line.getValue(25), line.getValue(24), line.getValue(21),
				line.getValue(22), line.getValue(23), line.getValue(19), });
	}

	private Object[] guParams = new Object[19];

	@LineParser(GrandesUsuariosDadosLineFactory.class)
	public void grandesUsuariosDados(Line line) {
		Object[] dados = new Object[] { line.getValue(9), line.getValue(1), line.getValue(3), line.getValue(6),
				line.getValue(10), line.getValue(11), line.getValue(12), };
		System.arraycopy(dados, 0, guParams, 0, dados.length);
	}

	@LineParser(GrandesUsuariosEnderecoLineFactory.class)
	public void grandesUsuariosEndereco(Line line) {
		Object[] endereco = new Object[] { line.getValue(3), line.getValue(4), line.getValue(5), line.getValue(7),
				line.getValue(8), line.getValue(9), line.getValue(10), line.getValue(11), line.getValue(12),
				line.getValue(13), line.getValue(14), line.getValue(15), };
		System.arraycopy(endereco, 0, guParams, 7, endereco.length);

		dneGuGrandesUsuariosSqlUpdate.update(guParams);
	}

	public void setDneGuLocalidadesSqlUpdate(DneGuLocalidadesSqlUpdate dneGuLocalidadesSqlUpdate) {
		this.dneGuLocalidadesSqlUpdate = dneGuLocalidadesSqlUpdate;
	}

	public void setDneGuCaixasPostaisComunitariasSqlUpdate(
			DneGuCaixasPostaisComunitariasSqlUpdate dneGuCaixasPostaisComunitariasSqlUpdate) {
		this.dneGuCaixasPostaisComunitariasSqlUpdate = dneGuCaixasPostaisComunitariasSqlUpdate;
	}

	public void setDneGuGrandesUsuariosSqlUpdate(DneGuGrandesUsuariosSqlUpdate dneGuGrandesUsuariosDadosSqlUpdate) {
		this.dneGuGrandesUsuariosSqlUpdate = dneGuGrandesUsuariosDadosSqlUpdate;
	}

	public void setDneGuBairrosSqlUpdate(DneGuBairrosSqlUpdate dneGuBairrosSqlUpdate) {
		this.dneGuBairrosSqlUpdate = dneGuBairrosSqlUpdate;
	}

	public void setDneGuLogradourosCompl1SqlUpdate(DneGuLogradourosCompl1SqlUpdate dneGuLogradourosCompl1SqlUpdate) {
		this.dneGuLogradourosCompl1SqlUpdate = dneGuLogradourosCompl1SqlUpdate;
	}

	public void setDneGuLogradourosNumLoteSqlUpdate(DneGuLogradourosNumLoteSqlUpdate dneGuLogradourosNumLoteSqlUpdate) {
		this.dneGuLogradourosNumLoteSqlUpdate = dneGuLogradourosNumLoteSqlUpdate;
	}

	public void setDneGuLogradourosSecSqlUpdate(DneGuLogradourosSecSqlUpdate dneGuLogradourosSecSqlUpdate) {
		this.dneGuLogradourosSecSqlUpdate = dneGuLogradourosSecSqlUpdate;
	}

	public void setDneGuLogradourosSqlUpdate(DneGuLogradourosSqlUpdate dneGuLogradourosSqlUpdate) {
		this.dneGuLogradourosSqlUpdate = dneGuLogradourosSqlUpdate;
	}

	public void setDneGuPaisesSqlUpdate(DneGuPaisesSqlUpdate dneGuPaisesSqlUpdate) {
		this.dneGuPaisesSqlUpdate = dneGuPaisesSqlUpdate;
	}

	public void setDneGuUnidadesFederacaoSqlUpdate(DneGuUnidadesFederacaoSqlUpdate dneGuUnidadesFederacaoSqlUpdate) {
		this.dneGuUnidadesFederacaoSqlUpdate = dneGuUnidadesFederacaoSqlUpdate;
	}

	public void setGuParams(Object[] guParams) {
		this.guParams = guParams;
	}

	public void setDneGuUnidadesOperacionaisSqlUpdate(
			DneGuUnidadesOperacionaisSqlUpdate dneGuUnidadesOperacionaisSqlUpdate) {
		this.dneGuUnidadesOperacionaisSqlUpdate = dneGuUnidadesOperacionaisSqlUpdate;
	}

}

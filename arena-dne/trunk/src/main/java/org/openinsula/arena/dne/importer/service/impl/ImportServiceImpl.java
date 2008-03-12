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
package org.openinsula.arena.dne.importer.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.dne.entity.Uf;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuBairrosExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuCaixasPostaisComunitariasExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuGrandesUsuariosExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuLocalidadesExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuLogradourosExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuPaisesExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuUnidadesFederacaoExpression;
import org.openinsula.arena.dne.importer.file.parser.expression.DneGuUnidadesOperacionaisExpression;
import org.openinsula.arena.dne.importer.handler.DneHandler;
import org.openinsula.arena.dne.importer.service.ImportService;
import org.openinsula.arena.io.textfile.FileParser;
import org.openinsula.arena.io.textfile.FileParserFactory;
import org.openinsula.arena.io.textfile.parser.expression.Expression;

public class ImportServiceImpl implements ImportService {
	protected final Log logger = LogFactory.getLog(getClass());

	private Map<String, Expression> expressionMap = new LinkedHashMap<String, Expression>();

	private DneHandler dneHandler;

	public ImportServiceImpl() {
		expressionMap.put("DNE_GU_PAISES.TXT", new DneGuPaisesExpression());
		expressionMap.put("DNE_GU_UNIDADES_FEDERACAO.TXT", new DneGuUnidadesFederacaoExpression());
		expressionMap.put("DNE_GU_LOCALIDADES.TXT", new DneGuLocalidadesExpression());
		expressionMap.put("DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT", new DneGuCaixasPostaisComunitariasExpression());
		expressionMap.put("DNE_GU_BAIRROS.TXT", new DneGuBairrosExpression());
		for (Uf uf : Uf.values()) {
			expressionMap.put("DNE_GU_" + uf.toString() + "_LOGRADOUROS.TXT", new DneGuLogradourosExpression());
		}
		expressionMap.put("DNE_GU_UNIDADES_OPERACIONAIS.TXT", new DneGuUnidadesOperacionaisExpression());
		expressionMap.put("DNE_GU_GRANDES_USUARIOS.TXT", new DneGuGrandesUsuariosExpression());
	}

	public void process(File dneDir) throws IOException {
		if (!dneDir.isDirectory()) {
			throw new IllegalArgumentException("O parametro do DNE deve ser um diretorio.");
		}

		for (String filename : expressionMap.keySet()) {
			if (logger.isInfoEnabled()) {
				logger.info("Processando arquivo: " + filename);
			}
			FileParser fileParser = FileParserFactory.newFileParser(expressionMap.get(filename));
			fileParser.parse(new FileInputStream(new File(dneDir, filename)), dneHandler);
		}
	}

	public void setDneHandler(DneHandler dneHandler) {
		this.dneHandler = dneHandler;
	}
}

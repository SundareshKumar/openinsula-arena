/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.io.textfile.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;

import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputStreamParseContext implements ParseContext {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected int lineCount = 0;

	protected Map<Class<? extends LineFactory>, Method> parseMap;

	protected Object handler;

	protected BufferedReader br;

	protected String nextLine;

	public InputStreamParseContext(InputStream in, Charset charset, Map<Class<? extends LineFactory>, Method> parseMap,
			Object handler) {
		this.parseMap = parseMap;
		this.handler = handler;
		br = new BufferedReader(new InputStreamReader(in, charset));
	}

	public String getLine() throws IOException {
		lineCount++;
		if (nextLine == null) {
			return br.readLine();
		}
		else {
			String s = nextLine;
			nextLine = null;
			return s;
		}
	}

	public String lookAhead() throws IOException {
		if (nextLine == null) {
			nextLine = br.readLine();
		}
		return nextLine;
	}

	public void process(Line line) {
		Method method = parseMap.get(line.getLineFactory().getClass());
		if (method != null) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Invoking method \"" + method.getName() + "\" to process line of "
							+ line.getLineFactory().getClass().getSimpleName());
				}
				method.invoke(handler, new Object[] { line });
			}
			catch (Exception ex) {
				logger.warn("Exception thrown invoking method \'" + method.getName() + "\' of class "
						+ handler.getClass().getSimpleName(), ex);
				logger.warn("Cause of exception : " + ex.getCause());
				throw new IllegalArgumentException(ex);
			}
		}
		else {
			if (logger.isWarnEnabled()) {
				logger.warn("No method found to process line of " + line.getLineFactory().getClass().getSimpleName());
			}
		}
	}

	public int getCurrentLineCount() {
		return lineCount;
	}
}
